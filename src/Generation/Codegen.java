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

  private ArrayList<String> register_variables = new ArrayList<>();

  public Codegen(SimpleNode root, SymbolTable st) throws IOException {
    root = root;
    symbolTable = st;

    String filename = root.getValue() + ".j";

    try {
      // Create gen folder if it doesn't exist
      String dirname = "../gen";
      File dir = new File(dirname);
      if (!dir.exists())
        dir.mkdir();

      FileWriter fw = new FileWriter(dirname + "/" + filename, false);
      BufferedWriter bw = new BufferedWriter(fw);
      out = new PrintWriter(bw);

    } catch (IOException e) {
      System.out.println("Error with i/o.");
      System.out.println(e.getMessage());
    }

    builder = new StringBuilder();
  }

  public void generateCode() {
    generateHeader();
    generateGlobals();
    generateStatic();
    generateFunctions();

    out.println(builder);
    out.close();

    moduleJavaCodegen();
  }

  private void appendln(String content) {
    builder.append(content);
    builder.append("\n");
  }

  private void appendln() { builder.append("\n"); }

  private void generateHeader() {
    appendln(".class public " + root.getValue());
    appendln(".super java/lang/Object"
             + "\n");

    moduleJavaCodegen();
  }

  private void generateGlobals() {}

  private void generateStatic() {}

  private void generateFunctions() {}

  private void moduleJavaCodegen() {

    clinitJavaCodegen();
    for (SymbolTable table : symbolTable.getChildren().values()) {
      if (table.getName().equals("main")) {
        printMainMethod(table);
      } else {
      }
      appendln();
    }

    out.close();
  }

  private void clinitJavaCodegen() {
    appendln(".method static public <clinit>()V");

    printStack(1);
    printLocals(1);

    appendln("\treturn");
    appendln(".end method");
  }

  private void printStack(int stack) { appendln("\t.limit stack " + stack); }

  private void printLocals(int locals) {
    appendln("\t.limit locals " + locals);
  }

  public void printMainMethod(SymbolTable table) {
    appendln(".method public static main([Ljava/lang/String;)V");
    printStack(table.getLocals().size() * 4); // Assuming each type is of size 4
    appendln("\treturn");

    appendln(".end method");
  }

  private void elseJavaCodegen(SimpleNode elseNode, int loop) {
    appendln();
    appendln("goto loop" + loop + "_next");
    appendln("loop" + loop + "_end:");
    // TODO: Pode ser recursivo
    appendln();
    appendln("loop" + loop + "_next:");
  }

  private String loadInteger(Integer value) {
    if (value >= 0 && value <= 5)
      return "iconst_" + value;
    else if (value >= -128 && value <= 127)
      return "bipush " + value;
    else if (value >= -32768 && value <= 32767)
      return "sipush " + value;
    else
      return "ldc " + value;
  }

  private void methodDecl(Node method) {
    appendln(".method " +
             ((SimpleNode)method.jjtGetChild(0).jjtGetChild(0)).getVal() + " " +
             ((SimpleNode)method.jjtGetChild(1)).getVal());
    int stack = 1;  // ?
    int locals = 1; // ?
    printStack(stack);
    printLocals(locals);

  }
}
