package uet.oop.bomberman.entities.Tile;

import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public class UnmovedTile {
    public Sprite sprite;
    protected boolean remove;
    public static UnmovedTile wall = new Wall(Sprite.border_sprite);
    public static UnmovedTile brick = new Brick(Sprite.brick_sprite);
    public static UnmovedTile grass = new Grass(Sprite.floor_sprite);
    public UnmovedTile(Sprite sprite){
        this.sprite = sprite;
    }
    public void render(int xOffset, int yOffset, Screeen screeen){

    }
    public boolean solid(){
        return false;
    }
    public boolean destroy(){
        return false;
    }
    public void remove(){

    }
    public boolean isRemoved(){
        return remove;
    }
}
