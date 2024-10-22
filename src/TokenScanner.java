import syspro.tm.lexer.Token;

import java.util.ArrayList;

public class TokenScanner {

    private String sequence;
    private int currentPosition;
    private StringBuilder buffer;


    public TokenScanner() {
        this.sequence = null;
        this.currentPosition = -1;
        this.buffer = new StringBuilder();
    }

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


    /*
    * Tokens:
    *   BAD - всё, что не подошло никуда
    *   KEY - попадает в множество
    *   Symbols - всё что попадает в множество символов + проверить след символ
    *   INDENT - все тривия + дополнительная обработка
    *   IDENTIFIER - начало - '_' | [\p{Nd}\p{Pc}\p{Mn}\p{Mc}\p{Cf}]
    *         - добавлять пока удовлетворяет рег выражению
    *
    *   literals:
    *       BOOLEAN - 'true' | 'false'
    *       INTEGER - [0-9]+ SUFFIX? - для этого парсится неправильно
    *                                - чинить головку
    *       RUNE    - '\' RUNE_CHAR '\'
    *       STRING  - '"' '"' - отдельный обработчик
    *
    *
    *
    */


    private Token nextToken(){
        int length = sequence.length();
        int i = 0;

        TokenCreator tc = null;

        while (i < length) {
            int codePoint = sequence.codePointAt(i);
            buffer.appendCodePoint(codePoint);

            if (isTrivial(codePoint)) {
                tc = new
            }



        }

        return tc.create(this);
    }

    public ArrayList<Token> scan(SequenceInfo sequenceInfo) {

        ArrayList<Token> output = new ArrayList<>();
        ArrayList<String> splitedTrivialSequence = splitTrivial(sequenceInfo);

        for (String sequence: splitedTrivialSequence) {

            this.sequence = sequence;
            this.currentPosition = 0;

            Token token = nextToken();
            while (token != null) {
                if (token != null) {
                    output.add(token);
                }
            }
        }

        return output;

    }

}
