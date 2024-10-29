package Designpattern;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;

public class ButtonClickCommand implements komut {
    private JTextComponent textComponent;
    private JButton button;

    // JButton parametresiyle constructure overload edelim.
    public ButtonClickCommand(JButton button) {
        this.button = button;
    }

    public ButtonClickCommand(JTextComponent textComponent, JButton button) {
        this.textComponent = textComponent;
        this.button = button;
    }

    @Override
    public void execute() {
        if (textComponent != null) {
            System.out.println("İşlem yapılıyor: " + textComponent.getText());
        }
        for (ActionListener al : button.getActionListeners()) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }
}
