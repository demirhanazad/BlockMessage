package Designpattern;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;

public class KeyHandler {
    private komut enterKeyCommand;

    public KeyHandler(komut enterKeyCommand) {
        this.enterKeyCommand = enterKeyCommand;
    }

    public void handleKey(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.isControlDown()) {
                enterKeyCommand.execute();
            } else {
                JTextComponent textComponent = (JTextComponent) e.getSource();
                if (textComponent instanceof JTextArea) {
                    ((JTextArea) textComponent).append("\n"); // Cast to JTextArea
                }
                else if (textComponent instanceof JTextField) {
                	enterKeyCommand.execute(); // JTextField için Enter komutunu çalıştırır
                }
                e.consume();
            }
        }
    }
}
