package org.hangman.services;

import org.springframework.stereotype.Service;

@Service
public class PrintMessagesService implements IPrintMessagesService {
    @Override
    public void printMenu() {
        System.out.println("Menú:");
        System.out.println("1.-Juego nuevo\n" +
                           "2.- Salir.");
    }

    @Override
    public void printGame(String word, int trys) {
        System.out.print(word + "  " + trys + " Intentos restantes   ");
    }

    @Override
    public void printFailGame(String correctWorld) {
        System.out.println("Has usado todos tus intentos...");
        System.out.println("La palabra era " + correctWorld);
    }

    @Override
    public void printWinGame() {
        System.out.println("¡¡¡Felicidades, juego completado!!!\n");
    }

    @Override
    public void printSuccessWord() {
        System.out.println("¡Bien hecho!");
        System.out.println("Siguiente palabra:");
    }
}
