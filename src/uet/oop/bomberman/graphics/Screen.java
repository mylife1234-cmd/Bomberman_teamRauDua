package uet.oop.bomberman.graphics;

public class Screen {
    private int width, heght;
    public int []pixel;
    private int transparent =0xffff00ff;
    public static int x=0, y=0;
    public Screen(int width, int heght){
        this.width = width;
        this.heght = heght;
        pixel = new int[width + heght];
    }
    public void clearScreen(){
        for(int i=0; i<pixel.length; i++){
            pixel[i] =0;
        }
    }
    public static void setPosition(int x0, int y0){
        x = x0;
        y = y0;
    }
    public static void caculatePosition(){

    }

}
