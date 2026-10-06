import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {

            char current = input.charAt(position);

            // String
            if (current == '"') {
                tokens.add(readString());
                continue;
            }

            // Símbolo
            if (JackSymbols.isSymbol(current)) {
                tokens.add(
                        new Token(
                                TokenType.SYMBOL,
                                String.valueOf(current)
                        )
                );

                position++;
                continue;
            }
        }

        return tokens;
    }

    private Token readString() {

        // Pula a primeira aspas
        position++;

        StringBuilder value = new StringBuilder();

        while (position < input.length()) {

            char current = input.charAt(position);

            // Encontrou a aspas final
            if (current == '"') {
                position++;

                return new Token(
                        TokenType.STRING_CONSTANT,
                        value.toString()
                );
            }

            value.append(current);
            position++;
        }

        throw new RuntimeException(
                "String não fechada."
        );
    }
}