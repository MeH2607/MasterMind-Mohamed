package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class MasterMind {

    String[] colours = {"red","green", "blue", "yellow", "orange", "pink", "black", "white"};
    ArrayList<String> code = new ArrayList<>(); //the list representing the code made by the computer
    Random random = new Random();
    int points = 10; //starts at 10 points and goes down for every wrong answer
    Scanner scanner = new Scanner(System.in);



public ArrayList<String> generateCode(){


    for(int i = 0; i<4; i++){
        code.add(colours[random.nextInt(colours.length)]);
    }

    return code;
}

public void startGame(){
    System.out.println("Welcome to Mastermind!");
    generateCode();

    System.out.println("Press enter to start game");
    scanner.next();



}

public void validateAndCheckGuess(){
    ArrayList<String> userGuess = new ArrayList<>();
    System.out.println("Enter your guess by pressing the corresponding numbers");
    System.out.println("\n 1. Red" +
            "\n 2. Green" +
            "\n 3. Blue" +
            "\n 4. Yellow" +
            "\n 5. Orange" +
            "\n 6. Pink" +
            "\n 7. Black" +
            "\n 8. White");

        try{
        String input = scanner.next();

        String[] inputSplit = input.split("(?!^)");

        for(int i = 0; i>inputSplit.length;i++){
            userGuess.add(colours[Integer.parseInt(inputSplit[i])]);
        }

        } catch (IllegalArgumentException  e) {
            System.out.println("please input the number value of your guess");
        }
}
}
