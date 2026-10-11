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

    // Padrão XML com escape de simbolos especiais
    @Override
    public String toString() {
        String tag = switch (type.toString()) {
            case "KEYWORD" -> "keyword";
            case "SYMBOL" -> "symbol";
            case "IDENTIFIER" -> "identifier";
            case "INTEGER_CONSTANT" -> "integerConstant";
            case "STRING_CONSTANT" -> "stringConstant";
            default -> throw new IllegalArgumentException(
                "Tipo de token desconhecido: " + type
            );
        };

        String xmlValue = value
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;");

        return "<" + tag + "> " + xmlValue
            + " </" + tag + ">";
    }
}