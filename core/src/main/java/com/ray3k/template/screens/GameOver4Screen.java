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
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.utils.SkeletonDrawable;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineGameOverEye.*;
import static com.ray3k.template.Resources.*;

public class GameOver4Screen extends JamScreen {
    private Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private ObjectSet<Sound> sounds;
    private SpineDrawable spineDrawable;
    private Label label;
    
    @Override
    public void show() {
        super.show();
        
        spineDrawables = new Array<>();
        sounds = new ObjectSet<>();
    
        spineDrawable = new SpineDrawable(skeletonRenderer, skeletonData, animationData);
        spineDrawable.getAnimationState().apply(spineDrawable.getSkeleton());
        spineDrawable.setCrop(0, 0, 1024, 576);
        spineDrawables.add(spineDrawable);
        animationData.setDefaultMix(.25f);
        
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
    
        label = new Label("Click and all will be forgiven", skin);
        label.setColor(Color.BLACK);
        table.add(label);
    
        spineDrawable.getAnimationState().setAnimation(0, animationEyeMovement, true);
        spineDrawable.getAnimationState().setAnimation(1, animationNeedle00, false);
        
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {
                if (entry.getAnimation() == animationNeedle04) {
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
                if (state.getCurrent(1).getAnimation() == animationNeedle00) {
                    state.setAnimation(1, animationNeedle01, false);
                } else if (state.getCurrent(1).getAnimation() == animationNeedle01) {
                    state.setAnimation(1, animationNeedle02, false);
                } else if (state.getCurrent(1).getAnimation() == animationNeedle02) {
                    state.setAnimation(1, animationNeedle03, false);
                } else if (state.getCurrent(1).getAnimation() == animationNeedle03) {
                    state.setAnimation(1, animationNeedle04, false);
                    state.getCurrent(0).setTimeScale(0);
                }
                
                return true;
            }
        });
    
//        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> {
//            bgm_gameoverbreathing.setLooping(true);
//            bgm_gameoverbreathing.play();
//        })));
        bgm_gameover4.play();
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
        bgm_gameoverbreathing.stop();
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
