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
            String next=sc.next();
            boolean onlypunctuation=true;
            for (char a : next.toCharArray())
            {
                if (Character.isLetter(a))
                {
                    onlypunctuation=false;
                    break;
                }
            }
            if (onlypunctuation) {
                result.append(next);
            }
            else {
                result.append(translateWord(next));
            }
            if (sc.hasNext()) result.append(" ");
                
        }

        sc.close();
        return result.toString();
    }

    private static String translateWord(String input) {
        String thirdPart="ay";
        if (input.length() == 0) return "";
        if (input.charAt(input.length()-1)=='.'||input.charAt(input.length()-1)==','||input.charAt(input.length()-1)=='!'||input.charAt(input.length()-1)=='?'||input.charAt(input.length()-1)==';'||input.charAt(input.length()-1)==':'||input.charAt(input.length()-1)=='\'') {
            thirdPart+=input.substring(input.length()-1);
            input=input.substring(0, input.length()-1);
        }
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(input.charAt(0)) != -1) {
            return input + thirdPart;
        }

        int firstVowel = -1;
        for (int i = 0; i < input.length(); i++) {
            if (vowels.indexOf(input.charAt(i)) != -1) {
                firstVowel = i;
                break;
            }
        }
        boolean firstlettercapped=false;
        if (Character.isUpperCase(input.charAt(0)))
        {
            //the first letter is capped
            firstlettercapped=true;
        }
        if (firstVowel == -1) return input + thirdPart;
        String start = input.substring(0, firstVowel);
        String end = input.substring(firstVowel);
        if (firstlettercapped)
        {
            start=start.substring(0,1).toLowerCase()+start.substring(1);
        }
        String result=end + start +thirdPart;
        if (firstlettercapped)
        {
            result=result.substring(0,1).toUpperCase()+result.substring(1);
        }
        return result;
    }
}
