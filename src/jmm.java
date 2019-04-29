/*
 * jmm.java
 *
 * A Java-- (MiniJava) compiler.
 */

import AST.*;
import Symbol.*;
import java.io.*;

public class jmm {
  private SymbolTable root_symbol_table = new SymbolTable("root");

  public static void main(String args[]) throws ParseException, IOException {
    try {
      InputStream parserStream =
        (args.length == 0) ? System.in : read_input_file(args[0]);

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

      // TODO: Perform semantic analysis

      // TODO: Generate code

    } catch (ParseException e) {
      System.out.println("Error parsing.");
      System.out.println(e.getMessage());
    } catch (TokenMgrError e) {
      System.out.println("Error with token.");
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

  public static InputStream read_input_file(String inputFile) throws IOException {
    File initialFile = new File(inputFile);
    InputStream targetStream = new FileInputStream(initialFile);
    return targetStream;
  }
}