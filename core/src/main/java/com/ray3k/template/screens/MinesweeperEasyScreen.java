package com.ray3k.template.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class MinesweeperEasyScreen extends MinesweeperScreen {
    public MinesweeperEasyScreen() {
        time = 75f;
        width = 9;
        height = 9;
        bombs = 10;
        progressIndex = 2;
        showTutorial = true;
    }
    
    @Override
    protected void gameOverEvent() {
        sfx_horn.play();
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.gameOverTransition())));
    }
    
    @Override
    protected void winEvent() {
        sfx_winSound.play();
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.transition(new Chapter3Screen()))));
    }
}
