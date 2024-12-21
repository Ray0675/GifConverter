package gui.components;

import javax.swing.*;

public class TextLabel extends JLabel {
    protected final int x, y, width, height;
    public TextLabel(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setVisible(true);
    }
}
