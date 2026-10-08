// O Token representa uma unidade léxica do código, contendo seu tipo e valor.

public class Token {

    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    // Retorna o tipo do token
    public TokenType getType() {
        return type;
    }

    // Retorna o valor do token
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<"+ type +">" + value + "</"+ type + ">";
    }
}