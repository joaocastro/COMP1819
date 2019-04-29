package Symbol;

public class Symbol {
    private String name;
    private String type;
    private boolean init = false;

    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Symbol(String name, String type, boolean init) {
        this.name = name;
        this.type = type;
        this.init = init;
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

    public void setInit(boolean val){
        this.init = val;
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
