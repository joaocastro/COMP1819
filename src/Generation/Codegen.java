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
    this.root = root;
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

  public String generateCode() {
    generateHeader();
    generateGlobals();
    generateStatic();
    generateFunctions();
    
    System.out.println(this.builder);
    this.out.println(this.builder);
    this.out.close();

    return this.builder.toString();
  }

  private void appendln(String content) {
    this.builder.append(content);
    this.builder.append("\n");
  }

  private void appendln() { this.builder.append("\n"); }

  private void generateHeader() {
    this.appendln(".class public " + root.getValue());
    this.appendln(".super java/lang/Object"
             + "\n");

    moduleJavaCodegen();
  }

  private void generateGlobals() {}

  private void generateStatic() {}

  private void generateFunctions() {}

  private void moduleJavaCodegen() {
    this.appendln(".method static public <clinit>()V");

    this.printStack(1);
    this.printLocals(1);

    this.appendln(TAB + "return");
    this.appendln(".end method");

    for (SymbolTable table : symbolTable.getChildren().values()) {
      if (table.getName().equals("main")) {
        this.printMainMethod(table);
      } else {
      }
      this.appendln();
    }
  }

  private void printStack(int stack) { this.appendln(TAB + ".limit stack " + stack); }

  private void printLocals(int locals) {
    this.appendln(TAB + ".limit locals " + locals);
  }

  public void printMainMethod(SymbolTable table) {
    this.appendln(".method public static main([Ljava/lang/String;)V");
    this.printStack(table.getLocals().size() * 4); // Assuming each type is of size 4
    this.appendln(TAB + "return");

    this.appendln(".end method");
  }

  private void elseJavaCodegen(SimpleNode elseNode, int loop) {
    this.appendln();
    this.appendln("goto loop" + loop + "_next");
    this.appendln("loop" + loop + "_end:");
    // TODO: Pode ser recursivo
    this.appendln();
    this.appendln("loop" + loop + "_next:");
  }

  private String loadInteger(int value) {
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
    this.appendln(".method " +
             ((SimpleNode)method.jjtGetChild(0).jjtGetChild(0)).getVal() + " " +
             ((SimpleNode)method.jjtGetChild(1)).getVal());
    int stack = 1;  // ?
    int locals = 1; // ?
    this.printStack(stack);
    this.printLocals(locals);
  }
}
