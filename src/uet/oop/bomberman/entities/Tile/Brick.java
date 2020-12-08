package uet.oop.bomberman.entities.Tile;

import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends UnmovedTile{
    public Brick(Sprite sprite) {
        super(sprite);
        remove = false;
    }
    public boolean solid(){
        return true;
    }
    public void remove(){
        remove = true;
    }

    public boolean destroy() {
        return true;
    }

    public void render(int xOffset, int yOffset, Screeen screeen) {
        screeen.renderTile(xOffset <<6,yOffset<<6,this);
    }
}
