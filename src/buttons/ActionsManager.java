package buttons;

import main.GifSequenceWriter;
import main.HelpMethods;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static buttons.RegisterComponents.timeField;

public class ActionsManager {

    public static ActionListener askAction = e -> JOptionPane.showConfirmDialog(null, "Тебе реально что-то непонятно в ИНТУИТИВНО ПОНЯТНОМ интерфейсе?", "?", JOptionPane.OK_CANCEL_OPTION);
    public static ActionListener closeAction = e -> System.exit(0);
    public static ActionListener uploadAction = e -> {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Изображения", "png", "jpeg", "jpg");
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(null);

        if (fileChooser.getSelectedFile() != null) {
            try {
                BufferedImage img = ImageIO.read(new File(fileChooser.getSelectedFile().getPath()));
                if (!Buffer.files.isEmpty()) {
                    if (Buffer.files.getFirst().getWidth() == img.getWidth()) {
                        if (Buffer.files.getFirst().getHeight() == img.getHeight()) {
                            Buffer.files.add(img);
                        } else {
                            JOptionPane.showMessageDialog(null, "Высота первого фото отличается от выбранного", "Фотография не загружена.", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ширина первого фото отличается от выбранного", "Фотография не загружена.", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Buffer.files.add(img);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    };
    public static ActionListener removeAllAction = e -> {
        if (Buffer.files.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Нет фото для удаления", "Null", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int result = JOptionPane.showConfirmDialog(null, "Удалить все выбранные фото?", "Удаление.", JOptionPane.YES_NO_OPTION);
        if (result == 1) {
            return;
        }
        Buffer.files.clear();
    };
    public static ActionListener saveAction = e -> {
        try {
            if (timeField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Укажите скорость переключения фото", "Ничего не произошло.", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Buffer.files.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Вы не выбрали ни одного фото", "Нет фото для обработки.", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Buffer.files.size() == 1) {
                int result = JOptionPane.showConfirmDialog(null, "Вы хотите создать гифку из одной фото?", "?!", JOptionPane.YES_NO_OPTION);
                if (result == 1) {
                    return;
                }
            }

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("GIF", "gif");
            fileChooser.setFileFilter(filter);
            fileChooser.showSaveDialog(null);
            if (fileChooser.getSelectedFile() == null) {
                return;
            }
            String path = fileChooser.getSelectedFile().getPath();
            if (!path.endsWith(".gif")) {
                path += ".gif";
            }
            ImageOutputStream output = new FileImageOutputStream(new File(path));

            GifSequenceWriter writer = new GifSequenceWriter(output, BufferedImage.TYPE_INT_RGB, Integer.parseInt(timeField.getText()), true);
            for (int i = 0; i < Buffer.files.size(); i++) {
                BufferedImage img = Buffer.files.get(i);
                writer.writeToSequence(img);
            }
            writer.close();
            output.close();

            JOptionPane.showConfirmDialog(null, "Файл сохранён", "Done.", JOptionPane.OK_CANCEL_OPTION);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    };
    public static DropTarget dropField = new DropTarget() {
        public synchronized void drop(DropTargetDropEvent e) {
            try {
                e.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transferable = e.getTransferable();
                DataFlavor[] flavors = transferable.getTransferDataFlavors();
                for (DataFlavor flavor : flavors) {
                    if (flavor.isFlavorJavaFileListType()) {
                        java.util.List<File> files = (java.util.List<File>) transferable.getTransferData(flavor);
                        for (File file : files) {
                            if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg")) {
                                BufferedImage img = ImageIO.read(new File(file.getPath()));
                                if (!Buffer.files.isEmpty()) {
                                    if (Buffer.files.getFirst().getWidth() == img.getWidth()) {
                                        if (Buffer.files.getFirst().getHeight() == img.getHeight()) {
                                            Buffer.files.add(img);
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Высота первого фото отличается от выбранного", "Фотография не загружена.", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Ширина первого фото отличается от выбранного", "Фотография не загружена.", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    Buffer.files.add(img);
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };
    public static final KeyListener onlyNums = new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                e.consume();
            }
        }
    };

    public static final MouseAdapter dragAndDrop =  new MouseAdapter() {

        int draggedIndex = -1;
        int targetIndex = -1;

        @Override
        public void mousePressed(MouseEvent e) {
            draggedIndex = HelpMethods.getImageIndexAt(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (draggedIndex != -1) {
                targetIndex = HelpMethods.getImageIndexAt(e.getX(), e.getY());
                if (targetIndex != -1 && targetIndex != draggedIndex) {
                    BufferedImage temp = Buffer.files.get(draggedIndex);
                    Buffer.files.remove(draggedIndex);
                    Buffer.files.add(targetIndex, temp);
                }
            }
            draggedIndex = -1;
            targetIndex = -1;
        }
    };

}
