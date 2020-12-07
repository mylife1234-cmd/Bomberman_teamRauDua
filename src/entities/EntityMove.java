package entities;

import javafx.scene.image.Image;

public abstract class EntityMove extends Entity{

    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 1;

    private int step = 4;
    private int status;
    private int frame;

    public EntityMove(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        status = DOWN;
        frame = 0;
    }

}
