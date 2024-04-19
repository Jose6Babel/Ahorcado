package org.hangman;

import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Game {
    public List<String> listWords;
    public String[] arrayWordUnsolved;
    public String wordUnsolved;
    public int trys;
    public String charOrWordIntroduced;
    public int numberActualWord;

    public Game() {
        loadListWords();
        this.trys = 8;
        numberActualWord = 0;
        Collections.shuffle(listWords);
        loadWord();

    }

    public void loadWord() {
        wordUnsolved = "";
        arrayWordUnsolved = new String[listWords.get(numberActualWord).length()];
        for (int x = 0; x < listWords.get(numberActualWord).length(); x++) {
            arrayWordUnsolved[x] = "_";
            wordUnsolved += " _ ";
        }
    }

    public void loadListWords() {
        listWords = new ArrayList<>();
        getWordsByFile("words", listWords);
    }

    public void getWordsByFile(String nameFile, List lista) {
        try {
            BufferedReader reader = null;

            try {
                File file = new File("src/main/resources/" + nameFile + ".properties");
                reader = new BufferedReader(new FileReader(file));

                String actualLine = null;
                while ((actualLine = reader.readLine()) != null) {
                    String[] splited = actualLine.split(":");
                    lista.add(splited[1]);
                }

            } catch (IOException e) {
                System.err.println("El archivo no existe");
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        catch(Exception e) {
            e.getStackTrace();
        }

    }

}
