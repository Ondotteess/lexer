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

        if (getCode() == '\n') {
            int codePoint = getCode();
            buff.appendCodePoint(codePoint);
            endToken += Character.charCount(codePoint);
        }

        if (getCode() == '#') {
            readComment(buff);
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

    private String readString() {
        StringBuilder stringBuffer = new StringBuilder();
        int codePoint = getCode();

        // Ensure we start with a quote
        if (codePoint == '"') {
            stringBuffer.appendCodePoint(codePoint);
            endToken += Character.charCount(codePoint);
            codePoint = getCode();

            // Loop through the characters in the string
            while (codePoint != '"' && codePoint != -1) {
                if (codePoint == '\\') {  // Handle escape sequences
                    stringBuffer.appendCodePoint(codePoint);  // Append the backslash
                    endToken += Character.charCount(codePoint);
                    codePoint = getCode();  // Next character after backslash
                    if (codePoint != -1) {
                        stringBuffer.appendCodePoint(codePoint);  // Append the escaped character
                        endToken += Character.charCount(codePoint);
                    }
                } else {
                    stringBuffer.appendCodePoint(codePoint);  // Regular string character
                    endToken += Character.charCount(codePoint);
                }
                codePoint = getCode();
            }

            if (codePoint == '"') {  // Closing quote
                stringBuffer.appendCodePoint(codePoint);
                endToken += Character.charCount(codePoint);
            }
        }
        return stringBuffer.toString();
    }

    private String readTokenSequence() {
        StringBuilder tokenBuffer = new StringBuilder();
        int codePoint = getCode();

        // If the token starts with a quote, read it as a string
        if (codePoint == '"') {
            return readString();
        }

        while (codePoint != ' ' && codePoint != '\t' && codePoint != '\n' && codePoint != '#' && codePoint != -1 && codePoint != '"') {
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
        String tokenSequence = readTokenSequence();

        String trailingTrivial = readTrivial();

        if (tokenSequence.isEmpty() && initialTrivial.isEmpty()) {
            endOfFile = true;
            return null;
        }

        int leadingTriviaLength = initialTrivial.length();
        int trailingTriviaLength = trailingTrivial.length();

        String fullValue = initialTrivial + tokenSequence + trailingTrivial;

        if (!initialTrivial.isEmpty()) {
            return new SequenceInfo(fullValue, startTrivial, endToken, leadingTriviaLength, trailingTriviaLength);
        } else {
            return new SequenceInfo(fullValue, startToken, endToken, leadingTriviaLength, trailingTriviaLength);
        }
    }


    public SequenceInfo readSequence() {
        SequenceInfo sequenceInfo = null;

        if (!endOfFile) {
            sequenceInfo = read();
            if (sequenceInfo != null) {
                System.out.println(sequenceInfo);  //TODO: debug mode
            }
        }
        return sequenceInfo;
    }
}
