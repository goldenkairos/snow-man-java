# Snowman Game

Welcome to the **Snowman Game**! This simple Java console-based game challenges you to guess a secret word before the snowman is fully built. Be careful, as you only have a limited number of wrong guesses before it's game over.

## How to Play

1. The game will randomly select a secret word for you to guess.
2. You can input a letter as your guess.
3. If the letter is correct, it will be revealed in the secret word.
4. If the letter is incorrect, a part of the snowman will be built.
5. Continue guessing until you either complete the word or make too many wrong guesses.

## Snowman Graphics

The snowman graphics are displayed based on the number of wrong guesses. Here's what each part of the snowman represents:

1. `*   *   *  *`
2. ` *   *_ *   *`
3. `*   _[_]_ *`
4. `   * (")    *`
5. `*  \( : )/ *`
6. `  *(_ : _)  *`
7. `--------------`

## Running the Game

To play the Snowman Game, run the `main` method in the `SnowMan` class. You'll be prompted to guess letters and see the snowman being built.

## Customizing the Words

The game uses a file named `words.txt` to load words. You can customize this file with your own list of words, each on a new line.

Feel free to explore and enjoy the Snowman Game! If you have any questions or feedback, please let us know.
