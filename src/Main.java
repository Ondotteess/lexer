import syspro.tm.Tasks;
import syspro.tm.lexer.*;

import java.util.*;

public class Main {
    private static class MyLexer implements Lexer {

        @Override
        public List<Token> lex(String s) {

            System.out.println(s);
            ArrayList<TokenInfo> a = new ArrayList<>();


            Head head = new Head(s);
            Scanner scanner = new Scanner();

            a = head.getToken();
            scanner.scan(a);


            return null;
        }
    }


    public static void main(String[] args) {
        Lexer MyLexer = new MyLexer();
        //Tasks.Lexer.registerSolution(MyLexer);

        MyLexer.lex("     +ddf\t\t\t\t    -gfh      fdg    одав=#kjlfg\n#fgkld\n  #fdgdf\n#fgn \t\t jifb\n\n\n\n#klfdf\n#bfd fgjj");

    }

}
