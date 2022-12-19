package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class MinesweeperScreen extends JamScreen {
    protected Stage stage;
    private Viewport viewport;
    private enum CellState {
        CLEAR, BOMB, FLAG_BOMB, FLAG_WRONG;
    }
    private MScell[][] grid;
    private Table root;
    private Table sweeperTable;
    private Label labelBombs;
    private Label labelTime;
    private int flags;
    private int clearedCells;
    private int totalCells;
    private static Vector2 temp = new Vector2();
    private Image santaImage;
    private boolean began;
    private float timerTick = 1f;
    protected float time = 400f;
    protected int width = 30;
    protected int height = 16;
    protected int bombs = 99;
    protected int progressIndex = 6;
    protected boolean showTutorial;
    
    @Override
    public void show() {
        super.show();
        
        core.progress = progressIndex;
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        
        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        
        var table = new Table();
        root.add(table).fillX().padBottom(20);
        
        labelBombs = new Label("", skin);
        labelBombs.setColor(Color.RED);
        labelBombs.setText(bombs);
        table.add(labelBombs).expandX().left().uniformX();
        
        santaImage = new Image(skin, "ms-santa");
        table.add(santaImage).expandX().uniformX();
        santaImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sfx_reset.play();
                generateGrid(width, height, bombs);
                resetPresents();
            }
        });
        
        labelTime = new Label("", skin);
        labelTime.setColor(Color.RED);
        table.add(labelTime).expandX().right().uniformX();
        
        root.row();
        sweeperTable = new Table();
        root.add(sweeperTable);
        
        if (showTutorial) {
            root.row();
            var label = new Label("Left Click to reveal a present.\nThe numbers indicate how many bad presents are in the 8 touching squares.\nRight Click to place a flag where you think a bad present is.\nPress Left Click + Right Click (or just Middle Click) to auto reveal presents from a satisfied square.\nWatch out for the timer!", skin);
            label.setColor(Color.BLACK);
            label.setAlignment(Align.center);
            root.add(label).padTop(20);
        }
        
        totalCells = width * height;
        
        generateGrid(width, height, bombs);
    
        resetPresents();
        
        var listener = new InputListener() {
            boolean beginChord;
            boolean invalidate;
            
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                var chordButton = Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.isButtonPressed(Buttons.RIGHT) || Gdx.input.isButtonPressed(Buttons.MIDDLE);
                invalidate = false;
                temp.set(Gdx.input.getX(), Gdx.input.getY());
                stage.getViewport().unproject(temp);
                var actor = stage.hit(temp.x, temp.y, true);
                
                if (chordButton) {
                    beginChord = true;
                    if (actor instanceof Image && actor.getUserObject() != null && actor.getUserObject() instanceof MScell) {
                        var cell = (MScell) actor.getUserObject();
                        highlightChordCells(cell);
                    }
                } else if (Gdx.input.isButtonPressed(Buttons.LEFT) && !Gdx.input.isButtonPressed(Buttons.RIGHT)) {
                    if (actor instanceof Image && actor.getUserObject() != null && actor.getUserObject() instanceof MScell) {
                        var cell = (MScell) actor.getUserObject();
                        highlightCell(cell);
                        if (!cell.cleared) sfx_highlight.play();
                    }
                }
                MinesweeperScreen.this.santaImage.setDrawable(skin, "ms-santa-o");
                return true;
            }
    
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if (invalidate) return;
    
                var chordButton = Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.isButtonPressed(Buttons.RIGHT) || Gdx.input.isButtonPressed(Buttons.MIDDLE);
                
                temp.set(Gdx.input.getX(), Gdx.input.getY());
                stage.getViewport().unproject(temp);
                var actor = stage.hit(temp.x, temp.y, true);
                if (actor instanceof Image && actor.getUserObject() != null && actor.getUserObject() instanceof MScell) {
                    var cell = (MScell) actor.getUserObject();
    
                    if (!began) {
                        while (cell.state == CellState.BOMB) {
                            generateGrid(width, height, bombs);
                            resetPresents();
                            cell = grid[cell.row][cell.column];
                        }
                        began = true;
                    }
        
                    if (!beginChord) {
                        MinesweeperScreen.this.santaImage.setDrawable(skin, "ms-santa");
                        if (button == Buttons.LEFT && !Gdx.input.isButtonPressed(Buttons.RIGHT)) {
                            if (!cell.cleared) sfx_clear.play();
                            clearCell(cell);
                            removeHighlight();
                        }
                        else if (button == Buttons.RIGHT && !Gdx.input.isButtonPressed(Buttons.LEFT))
                            inverseFlagCell(cell);
                        else chordCell(cell);
                    } else if (!chordButton) {
                        removeHighlight();
                        MinesweeperScreen.this.santaImage.setDrawable(skin, "ms-santa");
                        chordCell(cell);
                        beginChord = false;
                        invalidate = true;
                    }
                }
            }
    
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                var chordButton = Gdx.input.isButtonPressed(Buttons.LEFT) && Gdx.input.isButtonPressed(Buttons.RIGHT) || Gdx.input.isButtonPressed(Buttons.MIDDLE);
                
                if (chordButton) {
                    removeHighlight();
    
                    invalidate = false;
                    temp.set(Gdx.input.getX(), Gdx.input.getY());
                    stage.getViewport().unproject(temp);
                    var actor = stage.hit(temp.x, temp.y, true);
                    if (actor instanceof Image && actor.getUserObject() != null && actor.getUserObject() instanceof MScell) {
                        var cell = (MScell) actor.getUserObject();
                        highlightChordCells(cell);
                    }
                } else if (Gdx.input.isButtonPressed(Buttons.LEFT) && !Gdx.input.isButtonPressed(Buttons.RIGHT)) {
                    removeHighlight();
    
                    invalidate = false;
                    temp.set(Gdx.input.getX(), Gdx.input.getY());
                    stage.getViewport().unproject(temp);
                    var actor = stage.hit(temp.x, temp.y, true);
                    if (actor instanceof Image && actor.getUserObject() != null && actor.getUserObject() instanceof MScell) {
                        var cell = (MScell) actor.getUserObject();
                        highlightCell(cell);
                    }
                }
            }
        };
        stage.addListener(listener);
    }
    
    private void generateGrid(int width, int height, int bombs) {
        grid = new MScell[height][width];
        var states = new Array<CellState>();
        
        for (int i = 0; i < width * height - bombs; i++) {
            states.add(CellState.CLEAR);
        }
        for (int i = 0; i < bombs; i++) {
            states.add(CellState.BOMB);
        }
        states.shuffle();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                var cell = new MScell();
                cell.state = states.pop();
                cell.image = new Image();
                cell.image.setScaling(Scaling.none);
                cell.image.setDrawable(skin, "ms-gift-0" + cell.seed);
                grid[i][j] = cell;
                cell.image.setUserObject(cell);
            }
        }
    
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                var cell = grid[row][column];
                cell.row = row;
                cell.column = column;
                
                if (column - 1 >= 0) {
                    if (grid[row][column-1].state == CellState.BOMB) cell.adjacentBombs++;
                    if (row - 1 >= 0 && grid[row-1][column-1].state == CellState.BOMB) cell.adjacentBombs++;
                    if (row + 1 < grid.length && grid[row+1][column-1].state == CellState.BOMB) cell.adjacentBombs++;
                }
                if (column + 1 < grid[row].length) {
                    if (grid[row][column+1].state == CellState.BOMB) cell.adjacentBombs++;
                    if (row - 1 >= 0 && grid[row-1][column+1].state == CellState.BOMB) cell.adjacentBombs++;
                    if (row + 1 < grid.length && grid[row+1][column+1].state == CellState.BOMB) cell.adjacentBombs++;
                }
                if (row - 1 >= 0 && grid[row-1][column].state == CellState.BOMB) cell.adjacentBombs++;
                if (row + 1 < grid.length && grid[row+1][column].state == CellState.BOMB) cell.adjacentBombs++;
            }
        }
    }
    
    private void resetPresents() {
        sweeperTable.clear();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                sweeperTable.add(grid[i][j].image).size(30);
            }
            sweeperTable.row();
        }
    }
    
    private void clearCell(MScell cell) {
        if (cell.cleared) return;
        cell.cleared = true;
        clearedCells++;
        
        if (cell.state == CellState.BOMB) {
            cell.image.setDrawable(skin, "ms-pooh");
            revealBombs();
            Gdx.input.setInputProcessor(null);
            began = false;
            gameOverEvent();
            return;
        } else if (cell.state == CellState.CLEAR) if (cell.adjacentBombs == 0) {
            cell.image.setDrawable(skin, "ms-cell");
        } else {
            cell.image.setDrawable(skin, "ms-" + cell.adjacentBombs);
        }
    
        if (clearedCells >=  totalCells - bombs) {
            labelBombs.setText(0);
            santaImage.setDrawable(skin, "ms-santa-shades");
            began = false;
            Gdx.input.setInputProcessor(null);
            revealFlags();
            winEvent();
            return;
        }
    
        var column = cell.column;
        var row = cell.row;
        
        //clear left 3
        if (column - 1 >= 0) {
            var testCell = grid[row][column - 1];
            if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column - 1];
                if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column - 1];
                if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
            }
        }
        
        //clear right 3
        if (column + 1 < grid[row].length) {
            var testCell = grid[row][column + 1];
            if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column + 1];
                if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column + 1];
                if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
            }
        }
        
        //clear above
        if (row - 1 >= 0) {
            var testCell = grid[row - 1][column];
            if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
        }
        
        //clear below
        if (row + 1 < grid.length) {
            var testCell = grid[row + 1][column];
            if (testCell.state == CellState.CLEAR && (testCell.adjacentBombs == 0 || cell.adjacentBombs == 0)) clearCell(testCell);
        }
    }
    
    private void inverseFlagCell(MScell cell) {
        if (!cell.cleared) {
            if (cell.state == CellState.CLEAR || cell.state == CellState.BOMB) {
                cell.state = cell.state == CellState.BOMB ? CellState.FLAG_BOMB : CellState.FLAG_WRONG;
                cell.image.setDrawable(skin, "ms-flag");
                flags++;
                sfx_flag.play();
            } else {
                cell.state = cell.state == CellState.FLAG_BOMB ? CellState.BOMB : CellState.CLEAR;
                cell.image.setDrawable(skin, "ms-gift-0" + cell.seed);
                flags--;
                sfx_flagRemove.play();
            }
            labelBombs.setText(bombs - flags);
        }
    }
    
    private void chordCell(MScell cell) {
        if (!cell.cleared || cell.adjacentBombs == 0) return;
        
        int foundFlags = 0;
        var row = cell.row;
        var column = cell.column;
    
        //clear left 3
        if (column - 1 >= 0) {
            var testCell = grid[row][column - 1];
            if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column - 1];
                if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column - 1];
                if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
            }
        }
    
        //clear right 3
        if (column + 1 < grid[row].length) {
            var testCell = grid[row][column + 1];
            if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column + 1];
                if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column + 1];
                if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
            }
        }
    
        //clear above
        if (row - 1 >= 0) {
            var testCell = grid[row - 1][column];
            if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
        }
    
        //clear below
        if (row + 1 < grid.length) {
            var testCell = grid[row + 1][column];
            if (testCell.state == CellState.FLAG_BOMB || testCell.state == CellState.FLAG_WRONG) foundFlags++;
        }
        
        if (foundFlags != cell.adjacentBombs) return;
        
        var playSound = false;
        
        //clear left 3
        if (column - 1 >= 0) {
            var testCell = grid[row][column - 1];
            if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                clearCell(testCell);
                playSound = true;
            }
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column - 1];
                if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                    clearCell(testCell);
                    playSound = true;
                }
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column - 1];
                if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                    clearCell(testCell);
                    playSound = true;
                }
            }
        }
    
        //clear right 3
        if (column + 1 < grid[row].length) {
            var testCell = grid[row][column + 1];
            if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                clearCell(testCell);
                playSound = true;
            }
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column + 1];
                if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                    clearCell(testCell);
                    playSound = true;
                }
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column + 1];
                if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                    clearCell(testCell);
                    playSound = true;
                }
            }
        }
    
        //clear above
        if (row - 1 >= 0) {
            var testCell = grid[row - 1][column];
            if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                clearCell(testCell);
                playSound = true;
            }
        }
    
        //clear below
        if (row + 1 < grid.length) {
            var testCell = grid[row + 1][column];
            if (testCell.state == CellState.CLEAR && !testCell.cleared || testCell.state == CellState.BOMB) {
                clearCell(testCell);
                playSound = true;
            }
        }
        
        if (playSound) {
            sfx_chord.play();
        }
    }
    
    private void revealBombs() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                var cell = grid[i][j];
                if (cell.state == CellState.BOMB) cell.image.setDrawable(skin, "ms-pooh");
                else if (cell.state == CellState.FLAG_WRONG) cell.image.setDrawable(skin, "ms-flag-wrong");
            }
        }
    }
    
    private void revealFlags() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                var cell = grid[i][j];
                if (cell.state == CellState.BOMB) cell.image.setDrawable(skin, "ms-flag");
            }
        }
    }
    
    private void highlightChordCells(MScell cell) {
        if (!cell.cleared) return;
        var column = cell.column;
        var row = cell.row;
        var highlighted = false;
        
        //clear left 3
        if (column - 1 >= 0) {
            var testCell = grid[row][column - 1];
            if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) {
                testCell.image.setDrawable(skin, "ms-cell-highlight");
                highlighted = true;
            }
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column - 1];
                if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) {
                    testCell.image.setDrawable(skin, "ms-cell-highlight");
                    highlighted = true;
                }
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column - 1];
                if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) {
                    testCell.image.setDrawable(skin, "ms-cell-highlight");
                    highlighted = true;
                }
            }
        }
    
        //clear right 3
        if (column + 1 < grid[row].length) {
            var testCell = grid[row][column + 1];
            if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) {
                testCell.image.setDrawable(skin, "ms-cell-highlight");
                highlighted = true;
            }
            if (row - 1 >= 0) {
                testCell = grid[row - 1][column + 1];
                if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) {
                    testCell.image.setDrawable(skin, "ms-cell-highlight");
                    highlighted = true;
                }
            }
            if (row + 1 < grid.length) {
                testCell = grid[row + 1][column + 1];
                if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) {
                    testCell.image.setDrawable(skin, "ms-cell-highlight");
                    highlighted = true;
                }
            }
        }
    
        //clear above
        if (row - 1 >= 0) {
            var testCell = grid[row - 1][column];
            if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) testCell.image.setDrawable(skin, "ms-cell-highlight");
        }
    
        //clear below
        if (row + 1 < grid.length) {
            var testCell = grid[row + 1][column];
            if (!testCell.cleared && (testCell.state == CellState.CLEAR || testCell.state == CellState.BOMB)) testCell.image.setDrawable(skin, "ms-cell-highlight");
        }
        
        if (highlighted) {
            sfx_highlight.play();
        }
    }
    
    private void highlightCell(MScell cell) {
        if (!cell.cleared && (cell.state == CellState.CLEAR || cell.state == CellState.BOMB)) cell.image.setDrawable(skin, "ms-cell-highlight");
    }
    
    private void removeHighlight() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                var cell = grid[i][j];
                if (cell.image.getDrawable() == skin.getDrawable("ms-cell-highlight")) cell.image.setDrawable(skin, "ms-gift-0" + cell.seed);
            }
        }
    }
    
    protected void gameOverEvent() {
        sfx_horn.play();
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.gameOverTransition())));
    }
    
    protected void winEvent() {
        sfx_winSound.play();
        stage.addAction(Actions.sequence(Actions.delay(5), Actions.run(() -> core.transition(new ConclusionScreen()))));
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        if (began) {
            time -= delta;
            if (time < 10) {
                timerTick -= delta;
                if (timerTick < 0) {
                    timerTick = 1f;
                    sfx_timerTick.play();
                }
            }
            if (time <= 0) {
                began = false;
                gameOverEvent();
            }
        }
        labelTime.setText(MathUtils.round(time));
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
    
    private static class MScell {
        CellState state;
        int adjacentBombs;
        Image image;
        int row, column;
        boolean cleared;
        int seed = MathUtils.random(6);
    }
}