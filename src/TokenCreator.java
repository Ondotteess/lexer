import syspro.tm.lexer.Token;

public interface TokenCreator {
    Token create(TokenScanner scanner);
}
