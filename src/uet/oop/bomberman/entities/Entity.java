package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Level;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Sprite sprite;
    protected Level level;
    public boolean removed;
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas

    public void render(Screeen screeen) {

    }
    public abstract void update();
    public void remove(){

    }

    public Sprite getSprite() {
        return sprite;
    }
}
