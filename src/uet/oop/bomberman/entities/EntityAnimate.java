package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class EntityAnimate extends Entity {

    protected int animation=0;
    protected final int MAX_ANIMATION =100;
    public EntityAnimate(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public void animate(){
        if(animation < MAX_ANIMATION){
            animation++;
        }
        else{
            animation=0;
        }
    }
}
