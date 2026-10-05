import java.util.Set;

public final class JackSymbols {

    private JackSymbols() {
    }

    public static final Set<Character> SYMBOLS = Set.of(
            '{',
            '}',
            '(',
            ')',
            '.',
            ',',
            ';',
            '+',
            '-',
            '*',
            '/',
            '&',
            '|',
            '<',
            '>',
            '=',
            '~'
    );

    public static boolean isSymbol(char c) {
        return SYMBOLS.contains(c);
    }
}