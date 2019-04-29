package AST;

import Symbol.*;

public class SemanticVisitor implements ParserVisitor {
  public Object defaultVisit(SimpleNode node, Object data) {
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTProgram node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTClass node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTName node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTExtends node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTMain node, Object data) {
    SymbolTable st = (SymbolTable)data;

    return defaultVisit(node, st.getChild("main"));
  }
  public Object visit(ASTMethod node, Object data) {
    String method_name = ((SimpleNode)node.jjtGetChild(1)).getVal();

    SymbolTable st = (SymbolTable)data;

    return defaultVisit(node, st.getChild(method_name));
  }
  public Object visit(ASTReturn node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTReturnStmt node, Object data) {
    SymbolTable st = (SymbolTable)data;

    if (node.jjtGetChild(0) instanceof ASTId) {
      String id = ((SimpleNode)node.jjtGetChild(0)).getVal();
      Symbol sym = st.lookup(id);
      
      if (sym != null)
        if (sym.getInit() == false)
          System.err.println(
              "(error) this identifier has not been initialized: " + id);
    }

    return defaultVisit(node, data);
  }
  public Object visit(ASTMethodParams node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTMethodParam node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTType node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTVariable node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTAssign node, Object data) {
    String lhs = ((ASTLhs)node.jjtGetChild(0)).getVal();

    SymbolTable st = (SymbolTable)data;
    Symbol sym = st.lookup(lhs);

    if (sym == null) {
      System.err.println("(error) trying to assign to undeclared variable: " +
                         lhs);
    } else {
      sym.setInit(true);
    }

    return defaultVisit(node, data);
  }
  public Object visit(ASTLhs node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTRhs node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTIf node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTIfCondition node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTElse node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTWhile node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTWhileCondition node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTAnd node, Object data) {
    SymbolTable st = (SymbolTable)data;

    for (Node n : node.jjtGetChildren())
      if (n instanceof ASTId) {
        String id = ((SimpleNode)n).getVal();
        Symbol sym = st.lookup(id);
        if (sym != null)
          if (sym.getInit() == false)
            System.err.println(
                "(error) this identifier has not been initialized: " + id);
      }

    return defaultVisit(node, data);
  }
  public Object visit(ASTLessThan node, Object data) {
    SymbolTable st = (SymbolTable)data;

    for (Node n : node.jjtGetChildren())
      if (n instanceof ASTId) {
        String id = ((SimpleNode)n).getVal();
        Symbol sym = st.lookup(id);
        if (sym != null)
          if (sym.getInit() == false)
            System.err.println(
                "(error) this identifier has not been initialized: " + id);
      }

    return defaultVisit(node, data);
  }
  public Object visit(ASTAdd node, Object data) {
    SymbolTable st = (SymbolTable)data;

    for (Node n : node.jjtGetChildren())
      if (n instanceof ASTId) {
        String id = ((SimpleNode)n).getVal();
        Symbol sym = st.lookup(id);
        if (sym != null)
          if (sym.getInit() == false)
            System.err.println(
                "(error) this identifier has not been initialized: " + id);
      }

    return defaultVisit(node, data);
  }
  public Object visit(ASTSub node, Object data) {
    SymbolTable st = (SymbolTable)data;

    for (Node n : node.jjtGetChildren())
      if (n instanceof ASTId) {
        String id = ((SimpleNode)n).getVal();
        Symbol sym = st.lookup(id);
        if (sym != null)
          if (sym.getInit() == false)
            System.err.println(
                "(error) this identifier has not been initialized: " + id);
      }

    return defaultVisit(node, data);
  }
  public Object visit(ASTMul node, Object data) {
    SymbolTable st = (SymbolTable)data;

    for (Node n : node.jjtGetChildren())
      if (n instanceof ASTId) {
        String id = ((SimpleNode)n).getVal();
        Symbol sym = st.lookup(id);
        if (sym != null)
          if (sym.getInit() == false)
            System.err.println(
                "(error) this identifier has not been initialized: " + id);
      }

    return defaultVisit(node, data);
  }
  public Object visit(ASTDiv node, Object data) {
    SymbolTable st = (SymbolTable)data;

    for (Node n : node.jjtGetChildren())
      if (n instanceof ASTId) {
        String id = ((SimpleNode)n).getVal();
        Symbol sym = st.lookup(id);
        if (sym != null)
          if (sym.getInit() == false)
            System.err.println(
                "(error) this identifier has not been initialized: " + id);
      }

    return defaultVisit(node, data);
  }
  public Object visit(ASTExprSuffix node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTMethodCallArgs node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTInteger node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTTrue node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTFalse node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTId node, Object data) {
    SymbolTable st = (SymbolTable)data;

    if (st.lookup(node.val) == null)
      System.err.println(
          "(error) this identifier has not been declared in this or parent scope(s): " +
          node.val);

    return defaultVisit(node, data);
  }
  public Object visit(ASTThis node, Object data) {
    return defaultVisit(node, data);
  }
  public Object visit(ASTNew node, Object data) {
    return defaultVisit(node, data);
  }
}
