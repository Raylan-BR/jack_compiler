import java.util.List;

public class LexerTest {

    public static void main(String[] args) {
        // Testes de tokens básicos
        testKeywords();
        testIdentifiers();
        testSymbols();
        testIntegerConstants();
        testStringConstants();
        testInvalidInteger();
        testUnterminatedString();
        
        // Testes de comentários e espaços em branco
        testWhitespace();
        testLineComments();
        testBlockComments();
        testMultilineComments();
        testUnterminatedBlockComment();

        System.out.println("\nTodos os testes foram executados!");
    }

    private static void testKeywords() {
        assertSingleToken("class", TokenType.KEYWORD, "class");
        assertSingleToken("function", TokenType.KEYWORD, "function");
        assertSingleToken("return", TokenType.KEYWORD, "return");

        System.out.println("PASSOU: palavras-chave");
    }

    private static void testIdentifiers() {
        assertSingleToken("Main", TokenType.IDENTIFIER, "Main");
        assertSingleToken("main", TokenType.IDENTIFIER, "main");
        assertSingleToken("contador", TokenType.IDENTIFIER, "contador");

        System.out.println("PASSOU: identificadores");
    }

    private static void testSymbols() {
        String[] symbols = {"{", "}", "(", ")", ";", "="};

        for (String symbol : symbols) {
            assertSingleToken(symbol, TokenType.SYMBOL, symbol);
        }

        System.out.println("PASSOU: símbolos");
    }

    private static void testIntegerConstants() {
        assertSingleToken("0", TokenType.INTEGER_CONSTANT, "0");
        assertSingleToken("123", TokenType.INTEGER_CONSTANT, "123");
        assertSingleToken("32767", TokenType.INTEGER_CONSTANT, "32767");

        System.out.println("PASSOU: constantes inteiras");
    }

    private static void testStringConstants() {
        assertSingleToken("\"Olá\"", TokenType.STRING_CONSTANT, "Olá");
        assertSingleToken("\"\"", TokenType.STRING_CONSTANT, "");

        System.out.println("PASSOU: constantes de string");
    }

    private static void testInvalidInteger() {
        assertInvalidInput("32768");

        System.out.println("PASSOU: rejeição de inteiro fora do limite");
    }

    private static void testUnterminatedString() {
        assertInvalidInput("\"texto sem fechamento");

        System.out.println("PASSOU: rejeição de string não terminada");
    }

    
    private static void testWhitespace() {
        String input = "class \t Main\r\n { \n }";

        assertTokenSequence(
            input,
            "class", "Main", "{", "}"
        );

        System.out.println("PASSOU: espaços em branco");
    }

    
    
    private static void testLineComments() {
        String input =
            "class Main { // comentário de linha\n" +
            "function void main() { return; } // outro comentário";

        assertTokenSequence(
            input,
            "class", "Main", "{",
            "function", "void", "main", "(",
            ")", "{", "return", ";", "}"
        );

        System.out.println("PASSOU: comentários de linha");
    }

    
    private static void testBlockComments() {
        String input =
            "class /* comentário */ Main { }";

        assertTokenSequence(
            input,
            "class", "Main", "{", "}"
        );

        System.out.println("PASSOU: comentários de bloco");
    }

    
    private static void testMultilineComments() {
        String input =
            "class Main {\n" +
            "/* comentário\n" +
            "   em várias linhas\n" +
            "*/\n" +
            "function void main() { return; }\n" +
            "}";

        assertTokenSequence(
            input,
            "class", "Main", "{",
            "function", "void", "main", "(",
            ")", "{", "return", ";", "}", "}"
        );

        System.out.println("PASSOU: comentários multilinha");
    }

    
    private static void testUnterminatedBlockComment() {
        assertInvalidInput("class Main { /* comentário sem fechamento");

        System.out.println("PASSOU: rejeição de comentário não terminado");
    }

    
    private static void assertTokenSequence(
            String input,
            String... expectedValues) {

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        if (tokens.size() != expectedValues.length) {
            throw new AssertionError(
                "Quantidade de tokens incorreta. Esperado: "
                + expectedValues.length
                + ", recebido: " + tokens.size()
                + "\nEntrada: " + input
                + "\nTokens: " + tokens
            );
        }

        for (int i = 0; i < expectedValues.length; i++) {
            String actualValue = tokens.get(i).getValue();

            if (!actualValue.equals(expectedValues[i])) {
                throw new AssertionError(
                    "Token na posição " + i
                    + ": esperado [" + expectedValues[i]
                    + "], recebido [" + actualValue + "]"
                );
            }
        }
    }

    private static void assertSingleToken(
            String input,
            TokenType expectedType,
            String expectedValue) {

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        if (tokens.size() != 1) {
            throw new AssertionError(
                "Entrada: " + input
                + " | Esperado 1 token, recebido: " + tokens.size()
            );
        }

        Token token = tokens.get(0);

        if (token.getType() != expectedType) {
            throw new AssertionError(
                "Entrada: " + input
                + " | Tipo esperado: " + expectedType
                + " | Tipo recebido: " + token.getType()
            );
        }

        if (!token.getValue().equals(expectedValue)) {
            throw new AssertionError(
                "Entrada: " + input
                + " | Valor esperado: [" + expectedValue
                + "] | Valor recebido: [" + token.getValue() + "]"
            );
        }
    }

    private static void assertInvalidInput(String input) {
        try {
            Lexer lexer = new Lexer(input);
            lexer.tokenize();

            throw new AssertionError(
                "Era esperada uma exceção para a entrada: " + input
            );
        } catch (AssertionError error) {
            throw error;
        } catch (RuntimeException expected) {
            // A entrada inválida foi rejeitada como esperado.
        }
    }
}