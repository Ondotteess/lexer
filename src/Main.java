import syspro.tm.lexer.*;

import java.util.*;

public class Main {
    private static class MyLexer implements Lexer {

        @Override
        public List<Token> lex(String s) {

            //System.out.println(s); //TODO: debug mode
            ArrayList<SequenceInfo> a = new ArrayList<>();


            Head head = new Head(s);
            TokenScanner scanner = new TokenScanner();

            SequenceInfo sequenceInfo = head.readSequence();

            while (sequenceInfo != null) {
                scanner.scan(sequenceInfo);
                sequenceInfo = head.readSequence();
            }


            return null;
        }
    }


    public static void main(String[] args) {
        Lexer MyLexer = new MyLexer();
        //Tasks.Lexer.registerSolution(MyLexer);

        //MyLexer.lex("     +ddf\t\t\t\t    -gfh      fdg    одав=#kjlfg\n#fgkld\n  #fdgdf\n#fgn \t\t jifb\n\n\n\n#klfdf\n#bfd fgjj\n \"ds gf\"  \"dfjkl \t\t sdjklg\"");

        String[] testCases = {
                "token1 token2 token3",
                "\"This is a string with a newline\\ncharacter\" tokenAfterString",
                "\"Unicode example: \\U+0041\"",
                "token1 # this is a comment\ntoken2",
                "\"String with \\\"embedded quotes\\\" inside\"",
                "# This is a comment\n    \t   # Another comment\n",
                "token1 \"string \\\"with escapes\\\"\" # comment\n token2\t"
        };
        // TODO: руны не считываются за токен
        for (String test : testCases) {
            Head head = new Head(test);
            System.out.println("Test case: " + test);
            SequenceInfo seqInfo;
            while ((seqInfo = head.readSequence()) != null) {
                //System.out.println(seqInfo);
            }
            System.out.println("--- End of test ---\n");
        }

    }

}
