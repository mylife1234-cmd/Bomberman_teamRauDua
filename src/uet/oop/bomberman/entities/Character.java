package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Character extends Entity{
    protected int index=2;
    public boolean alive;
    public static int STEP = 5;
    @Override
    public void update() {

    }
    public void playerMove(int xOffset, int yOffset){
        if(xOffset!=0 && yOffset!=0){
            playerMove(xOffset,0);
            playerMove(0,yOffset);
        }
        if(xOffset > 0) index=1;
        if(xOffset < 0) index=3;
        if(yOffset > 0) index=2;
        if(yOffset < 0) index=0;
    }
    public boolean collision(int xOffset, int yOffset){
        boolean solid_ =false;
        int x1=0,y1=0;
        int x2=0,y2=0;
        int x_ =x + STEP;
        int y_ =y + STEP;
        if(index==0){
            x1 = (x_ + xOffset) >>6;
            y1 = (y_ + yOffset) >>6;
            x2 = (x + xOffset+58) >>6;
            y2 = (y + yOffset + 58) >> 6;
        }
        else if(index == 1) {
            x1 = (x_ + xOffset + 58) >>6;
            y1 = (y_ + yOffset) >>6;
            x2 = (x_ + xOffset + 58) >>6;
            y2 = (y_ + yOffset + 58) >>6;
        }else if(index == 2) {
            x1 = (x_ + xOffset) >> 6;
            y1 = (y_ + yOffset + 58) >> 6;
            x2 = (x_ + xOffset + 58) >> 6;
            y2 = (y_ + yOffset + 58) >> 6;
        }else {
            x1 = (x + xOffset) >> 6;
            y1 = (y_ + yOffset) >> 6;
            x2 = (x + xOffset) >> 6;
            y2 = (y_ + yOffset + 58) >> 6;
        }
        if(level.getUnmoveTile(x1, y1).solid() || level.getUnmoveTile(x2, y2).solid()) solid_ = true;

        return solid_;
    }
}
