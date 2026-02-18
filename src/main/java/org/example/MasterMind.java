package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

public class MasterMind {

    String[] colours = {"red","green", "blue", "yellow", "orange", "pink", "black", "white"};
    ArrayList<String> code = new ArrayList<>(); //the list representing the code made by the computer
    Random random = new Random();
    int points = 10; //starts at 10 points and goes down for every wrong answer
    Scanner scanner = new Scanner(System.in);

    public MasterMind() {
        startGame();
    }

    public ArrayList<String> generateCode(){


    for(int i = 0; i<4; i++){
        code.add(colours[random.nextInt(colours.length)]);
    }
        System.out.println(code.toString());
    return code;
}

public void startGame(){
    boolean codeCracked = false;

    System.out.println("Welcome to Mastermind!");
    generateCode();

    System.out.println("Press enter to start game");
    scanner.next();

    while(codeCracked==false){
    ArrayList<String> userGuess = new ArrayList<>();

    validateGuess(userGuess);
    }

}

public void validateGuess(ArrayList<String> userGuess ){
    boolean guessSubmitted = false;
    while(guessSubmitted == false) {
        System.out.println("Enter your guess by pressing the corresponding numbers");
        System.out.println("\n 1. Red" +
                "\n 2. Green" +
                "\n 3. Blue" +
                "\n 4. Yellow" +
                "\n 5. Orange" +
                "\n 6. Pink" +
                "\n 7. Black" +
                "\n 8. White");

        String input = scanner.next();
        List<String> inputSplit = Arrays.asList(input.split(""));

        try {

            for (int i = 0; i < 4; i++) {
                int parsedColourCode = Integer.parseInt(inputSplit.get(i));
                userGuess.add(colours[parsedColourCode-1]);
            }

            guessSubmitted = true;

        } catch (IllegalArgumentException e) {
            System.out.println("\nplease input the number value of your guess");
        }

    }

    testGuess(userGuess);

    System.out.println(userGuess.toString());
}

public void testGuess(ArrayList<String> userGuess ){
        int correctColourAndSpot = 0;
        int correctColourOnly = 0;
        boolean codeIsCorrect = userGuess.equals(code);
    System.out.println(codeIsCorrect);

    if(codeIsCorrect==false){
        points -= 1;
        for(int i = 0; i<4; i++){
            if (code.get(i) == userGuess.get(i)){
                correctColourAndSpot += 1;
            }
            else{
                if(code.contains(userGuess.get(i))){
                    correctColourOnly += 1;
                }
            }
        }
    }
    else{
        System.out.println("You guessed the code!!" +
                "\nYour final score is: " + points +
                "\nThank you for playing");
    }

}

}
