package com.example.chinczyk;

import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Set;

public class SimpleBot {

	private static ImageView diceIm;
	private static Set<Pawn> pawns;
	private static AnimationTimer at;

	public static void init(ImageView dice, Set<Pawn> pawns) {
		diceIm = dice;
		SimpleBot.pawns = pawns;
		at = new AnimationTimer() {
			long lastTime = 0;
			@Override
			public void handle(long now) {
				if (lastTime == 0) {
					lastTime = now;
					return;
				}
				final double elapsedMicroSeconds = (now - lastTime) / 1_000.0 ;
				if (elapsedMicroSeconds >= 500000) {
					pressAllPawns();
					lastTime = now;
				} else if (elapsedMicroSeconds >= 100000) {
					pressDice();
				}
			}
		};
		at.start();
	}

	public static void stop() {
		at.stop();
	}

	public static void pressAllPawns() {
		pawns.forEach(pawn -> {
			Scene scene = pawn.getScene();
			if (scene == null) return;
			Stage screen = (Stage) scene.getWindow();
			Event.fireEvent(pawn, new MouseEvent(MouseEvent.MOUSE_PRESSED,
					scene.getX(), scene.getY(), screen.getX(), screen.getY(), MouseButton.PRIMARY, 1,
					true, true, true, true, true, true, true, true, true, true, null));
		});
	}

	public static void pressDice() {
		Scene scene = diceIm.getScene();
		if (scene == null) return;
		Stage screen = (Stage) scene.getWindow();
		Event.fireEvent(diceIm, new MouseEvent(MouseEvent.MOUSE_PRESSED,
				scene.getX(), scene.getY(), screen.getX(), screen.getY(), MouseButton.PRIMARY, 1,
				true, true, true, true, true, true, true, true, true, true, null));
	}
}
