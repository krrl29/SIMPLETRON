import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronProgramDisplay extends JPanel {
    private JTextArea programDisplay;

    public SimpleTronProgramDisplay() {

        programDisplay = new JTextArea(30, 20);
        programDisplay.setEditable(false);

        addObj();
    }


    public void addObj(){
        this.add(programDisplay);

    }
}
