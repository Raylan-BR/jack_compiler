// O Token representa uma unidade léxica do código, contendo seu tipo e valor.

public class Token {

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String toString() {
        return "<"+ type +">" + value + "</"+ type + ">";
    }
}