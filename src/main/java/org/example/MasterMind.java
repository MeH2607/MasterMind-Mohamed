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


    //preparing the list representing the users guess
    ArrayList<String> userGuess = new ArrayList<>();

    System.out.println("Welcome to Mastermind!");


    System.out.println("Press enter to start game");
    scanner.nextLine();

    generateCode();

    //While loop to let you try again after failed attempts.
    while(true){

    //ensure the players guess resets between tries
    userGuess.clear();

    validateGuessInput(userGuess);
    }


}

//method to recieve the players input and validate that it's an acceptable answer
public void validateGuessInput(ArrayList<String> userGuess ){
    boolean guessSubmitted = false;

    while(!guessSubmitted) {
        System.out.println("Enter your guess by pressing the corresponding numbers");

        //Left adjust all the selections and make sure they are equally spaced
        System.out.printf(
            "%-15s%-15s%-15s%-15s%n%-15s%-15s%-15s%-15s%n",
            "1. Red", "2. Green", "3. Blue", "4. Yellow",
            "5. Orange", "6. Pink", "7. Black", "8. White");

        //takes the player  inputs and splits it to 4 elements
        String input = scanner.next();
        List<String> inputSplit = Arrays.asList(input.split(""));

        //try block ensures the player inputs numbers and not text or any other illegal input
        try {
            //ensure that the player inputs a 4 colour code
            if(inputSplit.size() == 4) {
                for (int i = 0; i < 4; i++) {
                    //the player input comes in as a string, parse it to int and find the corresponding colour with the number
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

    //the logic for handling wrong guesses
    if(codeIsCorrect==false){
        points -= 1;
        System.out.println("\nYour guess: \n" + userGuess + "\nwas incorrect. You have " + points + " points" );

        //if the player has 0 points, the game ends
        if(points == 0){
            endGameScreen(points);
        }


        //make a copy of the code that can be manipulated without manipulating the original
        ArrayList<String> copyCode = new ArrayList<>(code);
        //reverse for loop to prevent errors
        for(int i = 3; i>=0; i--){
            //if a guess is in the correct spot and colour, ie index i on both lists are the same, remove it from the copied code
            //the colour is removed to avoid situation where a correct guess would count for both correctColourAndSpot and correctColourOnly at the same time
            if (code.get(i) == userGuess.get(i)){
                correctColourAndSpot += 1;
                copyCode.remove(i);
            }
        }
        //checks if any of the guesses still remains in the leftover copy of the code. if it is, remove it from the copied code
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
                //ends the game if the player has guessed right
                endGameScreen(points);


    }

}

//method handles wether the player gets a win or lose screen and whether they play another game.
public void endGameScreen(int points){

        //win screen if they have more than 0 points, lose screen if they have less
        if(points>0){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "You guessed the code!!" +
                    "\nYour final score is: " + points +
                    "\nThank you for playing");
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "You have spent all your guesses" +
            "\nYou could not crack the code" +
            "\nThe code was " + code.toString() +
            "\nThank you for playing");

        }
    System.out.println("Do you want to play agan?" +
            "\n1. yes" +
            "\n2. No");


    boolean optionSelected = false;
    while(!optionSelected) {

    int input = scanner.nextInt();
        switch (input) {
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

}
