package com.ray3k.template.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class SimonHardScreen extends SimonScreen {
    public SimonHardScreen() {
        countMax = 12;
    }
    protected void winEvent() {
        stage.addAction(Actions.sequence(Actions.delay(.5f), Actions.run(() -> sfx_winSound.play())));
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.transition(new SplashScreen()))));
    }
    
    protected void gameOverEvent() {
        stage.addAction(Actions.sequence(Actions.delay(7), Actions.run(() -> core.transition(new SplashScreen()))));
    }
}
