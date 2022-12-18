package com.ray3k.template.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class MinesweeperMediumScreen extends MinesweeperScreen {
    public MinesweeperMediumScreen() {
        time = 200f;
        width = 16;
        height = 16;
        bombs = 40;
    }
    
    @Override
    protected void gameOverEvent() {
        sfx_horn.play();
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.transition(new SplashScreen()))));
    }
    
    @Override
    protected void winEvent() {
        sfx_winSound.play();
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.transition(new SplashScreen()))));
    }
}
