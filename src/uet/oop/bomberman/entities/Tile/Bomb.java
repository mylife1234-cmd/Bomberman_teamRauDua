package uet.oop.bomberman.entities.Tile;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Level;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int anim;
    private int bombCounter;
    private int range;

    public Bomb(int x, int y, Sprite sprite, Level level, int range) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.range = range;
        anim = 0;
        removed = false;
        bombCounter = 0;
        this.level = level;
    }

    public void update() {
        if(anim < 7500) anim++;
        else anim = 0;

        if(bombCounter > 40 || Collision.flameCollision(x, y, level)) {
            Level.listFlames.add(new Flame(x, y, range, Sprite.flame_1, level));
            remove();
        }
    }

    public void remove() {
        removed = true;
    }

    public void render(Screeen screeen) {
        if(anim % 30 > 20) {
            sprite = Sprite.bomb_3;
        }else if(anim % 30 > 10) {
            sprite = Sprite.bomb_2;
        }else {
            sprite = Sprite.bomb_1;
            bombCounter++;
        }

        screeen.renderBomb(x >> 6, y >> 6, sprite);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
