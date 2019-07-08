package Generation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.*;

import AST.*;
import Symbol.*;

public class Codegen {
	private static SymbolTable symbolTable;
	private static PrintWriter writer;
	private static Node rootNode;
	private static ArrayList<String> register_variables = new ArrayList<>();

	public static void generateJavaCodegen(Node root, SymbolTable st) throws IOException {
		symbolTable = st;
		rootNode = root;
		
		String dirName = "../gen";
		File dir = new File(dirName);
		if (!dir.exists())
			dir.mkdir();

		String fileName = ((SimpleNode) rootNode).jjtGetValue() + ".j";
		File jFile = new File(dirName + "/" + fileName);
		FileOutputStream jFileOS = new FileOutputStream(jFile);
		writer = new PrintWriter(jFileOS);

		moduleJavaCodegen();
	}

	private static void moduleJavaCodegen() {

		writer.println(".class public " + ((SimpleNode) rootNode).jjtGetValue());
		writer.println(".super java/lang/Object\n");

		clinitJavaCodegen();
		for(SymbolTable table : symbolTable.getChildren().values()) {
			if(table.getName().equals("main")) {
				printMainMethod(table);
			} else {

			}
			writer.println();
		}

		writer.close();
	}

	private static void clinitJavaCodegen() {
		writer.println(".method static public <clinit>()V");

		printStack(1);
		printLocals(1);

		writer.println("\treturn");
		writer.println(".end method");
	}

	private static void printStack(int stack) {
		writer.println("\t.limit stack " + stack);
	}

	private static void printLocals(int locals) {
		writer.println("\t.limit locals " + locals);
	}


    public static void printMainMethod(SymbolTable table) {
        writer.println(".method public static main([Ljava/lang/String;)V");
		printStack(table.getLocals().size() * 4); // Assuming each type is of size 4
		writer.println("\treturn");

		writer.println(".end method");
    }

	public static void printMethod(SymbolTable table) {

    }

	/*
	 * Handling each possible output from grammar
	 */

	private static void elseJavaCodegen(SimpleNode elseNode, int loop) {
		writer.println();
		writer.println("goto loop" + loop + "_next");
		writer.println("loop" + loop + "_end:");
		// TODO: Pode ser recursivo
		writer.println();
		writer.println("loop" + loop + "_next:");
	}

	private static String loadInteger(Integer value) {
		if (value >= 0 && value <= 5)
			return "iconst_" + value;
		else if (value >= -128 && value <= 127)
			return "bipush " + value;
		else if (value >= -32768 && value <= 32767)
			return "sipush " + value;
		else
			return "ldc " + value;
	}

	private static String loadString(String value) {
		return "ldc " + value;
	}

	private static void mainDecl() {
		writer.println(".method public static main([Ljava/lang/String;)V");
		printStack(1);
		printLocals(1);	

		statementDecl();
	}

	private static void methodDecl(Node method) {
		writer.println(".method " + ((SimpleNode) method.jjtGetChild(0).jjtGetChild(0)).getVal() + " " + ((SimpleNode) method.jjtGetChild(1)).getVal());
		int stack = 1; // ?  
		int locals = 1; // ?
		printStack(stack);
		printLocals(locals);

		statementDecl();
	}

	private static void statementDecl() {

	}
}
