package com.example.chinczyk;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Kostka {
    Random random = new Random();

    void roll(ImageView diceImage) {

        diceImage.setDisable(true);

        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
                try {
                    for (int i = 0; i < 15; i++) {
                        diceImage.setImage(new Image(getClass().getResourceAsStream("dice" + (random.nextInt(6)+1)+".png")));
                        Thread.sleep(50);
                    }
                    diceImage.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
