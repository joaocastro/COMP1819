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

    String methodName = "";
    if (method instanceof ASTMain)
      methodName = "mainString[]";
    else
      methodName = ((ASTMethod)method).getMethodName();

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
    Node[] methodParams = method.getMethodParams().jjtGetChildren();

    String params = "";

    // For every param in methodParams, add method params to params
    if (methodParams != null)
      for (Node n : methodParams)
        params += convertType(((ASTMethodParam)n).getParamType()) + ";";

    appendln(".method public " + identifier + "(" + params + ")" +
             convertType(type));
  }

  private void generateMethodBody(SimpleNode method, StackController stack) {
    String methodName = "";

    if (method instanceof ASTMain)
      methodName = "mainString[]";
    else
      methodName = ((ASTMethod)method).getMethodName();

    appendln(TAB + ".limit stack_" + methodName);

    SymbolTable functionTable = this.symbolTable.getTable(methodName);
    if (functionTable != null)
      appendln(TAB + ".limit locals " + functionTable.getLocals().size());

    // For every statement in method, generate statement
    for (Node n : method.jjtGetChildren())
      generateStatement((SimpleNode)n, stack);
  }

  private void generateMethodFooter(SimpleNode method, StackController stack) {
    String returnType = "void";
    SymbolTable functionTable;

    if (method instanceof ASTMain) {
      functionTable = this.symbolTable.getTable("main");
    } else {
      functionTable =
          this.symbolTable.getTable(((ASTMethod)method).getMethodName());

      if (functionTable != null)
        returnType = functionTable.getReturnType();

      ASTReturnStmt returnStmt = ((ASTMethod)method).getMethodReturnStmt();
      if (returnStmt != null) {
        generateReturnStmt(returnStmt, stack);
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

  private void generateStatement(SimpleNode stmt, StackController stack) {
    if (stmt instanceof ASTVariable) {
      generateVarDecl((ASTVariable)stmt, stack);
    } else if (stmt instanceof ASTAssign) {
      generateAssignment((ASTAssign)stmt, stack);
    } else if (stmt instanceof ASTIf) {
      generateIf((ASTIf)stmt, stack);
    } else if (stmt instanceof ASTWhile) {
      generateWhile((ASTWhile)stmt, stack);
    } else if (stmt instanceof ASTMethodCall) {
      System.out.println("found method call expression");
    } else {
      System.out.println("found some other statement");
    }

    return;
  }

  private void generateExpression(SimpleNode stmt, StackController stack) {
    if (stmt instanceof ASTId) {
      generateIdentifier((ASTId)stmt, stack);
    } else if (stmt instanceof ASTAdd) {
      generateAdd((ASTAdd)stmt, stack);
    } else if (stmt instanceof ASTSub) {
      generateSub((ASTSub)stmt, stack);
    } else if (stmt instanceof ASTMul) {
      generateMul((ASTMul)stmt, stack);
    } else if (stmt instanceof ASTDiv) {
      generateDiv((ASTDiv)stmt, stack);
    } else if (stmt instanceof ASTLessThan) {
      generateLessThan((ASTLessThan)stmt, stack);
    } else if (stmt instanceof ASTFalse) {
      generateFalse((ASTFalse)stmt, stack);
    } else if (stmt instanceof ASTNew) {
      generateNew((ASTNew)stmt, stack);
    } else if (stmt instanceof ASTMethodCall) {
      generateMethodCall((ASTMethodCall)stmt, stack);
    } else {
      System.out.println("found some other expression");
    }

    return;
  }

  private void generateVarDecl(ASTVariable varDecl, StackController stack) {
    System.out.println("found a variable declaration statement");
  }

  private void generateAssignment(ASTAssign assign, StackController stack) {
    System.out.println("found an assign statement");

    ASTLhs lhs = (ASTLhs)assign.jjtGetChild(0);
    ASTRhs rhs = (ASTRhs)assign.jjtGetChild(1);

    // Generate rhs
    generateExpression((SimpleNode)rhs.jjtGetChild(0), stack);

    // Generate lhs
    Symbol lhsSymbol = this.symbolTable.lookup(lhs.getVal());

    if (lhsSymbol != null) {
      String lhsType = lhsSymbol.getType();
      int index = lhsSymbol.getIndex();

      switch (lhsType) {
      case "int":
        stack.addInstruction(Instructions.ISTORE, 0);
        appendln(TAB + "istore" + ((lhsSymbol.getIndex() <= 3) ? "_" : " ") +
                 lhsSymbol.getIndex());
        break;
      case "int[]":
        stack.addInstruction(Instructions.ASTORE, 0);
        appendln(TAB + "astore" + ((lhsSymbol.getIndex() <= 3) ? "_" : " ") +
                 lhsSymbol.getIndex());
        break;
      case "boolean":
        stack.addInstruction(Instructions.ISTORE, 0);
        appendln(TAB + "istore" + ((lhsSymbol.getIndex() <= 3) ? "_" : " ") +
                 lhsSymbol.getIndex());
        break;
      default:
        stack.addInstruction(Instructions.ASTORE, 0);
        appendln(TAB + "astore" + ((lhsSymbol.getIndex() <= 3) ? "_" : " ") +
                 lhsSymbol.getIndex());
      }
    }
  }

  private void generateIf(ASTIf ifStmt, StackController stack) {
    System.out.println("found an if statement");

    // Condition is first child
    ASTIfCondition condStmt = (ASTIfCondition)ifStmt.jjtGetChild(0);
    // Else is last child
    ASTElse elseStmt =
        (ASTElse)ifStmt.jjtGetChild(ifStmt.jjtGetNumChildren() - 1);

    // Generate if condition
    generateExpression(condStmt, stack);

    // Generate if body
    appendln(TAB + "ifeq ELSE_" + (ifStmt.getId()));
    for (int i = 1; i < ifStmt.jjtGetNumChildren() - 1; i++)
      generateStatement((SimpleNode)ifStmt.jjtGetChild(i), stack);
    appendln(TAB + "goto NEXT_" + (ifStmt.getId()));

    // Generate else
    appendln(TAB + "ELSE_" + +ifStmt.getId() + ":");
    if (elseStmt.jjtGetChildren() != null)
      for (Node n : elseStmt.jjtGetChildren())
        generateStatement((SimpleNode)n, stack);
    appendln(TAB + "NEXT_" + ifStmt.getId() + ":");
  }

  private void generateWhile(ASTWhile whileStmt, StackController stack) {
    System.out.println("found a while statement");
  }

  private void generateReturnStmt(ASTReturnStmt returnStmt,
                                  StackController stack) {
    System.out.println("found a return statement");
  }

  private void generateGlobalVar(ASTVariable varDecl) {
    String name = varDecl.getVarName();
    String type = parseType(varDecl.getVarType());

    if (name.equals("field"))
      name = "'field'";

    appendln(".field private " + name + " " + type);
  }

  private void generateIdentifier(ASTId id, StackController stack) {
    System.out.println("found an identifier");
  }

  private void generateAdd(ASTAdd add, StackController stack) {
    System.out.println("found add expression");
  }

  private void generateSub(ASTSub sub, StackController stack) {
    System.out.println("found sub expression");
  }

  private void generateMul(ASTMul mul, StackController stack) {
    System.out.println("found mul expression");
  }

  private void generateDiv(ASTDiv mul, StackController stack) {
    System.out.println("found div expression");
  }

  private void generateLessThan(ASTLessThan lessthan, StackController stack) {
    System.out.println("found less than expression");
  }

  private void generateFalse(ASTFalse not, StackController stack) {
    System.out.println("found not expression");
  }

  private void generateNew(ASTNew newexpr, StackController stack) {
    System.out.println("found new expression");
  }

  private void generateMethodCall(ASTMethodCall methodcall,
                                  StackController stack) {
    System.out.println("found method call expression");
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
