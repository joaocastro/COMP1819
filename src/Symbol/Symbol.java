package Symbol;

public class Symbol {
    private String name;
    private String type;
    private boolean init = false;
    private int index;

    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;
        this.index = 0;
    }

    public Symbol(String name, String type, boolean init) {
        this.name = name;
        this.type = type;
        this.init = init;
        this.index = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean getInit() {
        return this.init;
    }

    public int getIndex() {
        return this.index;
    }

    public void setInit(boolean val){
        this.init = val;
    }

    public void setIndex(int val){
        this.index = val;
    }

    public boolean equals(String name) {
        return this.name.equals(name);
    }

    public boolean equals(Object symbol) {
        Symbol s = (Symbol) symbol;
        return this.name.equals(s.getName());
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return this.name + " - " + this.type;
    }
}
