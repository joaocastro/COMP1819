/* Generated By:JJTree: Do not edit this line. ASTId.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package AST;

public
class ASTId extends SimpleNode {
  public ASTId(int id) {
    super(id);
  }

  public ASTId(Parser p, int id) {
    super(p, id);
  }

  public String toString() {
    return "id" + " " + this.val;
  }

}
/* JavaCC - OriginalChecksum=1f7368955bd6aad692b10f39c061f49d (do not edit this line) */
