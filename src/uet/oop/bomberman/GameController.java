package uet.oop.bomberman;

import javafx.stage.Screen;
import uet.oop.bomberman.entities.Keyboard;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class GameController extends Canvas {
    public static int TITLE_SIZE = 16;
    public static int WIDTH_SIZE = 16 * 15;
    public static int HEIGHT_SIZE = 13 * 16;
    public static String tile = "BOMBERMAN";
    public static int TIME = 200;
    public static int SCORE = 0;
    protected int screen_delay = 3;
    private Keyboard input_;
    public boolean running = true;
    private Controller controller;
    private Screen screen;
    private BufferedImage bufferedImage = new BufferedImage(WIDTH_SIZE, HEIGHT_SIZE, BufferedImage.TYPE_INT_ARGB);
    private int []pixel =((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
    public GameController(Controller controller){

    }
}
