package Symbol;

import java.util.LinkedHashMap;

public class SymbolTable implements Cloneable {
  private String name;
  private String return_type = "none";

  private LinkedHashMap<String, Symbol> params;
  private LinkedHashMap<String, Symbol> locals;
  private LinkedHashMap<String, SymbolTable> children;
  private SymbolTable parent = null;

  public SymbolTable(String name) {
    this.name = name;
    this.params = new LinkedHashMap<String, Symbol>();
    this.locals = new LinkedHashMap<String, Symbol>();
    this.children = new LinkedHashMap<String, SymbolTable>();
  }

  public SymbolTable(String name, String type, SymbolTable parent) {
    this(name);
    this.return_type = type;
    this.parent = parent;
  }

  public LinkedHashMap<String, Symbol> getParams() { return this.params; }

  public LinkedHashMap<String, Symbol> getLocals() { return this.locals; }
  public int getLocalsNum() { return this.locals.size(); }


  public LinkedHashMap<String, SymbolTable> getChildren() { return this.children; }

  public void setIndexs(int count) {
    for (Symbol s : this.params.values())
      s.setIndex(count++);

    for (Symbol s : this.locals.values())
      s.setIndex(count++);

    for (SymbolTable child : this.children.values())
      child.setIndexs(count);
  }
  
  public Symbol lookup(String name) {
    for (Symbol s : this.params.values())
      if (s.equals(name))
        return s;

    for (Symbol s : this.locals.values())
      if (s.equals(name))
        return s;

    if (this.parent == null)
      return null;

    return this.parent.lookup(name);
  }

  public SymbolTable getTable(String name) {
    if (this.name == name)
      return this;

    for (SymbolTable st : this.children.values())
      return st.getTable(name);

    return null;
  }

  public String getName() { return this.name; }

  public SymbolTable getChild(String name) { return this.children.get(name); }

  public String getReturnType() { return return_type; }

  public void setName(String name) { this.name = name; }

  public boolean addVariable(String name, String type) {
    if (this.locals.containsKey(name)) {
      return false;
    } else {
      Symbol s = new Symbol(name, type);
      this.locals.put(name, s);
      return true;
    }
  }

  public boolean addParam(String name, String type) {
    if (this.params.containsKey(name)) {
      return false;
    } else {
      Symbol s = new Symbol(name, type, true);
      this.params.put(name, s);
      return true;
    }
  }

  public SymbolTable addChild(String name, String type) {
    SymbolTable st = new SymbolTable(name, type, this);

    this.children.put(name, st);

    return st;
  }

  public void removeVariable(String name) {
    if (!this.locals.containsKey(name)) {
      return;
    } else {
      this.locals.remove(name);
    }
  }

  public void show(String prefix) {
    System.out.println(prefix + "name: " + this.name);
    System.out.println(prefix + "return type: " + this.return_type);

    System.out.println(prefix + "params:");
    for (Symbol s : this.params.values())
      System.out.println(prefix + "  " + s.toString());

    System.out.println(prefix + "locals:");
    for (Symbol s : this.locals.values())
      System.out.println(prefix + "  " + s.toString());

    System.out.println(prefix + "children:");
    for (SymbolTable s : this.children.values()) {
      s.show("  ");
      System.out.println(prefix + "  "
                         + "------");
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public SymbolTable clone() throws CloneNotSupportedException {
    SymbolTable newTable = (SymbolTable)super.clone();
    newTable.locals = (LinkedHashMap<String, Symbol>)this.locals.clone();

    return newTable;
  }
}
