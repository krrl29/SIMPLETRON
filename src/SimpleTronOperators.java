import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimpleTronOperators extends JPanel {
    private JButton jbtSubmitInstruction;
    private JButton jbtSubmitInput;
    private JLabel jlblEnterInstruction;
    private JLabel jlblUserInput;
    private JTextField jtxtInstructions;
    private JTextField jtxtUserInput;

    private Border solidBorder;

    private final int NUM_ROWS = 2;
    private final int NUM_COLS = 3;

    public SimpleTronOperators() {

        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        jbtSubmitInstruction = new JButton("Submit Instruction");
        jbtSubmitInput = new JButton("Submit Input");
        jlblUserInput = new JLabel("User Input: ");
        jlblEnterInstruction = new JLabel("Enter Instruction: ");
        jtxtInstructions = new JTextField();
        jtxtUserInput = new JTextField();

        jtxtInstructions.setBorder(solidBorder);
        jtxtUserInput.setBorder(solidBorder);

        jlblEnterInstruction.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblUserInput.setHorizontalAlignment(SwingConstants.RIGHT);

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
