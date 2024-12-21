package buttons;

import gui.components.Button;
import gui.Constants;
import gui.components.TextField;
import gui.components.TextLabel;

import static buttons.ActionsManager.*;
import static buttons.ImageButtonManager.*;

public class RegisterComponents {
    public static final Button closeButton = new Button(1200, 10, Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, closeAction, closeImageIcon);
    public static final Button uploadButton = new Button(30, 10, Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, uploadAction, uploadImageIcon);
    public static final Button removeButton = new Button(1000, 10, Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, removeAllAction, removeAllImageIcon);
    public static final Button saveButton = new Button(100, 10, Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, saveAction, saveImageIcon);
    public static TextField timeField = new TextField(390, 25, 50, 20, "500", onlyNums);
    public static final TextLabel timeLabel = new TextLabel(170, -15, 300, 100, "Скорость переключения фото (в мс)");
    public static final Button askButton = new Button(1140, 10, Constants.BUTTON_SIZE.width, Constants.BUTTON_SIZE.height, askAction, askImageIcon);
}

