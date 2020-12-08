package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Level;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    private int count, animation, range;
    private int up =0, down=0, left=0, right=0;
    private int rightIndex = -1, leftIndex = -1, downIndex = -1, upIndex = -1;
    public Flame(int xOffset, int yOffset, int range, Sprite sprite, Level level){
        x = xOffset;
        y = yOffset;
        this.sprite = sprite;
        this.level = level;
        count = 0;
        removed = false;
        this.range = range;
    }

    @Override
    public void update() {
        count ++;
        if(animation<7500){
            animation++;
        }
        else{
            animation=0;
        }
    }
    public void remove(){
        removed = true;
    }
    public void render(Screeen screeen){
        if(animation % 25 > 20) {
            sprite = Sprite.flame_5;
        }else if(animation % 25 > 15) {
            sprite = Sprite.flame_4;
        }else if(animation % 25 > 10) {
            sprite = Sprite.flame_3;
        }else if(animation % 25 > 5) {
            sprite = Sprite.flame_2;
        }else {
            sprite = Sprite.flame_1;
        }

        for(int i = 1; i <= range; i++) {
            if(rightIndex == -1) {
                if(level.getUnmoveTile((x >> 6) + i, y >> 6).solid()) rightIndex = i - 1;
                if(level.getUnmoveTile((x >> 6) + i, y >> 6).destroy()) right = i;
            }
            if(leftIndex == -1) {
                if(level.getUnmoveTile((x >> 6) - i, y >> 6).solid()) leftIndex = i - 1;
                if(level.getUnmoveTile((x >> 6) - i, y >> 6).destroy()) left = i;
            }
            if(downIndex == -1) {
                if(level.getUnmoveTile(x >> 6, (y >> 6) + i).solid()) downIndex = i - 1;
                if(level.getUnmoveTile(x >> 6, (y >> 6) + i).destroy()) down = i;
            }
            if(upIndex == -1) {
                if(level.getUnmoveTile(x >> 6, (y >> 6) - i).solid()) upIndex = i - 1;
                if(level.getUnmoveTile(x >> 6, (y >> 6) - i).destroy()) up = i;
            }

            if(i == range) {
                if(rightIndex == -1) rightIndex = i;
                if(leftIndex == -1) leftIndex = i;
                if(downIndex == -1) downIndex = i;
                if(upIndex == -1) upIndex = i;
            }
        }

        for(int i = 1; i <= range; i++) {
            screeen.renderFlame(x >> 6, y >> 6, sprite);

            if(rightIndex >= i || right >= i) screeen.renderFlame((x >> 6) + i, y >> 6, sprite);
            if(leftIndex >= i || left >= i) screeen.renderFlame((x >> 6) - i, y >> 6, sprite);
            if(downIndex >= i || down >= i) screeen.renderFlame(x >> 6, (y >> 6) + i, sprite);
            if(upIndex >= i || up >= i) screeen.renderFlame(x >> 6, (y >> 6) - i, sprite);

            if(count == 25) {
                remove();
                if(right == i) level.addBrick((x >> 6) + i, y >> 6);
                if(left == i) level.addBrick((x >> 6) - i, y >> 6);
                if(down == i) level.addBrick(x >> 6, (y >> 6) + i);
                if(up == i) level.addBrick(x >> 6, (y >> 6) - i);
            }

        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRange() {
        return range;
    }

    public boolean flameCollision(int x, int y) {
        for(int i = 1; i < range; i++) {
            if(x >= this.x >> 6 && x <= this.x >> 6 && y >= this.y >> 6 && y <= this.y >> 6) {
                return true;
            }
            if(rightIndex >= i || right >= i) {
                if(x >= (this.x >> 6) + i && x <= (this.x >> 6) + i && y >= this.y >> 6 && y <= this.y >> 6) {
                    return true;
                }
            }
            if(leftIndex >= i || left >= i) {
                if(x >= (this.x >> 6) - i && x <= (this.x >> 6) - i && y >= this.y >> 6 && y <= this.y >> 6) {
                    return true;
                }
            }
            if(downIndex >= i || down >= i) {
                if(x >= this.x >> 6 && x <= this.x >> 6 && y >= (this.y >> 6) + i && y <= (this.y >> 6) + i) {
                    return true;
                }
            }
            if(upIndex >= i || up >= i) {
                if(x >= this.x >> 6 && x <= this.x >> 6 && y >= (this.y >> 6) - i && y <= (this.y >> 6) - i) {
                    return true;
                }
            }
        }

        return false;
    }
}
