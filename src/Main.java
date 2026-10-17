import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Arquivos que irão ser lidos
        String[] arquivos = {
            "Main",
            "Square",
            "SquareGame"
        };

        try {
            for (String arquivo : arquivos) {
                // Lê o arquivo .jack
                Path filePath = Path.of(
                    "AnalisadorLexico", "test", 
                    "fixtures", arquivo + ".jack"
                );

                // Lê o conteúdo do arquivo .jack
                String jackCode = Files.readString(filePath);

                // Instancia o Scanner e gera a lista de tokens
                Lexer lexer = new Lexer(jackCode);
                List<Token> tokens = lexer.tokenize();

                // Imprime a lista de tokens gerados
                System.out.println("Tokens do arquivo " + arquivo + ".jack:");
                for (Token token : tokens) {
                    System.out.println(token);
                }

                // Instancia o TokenXmlWriter e gera o XML
                TokenXmlWriter xmlWriter = new TokenXmlWriter();
                String xmlOutput = xmlWriter.write(tokens);

                // Cria diretório de saída e escreve o arquivo XML
                Path outputPath = Path.of(
                    "AnalisadorLexico", "test", 
                    "fixtures", arquivo + "TGenerated.xml"
                );

                Files.createDirectories(outputPath.getParent());

                Files.writeString(outputPath, xmlOutput);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler ou salvar arquivo: " + e.getMessage());
        }
    }
}