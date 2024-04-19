package org.hangman.Components;

import lombok.SneakyThrows;
import org.hangman.Game;
import org.hangman.services.IConfigGameService;
import org.hangman.services.IPrintMessagesService;
import org.springframework.stereotype.Component;

@Component
public class HangmanApp {
    IPrintMessagesService printMessages;
    IConfigGameService configGame;

    public HangmanApp (IPrintMessagesService printMessages, IConfigGameService configGame) {
        this.printMessages = printMessages;
        this.configGame = configGame;
    }

    @SneakyThrows
    public void run() {
        printMessages.printMenu();
        int chooseOptionMenu = configGame.menuOption();

        if (chooseOptionMenu != 2) {
            Game game = new Game();
            String correctWorld = "";

            while (game.numberActualWord < game.listWords.size() && game.trys != 0) {
                correctWorld = game.listWords.get(game.numberActualWord);

                printMessages.printGame(game.getWordUnsolved(), game.trys);
                String wordOrChar = configGame.writeChar();
                try {
                    boolean isCorrect = configGame.checkWord(game, wordOrChar);
                    if (!isCorrect) {
                        game.trys--;
                    }

                    boolean isComplete = configGame.isComplete(correctWorld, game.wordUnsolved);
                    if (isComplete) {
                        printMessages.printGame(game.getWordUnsolved(), game.trys);
                        System.out.println();
                        game.numberActualWord++;
                        if (game.numberActualWord < game.listWords.size()) {
                            printMessages.printSuccessWord();
                            game.loadWord();
                        }
                    }
                } catch (StringIndexOutOfBoundsException error) {}
            }

            if (game.trys == 0) {
                printMessages.printFailGame(correctWorld.toUpperCase());
            } else if (game.numberActualWord == game.listWords.size()) {
                printMessages.printWinGame();
            }
            run();

        }

    }
}
