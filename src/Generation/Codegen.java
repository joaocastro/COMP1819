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

    String filename = root.jjtGetValue() + ".j";

		try {
    // Create gen folder if it doesn't exist
    String dirname = "../gen";
    File dir = new File(dirname);
    if (!dir.exists())
      dir.mkdir();

    FileWriter fw = new FileWriter(dirname + "/" + filename, false);
    BufferedWriter bw = new BufferedWriter(fw);
		this.out = new PrintWriter(bw);
		
		} catch (IOException e) {
      System.out.println("Error with i/o.");
      System.out.println(e.getMessage());
    }

    this.builder = new StringBuilder();
  }

  public void generateCode() { moduleJavaCodegen(); }

  private void moduleJavaCodegen() {

    this.out.println(".class public " + this.root.jjtGetValue());
    this.out.println(".super java/lang/Object\n");

    clinitJavaCodegen();
    for (SymbolTable table : symbolTable.getChildren().values()) {
      if (table.getName().equals("main")) {
        printMainMethod(table);
      } else {
      }
      this.out.println();
    }

    this.out.close();
  }

  private void clinitJavaCodegen() {
    this.out.println(".method static public <clinit>()V");

    printStack(1);
    printLocals(1);

    this.out.println("\treturn");
    this.out.println(".end method");
  }

  private void printStack(int stack) {
    this.out.println("\t.limit stack " + stack);
  }

  private void printLocals(int locals) {
    this.out.println("\t.limit locals " + locals);
  }

  public void printMainMethod(SymbolTable table) {
    this.out.println(".method public static main([Ljava/lang/String;)V");
    printStack(table.getLocals().size() * 4); // Assuming each type is of size 4
    this.out.println("\treturn");

    this.out.println(".end method");
  }

  public void printMethod(SymbolTable table) {}

  /*
   * Handling each possible this.output from grammar
   */

  private void elseJavaCodegen(SimpleNode elseNode, int loop) {
    this.out.println();
    this.out.println("goto loop" + loop + "_next");
    this.out.println("loop" + loop + "_end:");
    // TODO: Pode ser recursivo
    this.out.println();
    this.out.println("loop" + loop + "_next:");
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

  private String loadString(String value) { return "ldc " + value; }

  private void mainDecl() {
    this.out.println(".method public static main([Ljava/lang/String;)V");
    printStack(1);
    printLocals(1);

    statementDecl();
  }

  private void methodDecl(Node method) {
    this.out.println(".method " +
                ((SimpleNode)method.jjtGetChild(0).jjtGetChild(0)).getVal() +
                " " + ((SimpleNode)method.jjtGetChild(1)).getVal());
    int stack = 1;  // ?
    int locals = 1; // ?
    printStack(stack);
    printLocals(locals);

    statementDecl();
  }

  private void statementDecl() {}
}
