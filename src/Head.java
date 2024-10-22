public class Head {

    private final String s;
    private int startToken;
    private int endToken;
    private boolean endOfFile;

    public Head(String s) {
        this.s = s;
        this.startToken = 0;
        this.endToken = 0;
        this.endOfFile = false;
    }

    private int getCode() {
        if (endToken < s.length()) {
            return s.codePointAt(endToken);
        }
        return -1;
    }

    public boolean isEndOfFile() {
        return endOfFile;
    }

    private void readComment(StringBuilder buff) {
        while (getCode() != '\n' && getCode() != -1) {
            int codePoint = getCode();
            buff.appendCodePoint(codePoint);
            endToken += Character.charCount(codePoint);
        }

        // Если это конец строки, добавляем перенос строки
        if (getCode() == '\n') {
            int codePoint = getCode();
            buff.appendCodePoint(codePoint);
            endToken += Character.charCount(codePoint);
        }

        // После прочтения комментария продолжаем искать другие тривии
        if (getCode() == '#') {
            readComment(buff);  // Читаем следующий комментарий
        }
    }

    private String readTrivial() {
        StringBuilder trivialBuffer = new StringBuilder();
        int codePoint = getCode();

        while (codePoint == ' ' || codePoint == '\t' || codePoint == '\n' || codePoint == '#') {
            if (codePoint == '#') {
                readComment(trivialBuffer);  // Читаем комментарий как тривию
            } else {
                trivialBuffer.appendCodePoint(codePoint);  // Читаем обычные тривии (пробелы, переносы)
                endToken += Character.charCount(codePoint);
            }
            codePoint = getCode();  // Читаем следующий символ
        }
        return trivialBuffer.toString();
    }

    private String readToken() {
        StringBuilder tokenBuffer = new StringBuilder();
        int codePoint = getCode();

        while (codePoint != ' ' && codePoint != '\t' && codePoint != '\n' && codePoint != '#' && codePoint != -1) {
            tokenBuffer.appendCodePoint(codePoint);
            endToken += Character.charCount(codePoint);
            codePoint = getCode();
        }
        return tokenBuffer.toString();
    }

    private SequenceInfo read() {
        int startTrivial = endToken;
        String initialTrivial = readTrivial();

        int startToken = endToken;
        String token = readToken();

        String trailingTrivial = readTrivial();

        if (token.isEmpty() && initialTrivial.isEmpty()) {
            endOfFile = true;
            return null;
        }

        int leadingTriviaLength = initialTrivial.length();
        int trailingTriviaLength = trailingTrivial.length();

        String fullValue = initialTrivial + token + trailingTrivial;

        if (!initialTrivial.isEmpty()) {
            return new SequenceInfo(fullValue, startTrivial, endToken, leadingTriviaLength, trailingTriviaLength);
        } else {
            return new SequenceInfo(fullValue, startToken, endToken, leadingTriviaLength, trailingTriviaLength);
        }
    }


    public SequenceInfo readSequence() {
        SequenceInfo tokenInfo = null;

        if (!endOfFile) {
            tokenInfo = read();
            if (tokenInfo != null) {
                System.out.println(tokenInfo);  //TODO: debug mode
            }
        }
        return tokenInfo;
    }
}
