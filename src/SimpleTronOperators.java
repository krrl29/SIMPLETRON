import javax.swing.*;
import java.awt.*;

public class SimpleTronOperators extends JPanel {
    private JButton jbtSubmitInstruction;
    private JButton jbtSubmitInput;
    private JButton jbtDisplayDump;
    private JLabel jlblEnterInstruction;
    private JLabel jlblUserInput;
    private JTextField jtxtInstructions;
    private JTextField jtxtUserInput;

    private final int NUM_ROWS = 2;
    private final int NUM_COLS = 3;

    public SimpleTronOperators() {

        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        jbtSubmitInstruction = new JButton("Submit Instruction");
        jbtSubmitInput = new JButton("Submit Input");
        jbtDisplayDump = new JButton("Display Memory Dump");
        jlblUserInput = new JLabel("User Input: ");
        jlblEnterInstruction = new JLabel("Enter Instruction: ");
        jtxtInstructions = new JTextField();
        jtxtUserInput = new JTextField();


        addObj();
    }
    public void addObj() {
        this.add(jlblEnterInstruction);
        this.add(jtxtInstructions);
        this.add(jbtSubmitInstruction);
        this.add(jlblUserInput);
        this.add(jtxtUserInput);
        this.add(jbtSubmitInput);
    }
}
