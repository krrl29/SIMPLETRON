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
        programDisplay.setText("Program Operations Will Be " + '\n' + "Displayed Here When Input");
        addObj();
    }


    public void addObj(){
        this.add(programDisplay);

    }
}
