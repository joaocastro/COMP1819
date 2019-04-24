/* Generated By:JavaCC: Do not edit this line. ParserDefaultVisitor.java Version 6.0_1 */
package AST;

public class ParserDefaultVisitor implements ParserVisitor{
  public Object defaultVisit(SimpleNode node, Object data){
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTStart node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTClass node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTName node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTExtends node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMain node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMethod node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTReturn node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTReturnStmt node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMethodParams node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMethodParam node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTType node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTVariable node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTAssign node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTLhs node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTRhs node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTIf node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTIfCondition node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTElse node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTWhile node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTWhileCondition node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTAnd node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTLessThan node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTAdd node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTSub node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMul node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTDiv node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTExprSuffix node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMethodCallArgs node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTInteger node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTTrue node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTFalse node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTId node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTThis node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTNew node, Object data){
    return defaultVisit(node, data);
  }
}
/* JavaCC - OriginalChecksum=f9b83f93b946f4c6cc54a4ff7c13497c (do not edit this line) */
