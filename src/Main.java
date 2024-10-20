import syspro.tm.lexer.*;
import syspro.tm.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static class MyLexer implements Lexer {

        private int position = 0;
        private int currentIndentLevel = 0;

        private static final Set<String> Symbols = EnumSet.allOf(Symbol.class)
                .stream()
                .map(symbol -> symbol.text)
                .collect(Collectors.toSet()); //

        @Override
        public List<Token> lex(String s) {
            ArrayList<Token> a = new ArrayList<>();

            while (position < s.length()) {
                int currentChar = s.codePointAt(position);


                position += currentChar;
            }

            return a;
        }
    }


    public static void main(String[] args) {
        Lexer MyLexer = new MyLexer();
        //Tasks.Lexer.registerSolution(MyLexer);

        ArrayList<Token> result = new ArrayList<>();

        result = (ArrayList<Token>) MyLexer.lex("+ - =");

        for (var token: result) {

            System.out.println(token);
        }
    }

}
