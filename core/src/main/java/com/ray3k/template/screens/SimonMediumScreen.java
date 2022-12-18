package com.ray3k.template.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class SimonMediumScreen extends SimonScreen {
    public SimonMediumScreen() {
        countMax = 8;
        progressIndex = 3;
    }
    protected void winEvent() {
        stage.addAction(Actions.sequence(Actions.delay(.5f), Actions.run(() -> sfx_winSound.play())));
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.transition(new MinesweeperMediumScreen()))));
    }
    
    protected void gameOverEvent() {
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.gameOverTransition())));
    }
}
