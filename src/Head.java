import syspro.tm.lexer.Token;

import java.util.ArrayList;

public class Head {

    private final StringBuilder buffer;
    private final String s;
    private int startToken;
    private int endToken;
    private boolean trivial;
    private boolean token;
    private boolean endOfFile;
    public Head(String s) {
        this.s = s;
        this.buffer = new StringBuilder();
        this.startToken = 0;
        this.endToken = 0;
        this.trivial = false;
        this.token = false;
        this.endOfFile = false;
    }

    private int getCode() {
        return 0;
    }

    private int peekCode() {
        return 0;
    }

    private String read(){
        

        return null;
    }

    public ArrayList<Token> readToken(){
        ArrayList<Token> result = new ArrayList<>();

        while (endOfFile != false) {


        }

        return result;
    }


}
