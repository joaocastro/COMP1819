package AST;

import Symbol.*;

public class SymbolVisitor implements ParserVisitor{
  public Object defaultVisit(SimpleNode node, Object data){
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTProgram node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTClass node, Object data){
    String class_name = ((SimpleNode) node.jjtGetChild(0)).getVal();

    SymbolTable st = (SymbolTable) data;
    st.setName(class_name);

    return defaultVisit(node, st);
  }
  public Object visit(ASTName node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTExtends node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMain node, Object data){
    SymbolTable st = (SymbolTable) data;
  
    return defaultVisit(node, st.addChild("main", "void"));
  }
  public Object visit(ASTMethod node, Object data){
    String method_name = ((SimpleNode) node.jjtGetChild(1)).getVal();
    String method_type = ((SimpleNode) node.jjtGetChild(0).jjtGetChild(0)).getVal();
    
    SymbolTable st = (SymbolTable) data;
  
    return defaultVisit(node, st.addChild(method_name, method_type));
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
    String name = node.val;
    String type = ((SimpleNode) node.jjtGetChild(0)).getVal();
    
    SymbolTable st = (SymbolTable) data;
    st.addParam(name, type);
    
    return defaultVisit(node, st);
  }
  public Object visit(ASTType node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTVariable node, Object data){
    String name = node.val;
    String type = ((SimpleNode) node.jjtGetChild(0)).getVal();
    
    SymbolTable st = (SymbolTable) data;
    st.addVariable(name, type);
    
    return defaultVisit(node, st);
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
