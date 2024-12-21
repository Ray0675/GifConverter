package gui.components;

import buttons.ActionsManager;
import buttons.Buffer;
import buttons.ImageButtonManager;
import gui.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ConverterPanel extends JPanel {

    public ConverterPanel() {
        this.setBounds(50, 80, 1180, 710);
        this.setDropTarget(ActionsManager.dropField);
        this.setLayout(null);

        this.addMouseListener(ActionsManager.dragAndDrop);
    }

    public void render(Graphics g) {
        g.drawRect(0, 0, 1179, 570);
        this.removeAll();
        Buffer.removeButtons.clear();
        for (int i = 0; i < Buffer.files.size(); i++) {
            BufferedImage img = Buffer.files.get(i);
            JButton deleteButton = new JButton();
            int yOffset = i / 10;
            int xOffset = i % 10;
            g.drawImage(img, 110 * xOffset + 10, 100 * yOffset + 20, 100, 100, null);
            deleteButton.setBounds(110 * xOffset + 10, 100 * yOffset + 10, Constants.REMOVE_BUTTON_SIZE.width, Constants.REMOVE_BUTTON_SIZE.height);
            deleteButton.setBorderPainted(false);
            deleteButton.setIcon(ImageButtonManager.removeImageIcon);
            final int index = i;
            deleteButton.addActionListener(e -> {
                Buffer.files.remove(index);

            });
            Buffer.removeButtons.add(deleteButton);
            this.add(deleteButton);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }


}
