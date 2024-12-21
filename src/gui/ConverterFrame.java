package gui;

import buttons.RegisterComponents;
import gui.components.ConverterPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class ConverterFrame {

    protected int width, height;
    private final JFrame jFrame;

    public ConverterFrame(int width, int height, ConverterPanel panel) {
        this.width = width;
        this.height = height;
        jFrame = new JFrame("Gif Converter");

        setSize();

        addComponentsAutomatically(jFrame);

        jFrame.add(panel);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    private void setSize() {
        Dimension size = new Dimension(width, height);

        jFrame.setSize(size);
        jFrame.setMaximumSize(size);
        jFrame.setMinimumSize(size);
        jFrame.setResizable(false);
    }

    private void addComponentsAutomatically(JFrame jFrame) {
        Field[] fields = RegisterComponents.class.getDeclaredFields();
        for (Field field : fields) {
            if (Component.class.isAssignableFrom(field.getType())) {
                try {
                    Component comp = (Component) field.get(this);
                    jFrame.add(comp);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
