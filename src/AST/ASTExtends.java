/* Generated By:JJTree: Do not edit this line. ASTExtends.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package AST;

public
class ASTExtends extends SimpleNode {
  public ASTExtends(int id) {
    super(id);
  }

  public ASTExtends(Parser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=766aa59b279e30f337c25d30d045a11f (do not edit this line) */
