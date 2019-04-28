package Symbol;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable implements Cloneable {
  private String name;
  private LinkedHashMap<String, Symbol> params;
  private LinkedHashMap<String, Symbol> locals;
  private LinkedHashMap<String, SymbolTable> children;
  private SymbolTable parent = null;
  
  private Symbol returnSymbol = null;
  private boolean returned = false;

  public SymbolTable(String name) {
    this.name = name;
    this.params = new LinkedHashMap<String, Symbol>();
    this.locals = new LinkedHashMap<String, Symbol>();
  }

  public LinkedHashMap<String, Symbol> getParams() {
    return this.params;
  }

  public LinkedHashMap<String, Symbol> getLocals() { 
    return this.locals; 
  }

  public Symbol getReturnSymbol() {
    if (this.returnSymbol != null)
      return this.returnSymbol;

    return null;
  }

  public Boolean getReturned() { return this.returned; }

  public void setReturnSymbol(String name, String type) {
    Symbol s = new Symbol(name, type);
    this.returnSymbol = s;
  }

  public void setReturned(Boolean returned) { this.returned = returned; }

  public boolean addParameter(String name, String type) {
    if (this.params.containsKey(name)) {
      return false;
    } else {
      Symbol s = new Symbol(name, type, true);
      this.params.put(name, s);
      return true;
    }
  }

  public boolean addVariable(String name, String type) {
    if (this.locals.containsKey(name)) {
      return false;
    } else {
      Symbol s = new Symbol(name, type, true);
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

  public void removeVariable(String name) {
    if (!this.locals.containsKey(name)) {
      return;
    } else {
      this.locals.remove(name);
    }
  }

  public void show(String prefix) {
    System.out.println(prefix + "name: " + this.name);
    
    System.out.println(prefix + "params:");
    for (Symbol s : this.params.values())
      System.out.println(prefix + s.toString());

    System.out.println(prefix + "locals:");
    for (Symbol s : this.locals.values())
      System.out.println(prefix + s.toString());
  }

  @SuppressWarnings("unchecked")
  @Override
  public SymbolTable clone() throws CloneNotSupportedException {
    SymbolTable newTable = (SymbolTable)super.clone();
    newTable.locals = (LinkedHashMap<String, Symbol>)this.locals.clone();
    
    return newTable;
  }
}
