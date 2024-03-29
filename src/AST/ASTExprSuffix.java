/* Generated By:JJTree: Do not edit this line. ASTExprSuffix.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package AST;

public
class ASTExprSuffix extends SimpleNode {
  protected String kind = null;

  public ASTExprSuffix(int id) {
    super(id);
  }

  public ASTExprSuffix(Parser p, int id) {
    super(p, id);
  }
  
  public String toString() {
    if (children == null) // ExprSuffix without children
      return super.toString();

    if (this.val == null) // ArrayAccess
      return this.kind;
    else                  // MethodCall
      return this.kind + " " + this.val;
  }

  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=66efb7369672a560184d480074f1255c (do not edit this line) */
