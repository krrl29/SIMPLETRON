import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronProgramDisplay extends JPanel {
    //instance vars
    private JTextArea programDisplay;
    private Border solidBorder;

    //gets and sets
    public JTextArea getProgramDisplay() {
        return programDisplay;
    }
    public void setProgramDisplay(JTextArea programDisplay) {
        this.programDisplay = programDisplay;
    }
    public Border getSolidBorder() {
        return solidBorder;
    }
    public void setSolidBorder(Border solidBorder) {
        this.solidBorder = solidBorder;
    }

    public SimpleTronProgramDisplay() {
        //set up program display panel
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
