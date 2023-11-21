import java.util.HashMap;
import java.util.Scanner;

public class SnowMan {
    private static final int SNOWMAN_MIN_WORD_LENGTH = 5;
    private static final int SNOWMAN_MAX_WORD_LENGTH = 8;
    private static final int SNOWMAN_MAX_WRONG_GUESSES = 7;

    private static final String SNOWMAN_1 = "*   *   *  *";
    private static final String SNOWMAN_2 = " *   *_ *   *";
    private static final String SNOWMAN_3 = "*   _[_]_ * ";
    private static final String SNOWMAN_4 = "   * (\")    *";
    private static final String SNOWMAN_5 = "*  \\( : )/ *";
    private static final String SNOWMAN_6 = "  *(_ : _)  *";
    private static final String SNOWMAN_7 = "-----------";

    public static void main(String args[]) {

        printSnowmanGraphic(5);

        HashMap<Character, Boolean> wordMap = buildWordDict("HelloWorld");
        System.out.println(wordMap);

        wordMap.put('l', true);
        wordMap.put('o', true);
        System.out.println(wordMap);

        printWordProgressString("HelloWorld", wordMap);

        boolean res = getWordProgress("HelloWord", wordMap);
        System.out.println(res);

    }

    // This function prints out the appropriate snowman image
    // depending on the number of wrong guesses the player has made.
    public static void printSnowmanGraphic(int numWrongGuesses) {

        StringBuilder graphic = new StringBuilder();
        for (int i = 1; i <= numWrongGuesses; i++) {
            if (i == 1) {
                graphic.append(SNOWMAN_1);
            } else if (i == 2) {
                graphic.append(SNOWMAN_2);
            } else if (i == 3) {
                graphic.append(SNOWMAN_3);
            } else if (i == 4) {
                graphic.append(SNOWMAN_4);
            } else if (i == 5) {
                graphic.append(SNOWMAN_5);
            } else if (i == 6) {
                graphic.append(SNOWMAN_6);
            } else if (i == 7) {
                graphic.append(SNOWMAN_7);
            }
        }
        System.out.println(graphic);
    }

    // This function takes snowmanWord string as input and returns
    // a HashMap with a key-value pair for each letter in
    // snowmanWord where the key is the letter and the value is `false`.
    public static HashMap<Character, Boolean> buildWordDict(String snowmanWord) {
        HashMap<Character, Boolean> snowmanWordMap = new HashMap<>();

        for (char letter : snowmanWord.toCharArray()) {
            snowmanWordMap.put(letter, false);
        }
        return snowmanWordMap;
    }

    // This function takes the snowman_word and snowman_word_dict as input.
    // It prints an output_string that shows the correct letter guess placements
    // as well as the placements for the letters yet to be guessed.
    public static void printWordProgressString(String snowmanWord, HashMap<Character, Boolean> snowmanHashMap) {
        // String progressString = "";
        StringBuilder progressString = new StringBuilder();

        for (char letter : snowmanWord.toCharArray()) {
            if (snowmanHashMap.get(letter)) {
                progressString.append(letter);
            } else {
                progressString.append("_");
            }
        }
        System.out.println(progressString);
    }

    // This function takes the snowman_word and snowman_word_dict as input.
    // It returns True if all the letters of the word have been guessed, and False
    // otherwise.
    public static boolean getWordProgress(String snowmanWord, HashMap<Character, Boolean> snowmanHashMap) {

        for (char letter : snowmanWord.toCharArray()) {
            if (!snowmanHashMap.get(letter)) {
                return false;
            }
        }
        return true;
    }

    // This function takes the snowman_word_dict and the list of characters
    // that have been guessed incorrectly (wrong_guesses_list) as input.
    // It asks for input from the user of a single character until
    // a valid character is provided and then returns this character.
    public static String getLetterfromUser(HashMap<Character, Boolean> snowmanHashMap, char[] wrongGuessArr){

        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        String userInputString ="";

        while (!validInput) {
            System.out.println("Guess a letter: ");
            userInputString = scanner.nextLine();

            if (userInputString.length() > 1){
                System.out.println("You must only enter 1 letter at a time!");
            } else if (userInputString.length() == 1){
            char userInputChar = userInputString.charAt(0);
                if (!Character.isLetter(userInputChar)){
                System.out.println("You must input a letter!");
            } else if (snowmanHashMap.containsKey(userInputChar) && snowmanHashMap.get(userInputChar)){
                System.out.println("You already guesses that letter and it's in the word!");
            } else if (isCharinArr(userInputChar, wrongGuessArr)){
                System.out.println("You already guessed that letter and it's not in the word!");
            } else {
                validInput = true;
            }
        }
    }
        scanner.close();
        return userInputString;
}

    public static boolean isCharinArr(char c, char[] arr) {
        for (char ch : arr) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }
}