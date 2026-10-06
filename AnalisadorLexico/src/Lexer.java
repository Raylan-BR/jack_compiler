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

            // Número inteiro
            if (Character.isDigit(current)) {
                tokens.add(readInteger());
                continue;
            }

            // Identificador ou keyword
            if (Character.isLetter(current) || current == '_') {
                tokens.add(readIdentifierOrKeyword());
                continue;
            }

            // Caractere inválido
            throw new RuntimeException(
                    "Caractere inválido na posição "
                            + position
                            + ": "
                            + current
            );
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
    
    private Token readInteger() {

        StringBuilder value = new StringBuilder();

        while (position < input.length()
                && Character.isDigit(input.charAt(position))) {

            value.append(input.charAt(position));
            position++;
        }

        int number = Integer.parseInt(value.toString());

        // Jack permite inteiros de 0 a 32767
        if (number < 0 || number > 32767) {
            throw new RuntimeException(
                    "Integer constant fora do intervalo: "
                            + number
            );
        }

        return new Token(
                TokenType.INTEGER_CONSTANT,
                value.toString()
        );
    }

    private Token readIdentifierOrKeyword() {

        StringBuilder value = new StringBuilder();

        while (position < input.length()) {

            char current = input.charAt(position);

            if (Character.isLetterOrDigit(current)
                    || current == '_') {

                value.append(current);
                position++;

            } else {
                break;
            }
        }

        String word = value.toString();

        if (JackKeywords.isKeyword(word)) {
            return new Token(
                    TokenType.KEYWORD,
                    word
            );
        }

        return new Token(
                TokenType.IDENTIFIER,
                word
        );
    }
}