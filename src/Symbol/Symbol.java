package Symbol;

public class Symbol {
    private String name = null;
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

    public void setInit(){
        this.init = true;
    }

    public void setNotInit(){
        this.init = false;
    }

    public boolean equals(Object symbol) {
        Symbol s = (Symbol) symbol;
        return this.name.equals(s.getName());
    }

    public int hashCode() {
        return name.hashCode();
    }
}
