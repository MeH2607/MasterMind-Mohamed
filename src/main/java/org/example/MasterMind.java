package org.example;


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
    code.clear();

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
    scanner.nextLine();

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
                "\t 2. Green" +
                "\t 3. Blue" +
                "\t 4. Yellow" +
                "\n 5. Orange" +
                "\t 6. Pink" +
                "\t 7. Black" +
                "\t 8. White");

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
        //make a copy of the code that can be manipulated without manipulating the original
        ArrayList<String> copyCode = new ArrayList<>(code);
        //reverse for loop to prevent errors
        for(int i = 3; i>=0; i--){
            //if a guess is in the correct spot and colour, remove it from the copied code
            if (code.get(i) == userGuess.get(i)){
                copyCode.remove(i);
            }
        }
        //checks if any of the guesses still remains in the leftover copy of the code.
        for(String s : userGuess){
            if(copyCode.contains(s)){
                correctColourOnly +=1;
            }
        }
        correctColourAndSpot = code.size()-copyCode.size();
    }
    else{
        System.out.println("You guessed the code!!" +
                "\nYour final score is: " + points +
                "\nThank you for playing");

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

}
