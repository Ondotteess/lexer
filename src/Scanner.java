import syspro.tm.lexer.Token;

import java.util.ArrayList;

public class Scanner {

    private ArrayList<String> splitTrivial(SequenceInfo tokenInfo) {
        ArrayList<String> output = new ArrayList<>();

        String token = tokenInfo.getToken();
        int leadingTriviaLength = tokenInfo.getLeadingTriviaLength();
        int trailingTriviaLength = tokenInfo.getTrailingTriviaLength();

        if (leadingTriviaLength > 0) {
            String leadingTrivia = token.substring(0, leadingTriviaLength);
            output.add(leadingTrivia);
        }

        int tokenStart = leadingTriviaLength;
        int tokenEnd = token.length() - trailingTriviaLength;
        if (tokenEnd > tokenStart) {
            String mainToken = token.substring(tokenStart, tokenEnd);
            output.add(mainToken);
        }

        if (trailingTriviaLength > 0) {
            String trailingTrivia = token.substring(tokenEnd);
            output.add(trailingTrivia);
        }

        return output;
    }

    private boolean isTrivial(int codePoint) {
        return Character.isWhitespace(codePoint) || codePoint == '#' || codePoint == '\n' || codePoint == '\r';
    }

    private void typeTokenParse(String s){
        int length = s.length();
        int i = 0;

        ArrayList<Token> parsedTokens = new ArrayList<>();

        while (i < length) {
            int codePoint = s.codePointAt(i);
            if (isTrivial(codePoint)) {
                //TODO: подумать над дизайном
            }
            else if (Character.isLetter(codePoint) || codePoint == '_') {
                //TODO:
            } else if (Character.isDigit(codePoint)) {
                //TODO:
            } else {

            }


        }
    }

    public ArrayList<Token> scan(SequenceInfo sequenceInfo) {

        ArrayList<Token> output = new ArrayList<>();
        ArrayList<String> splitedTrivialSequence = splitTrivial(sequenceInfo);

        for (String sequence: splitedTrivialSequence) {
            Tokenizer tokenizer = new Tokenizer();
            System.out.println(sequence);
        }

        return output;

    }

}
