package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SpineDisco.*;

public class CreditsScreen extends JamScreen {
    private Stage stage;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private SpineDrawable spineDrawable;
    
    @Override
    public void show() {
        super.show();
        bgm_disco.setLooping(true);
        bgm_disco.play();
        
        core.progress = 0;
        core.gameOverScreen = 0;
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        
        var root = new Table();
        root.setFillParent(true);
        root.pad(30);
        stage.addActor(root);
        
        spineDrawable = new SpineDrawable(skeletonRenderer, skeletonData, animationData);
        spineDrawable.getAnimationState().setAnimation(0, animationAnimation, true);
        spineDrawable.setCrop(0, 0, 1024,576);
        var image = new Image(spineDrawable);
        image.setScaling(Scaling.fit);
        root.add(image);
        
        root.row();
        var label = new Label("A Raeleus Ray3K joint.\n\nA special thanks to DragonQueen for the sound effects.\n\nCopyright Raymond \"Raeleus\" Buckley © 2022\n\nYou may shut your console off now.\nOr click to play again...", skin);
        label.setWrap(true);
        label.setAlignment(Align.center);
        root.add(label).growX();
        
        stage.addListener(new ClickListener(Input.Buttons.LEFT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(null);
                core.transition(new Chapter1Screen());
            }
        });
    }
    
    @Override
    public void act(float delta) {
        spineDrawable.update(delta);
        stage.act(delta);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void hide() {
        stage.dispose();
        bgm_disco.stop();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void dispose() {
        stage.dispose();
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
}
