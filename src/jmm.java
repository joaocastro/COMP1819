/*
 * jmm.jjt
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
import java.util.List;
import java.util.Map;

public class jmm {
  private HashMap<String, SymbolTable> symbol_tables =
      new HashMap<String, SymbolTable>();

  public static String file_name = "";

  private String module_name;
  private int error_count = 0;

  public static void main(String args[]) throws ParseException, IOException {
    Parser parser;

    try {
      InputStream parserStream =
        (args.length == 0) ? System.in : readInputFile(args[0]);

      parser = new Parser(parserStream);
    } catch (FileNotFoundException e) {
      System.out.println("File does not exist.");
      return;
    }

    new jmm(parser);
  }

  public jmm(Parser parser) {
    try {
      // Begin parsing
      SimpleNode root = parser.Program();

      // Print AST
      root.dump("");
    } catch (ParseException e) {
      System.out.println("Error parsing.");
      System.out.println(e.getMessage());
    } catch (TokenMgrError e) {
      System.out.println("Error parsing.");
      System.out.println(e.getMessage());
    }
  }

  public static InputStream readInputFile(String inputFile) throws IOException {
    File initialFile = new File(inputFile);
    InputStream targetStream = new FileInputStream(initialFile);
    return targetStream;
  }
}
