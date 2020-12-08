package entities;

import javafx.scene.image.Image;

import java.util.List;

public abstract class EntityMove extends Entity{

    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 1;

    protected int step = 4;
    protected int status;
    protected int frame;

    protected List<Entity> entities;

    public EntityMove(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        status = DOWN;
        frame = 0;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
