package org.hangman;


import org.hangman.Components.HangmanApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HangmanRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        HangmanApp hangmanApp = context.getBean(HangmanApp.class);

        hangmanApp.run();
    }
}