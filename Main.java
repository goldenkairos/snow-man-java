import java.util.Scanner;
public class Main {
    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 'p' to start a game: ");
        String userInput = scanner.nextLine();

        if ("p".equals(userInput)){
        System.out.println(" ");
        SnowMan.StartGame();
        scanner.close();
        }
    }
}
