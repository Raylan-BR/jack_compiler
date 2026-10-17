import java.util.List;

public class Parser {

    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // Verifica se todos os tokens foram consumidos.
    private boolean isAtEnd() {
        return current >= tokens.size();
    }

    // Retorna o token atual sem avançar.
    private Token peek() {
        if (isAtEnd()) {
            return null;
        }

        return tokens.get(current);
    }

    // Consome o token atual e avança para o próximo.
    private Token advance() {
        if (!isAtEnd()) {
            current++;
        }

        return tokens.get(current - 1);
    }

    // Verifica se o token atual é de determinado tipo.
    private boolean match(TokenType type) {
        if (isAtEnd()) {
            return false;
        }

        if (peek().getType() != type) {
            return false;
        }

        advance();
        return true;
    }
    private Token consume(TokenType type) {
        if (isAtEnd()) {
            throw new RuntimeException(
                "Erro de sintaxe: esperado " + type
                + ", mas o arquivo terminou."
            );
        }

        if (peek().getType() == type) {
            return advance();
        }

        Token token = peek();

        throw new RuntimeException(
            "Erro de sintaxe na linha " + token.getLine()
            + ", coluna " + token.getColumn()
            + ": esperado " + type
            + ", mas encontrado '" + token.getValue() + "'."
        );
    }
}
