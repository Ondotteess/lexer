import syspro.tm.lexer.*;

import java.util.*;

public class Main {
    private static class MyLexer implements Lexer {

        private int position = 0;
        private int currentIndentLevel = 0;

        private void scan(StringBuilder sb, int start, int end){
            if (!sb.isEmpty()) {
                Scanner.scanToken(sb, start, end);      // TODO: убрать копировалки
                sb.setLength(0);
            }
        }


        private void trivialWhitespaces(String s, StringBuilder sb, int currentChar) { // TODO: переименовать
            int start = position;
            while (position < s.length() && (s.charAt(position) == ' ' || s.charAt(position) == '\t')) {
                sb.append(s.charAt(position));
                position += Character.charCount(currentChar);
            }
            scan(sb, start, position);  // TODO: тут входная строка может начинаться с пробелов табуляций
        }


        private void newLine(String s, StringBuilder sb, int currentChar){
            position += Character.charCount(currentChar);

            while (position < s.length()) {
                currentChar = s.codePointAt(position);
                if (currentChar == ' ' || currentChar == '\t') {
                    sb.appendCodePoint(currentChar);
                    position += Character.charCount(currentChar);
                } else {
                    break;      // TODO: тут посмотреть вперед, если впереди НЕ пробел и табуляция - свернуть токен
                }
            }
            scan(sb, 1, 1);
        }


        private void comment(String s, StringBuilder sb, int currentChar) {
            sb.appendCodePoint(currentChar);
            position += Character.charCount(currentChar); //TODO: ошибка ли пробелы потом комментарий? ДА

            while (position < s.length()) {
                currentChar = s.codePointAt(position);
                if (currentChar == '\n') {
                    sb.appendCodePoint(currentChar);
                    scan(sb, 1, 1);
                    break;
                } else {
                    sb.appendCodePoint(currentChar);
                    position += Character.charCount(currentChar);
                }
            }
            scan(sb, 1, 1);
        }

        @Override
        public List<Token> lex(String s) {
            ArrayList<Token> a = new ArrayList<>();

            StringBuilder sb = new StringBuilder();

            /*
             *   Встретили пробел -> добавляем все пробелы и табуляции прямо в токен
             *   Перенос и дальше пробелы/табуляции-> индентационный токен
             *   Решётку -> добавляем в токен всё до переноса
             */

            while (position < s.length()) {                     // TODO: сохранять стартовую и конечную позицию
                int currentChar = s.codePointAt(position);

                if (currentChar == ' ' || currentChar == '\t') {
                    trivialWhitespaces(s, sb, currentChar);
                } else if (currentChar == '\n') {
                    newLine(s, sb, currentChar);
                } else if (currentChar == '#') {
                    comment(s, sb, currentChar);
                } else {
                    sb.appendCodePoint(currentChar);            // TODO: для остальных поумнее
                    position += Character.charCount(currentChar);
                }
            }

            scan(sb, 1, 1); //TODO: костыль

            return a;
        }
    }


    public static void main(String[] args) {
        Lexer MyLexer = new MyLexer();
        //Tasks.Lexer.registerSolution(MyLexer);

        ArrayList<Token> result = new ArrayList<>();

        result = (ArrayList<Token>) MyLexer.lex("+ddf\t\t\t\t    -gfh      fdg    одав=#kjlfg sdgk;l");

        for (var token: result) {

                System.out.println(token);
            }
        System.out.println("lol");
    }

}
