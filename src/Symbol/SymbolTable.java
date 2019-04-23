package Symbol;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable implements Cloneable {
  private LinkedHashMap<String, Symbol> params;
  private LinkedHashMap<String, Symbol> locals;
  private Symbol returnSymbol = null;
  private boolean returned = false;

  public SymbolTable() {
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

  public void removeVariable(String name) {
    if (!this.locals.containsKey(name)) {
      return;
    } else {
      this.locals.remove(name);
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
