package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;
import com.esotericsoftware.spine.utils.SkeletonDrawable;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineSimon.*;

public class SimonScreen extends JamScreen {
    private Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    private ObjectSet<Sound> sounds;
    private SpineDrawable spineDrawable;
    private enum Command {
        UP, DOWN, LEFT, RIGHT
    }
    private final Array<Command> cpuCommands = new Array<>();
    private final Array<Command> levelCommands = new Array<>();
    private final Array<Command> playerCommands = new Array<>();
    private enum Mode {
        CPU, PLAYER
    }
    private Mode mode = Mode.CPU;
    private Polygon boxRight;
    private Polygon boxLeft;
    private Polygon boxTop;
    private Polygon boxBottom;
    private Image image;
    private boolean failed;
    private int counter = 1;
    
    @Override
    public void show() {
        super.show();
        
        spineDrawables = new Array<>();
        sounds = new ObjectSet<>();
    
        spineDrawable = new SpineDrawable(skeletonRenderer, skeletonData, animationData);
        spineDrawable.getAnimationState().apply(spineDrawable.getSkeleton());
        spineDrawable.setCrop(0, 0, 1024, 576);
        spineDrawables.add(spineDrawable);
    
        var box = (BoundingBoxAttachment) spineDrawable.getSkeleton().findSlot("bbox-right").getAttachment();
        boxRight = new Polygon(box.getVertices());
        box = (BoundingBoxAttachment) spineDrawable.getSkeleton().findSlot("bbox-left").getAttachment();
        boxLeft = new Polygon(box.getVertices());
        box = (BoundingBoxAttachment) spineDrawable.getSkeleton().findSlot("bbox-top").getAttachment();
        boxTop = new Polygon(box.getVertices());
        box = (BoundingBoxAttachment) spineDrawable.getSkeleton().findSlot("bbox-bottom").getAttachment();
        boxBottom = new Polygon(box.getVertices());
        
        stage = new Stage(new FitViewport(1024, 576), batch);
        Gdx.input.setInputProcessor(stage);
    
        image = new Image(spineDrawable);
        image.setScaling(Scaling.none);
        image.setFillParent(true);
        stage.addActor(image);
    
        spineDrawable.getAnimationState().setAnimation(0, animationIntro, false);
        spineDrawable.getAnimationState().addAnimation(0, animationStand, false, 0);
        
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {

            }
            
            @Override
            public void event(AnimationState.TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null && !event.getData().getAudioPath().equals("")) {
                    Sound sound = assetManager.get(event.getData().getAudioPath());
                    sound.play();
                    sounds.add(sound);
                }
            }
        });
        
        generateCommand();
    }
    
    private final static Vector3 temp = new Vector3();
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        
        var state = spineDrawable.getAnimationState();
        var current = state.getCurrent(0).getAnimation();
        if (mode == Mode.CPU) {
            if (current == animationStand) {
                if (cpuCommands.size > 0) {
                    switch (cpuCommands.first()) {
                        case UP:
                            state.setAnimation(0, animationTop, false);
                            break;
                        case DOWN:
                            state.setAnimation(0, animationBottom, false);
                            break;
                        case LEFT:
                            state.setAnimation(0, animationLeft, false);
                            break;
                        case RIGHT:
                            state.setAnimation(0, animationRight, false);
                            break;
                    }
                    state.addAnimation(0, animationStand, false, 0);
                    cpuCommands.removeIndex(0);
                } else {
                    mode = Mode.PLAYER;
                }
            }
        } else if (!failed && current == animationStand && levelCommands.size > 0) {
            temp.x = Gdx.input.getX();
            temp.y = Gdx.input.getY();
            stage.getCamera().unproject(temp);
    
            if (!failed && Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
                if (boxRight.contains(temp.x, temp.y)) playerCommands.add(Command.RIGHT);
                if (boxLeft.contains(temp.x, temp.y)) playerCommands.add(Command.LEFT);
                if (boxTop.contains(temp.x, temp.y)) playerCommands.add(Command.UP);
                if (boxBottom.contains(temp.x, temp.y)) playerCommands.add(Command.DOWN);
            }
    
            if (playerCommands.size > 0) {
                if (playerCommands.first() != levelCommands.first()) {
                    Resources.sfx_horn.play();
                    failed = true;
                    stage.addAction(Actions.sequence(Actions.delay(7), Actions.run(() -> core.transition(new SplashScreen()))));
                } else {
                    cpuCommands.add(levelCommands.first());
                    levelCommands.removeIndex(0);
                    switch (playerCommands.first()) {
                        case UP:
                            state.setAnimation(0, animationTopQuick, false);
                            break;
                        case DOWN:
                            state.setAnimation(0, animationBottomQuick, false);
                            break;
                        case LEFT:
                            state.setAnimation(0, animationLeftQuick, false);
                            break;
                        case RIGHT:
                            state.setAnimation(0, animationRightQuick, false);
                            break;
                    }
                    state.addAnimation(0, animationStand, false, levelCommands.size > 0 ? 0 : 1.5f);
                    playerCommands.removeIndex(0);
                    
                    if (levelCommands.size == 0) {
                        counter++;
                        generateCommand();
                        mode = Mode.CPU;
                    }
                }
            }
        }
        
        for (SkeletonDrawable skeletonDrawable : spineDrawables) {
            skeletonDrawable.update(delta);
        }
    }
    
    private void generateCommand() {
        cpuCommands.add(Command.values()[MathUtils.random(3)]);
        levelCommands.clear();
        levelCommands.addAll(cpuCommands);
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
