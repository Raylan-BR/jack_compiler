import java.util.List;

public class LexerTest {

    public static void main(String[] args) {
        testKeywords();
        testIdentifiers();
        testSymbols();
        testIntegerConstants();
        testStringConstants();
        testInvalidInteger();
        testUnterminatedString();

        System.out.println("\nTodos os testes do commit 2 foram executados!");
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