package gui.components;

import javax.swing.*;
import java.awt.event.KeyListener;

public class TextField extends JTextField {

    protected int x, y, width, height;

    public TextField(int x, int y, int width, int height, String txt, KeyListener keyListener) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setText(txt);
        this.setBounds(x, y, width, height);
        this.addKeyListener(keyListener);
    }
}
