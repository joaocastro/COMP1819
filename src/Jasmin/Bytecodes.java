package Jasmin;

import AST.*;
import Symbol.*;
import java.io.*;
import java.util.ArrayList;

public class Bytecodes{

    private static SymbolTable symbolTable;
    private static PrintWriter writer;
    private static ArrayList<String> register_variables;

    public static void generateJavaBytecodes(Node root, SymbolTable st) throws IOException {
		symbolTable = st;
		String dirName = "bin/generatedFiles";
		File dir = new File(dirName);
		if(!dir.exists())
			dir.mkdir();

		String fileName =((SimpleNode) root).jjtGetValue() + ".j";
		File jFile = new File(dirName + "/" + fileName);
	    FileOutputStream jFileOS = new FileOutputStream(jFile);
	    writer = new PrintWriter(jFileOS);

	    moduleJavaBytecodes(root);

    }

    private static void moduleJavaBytecodes(Node root){
		
		ByteArrayOutputStream clinitBuffer = new ByteArrayOutputStream();
		PrintWriter clinitWriter = new PrintWriter(clinitBuffer);

		writer.println(".class public " + ((SimpleNode) root).jjtGetValue());
	    writer.println(".super java/lang/Object\n");

	    int numChildren = root.jjtGetNumChildren();
	    for(int i = 0; i < numChildren; i++) {
            //TODO - Tratar dos filhos
		}
		clinitWriter.close();
	    clinitJavaBytecodes(clinitBuffer.toString());

	    writer.close();

    }
    
    private static void clinitJavaBytecodes(String content){
	    writer.println(".method static public <clinit>()V");
	    writer.println(".limit stack " + 0);
		writer.println(".limit locals " + register_variables.indexOf(null));
		writer.println(content);
	    writer.println("return");
	    writer.println(".end method ");

    }

    /*
    Handling each possible out from grammar
    */

    private static void elseJavaByteCodes(SimpleNode elseNode, int loop){
        writer.println();
        writer.println("goto loop" + loop + "_next");
        writer.println("loop" + loop + "_end:");
       //TODO: Pode ser recursivo
        writer.println();
        writer.println("loop" + loop + "_next:");
    }

    private static String loadInteger(Integer value){
		if(value >= 0 && value <= 5)
			return "iconst_" + value;
	    else if(value >= -128 && value <= 127)
	        return "bipush " + value;
		else if(value >= -32768 && value <= 32767)
			return "sipush " + value;
		else
			return "ldc " + value;
    }

    private static String loadString(String value){
		return "ldc " + value;
    }

}