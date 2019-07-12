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

  private ASTClass root;
  private SymbolTable symbolTable;

  private PrintWriter out;
  private StringBuilder builder;

  private boolean o_flag = false;

  public Codegen(ASTClass root, SymbolTable st) throws IOException {
    // class declaration is root
    this.root = root;
    this.symbolTable = st;

    String filename = this.root.getClassName() + ".j";

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
    generateClass();
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

  private void generateClass() {
    this.appendln(".class public " + this.root.getClassName());

    if (!this.root.getExtends().equals(""))
      this.appendln(".super " + this.root.getExtends());
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

  private void generateInit() {
    appendln(".method public <init>()V");
    appendln(TAB + "aload_0");

    if (!this.root.getExtends().equals(""))
      appendln(TAB + "invokespecial " + this.root.getExtends() + "/<init>()V");
    else
      appendln(TAB + "invokespecial java/lang/Object/<init>()V");

    appendln(TAB + "return");
    appendln(".end method");
  }

  private void generateMethods() {
    for (Node n : root.jjtGetChildren())
      if (n instanceof ASTMain || n instanceof ASTMethod)
        generateMethod((SimpleNode)n);
  }

  private void generateMethod(SimpleNode method) {
    StackController stack = new StackController();

    generateMethodSignature(method);
    generateMethodBody(method, stack);
    generateMethodFooter(method, stack);

    System.out.println("Current max stack: " + stack.getMaxStack());

    // String methodName = "";
    // if (method instanceof ASTMethod)
    //   methodName = ((ASTMethod)method).getMethodName();
    // else
    //   methodName = "mainString[]";

    // writeStackLimit(methodName, stack);
  }

  private void generateMethodSignature(SimpleNode methodNode) {
    appendln();

    // ASTMain
    if (methodNode instanceof ASTMain) {
      appendln(".method public static main([Ljava/lang/String;)V");
      return;
    }

    // ASTMethod
    ASTMethod method = (ASTMethod)methodNode;
    String identifier = method.getMethodName();
    String type = method.getMethodType();
    ASTMethodParams methodParams = method.getMethodParams();

    String params = "";

    // if method has params
    if (methodParams != null) {
      // TODO: for every param in methodParams, add method params to params
      // string for (int i = 0; i < ((SimpleNode) method.).jjtGetNumChildren();
      // i++) {
      //   ASTMethodParam argNode = (ASTMethodParam) ((SimpleNode)
      //   method.jjtGetChild(0)).jjtGetChild(i);
      //    params += convertType(argNode.getType());
      // }
    }

    appendln(".method public " + identifier + "(" + params + ")" +
             convertType(type));
  }

  private void generateMethodBody(SimpleNode method, StackController stack) {
    String methodName = "";

    if (method instanceof ASTMethod)
      methodName = ((ASTMethod)method).getMethodName();
    else
      methodName = "mainString[]";

    appendln(TAB + ".limit stack_" + methodName);

    SymbolTable functionTable = this.symbolTable.getTable(methodName);
    if (functionTable != null)
      appendln(TAB + ".limit locals " + functionTable.getLocals().size());

    // TODO: for every statement in method, generate statement
  }

  private void generateMethodFooter(SimpleNode method, StackController stack) {
    String returnType = "void";
    SymbolTable functionTable;

    if (method instanceof ASTMain) {
      functionTable = this.symbolTable.getTable("main");
    } else {
      functionTable = this.symbolTable.getTable(((ASTMethod)method).getMethodName());

      if (functionTable != null)
        returnType = functionTable.getReturnType();

      ASTReturnStmt returnStmt = ((ASTMethod) method).getMethodReturnStmt();
      if (returnStmt != null) {
        // TODO: generate return statement
      }
    }

    switch (returnType) {
    case "int":
      stack.addInstruction(Instructions.IRETURN, 0);
      appendln(TAB + "ireturn");
      break;
    case "int[]":
      stack.addInstruction(Instructions.ARETURN, 0);
      appendln(TAB + "areturn");
      break;
    case "boolean":
      stack.addInstruction(Instructions.IRETURN, 0);
      appendln(TAB + "ireturn");
      break;
    case "void":
      stack.addInstruction(Instructions.RETURN, 0);
      appendln(TAB + "return");
      break;
    default:
      stack.addInstruction(Instructions.ARETURN, 0);
      appendln(TAB + "areturn");
    }

    appendln(".end method");
    appendln();
  }

  private void generateGlobalVar(ASTVariable varDecl) {
    String name = varDecl.getVarName();
    String type = parseType(varDecl.getVarType());

    if (name.equals("field"))
      name = "'field'";

    appendln(".field private " + name + " " + type);
  }

  private String convertType(String type) {
    switch (type) {
    case "int":
      return "I";
    case "int[]":
      return "[I";
    case "boolean":
      return "Z";
    case "void":
      return "V";
    default:
      return "";
    }
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

  private void parseNumber(int value, StackController stack) {
    if (value <= 5) {
      stack.addInstruction(Instructions.ICONST, 0);
      appendln(TAB + "iconst_" + value);
      return;
    }

    if (value <= 127) {
      stack.addInstruction(Instructions.BIPUSH, 0);
      appendln(TAB + "bipush " + value);
      return;
    }

    if (value <= 32767) {
      stack.addInstruction(Instructions.SIPUSH, 0);
      appendln(TAB + "sipush " + value);
      return;
    }

    stack.addInstruction(Instructions.LDC, 0);
    appendln(TAB + "ldc " + value);
  }

  private void writeStackLimit(String methodName, StackController stack) {
    try {
      File dir = new File("gen");
      if (!dir.exists())
        dir.mkdirs();

      File file = new File("gen/" + this.root.getClassName() + ".j");
      if (!file.exists())
        file.createNewFile();

      int index = this.builder.indexOf("stack_" + methodName);
      // FIXME: error in this line
      this.builder.replace(index, index + methodName.length() + 6,
                           "stack " + stack.getMaxStack()); // 6 - "stack_"

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
