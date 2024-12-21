package main;

import buttons.Buffer;

public class HelpMethods {

    public static int getImageIndexAt(int x, int y) {
        for (int i = 0; i < Buffer.files.size(); i++) {
            int yOffset = i / 10;
            int xOffset = i % 10;
            int imgX = 110 * xOffset + 10;
            int imgY = 100 * yOffset + 20;
            if (x >= imgX && x <= imgX + 100 && y >= imgY && y <= imgY + 100) {
                return i;
            }
        }
        return -1;
    }

}
