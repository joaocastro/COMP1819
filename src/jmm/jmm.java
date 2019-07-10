/*
 * jmm.java
 *
 * A Java-- (MiniJava) compiler.
 */

import AST.*;
import Generation.*;
import Symbol.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
      System.out.println("Error with file.");
      System.out.println(e.getMessage());
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
      System.out.println("done building symbol table.");

      // Perform semantic analysis
      System.out.println("performing semantic analysis...");
      perform_semantic_analysis(root);
      System.out.println("done performing semantic analysis.");

      // Generate code
      System.out.println("generating code...");
      generate_code(root);
      System.out.println("done generating code.");
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
    root.jjtAccept(visitor, (Object)root_symbol_table);

    // Print contents of symbol table
    root_symbol_table.show("");
  }

  public void perform_semantic_analysis(SimpleNode root) {
    SemanticVisitor visitor = new SemanticVisitor();

    // Go through tree and build symbol table
    root.jjtAccept(visitor, (Object)root_symbol_table);
  }

  public void generate_code(SimpleNode root) throws IOException {
    // Get the given file's name (class name)
    file_name = file_name.substring(file_name.lastIndexOf('/') + 1,
                                    file_name.lastIndexOf('.'));
    root.jjtSetValue(file_name);

    // Generate code
    Codegen codeGenerator = new Codegen(root, root_symbol_table);
    codeGenerator.generateCode();
  }

  public static InputStream read_input_file(String inputFile)
      throws IOException {
    File initialFile = new File(inputFile);
    InputStream targetStream = new FileInputStream(initialFile);
    return targetStream;
  }
}
