package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.utils.SkeletonDrawable;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineGameOverNoose.*;
import static com.ray3k.template.Resources.*;

public class GameOver6Screen extends JamScreen {
    private Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private ObjectSet<Sound> sounds;
    private SpineDrawable spineDrawable;
    
    @Override
    public void show() {
        super.show();
        
        spineDrawables = new Array<>();
        sounds = new ObjectSet<>();
    
        spineDrawable = new SpineDrawable(skeletonRenderer, skeletonData, animationData);
        spineDrawable.getAnimationState().apply(spineDrawable.getSkeleton());
        animationData.setDefaultMix(.25f);
        spineDrawable.setCrop(0, 0, 1024, 576);
        spineDrawables.add(spineDrawable);
        
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
    
        Image image = new Image(spineDrawable);
        image.setScaling(Scaling.fit);
        image.setFillParent(true);
        stage.addActor(image);
        
        Table table = new Table();
        table.setFillParent(true);
        table.top().pad(20);
        stage.addActor(table);
        
        var label = new Label("Click to release your friend", skin);
        table.add(label);
    
        spineDrawable.getAnimationState().setAnimation(0, animationWiggle, true);
        
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {
                if (entry.getAnimation() == animationDeath) {
                    core.continueTransition();
                }
            }
            
            @Override
            public void event(AnimationState.TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null && !event.getData().getAudioPath().equals("")) {
                    Music sound = assetManager.get(event.getData().getAudioPath());
                    sound.play();
                }
            }
        });
        
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                var state = spineDrawable.getAnimationState();
                if (state.getCurrent(0).getAnimation() != animationDeath) {
                    state.setAnimation(0, animationDeath, false);
                    bgm_gameovercry.stop();
                }
                
                return true;
            }
        });
    
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> {
            bgm_gameovercry.setLooping(true);
            bgm_gameovercry.play();
        })));
        bgm_gameover3.play();
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        
        for (SkeletonDrawable skeletonDrawable : spineDrawables) {
            skeletonDrawable.update(delta);
        }
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void hide() {
        super.hide();
        for (Sound sound : sounds) {
            sound.stop();
        }
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    @Override
    public void dispose() {
    
    }
}
