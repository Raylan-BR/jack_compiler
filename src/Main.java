import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        // Verifica se o usuário informou um arquivo
        if (args.length != 1) {
            System.err.println(
                "Uso: java Main <arquivo.jack>"
            );
            System.exit(1);
        }

        // Obtém o caminho informado na linha de comando
        Path filePath = Path.of(args[0]);

        // Verifica se a extensão é .jack
        if (!filePath.getFileName().toString()
                .toLowerCase()
                .endsWith(".jack")) {
            System.err.println(
                "Erro: o arquivo informado deve ter extensão .jack."
            );
            System.exit(1);
        }

        // Verifica se o arquivo existe e é um arquivo regular
        if (!Files.isRegularFile(filePath)) {
            System.err.println(
                "Erro: arquivo não encontrado ou inválido: " + filePath
            );
            System.exit(1);
        }

        try {
            // Lê o conteúdo do arquivo Jack
            String jackCode = Files.readString(filePath);

            // Executa o analisador léxico
            Lexer lexer = new Lexer(jackCode);
            List<Token> tokens = lexer.tokenize();

            // Exibe os tokens
            System.out.println(
                "Tokens do arquivo " + filePath.getFileName() + ":"
            );

            for (Token token : tokens) {
                System.out.println(token);
            }

            // Gera o XML dos tokens
            TokenXmlWriter xmlWriter = new TokenXmlWriter();
            String xmlOutput = xmlWriter.write(tokens);
            
            // Define o caminho do XML gerado em test/actual
            String inputName = filePath.getFileName().toString();

            String outputName = inputName.substring(
                0, inputName.length() - ".jack".length()
            ) + "T.xml";

            Path outputPath = Path.of("test", "actual", outputName);

            // Cria o diretório de saída, caso não exista
            Files.createDirectories(outputPath.getParent());

            // Salva o XML gerado
            Files.writeString(outputPath, xmlOutput);

            System.out.println(
                "XML gerado com sucesso: " + outputPath
            );

        } catch (IOException e) {
            System.err.println(
                "Erro ao ler ou salvar arquivo: " + e.getMessage()
            );
            System.exit(1);
        } catch (RuntimeException e) {
            System.err.println(
                "Erro durante a análise léxica: " + e.getMessage()
            );
            System.exit(1);
        }
    }
}