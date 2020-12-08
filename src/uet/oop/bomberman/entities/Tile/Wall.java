package uet.oop.bomberman.entities.Tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends UnmovedTile {

    public Wall(Sprite sprite) {
        super(sprite);
        remove = false;
    }
    public boolean solid(){
        return true;
    }
    public void render(int xOffset, int yOffset, Screeen screeen){
        screeen.renderTile(xOffset <<6,yOffset <<6,this);
    }
    public boolean isRemoved(){
        return remove;
    }
}
