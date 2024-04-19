package org.hangman.services;

import org.hangman.Game;

import java.io.IOException;

public interface IConfigGameService {

    public int menuOption() throws IOException;
    public String writeChar() throws IOException;
    public boolean checkWord(Game game, String word);
    public boolean isComplete(String wordComplete, String wordOrCharIntroduced);
    public String changeWord(String[] arrayWord, String word);

}