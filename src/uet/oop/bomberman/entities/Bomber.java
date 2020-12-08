package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.Enermy.Balloon;
import uet.oop.bomberman.entities.Tile.Bomb;
import uet.oop.bomberman.entities.input.KeyBoard;
import uet.oop.bomberman.graphics.Level;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Character {
    private KeyBoard keyBoard;
    private int animation, range;
    private  boolean moved;
    private int speed;
    private int flip;
    private Bomb bomb;
    private int bombInHand;
    private boolean died;
    public Bomber(int xOffset, int yOffset, KeyBoard keyBoard, Level level){
        x = xOffset  << 6;
        y = yOffset << 6;
        this.keyBoard = keyBoard;
        this.level = level;
        sprite =Sprite.player_forward_1;
        animation = 0;
        moved = false;
        speed =2;
        flip = -1;
        bombInHand =1;
        died =false;
        alive = true;
        range = 5;
    }
    @Override
    public void update() {
        int x_ =0;
        int y_ =0;
        if(animation < 7500){
            animation++;
        }
        else{
            animation=0;
        }
        if(keyBoard.up) y_ -= speed;
        if(keyBoard.down) y_ +=speed;
        if(keyBoard.left) x_ -=speed;
        if(keyBoard.right) x_ +=speed;
        if(keyBoard.space){
            if(bombInHand>0 ){
                bombInHand--;
                plantBomb();
            }
        }
        if(x_ !=0 && y_ !=0){
            moved = true;
            playerMove(x_, y_);
        }
        else{
            moved = false;
        }
        clear();
    }
    public void plantBomb(){
        bomb = new Bomb(((x + 20) >> 6) << 6, ((y + 20) >> 6) << 6, Sprite.bomb_1, level, range);
        level.addBomb(bomb);
    }
    public void clear(){
        for(int i=0; i<Level.listBomb.size(); i++){
            if(Level.listBomb.get(i).removed){
                Level.listBomb.remove(i);
                bombInHand++;
            }
        }
        for(int i = 0; i < Level.listFlames.size(); i++) {
            if(Level.listFlames.get(i).removed) {
                Level.listFlames.remove(i);
            }
        }
    }
    public void render(Screeen screeen){
        if(index == 0) {
            flip = -1;
            sprite = Sprite.player_back_1;
            if(moved) {
                if(animation % 40 > 35) {
                    if(died) alive = false;
                    sprite = Sprite.player_back_8;
                }else if(animation % 40 > 30) {
                    sprite = Sprite.player_back_7;
                }else if(animation % 40 > 25) {
                    sprite = Sprite.player_back_6;
                }else if(animation % 40 > 20) {
                    sprite = Sprite.player_back_5;
                }else if(animation % 40 > 15) {
                    sprite = Sprite.player_back_4;
                }else if(animation % 40 > 10) {
                    sprite = Sprite.player_back_3;
                }else {
                    sprite = Sprite.player_back_2;
                }
            }
        }else if(index == 1) {
            flip = -1;
            sprite = Sprite.player_side_1;
            if(moved) {
                if(animation % 40 > 35) {
                    if(died) alive = false;
                    sprite = Sprite.player_side_8;
                }else if(animation % 40 > 30) {
                    sprite = Sprite.player_side_7;
                }else if(animation % 40 > 25) {
                    sprite = Sprite.player_side_6;
                }else if(animation % 40 > 20) {
                    sprite = Sprite.player_side_5;
                }else if(animation % 40 > 15) {
                    sprite = Sprite.player_side_4;
                }else if(animation % 40 > 10) {
                    sprite = Sprite.player_side_3;
                }else {
                    sprite = Sprite.player_side_2;
                }
            }
        }else if(index == 2) {
            flip = -1;
            sprite = Sprite.player_forward_1;
            if(moved) {
                if(animation % 40 > 35) {
                    if(died) alive = false;
                    sprite = Sprite.player_forward_8;
                }else if(animation % 40 > 30) {
                    sprite = Sprite.player_forward_7;
                }else if(animation % 40 > 25) {
                    sprite = Sprite.player_forward_6;
                }else if(animation % 40 > 20) {
                    sprite = Sprite.player_forward_5;
                }else if(animation % 40 > 15) {
                    sprite = Sprite.player_forward_4;
                }else if(animation % 40 > 10) {
                    sprite = Sprite.player_forward_3;
                }else {
                    sprite = Sprite.player_forward_2;
                }
            }
        }else {
            flip = 3;
            sprite = Sprite.player_side_1;
            if(moved) {
                if(animation % 40 > 35) {
                    if(died) alive = false;
                    sprite = Sprite.player_side_8;
                }else if(animation % 40 > 30) {
                    sprite = Sprite.player_side_7;
                }else if(animation % 40 > 25) {
                    sprite = Sprite.player_side_6;
                }else if(animation % 40 > 20) {
                    sprite = Sprite.player_side_5;
                }else if(animation % 40 > 15) {
                    sprite = Sprite.player_side_4;
                }else if(animation % 40 > 10) {
                    sprite = Sprite.player_side_3;
                }else {
                    sprite = Sprite.player_side_2;
                }
            }
        }

        screeen.renderPlayer(x >> sprite.SIZE, y >> sprite.SIZE, sprite, flip);
    }
}
