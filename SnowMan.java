import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
// import org.apache.commons.lang3.RandomStringUtils;
//https://replit.com/@ViktoriiaZoloto/viktoriiasnowmanprojectC18#game.py

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
    private static final String SNOWMAN_7 = "--------------";

    private static final String WORDS_FILE_PATH = "words.txt";
    private static List<String> WORDS;

    static {
        WORDS = loadWordsFromFile();
    }

    public static void main(String args[]) {

        // printSnowmanGraphic(5);

        // HashMap<Character, Boolean> wordMap = buildWordDict("HelloWorld");
        // System.out.println(wordMap);

        // wordMap.put('l', true);
        // wordMap.put('o', true);
        // System.out.println(wordMap);

        // printWordProgressString("HelloWorld", wordMap);

        // boolean res = getWordProgress("HelloWord", wordMap);
        // System.out.println(res);

        // ArrayList<Character> wrongGuessList = new ArrayList<>(Arrays.asList('b', 'c', 'a'));

        // String uInput = getLetterfromUser(wordMap, wrongGuessList);

        // String Word =
        // snowmanWordGenerator(SNOWMAN_MIN_WORD_LENGTH,SNOWMAN_MAX_WORD_LENGTH);

        // System.out.println(Word);

        Scanner scanner = new Scanner(System.in);
        String snowManWord = getRandomWord(SNOWMAN_MIN_WORD_LENGTH, SNOWMAN_MAX_WORD_LENGTH);
        // System.out.println("Secret Word: " + snowManWord);

        snowMan(snowManWord);
        scanner.close();
    }

    public static void snowMan(String snowManWord) {
        
        HashMap<Character, Boolean> snowManWordMap = buildWordDict(snowManWord);
        ArrayList<Character> wrongGuessList = new ArrayList<>();
        boolean continueGuess = true;

        while (wrongGuessList.size() <= SNOWMAN_MAX_WRONG_GUESSES && continueGuess){
            Character userInput = getLetterfromUser(snowManWordMap, wrongGuessList);

            if (snowManWordMap.containsKey(userInput)){
                snowManWordMap.put(userInput,true);
                // System.out.println("You guessed a letter that's in the world!");
            } else {
                wrongGuessList.add(userInput);
                System.out.println("The letter is not in the word");
            }
            printSnowmanGraphic(wrongGuessList.size());
            printWordProgressString(snowManWord, snowManWordMap);
            printWrongGuessList(wrongGuessList);

            if (getWordProgress(snowManWord,snowManWordMap)){
                System.out.println("Congratulations, you win!");
                continueGuess = false;
            } else if (wrongGuessList.size() == SNOWMAN_MAX_WRONG_GUESSES){
                System.out.println("Sorry, you lose! The word is "+ snowManWord);
                continueGuess = false;
            }

        }
    }
    // This method is designed to load words from words.txt
    private static List<String> loadWordsFromFile() {
        List<String> words = new ArrayList<>();

        // starts a try-with-resources block. It creates a BufferedReader to efficiently
        // read the contents of a file specified by the WORDS_FILE_PATH.
        // The try-with-resources ensures that the BufferedReader is closed properly
        // after use.
        try (BufferedReader reader = new BufferedReader(new FileReader(WORDS_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }

            // This block catches any IOException that might occur during the file reading
            // process.
            // If an exception occurs, it prints the stack trace to the console using
            // e.printStackTrace().
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static String getRandomWord(int minLen, int maxLen) {

        List<String> filteredWordList = WORDS.stream()
                .filter(word -> word.length() >= minLen && word.length() <= maxLen).collect(Collectors.toList());

        Random random = new Random();
        int randomIndex = random.nextInt(filteredWordList.size());
        return filteredWordList.get(randomIndex);
    }

    // This function prints out the appropriate snowman image
    // depending on the number of wrong guesses the player has made.
    public static void printSnowmanGraphic(int numWrongGuesses) {

        StringBuilder graphic = new StringBuilder();
        for (int i = 1; i <= numWrongGuesses; i++) {
            if (i == 1) {
                // graphic.append(SNOWMAN_1);
                System.out.println(SNOWMAN_1);
            } else if (i == 2) {
                System.out.println(SNOWMAN_2);
            } else if (i == 3) {
                System.out.println(SNOWMAN_3);
            } else if (i == 4) {
                System.out.println(SNOWMAN_4);
            } else if (i == 5) {
                System.out.println(SNOWMAN_5);
            } else if (i == 6) {
                System.out.println(SNOWMAN_6);
            } else if (i == 7) {
                System.out.println(SNOWMAN_7);
            }
        }
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
        // String progressString = ""; //string is immutable
        StringBuilder progressString = new StringBuilder();

        for (char letter : snowmanWord.toCharArray()) {
            if (snowmanHashMap.get(letter)) {
                progressString.append(letter + " ");
            } else {
                progressString.append("_ ");
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
    public static Character getLetterfromUser(HashMap<Character, Boolean> snowmanHashMap,
            ArrayList<Character> wrongGuessArr) {

        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);
        String userInputString = "";

        while (!validInput) {
            System.out.println("Guess a letter: ");
            userInputString = scanner.nextLine();

            if (userInputString.length() > 1) {
                System.out.println("You must only enter 1 letter at a time!");
            } else if (userInputString.length() == 1) {
                char userInputChar = Character.toLowerCase(userInputString.charAt(0));
                if (!Character.isLetter(userInputChar)) {
                    System.out.println("You must input a letter!");
                } else if (snowmanHashMap.containsKey(userInputChar) && snowmanHashMap.get(userInputChar)) {
                    System.out.println("You already guessed that letter and it's in the word!");
                } else if (isCharinArr(userInputChar, wrongGuessArr)) {
                    System.out.println("You already guessed that letter and it's not in the word!");
                } else {
                    validInput = true;
                }
            }
        }
        return Character.toLowerCase(userInputString.charAt(0));
    }

    public static boolean isCharinArr(char c, ArrayList<Character> arr) {
        for (char ch : arr) {
            if (Character.toLowerCase(c) == Character.toLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }

    public static void printWrongGuessList(ArrayList<Character> wrongGuessList){
        String guesses = "";
        System.out.println("Wrong guesses: ");

        for (char guess: wrongGuessList){
            guesses+=guess + " ";
        }
        System.err.println(guesses);
    }
}
