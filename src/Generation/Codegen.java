package Generation;

import AST.*;
import Symbol.*;
import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Codegen {
  private static String TAB = "\t";

  private SimpleNode root;
  private SymbolTable symbolTable;

  private PrintWriter out;
  private StringBuilder builder;

  private boolean o_flag = false;

  public Codegen(SimpleNode root, SymbolTable st) throws IOException {
    this.root =
        (SimpleNode)root.jjtGetChildren()[0]; // class declaration is root
    this.symbolTable = st;

    String filename = root.getValue() + ".j";

    try {
      // Create gen folder if it doesn't exist
      String dirname = "../gen";
      File dir = new File(dirname);
      if (!dir.exists())
        dir.mkdir();

      FileWriter fw = new FileWriter(dirname + "/" + filename, false);
      BufferedWriter bw = new BufferedWriter(fw);

      this.builder = new StringBuilder();
      this.out = new PrintWriter(bw);
    } catch (IOException e) {
      System.out.println("Error with i/o.");
      System.out.println(e.getMessage());
    }
  }

  public void generateCode() {
    generateHeader();
    generateGlobals();

    generateInit();
    generateMethods();

    // Write to file
    this.out.println(this.builder);
    this.out.close();
  }

  private void appendln() { this.builder.append("\n"); }
  private void appendln(String content) {
    this.builder.append(content);
    this.builder.append("\n");
  }

  private void generateHeader() {
    this.appendln(".class public " + root.getValue());
    if (!((ASTClass)this.root).getExtends().equals(""))
      this.appendln(".super " + ((ASTClass)this.root).getExtends());
    else
      this.appendln(".super java/lang/Object");

    this.appendln();
  }

  private void generateGlobals() {
    for (Node n : root.jjtGetChildren())
      if (n instanceof ASTVariable)
        generateGlobalVar((ASTVariable)n);

    appendln();
  }

  private void generateInit() {}

  private void generateMethods() {}

  private void generateGlobalVar(ASTVariable vardecl) {
    String name = vardecl.getVal().toString();
    String type = parseType(
        ((SimpleNode)vardecl.jjtGetChildren()[0]).getVal().toString());

    if (name.equals("field"))
      name = "'field'";

    appendln(".field private " + name + " " + type);
  }

  private String parseType(String type) {
    switch (type) {
    case "int":
      return "I";
    case "int[]":
      return "[I";
    case "boolean":
      return "Z";
    case "void":
      return "V";
    case "":
      return "V";
    default:
      return "L" + type + ";";
    }
  }
}
