package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Tile.UnmovedTile;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;

public class Screeen {
    public int width;
    public int height;
    public int[] pixels;

    public Screeen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void renderTile(int xp, int yp, UnmovedTile tile) {
        for(int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for(int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0) xa = 0;
                int col = tile.sprite._pixels[x + y * tile.sprite.SIZE];
                pixels[xa + ya * width] = col;
            }
        }
    }


    public void renderPlayer(int xOffset, int yOffset, Sprite sprite, int flip) {
        for(int y = 0; y < sprite.SIZE; y++) {
            int ya = yOffset + y;
            int ys = y;
            if(flip == 0 || flip == 2) ys = (sprite.SIZE - 1) - ys;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = xOffset + x;
                int xs = x;
                if(flip == 1 || flip == 3) xs = (sprite.SIZE - 1) - xs;
                if(x < -sprite.SIZE || x >= width || y < 0 || y >= height) break;
                if(x < 0) x = 0;
                int col = sprite._pixels[xs + ys * sprite.SIZE];
                if(col == 0xFF870087) continue;
                pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderBomb(int xOffset, int yOffset, Sprite sprite) {
        xOffset = xOffset << 6;
        yOffset = yOffset << 6;

        for(int y = 0; y < sprite.SIZE; y++) {
            int ya = yOffset + y;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = xOffset + x;
                int col = sprite._pixels[x + y * sprite.SIZE];
                if(col == 0xFF870087) continue;
                pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderFlame(int xp, int yp, Sprite sprite) {
        xp = xp << 6;
        yp = yp << 6;

        for(int y = 0; y < sprite.SIZE; y++) {
            int ya = yp + y;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = xp + x;
                int col = sprite._pixels[x + y * sprite.SIZE];
                if(col == 0xFF870087) continue;
                pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderBalloon(int xp, int yp, Sprite sprite, int flip) {
        for(int y = 0; y < sprite.SIZE; y++) {
            int ya = yp + y;
            int ys = y;
            if(flip == 0 || flip == 2) ys = (sprite.SIZE - 1) - ys;
            for(int x = 0; x < sprite.SIZE; x++) {
                int xa = xp + x;
                int xs = x;
                if(flip == 1 || flip == 3) xs = (sprite.SIZE - 1) - xs;
                if(x < -sprite.SIZE || x >= width || y < 0 || y >= height) break;
                if(x < 0) x = 0;
                int col = sprite._pixels[xs + ys * sprite.SIZE];
                if(col == 0xFF870087) continue;
                pixels[xa + ya * width] = col;
            }
        }
    }
}
