import syspro.tm.lexer.*;

import java.util.*;

public class Main {
    private static class MyLexer implements Lexer {

        @Override
        public List<Token> lex(String s) {

            //System.out.println(s); //TODO: debug mode
            ArrayList<SequenceInfo> a = new ArrayList<>();


            Head head = new Head(s);
            Scanner scanner = new Scanner();

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

        MyLexer.lex("     +ddf\t\t\t\t    -gfh      fdg    одав=#kjlfg\n#fgkld\n  #fdgdf\n#fgn \t\t jifb\n\n\n\n#klfdf\n#bfd fgjj");

    }

}
