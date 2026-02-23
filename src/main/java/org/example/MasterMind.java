package org.example;


import java.util.*;

public class MasterMind {

    //The colours used for the game
    String[] colours = {"red","green", "blue", "yellow", "orange", "pink", "black", "white"};
    //the list representing the code the computer will generate
    ArrayList<String> code = new ArrayList<>(); //the list representing the code made by the computer
    Random random = new Random();
    int points = 10; //starts at 10 points and goes down for every wrong answer
    Scanner scanner = new Scanner(System.in);

    public MasterMind() {
        startGame();
    }

    public void generateCode(){
        //on new game, ensure the code is cleared
        code.clear();

    for(int i = 0; i<4; i++){
        code.add(colours[random.nextInt(colours.length)]);
    }
        //for debugging purposes
        //System.out.println(code.toString());
}

public void startGame(){
    //checks if the code has been cracked
    boolean codeCracked = false;

    //preparing the list representing the users guess
    ArrayList<String> userGuess = new ArrayList<>();

    System.out.println("Welcome to Mastermind!");


    System.out.println("Press enter to start game");
    scanner.nextLine();

    generateCode();

    //While loop to let you try again after failed attempts. Stops when you crack the code or reach zero points
    while(codeCracked==false || points != 0){
    userGuess.clear();

    validateGuessInput(userGuess);
    }

    if(points == 0){
        askForReplay();
    }

}

//method to recieve the players input and validate that it's an acceptable answer
public void validateGuessInput(ArrayList<String> userGuess ){
    boolean guessSubmitted = false;

    while(guessSubmitted == false) {
        System.out.println("Enter your guess by pressing the corresponding numbers");
     /*   System.out.println("\n 1. Red" +
                "\t 2. Green" +
                "\t 3. Blue" +
                "\t 4. Yellow" +
                "\n 5. Orange" +
                "\t 6. Pink" +
                "\t 7. Black" +
                "\t 8. White"); //ToDo make into printf and make prettier*/

System.out.printf("%-15s%-15s%-15s%-15s%n%-15s%-15s%-15s%-15s%n", 
    "1. Red", "2. Green", "3. Blue", "4. Yellow", 
    "5. Orange", "6. Pink", "7. Black", "8. White");

        String input = scanner.next();
        List<String> inputSplit = Arrays.asList(input.split(""));

        try {
            //ensure that the player inputs a 4 colour code
            if(inputSplit.size() == 4) {
                for (int i = 0; i < 4; i++) {
                    int parsedColourCode = Integer.parseInt(inputSplit.get(i));
                    userGuess.add(colours[parsedColourCode - 1]);
                }
                guessSubmitted = true;
            }
            else{
                System.out.println("Please write a 4 colour code");
            }



        } catch (IllegalArgumentException e) {
            System.out.println("\nplease input the number value of your guess");
        }

    }

    testGuess(userGuess);


}

public void testGuess(ArrayList<String> userGuess ){
        int correctColourAndSpot = 0;
        int correctColourOnly = 0;
        boolean codeIsCorrect = userGuess.equals(code);


    if(codeIsCorrect==false){
        System.out.println("Your guess: \n" + userGuess.toString() + "\nwas incorrect" );
        points -= 1;
        //make a copy of the code that can be manipulated without manipulating the original
        ArrayList<String> copyCode = new ArrayList<>(code);
        //reverse for loop to prevent errors
        for(int i = 3; i>=0; i--){
            //if a guess is in the correct spot and colour, remove it from the copied code
            if (code.get(i) == userGuess.get(i)){
                correctColourAndSpot += 1;
                copyCode.remove(i);
            }
        }
        //checks if any of the guesses still remains in the leftover copy of the code.
        for(String s : userGuess) {
            if (copyCode.contains(s)) {
                correctColourOnly += 1;
                copyCode.remove(s);
            }
        }

        System.out.println("Your guess included:\n"
                + correctColourAndSpot + " correct colour and the correct spot\n" +
                correctColourOnly + " correct colour in a wrong sport ");
    }
    else{
        System.out.println("You guessed the code!!" +
                "\nYour final score is: " + points +
                "\nThank you for playing");
                askForReplay();


    }

}

public void endGameScreen(){
    System.out.println("Do you want to play agan?" +
            "\n1. yes" +
            "\n2. No");

    int input = scanner.nextInt();
    boolean optionSelected = false;
    while(optionSelected == false)

        switch(input){
            case 1:
                optionSelected = true;
                startGame();
                break;
            case 2:
                optionSelected = true;
                System.out.println("Thank you for playing Mastermind :D");
                System.exit(0);
                break;
            default:
                System.out.println("please choose one of the options above");
                break;
        }
}

}
