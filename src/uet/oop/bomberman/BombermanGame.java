package uet.oop.bomberman;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Screeen;
import uet.oop.bomberman.entities.input.KeyBoard;
import uet.oop.bomberman.graphics.Level;

public class BombermanGame extends Canvas implements Runnable {
    private JFrame jFrame;
    private int width = 960, height = 575;
    private int scale = 1;
    private Dimension dimension;
    private String title = "Bomberman";
    private Thread thread;
    private boolean moved = false;
    private int levelIndex = 0;

    private Screeen screeen;
    private KeyBoard keyBoard;
    private Bomber bomber;
    private Level level;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public BombermanGame() {
        dimension = new Dimension(width * scale, height * scale);
        jFrame = new JFrame();
        jFrame.setPreferredSize(dimension);
        screeen = new Screeen(width, height);
        keyBoard = new KeyBoard();
        setLevel();
        addKeyListener(keyBoard);
    }

    public synchronized void start() {
        moved = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        moved = false;
        try {
            thread.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();

        while(moved) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }

            render();
            frames++;

            if((System.currentTimeMillis() - timer) > 1000) {
                frames = 0;
                updates = 0;
                timer += 1000;
            }
        }
    }

    public void update() {
        if(bomber.alive){
            keyBoard.update();
            level.update();
            bomber.update();
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        if(level.isCompleted()){
            screeen.clear();
            level.render(screeen);
            bomber.render(screeen);
            setLevel();
        }
        else if(bomber.alive) {
            screeen.clear();
            level.render(screeen);
            bomber.render(screeen);
        }
        else {
            screeen.clear();
            level.render(screeen);
            bomber.render(screeen);
        }

        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = screeen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    private void setLevel() {
        levelIndex++;
        level = new Level("res/levels/Level1.txt", levelIndex);
        level.addBalloon();
        bomber = new Bomber(1, 1, keyBoard, level);
    }

    public static void main(String[] args) {
        BombermanGame game = new BombermanGame();
        game.jFrame.setResizable(true);
        game.jFrame.setTitle(game.title);
        game.jFrame.add(game);
        game.jFrame.pack();
        game.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.jFrame.setLocationRelativeTo(null);
        game.jFrame.setVisible(true);

        game.start();
    }

}
