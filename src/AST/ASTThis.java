/* Generated By:JJTree: Do not edit this line. ASTThis.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package AST;

public
class ASTThis extends SimpleNode {
  public ASTThis(int id) {
    super(id);
  }

  public ASTThis(Parser p, int id) {
    super(p, id);
  }
  
  public String toString() {
    return "this";
  }

  /** Accept the visitor. **/
  public Object jjtAccept(ParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=00c7eaacf472c02b0c627927b04c2981 (do not edit this line) */
