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
import static com.ray3k.template.Resources.SpineGameOverLeg.*;
import static com.ray3k.template.Resources.*;

public class GameOver2Screen extends JamScreen {
    private Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private ObjectSet<Sound> sounds;
    private SpineDrawable spineDrawable;
    private float pain = -1f;
    private float par;
    private Label label;
    
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
    
        label = new Label("Click to escape", skin);
        label.setColor(Color.BLACK);
        table.add(label);
    
        spineDrawable.getAnimationState().setAnimation(0, animationStand, false);
        
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {
                if (entry.getAnimation() == animationAnimation06) {
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
                if (state.getCurrent(0).getAnimation() == animationStand) {
                    state.setAnimation(0, animationAnimation00, false);
                } else if (state.getCurrent(0).getAnimation() == animationAnimation00) {
                    state.setAnimation(0, animationAnimation01, false);
                } else if (state.getCurrent(0).getAnimation() == animationAnimation01) {
                    state.setAnimation(0, animationAnimation02, true);
                } else if (pain < 1) {
                    label.setText("Keep going! FASTER!");
                    pain += .10f;
                }
                
                return true;
            }
        });
    
//        stage.addAction(Actions.sequence(Actions.delay(6), Actions.run(() -> {
//            bgm_gameoverbreathing.setLooping(true);
//            bgm_gameoverbreathing.play();
//        })));
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
    
        var state = spineDrawable.getAnimationState();
        var current0 = state.getCurrent(0).getAnimation();
        
        if (current0 != animationStand && current0 != animationAnimation00 && current0 != animationAnimation01) {
            pain -= .35f * delta;
            Animation animation = current0;
            var loop = true;
            if (pain < par) {
                pain = par;
            }
            if (pain >= 1f) {
                animation = animationAnimation06;
                loop = false;
                par = 1f;
            } else if (pain >= .75f) {
                animation = animationAnimation05;
                par = .75f;
            } else if (pain >= .5f) {
                animation = animationAnimation04;
                par = .5f;
            } else if (pain >= .25f) {
                animation = animationAnimation03;
                par = .25f;
            } else if (pain > 0f){
                animation = animationAnimation02;
                par = 0;
            }
            if (current0 != animation) state.setAnimation(0, animation, loop);
        }
        
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
