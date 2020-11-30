package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    private boolean[] key =new boolean[120];
    private boolean up, down, left, right,enter;
    public void updateKeys(){
        up = key[KeyEvent.VK_UP];
        down = key[KeyEvent.VK_DOWN];
        left = key[KeyEvent.VK_LEFT];
        right = key[KeyEvent.VK_RIGHT];
        enter = key[KeyEvent.VK_ENTER];
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
    }
}
