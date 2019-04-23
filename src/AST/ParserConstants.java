/* Generated By:JJTree&JavaCC: Do not edit this line. ParserConstants.java */
package AST;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 6;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 7;
  /** RegularExpression Id. */
  int DOCS_COMMENT = 8;
  /** RegularExpression Id. */
  int CLASS = 9;
  /** RegularExpression Id. */
  int EXTENDS = 10;
  /** RegularExpression Id. */
  int PUBLIC = 11;
  /** RegularExpression Id. */
  int STATIC = 12;
  /** RegularExpression Id. */
  int VOID = 13;
  /** RegularExpression Id. */
  int MAIN = 14;
  /** RegularExpression Id. */
  int RETURN = 15;
  /** RegularExpression Id. */
  int IF = 16;
  /** RegularExpression Id. */
  int ELSE = 17;
  /** RegularExpression Id. */
  int WHILE = 18;
  /** RegularExpression Id. */
  int LENGTH = 19;
  /** RegularExpression Id. */
  int NEW = 20;
  /** RegularExpression Id. */
  int TRUE = 21;
  /** RegularExpression Id. */
  int FALSE = 22;
  /** RegularExpression Id. */
  int THIS = 23;
  /** RegularExpression Id. */
  int DOT = 24;
  /** RegularExpression Id. */
  int COMMA = 25;
  /** RegularExpression Id. */
  int NOT = 26;
  /** RegularExpression Id. */
  int SEMICOLON = 27;
  /** RegularExpression Id. */
  int LPARENS = 28;
  /** RegularExpression Id. */
  int RPARENS = 29;
  /** RegularExpression Id. */
  int LSQPARENS = 30;
  /** RegularExpression Id. */
  int RSQPARENS = 31;
  /** RegularExpression Id. */
  int LBRACKET = 32;
  /** RegularExpression Id. */
  int RBRACKET = 33;
  /** RegularExpression Id. */
  int ASSIGN = 34;
  /** RegularExpression Id. */
  int TYPE_STRING = 35;
  /** RegularExpression Id. */
  int TYPE_BOOLEAN = 36;
  /** RegularExpression Id. */
  int TYPE_INT = 37;
  /** RegularExpression Id. */
  int LESSTHAN = 38;
  /** RegularExpression Id. */
  int AND = 39;
  /** RegularExpression Id. */
  int PLUS = 40;
  /** RegularExpression Id. */
  int MINUS = 41;
  /** RegularExpression Id. */
  int TIMES = 42;
  /** RegularExpression Id. */
  int DIVIDES = 43;
  /** RegularExpression Id. */
  int ARITHMETIC_OPERATOR = 44;
  /** RegularExpression Id. */
  int INTEGER_LITERAL = 45;
  /** RegularExpression Id. */
  int BOOLEAN_LITERAL = 46;
  /** RegularExpression Id. */
  int IDENTIFIER = 47;
  /** RegularExpression Id. */
  int DIGIT = 48;
  /** RegularExpression Id. */
  int LETTER = 49;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "<SINGLE_LINE_COMMENT>",
    "<MULTI_LINE_COMMENT>",
    "<DOCS_COMMENT>",
    "\"class\"",
    "\"extends\"",
    "\"public\"",
    "\"static\"",
    "\"void\"",
    "\"main\"",
    "\"return\"",
    "\"if\"",
    "\"else\"",
    "\"while\"",
    "\"length\"",
    "\"new\"",
    "\"true\"",
    "\"false\"",
    "\"this\"",
    "\".\"",
    "\",\"",
    "\"!\"",
    "\";\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\"{\"",
    "\"}\"",
    "\"=\"",
    "\"String\"",
    "\"boolean\"",
    "\"int\"",
    "\"<\"",
    "\"&&\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "<ARITHMETIC_OPERATOR>",
    "<INTEGER_LITERAL>",
    "<BOOLEAN_LITERAL>",
    "<IDENTIFIER>",
    "<DIGIT>",
    "<LETTER>",
  };

}