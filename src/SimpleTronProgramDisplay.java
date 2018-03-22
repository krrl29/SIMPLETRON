import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronProgramDisplay extends JPanel {
    private JTextArea programDisplay;
    private Border solidBorder;


    public SimpleTronProgramDisplay() {

        programDisplay = new JTextArea(30, 20);
        programDisplay.setEditable(false);
        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        programDisplay.setBorder(solidBorder);
        addObj();
    }


    public void addObj(){
        this.add(programDisplay);

    }
}
