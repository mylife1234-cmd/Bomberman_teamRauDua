package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import graphics.Sprite;
import javafx.scene.input.KeyEvent;

import java.util.List;

public abstract class Entity implements Moveable {

//    public static final double SAISO = 0.1;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    //check Move
    protected boolean stop;



    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        stop = false;
    }


    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update(KeyEvent event);
    public abstract void update();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public boolean isStop() {
        return stop;
    }

    public static boolean checkToUnmove(EntityMove bomber, Entity entity) {
        if (entity instanceof Grass) {
            return true;
        }
        int x1 = bomber.getX();
        int y1 = bomber.getY();

        int x2 = entity.getX();
        int y2 = entity.getY();

        switch (bomber.getStatus()) {
            case Bomber.DOWN: {
                y1 += bomber.getStep();
                break;
            }
            case Bomber.LEFT: {
                x1 -= bomber.getStep();
                break;
            }
            case Bomber.RIGHT: {
                x1 += bomber.getStep();
                break;
            }
            case Bomber.UP: {
                y1 -= bomber.getStep();
                break;
            }
        }

        if ((x1 + bomber.img.getWidth() <= x2 || x1 >= x2 + entity.img.getWidth() || y1 + bomber.img.getHeight() <= y2 ) || y1 >= y2 + entity.img.getHeight()) {
            return true;
        }
        return false;
    }

}
