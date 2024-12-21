package gui.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {

    protected int x, y, width, height;

    public Button(int x, int y, int width, int height, ActionListener action, ImageIcon img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setBounds(x, y, width, height);
        this.setIcon(img);

        this.addActionListener(action);
    }

}
