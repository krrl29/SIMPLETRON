import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimpleTronMain extends JFrame{
    private SimpleTronOperators operatorsPanel;
    private SimpleTronProgramDisplay programDisplay;
    private SimpleTronOutput programOutput;
    public SimpleTronMain(){
        this.setTitle("SIMPLETRON");
        this.setSize(1000,500);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setLayout(new BorderLayout());
        operatorsPanel = new SimpleTronOperators();
        programDisplay = new SimpleTronProgramDisplay();
        programOutput = new SimpleTronOutput();

        addPanels();

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleTronMain main = new SimpleTronMain();
    }


    public void addPanels() {
        this.add(operatorsPanel, BorderLayout.SOUTH);
        this.add(programDisplay, BorderLayout.WEST);
        this.add(programOutput, BorderLayout.EAST);
    }


}
