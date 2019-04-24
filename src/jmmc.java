/*
 * jmmc.java
 *
 * A Java-- (MiniJava) compiler.
 */

import AST.*;
import Symbol.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class jmmc {
  private HashMap<String, SymbolTable> symbol_tables =
      new HashMap<String, SymbolTable>();

  public static String file_name = "";

  private String module_name;
  private int error_count = 0;

  public static void main(String args[]) throws ParseException, IOException {
    Parser parser;

    try {
      InputStream parserStream =
        (args.length == 0) ? System.in : read_input_file(args[0]);

      parser = new Parser(parserStream);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist.");
      return;
    }

    new jmmc(parser);
  }

  public jmmc(Parser parser) {
    try {
      // Begin parsing
      SimpleNode root = parser.Program();

      // Print AST
      root.dump("");

      // Build symbol table
      build_symbol_table(root);
    } catch (ParseException e) {
      System.out.println("Error parsing.");
      System.out.println(e.getMessage());
    } catch (TokenMgrError e) {
      System.out.println("Error with token.");
      System.out.println(e.getMessage());
    }
  }

  public void build_symbol_table(SimpleNode root) {
    System.out.println("building symbol table...");
    System.out.println("not done yet, obviously");
  }

  public static InputStream read_input_file(String inputFile) throws IOException {
    File initialFile = new File(inputFile);
    InputStream targetStream = new FileInputStream(initialFile);
    return targetStream;
  }
}
