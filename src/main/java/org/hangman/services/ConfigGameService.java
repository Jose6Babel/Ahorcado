package org.hangman.services;

import org.hangman.Game;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConfigGameService implements IConfigGameService {

    @Override
    public int menuOption() throws IOException {
        int numMenuChoose = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (numMenuChoose < 1 || numMenuChoose > 2) {
            String printMenu = br.readLine();
            try {
                numMenuChoose = Integer.parseInt(printMenu);
                if (numMenuChoose < 1 || numMenuChoose > 2) {
                    System.err.println("Error, introduzca un numero valido");
                }
            } catch (NumberFormatException error) {
                System.err.println("Error, introduzca un numero valido");
            }
        }
        return numMenuChoose;

    }

    @Override
    public String writeChar() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCharWord = 0;
        String printCharOrWord = br.readLine();
        try {
            numCharWord = Integer.parseInt(printCharOrWord);
            System.err.println("No introduzca números");
        } catch (NumberFormatException error) {
            return printCharOrWord;
        }

        return "";
    }

    @Override
    public boolean checkWord(Game game, String word) {
        boolean isCorrect = false;
        String correctWorld = game.listWords.get(game.numberActualWord);

        // Introduce la palabra en vez de una letra
        if (word.length()>1) {
            if (correctWorld.equalsIgnoreCase(word)) {
                for (int x = 0; x < correctWorld.length(); x++) {
                    game.arrayWordUnsolved[x] = String.valueOf(correctWorld.toUpperCase().charAt(x));
                }
                isCorrect = true;
            }
        } else {
            char charWord = word.charAt(0);

            for (int x = 0; x < correctWorld.length(); x++) {
                if (correctWorld.charAt(x) == charWord ||
                        correctWorld.toLowerCase().charAt(x) == charWord ||
                        correctWorld.toUpperCase().charAt(x) == charWord) {
                    game.arrayWordUnsolved[x] = String.valueOf(correctWorld.toUpperCase().charAt(x));
                    isCorrect = true;
                }
            }
        }

        if (isCorrect) {
            String wordChanged = changeWord(game.arrayWordUnsolved, game.wordUnsolved);
            game.setWordUnsolved(wordChanged);
        }

        return isCorrect;
    }

    @Override
    public boolean isComplete(String wordComplete, String wordOrCharIntroduced) {
        if(wordComplete.equalsIgnoreCase(wordOrCharIntroduced.replaceAll("\\s+",""))) {
            return true;
        }
        return false;
    }

    @Override
    public String changeWord(String[] arrayWord, String word) {
        word = "";
        for (int x = 0; x < arrayWord.length; x++) {
            word += " " + arrayWord[x] + " ";
        }

        return word;
    }


}
