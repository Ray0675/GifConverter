package buttons;

import gui.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageButtonManager {
    public static final Image closeImage;
    public static final Image uploadImage;
    public static final Image saveImage;
    public static final Image removeAllImage;
    public static final Image removeImage;
    public static final Image askImage;

    static {
        try {
            closeImage = ImageIO.read(Objects.requireNonNull(ImageButtonManager.class.getResourceAsStream("/textures/close.png"))).getScaledInstance(Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, BufferedImage.SCALE_SMOOTH);
            uploadImage = ImageIO.read(Objects.requireNonNull(ImageButtonManager.class.getResourceAsStream("/textures/upload.png"))).getScaledInstance(Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, BufferedImage.SCALE_SMOOTH);
            saveImage = ImageIO.read(Objects.requireNonNull(ImageButtonManager.class.getResourceAsStream("/textures/save.png"))).getScaledInstance(Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, BufferedImage.SCALE_SMOOTH);
            removeAllImage = ImageIO.read(Objects.requireNonNull(ImageButtonManager.class.getResourceAsStream("/textures/removeAll.png"))).getScaledInstance(Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, BufferedImage.SCALE_SMOOTH);
            removeImage = ImageIO.read(Objects.requireNonNull(ImageButtonManager.class.getResourceAsStream("/textures/remove.png"))).getScaledInstance(Constants.REMOVE_BUTTON_SIZE.width, Constants.REMOVE_BUTTON_SIZE.height, BufferedImage.SCALE_SMOOTH);
            askImage = ImageIO.read(Objects.requireNonNull(ImageButtonManager.class.getResourceAsStream("/textures/ask.png"))).getScaledInstance(Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, BufferedImage.SCALE_SMOOTH);
       } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final ImageIcon closeImageIcon = new ImageIcon(closeImage);
    public static final ImageIcon uploadImageIcon = new ImageIcon(uploadImage);
    public static final ImageIcon saveImageIcon = new ImageIcon(saveImage);
    public static final ImageIcon removeAllImageIcon = new ImageIcon(removeAllImage);
    public static final ImageIcon removeImageIcon = new ImageIcon(removeImage);
    public static final ImageIcon askImageIcon = new ImageIcon(askImage);
}
