import java.util.List;

public class LexerTest {
    public static void main(String[] args) {
        testKeywords();

        System.out.println("Todos os testes foram executados!");
    }

    // Testa se o lexer reconhece corretamente as palavras-chave da linguagem Jack
    private static void testKeywords() {
        Lexer lexer = new Lexer("class");

        List<Token> tokens = lexer.tokenize();
        
        // Verifica se o lexer reconheceu corretamente a palavra-chave "class"
        if (tokens.size() != 1) {
            throw new AssertionError("Esperado 1 token, mas obteve " + tokens.size());
        }

        Token token = tokens.get(0);

        if (token.getType() != TokenType.KEYWORD) {
            throw new AssertionError("Esperado token do tipo KEYWORD, mas obteve " + token.getType());
        }

        if (!token.getValue().equals("class")) {
            throw new AssertionError("Esperado token com valor 'class', mas obteve " + token.getValue());
        }

        System.out.println("PASSOU: keyword");
    }
}
