import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            // Lê o arquivo Main.jack
            Path filePath = Path.of(
                "AnalisadorLexico", "test", 
                "fixtures", "Main.jack"
        );

            String jackCode = Files.readString(filePath);

            // Instancia o Lexer e gera a lista de tokens
            Lexer lexer = new Lexer(jackCode);
            List<Token> tokens = lexer.tokenize();

            // Imprime a lista de tokens gerados

            for (Token token : tokens) {
                System.out.println(token);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo Main.jack:");
        }
    }
}