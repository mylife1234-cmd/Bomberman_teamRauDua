package uet.oop.bomberman.Level;

public abstract class level {
    private int height = 640;
    private int width = 800;
    protected int level;
    public level(int level){
        this.level = level;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

    public int getWidth() {
        return width;
    }
    public abstract void loadLevel(int level);
    public abstract void creatEntryLevel();
}
