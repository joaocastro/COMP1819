/* Generated By:JJTree: Do not edit this line. ASTLhs.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package AST;

public
class ASTLhs extends SimpleNode {
  public ASTLhs(int id) {
    super(id);
  }

  public ASTLhs(Parser p, int id) {
    super(p, id);
  }

  public String toString() {
    if (children == null)
      return super.toString();

    return super.toString() + "[" + children[0].toString() + "]";
  }

  public void dump(String prefix) {
    System.out.println(toString(prefix));
  }

}
/* JavaCC - OriginalChecksum=8b51936d91e24045e30afbdc0881ea7d (do not edit this line) */
