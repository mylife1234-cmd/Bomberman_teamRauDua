package uet.oop.bomberman.entities.Enermy;

import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.graphics.Level;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Character {
    private Random random;
    private int animation, x_, y_, balloonIndex, count, flip;

    public Balloon(int xOffset, int y, Sprite sprite, Level level) {
        x = xOffset << 6;
        this.y = y << 6;
        this.sprite = sprite;
        this.level = level;
        balloonIndex = 0;
        count = 0;
        animation = 0;
        removed = false;
        flip = -1;
        random = new Random();
    }

    public void update() {
        x_ = 0;
        y_ = 0;

        if(animation < 7500) animation++;
        else animation = 0;

        updateIndex();
        RandomIndex();

        for(int i = 0; i < Level.listFlames.size(); i++) {
            if(Level.listFlames.get(i).flameCollision(x + 20 >> 6, y + 10 >> 6)) {
                removed = true;
                break;
            }
        }
    }

    private void RandomIndex() {
        if(x % sprite.SIZE == 0 && y % sprite.SIZE == 0) {
            if(count < 50) count++;
            else count = 0;
        }

        if(collision(x_, y_) || count % 4 == 0) balloonIndex = random.nextInt(4);
        else playerMove(x_, y_);
    }

    public void updateIndex() {
        if(balloonIndex == 0) y_ -= 2;
        else if(balloonIndex == 1) x_ += 2;
        else if(balloonIndex == 2) y_ += 2;
        else x_ -= 2;
    }

    public void render(Screeen screen) {
        if(index == 0) {
            flip = -1;
            if(animation % 60 > 50) {
                sprite = Sprite.creep_back_6;
            }else if(animation % 60 > 40) {
                sprite = Sprite.creep_back_5;
            }else if(animation % 60 > 30) {
                sprite = Sprite.creep_back_4;
            }else if(animation % 60 > 20) {
                sprite = Sprite.creep_back_3;
            }else if(animation % 60 > 10) {
                sprite = Sprite.creep_back_2;
            }else {
                sprite = Sprite.creep_back_1;
            }
        }else if(index == 1) {
            flip = -1;
            if(animation % 60 > 50) {
                sprite = Sprite.creep_side_6;
            }else if(animation % 60 > 40) {
                sprite = Sprite.creep_side_5;
            }else if(animation % 60 > 30) {
                sprite = Sprite.creep_side_4;
            }else if(animation % 60 > 20) {
                sprite = Sprite.creep_side_3;
            }else if(animation % 60 > 10) {
                sprite = Sprite.creep_side_2;
            }else {
                sprite = Sprite.creep_side_1;
            }
        }else if(index == 2) {
            flip = -1;
            if(animation % 60 > 50) {
                sprite = Sprite.creep_forward_6;
            }else if(animation % 60 > 40) {
                sprite = Sprite.creep_forward_5;
            }else if(animation % 60 > 30) {
                sprite = Sprite.creep_forward_4;
            }else if(animation % 60 > 20) {
                sprite = Sprite.creep_forward_3;
            }else if(animation % 60 > 10) {
                sprite = Sprite.creep_forward_2;
            }else {
                sprite = Sprite.creep_forward_1;
            }
        }else {
            flip = 3;
            if(animation % 60 > 50) {
                sprite = Sprite.creep_side_6;
            }else if(animation % 60 > 40) {
                sprite = Sprite.creep_side_5;
            }else if(animation % 60 > 30) {
                sprite = Sprite.creep_side_4;
            }else if(animation % 60 > 20) {
                sprite = Sprite.creep_side_3;
            }else if(animation % 60 > 10) {
                sprite = Sprite.creep_side_2;
            }else {
                sprite = Sprite.creep_side_1;
            }
        }

        screen.renderBalloon(x >> 64, y >> 64, sprite, flip);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
