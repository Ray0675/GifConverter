package main;

import gui.Constants;
import gui.ConverterFrame;
import gui.components.ConverterPanel;

public class Converter implements Runnable {

    private final ConverterPanel panel;
    private Thread thread;

    private final int FPS_SET = 10;

    public Converter() {
        panel = new ConverterPanel();

        new ConverterFrame(Constants.WINDOW_SIZE.width, Constants.WINDOW_SIZE.height, panel);

        startLoop();
    }

    private void startLoop() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaF >= 1) {
                panel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
            }

        }
    }
}
