# Analisador Léxico Jack — Nand2Tetris

## 1. Integrantes

- **Raylan Bruno Santana Carvalho** — Matrícula: `20260001132`
- **Kauan Guilherme Alves Pinheiro Santos** — Matrícula: `20260001160`

## 2. Linguagem de programação

O projeto foi desenvolvido em **Java**, com o objetivo de implementar um analisador léxico para a linguagem Jack, utilizada no projeto Nand2Tetris.

O analisador léxico recebe um arquivo-fonte `.jack`, identifica suas unidades léxicas e gera uma sequência de tokens.

## 3. Estrutura do projeto

A estrutura relevante do projeto é apresentada a seguir:

```text
AnalisadorLexico/
├── src/
│   ├── Main.java
│   ├── Lexer.java
│   ├── Token.java
│   └── TokenType.java
└── test/
    └── fixtures/
        ├── Main.jack
        ├── MainT.xml
        ├── MainTGenerated.xml
        ├── Square.jack
        ├── SquareT.xml
        ├── SquareTGenerated.xml
        ├── SquareGame.jack
        ├── SquareGameT.xml
        └── SquareGameTGenerated.xml
```

- `Main.java`: ponto de entrada do programa, responsável por executar o analisador léxico e gerar o arquivo de saída.
- `Lexer.java`: responsável por percorrer o código-fonte e identificar os tokens.
- `Token.java`: representa cada token, armazenando seu tipo e valor.
- `TokenType.java`: define as categorias de tokens reconhecidas pelo analisador.
- `Main.jack`: arquivo de entrada utilizado nos testes.
- `MainT.xml`: arquivo oficial de referência disponibilizado pelo projeto Nand2Tetris.
- `MainTGenerated.xml`: arquivo gerado pelo nosso analisador para comparação com o gabarito.
- `Square.jack:` arquivo de entrada do exemplo Square.
- `SquareT.xml:` arquivo XML oficial de referência para o `Square.jack`.
- `SquareTGenerated.xml:` arquivo XML gerado pelo analisador a partir do `Square.jack`.
- `SquareGame.jack:` arquivo de entrada do exemplo SquareGame.
- `SquareGameT.xml:` arquivo XML oficial de referência para o `SquareGame.jack`.
- `SquareGameTGenerated.xml:` arquivo XML gerado pelo analisador a partir do `SquareGame.jack`.

## 4. Requisitos para execução

Para executar o projeto, são necessários:

- Java Development Kit (JDK) instalado.
- Visual Studio Code.

Para verificar a instalação do Java, execute no terminal:

```bash
java -version
javac -version
```
## 5. Como compilar e executar

### 5.1. Pelo Visual Studio Code

1. Abra a pasta do projeto no VS Code.
2. Abra o arquivo `Main.java`.
3. Verifique se o JDK está configurado corretamente.
4. Clique no botão **Run Java** ou **Run** exibido acima do método `main`.
5. Consulte a saída no terminal ou no painel de execução.

O programa lerá o arquivo `Main.jack`, executará o analisador léxico e produzirá a sequência de tokens.

Se o projeto utilizar pacotes Java ou uma estrutura diferente, os comandos deverão ser ajustados conforme a organização do código.

**Importante:** os caminhos utilizados para localizar os arquivos de entrada e de referência devem corresponder à estrutura de diretórios do projeto e ao diretório de execução.

## 6. Estratégia de testes

A estratégia de testes adotada consiste na **geração de uma saída XML pelo analisador léxico e na comparação dessa saída com os arquivos oficiais de referência do Nand2Tetris**, utilizando a ferramenta `diff`.

Essa abordagem permite verificar se os tokens identificados pelo nosso programa correspondem aos tokens esperados para o mesmo arquivo-fonte.

### 6.1. Etapas do teste

1. **Entrada:** o programa lê o arquivo `Main.jack`, localizado no diretório `test/fixtures`.
2. **Análise léxica:** o Lexer percorre o código-fonte e identifica os tokens.
3. **Geração do XML:** os tokens são serializados em um arquivo XML, seguindo a estrutura esperada pelo projeto Nand2Tetris.
4. **Comparação:** o arquivo XML gerado é comparado com o arquivo oficial `MainT.xml`, utilizando `diff`.
5. **Validação:** se não houver diferenças, a saída do analisador é considerada equivalente ao gabarito para aquele caso de teste. Se houver diferenças, elas são examinadas para identificar possíveis erros na classificação ou representação dos tokens.

### 6.2. Formato da saída XML

A saída é organizada dentro de uma tag raiz `<tokens>`, com uma tag específica para cada categoria de token.

Exemplo:

```xml
<tokens>
    <keyword> class </keyword>
    <identifier> Main </identifier>
    <symbol> { </symbol>
    <keyword> function </keyword>
    <keyword> void </keyword>
    <identifier> main </identifier>
    <symbol> ( </symbol>
    <symbol> ) </symbol>
    <symbol> { </symbol>
    <keyword> return </keyword>
    <symbol> ; </symbol>
    <symbol> } </symbol>
    <symbol> } </symbol>
</tokens>
```

As categorias seguem a convenção do Nand2Tetris:

- `keyword`: palavras-chave da linguagem.
- `symbol`: símbolos da linguagem.
- `identifier`: identificadores.
- `integerConstant`: constantes inteiras.
- `stringConstant`: constantes de string.

Os caracteres especiais do XML, como `<`, `>` e `&`, são representados por suas entidades correspondentes quando aparecem no conteúdo dos tokens.

### 6.3. Comparação com `diff`

Após gerar o XML, a comparação pode ser realizada pelo terminal com:

```bash
diff -u test/fixtures/MainT.xml test/fixtures/MainTGenerated.xml
```

O comando acima pressupõe que o terminal esteja na raiz do projeto.

Interpretação do resultado:

- **Nenhuma saída:** os arquivos são idênticos.
- **Saída com diferenças:** o `diff` apresenta as linhas divergentes entre o gabarito e o XML gerado.


