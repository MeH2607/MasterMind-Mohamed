import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

public class MasterMindTerminalTest {

    ArrayList<String> code = new ArrayList<>(
            Arrays.asList("blue", "yellow", "yellow", "red"));




   /* @Test
    void testGuessResponse(){


        ArrayList<Integer>correctIndexes = new ArrayList<>();


        int correctColourAndSpot = 0;
        int correctColourOnly = 0;
        boolean codeIsCorrect = userGuess.equals(code);
        System.out.println(codeIsCorrect);

        if(codeIsCorrect==false){
            for(int i = 0; i<4; i++){
                if (code.get(i) == userGuess.get(i)){
                    correctColourAndSpot += 1;

                }
                else {
                    if(code.contains(userGuess.get(i))){
                        correctColourOnly += 1;
                    }
                }
            }
        }
        assertEquals(0, correctColourOnly);
    }*/

    @Test
    void testAllCorrect(){

        ArrayList<String> userGuess = new ArrayList<>(
                Arrays.asList("blue", "yellow", "yellow", "red"));

        int correctColourAndSpot = 0;
        int correctColourOnly = 0;
        boolean codeIsCorrect = userGuess.equals(code);

        if(codeIsCorrect==false){
            //make a copy of the code that can be manipulated without manipulating the original
            ArrayList<String> copyCode = new ArrayList<>(code);
            //reverse for loop to prevent errors
            for(int i = 3; i>=0; i--){
                //if a guess is in the correct spot and colour, remove it from the copied code
                if (code.get(i).equals(userGuess.get(i))){
                    copyCode.remove(i);
                    userGuess.remove(i);
                    correctColourAndSpot +=1;
                }
            }
            //checks if any of the guesses still remains in the leftover copy of the code.
            for(String s : userGuess){
                if(copyCode.contains(s)){
                    correctColourOnly +=1;
                    copyCode.remove(s);
                }
            }
        }

        assertTrue(codeIsCorrect);
    }

    @Test
    void testHalfCorrectHalfWrongColours(){

        ArrayList<String> userGuess = new ArrayList<>(
                Arrays.asList("blue", "yellow", "white", "green"));

        int correctColourAndSpot = 0;
        int correctColourOnly = 0;
        boolean codeIsCorrect = userGuess.equals(code);

        if(codeIsCorrect==false){
            //make a copy of the code that can be manipulated without manipulating the original
            ArrayList<String> copyCode = new ArrayList<>(code);
            //reverse for loop to prevent errors
            for(int i = 3; i>=0; i--){
                //if a guess is in the correct spot and colour, remove it from the copied code
                if (code.get(i).equals(userGuess.get(i))){
                    copyCode.remove(i);
                    userGuess.remove(i);
                    correctColourAndSpot +=1;
                }
            }
            //checks if any of the guesses still remains in the leftover copy of the code.
            for(String s : userGuess){
                if(copyCode.contains(s)){
                    correctColourOnly +=1;
                    copyCode.remove(s);
                }
            }
        }

        assertEquals(0, correctColourOnly);
        assertEquals(2, correctColourAndSpot);
    }

    @Test
    void testHalfCorrectHalrightColourWrongSpotWithDuplicateColour(){
        //"blue", "yellow", "yellow", "red"
        ArrayList<String> userGuess = new ArrayList<>(
                Arrays.asList("blue", "yellow", "red", "yellow"));

        int correctColourAndSpot = 0;
        int correctColourOnly = 0;
        boolean codeIsCorrect = userGuess.equals(code);

        if(codeIsCorrect==false){
            //make a copy of the code that can be manipulated without manipulating the original
            ArrayList<String> copyCode = new ArrayList<>(code);
            //reverse for loop to prevent errors
            for(int i = 3; i>=0; i--){
                //if a guess is in the correct spot and colour, remove it from the copied code
                if (code.get(i).equals(userGuess.get(i))){
                    copyCode.remove(i);
                    userGuess.remove(i);
                    correctColourAndSpot +=1;
                }
            }
            //checks if any of the guesses still remains in the leftover copy of the code.
            for(String s : userGuess){
                if(copyCode.contains(s)){
                    correctColourOnly +=1;
                    copyCode.remove(s);
                }
            }
        }

        assertEquals(2, correctColourOnly);
        assertEquals(2, correctColourAndSpot);
    }
}
