package org.hangman.services;

public interface IPrintMessagesService {
    public void printMenu();
    public void printGame(String word, int trys);
    public void printFailGame(String correctWord);
    public void printWinGame();
    public void printSuccessWord();

}
