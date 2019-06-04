/*
 * jmm.java
 *
 * A Java-- (MiniJava) compiler.
 */

import AST.*;
import Symbol.*;
import java.io.*;
import Jasmin.*;

public class jmm {
  private static String file_name = "null";
  private SymbolTable root_symbol_table = new SymbolTable("root");

  public static void main(String args[]) throws ParseException, IOException {
    try {
		file_name = args[0];
      
      InputStream parserStream = read_input_file(file_name);

      Parser parser = new Parser(parserStream);
      
      new jmm(parser);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist.");
      return;
    }
  }

  public jmm(Parser parser) {
    try {
      // Begin parsing
      SimpleNode root = parser.Program();

      // Print AST
      root.dump("");

      // Build symbol table
      System.out.println("building symbol table...");
      build_symbol_table(root);

      // Perform semantic analysis
      System.out.println("performing semantic analysis...");
      perform_semantic_analysis(root);

	  // Generate code
      file_name = file_name.substring(file_name.indexOf('/') + 1, file_name.indexOf('.'));
      root.jjtSetValue(file_name);
      Bytecodes.generateJavaBytecodes(root, root_symbol_table);
				
    } catch (ParseException e) {
      System.out.println("Error parsing.");
      System.out.println(e.getMessage());
    } catch (TokenMgrError e) {
      System.out.println("Error with token.");
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println("Error with i/o.");
      System.out.println(e.getMessage());
    }
  }

  public void build_symbol_table(SimpleNode root) {
    SymbolVisitor visitor = new SymbolVisitor();

    // Go through tree and build symbol table
    root.jjtAccept(visitor, (Object) root_symbol_table);

    // Print contents of symbol table
    root_symbol_table.show("");
  }

  public void perform_semantic_analysis(SimpleNode root) {
    SemanticVisitor visitor = new SemanticVisitor();

    // Go through tree and build symbol table
    root.jjtAccept(visitor, (Object) root_symbol_table);
  }

  public static InputStream read_input_file(String inputFile) throws IOException {
    File initialFile = new File(inputFile);
    InputStream targetStream = new FileInputStream(initialFile);
    return targetStream;
  }
}
