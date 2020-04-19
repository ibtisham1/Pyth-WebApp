package stickman.view;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import stickman.model.EntityImpl;
import stickman.model.GameEngine;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class KeyboardInputHandler{
    private final GameEngine model;
    private boolean left = false;
    private boolean right = false;
    private Set<KeyCode> pressedKeys = new HashSet<>();
    private boolean jumpy=false;

    private Map<String, MediaPlayer> sounds = new HashMap<>();

    KeyboardInputHandler(GameEngine model) {
        this.model = model;

        URL mediaUrl = getClass().getResource("/jump.wav");
        String jumpURL = mediaUrl.toExternalForm();

        Media sound = new Media(jumpURL);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        sounds.put("jump", mediaPlayer);
    }

    void handlePressed(KeyEvent keyEvent) {
        if (pressedKeys.contains(keyEvent.getCode())) {
            return;
        }
        pressedKeys.add(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.UP)) {
            jumpy=true;//
            if (model.jump(jumpy)) {//
                MediaPlayer jumpPlayer = sounds.get("jump");
                jumpPlayer.stop();
                jumpPlayer.play();
            }
        }

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {

            left = true;
            model.moveLeft(left);
        }
        else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {

            right = true;
            model.moveRight(right);
        } else {
            return;
        }
    }

    void handleReleased(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = false;
            model.moveLeft(left);
        }
        else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = false;
            model.moveRight(right);
        }
        else if(keyEvent.getCode().equals(KeyCode.UP)) {
            jumpy=false;
            model.jump(jumpy);


        }
        else {
            return;
        }

    }
}
