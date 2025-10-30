package piglatin;

import java.util.Scanner;

public class PigLatinTranslator {

    public static Book translate(Book input) {
        Book translatedBook = new Book();

        for (int i=0;i<input.getLineCount();i++) {
            translatedBook.appendLine(translate(input.getLine(i)));
        }

        return translatedBook;
    }

    public static String translate(String input) {
        StringBuilder result = new StringBuilder();
        Scanner sc = new Scanner(input);

        while (sc.hasNext()) {
            result.append(translateWord(sc.next()));
            if (sc.hasNext()) result.append(" ");
        }

        sc.close();
        return result.toString();
    }

    private static String translateWord(String input) {
        if (input.length() == 0) return "";

        String vowels = "aeiouAEIOU";

        if (vowels.indexOf(input.charAt(0)) != -1) {
            return input + "ay";
        }

        int firstVowel = -1;
        for (int i = 0; i < input.length(); i++) {
            if (vowels.indexOf(input.charAt(i)) != -1) {
                firstVowel = i;
                break;
            }
        }

        if (firstVowel == -1) return input + "ay";
        
        String start = input.substring(0, firstVowel);
        String end = input.substring(firstVowel);
        return end + start + "ay";
    }
}
