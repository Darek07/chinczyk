package com.example.chinczyk;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Dice {
    Random random = new Random();

    int roll(ImageView diceImage) {

        diceImage.setDisable(true);
        int dice_random=(random.nextInt(6)+1);
        diceImage.setImage(new Image(getClass().getResourceAsStream("dice" + dice_random +".png")));
        diceImage.setDisable(false);
        return dice_random;
    }
}