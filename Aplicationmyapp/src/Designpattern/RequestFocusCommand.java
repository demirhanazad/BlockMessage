package Designpattern;
import javax.swing.*;

public class RequestFocusCommand implements komut {
    private JComponent component;

    public RequestFocusCommand(JComponent component) {
        this.component = component;
    }

    @Override
    public void execute() {
        component.requestFocusInWindow();
    }
}
