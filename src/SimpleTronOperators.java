import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class SimpleTronOperators extends JPanel {
    private JButton jbtSubmitInstruction;
    private JButton jbtSubmitInput;
    private JLabel jlblEnterInstruction;
    private JLabel jlblUserInput;
    private JTextField jtxtInstructions;
    private JTextField jtxtUserInput;
    private SimpleTronLogic smplLogic;

    private Border solidBorder;

    private final int NUM_ROWS = 2;
    private final int NUM_COLS = 3;

    public SimpleTronOperators(SimpleTronLogic smplLogic) {
        this.smplLogic = smplLogic;
        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS,5,5));

        jbtSubmitInstruction = new JButton("Submit Instruction");
        jbtSubmitInput = new JButton("Submit Input");

        // labels
        jlblUserInput = new JLabel("User Input:");
        jlblEnterInstruction = new JLabel("Enter Instruction:");

        // text fields
        jtxtInstructions = new JTextField();
        jtxtUserInput = new JTextField();

        //adding borders
        jtxtInstructions.setBorder(solidBorder);
        jtxtUserInput.setBorder(solidBorder);

        jlblEnterInstruction.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblUserInput.setHorizontalAlignment(SwingConstants.RIGHT);

        addActionListeners(); // adds action listeners to the buttons that have been created
        addObj();
    }
    public void addObj() {
        this.add(jlblEnterInstruction);
        this.add(jtxtInstructions);
        this.add(jbtSubmitInstruction);
        this.add(jlblUserInput);
        this.add(jtxtUserInput);
        this.add(jbtSubmitInput);
        //this.getRootPane().setDefaultButton(jbtSubmitInstruction);
    }

    private void addActionListeners() {
        SubmitInstructionActionListener SubInstAL = new SubmitInstructionActionListener();
        SubmitInputActionListener SubInAL = new SubmitInputActionListener();
        jbtSubmitInput.addActionListener(SubInAL);
        jbtSubmitInstruction.addActionListener(SubInstAL);

        InputKeyEvent inputKeyEvent = new InputKeyEvent();
        InstructionKeyEvent instructionKeyEvent = new InstructionKeyEvent();
        jtxtInstructions.addKeyListener(instructionKeyEvent);
        jtxtUserInput.addKeyListener(inputKeyEvent);


    }

    public class SubmitInstructionActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String test = jtxtInstructions.getText();
            if(Arrays.asList(smplLogic.getCOMMANDS_DEF()).contains(test.substring(0,3))){
                smplLogic.getCommand().add(test);
                smplLogic.DisplayCommands(test);
            } else {
                JOptionPane.showMessageDialog(null,"Please Enter a Valid Command!");
            }
        }
    }

    public class SubmitInputActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                smplLogic.setUsrValue(Integer.parseInt(jtxtUserInput.getText()));
                synchronized (smplLogic.getLock()){
                    smplLogic.getLock().notify();
                }
            } catch (NumberFormatException nfx) {
                JOptionPane.showMessageDialog(null,"Please Enter a Valid Number!");
            }
        }
    }

    public class InstructionKeyEvent implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                jtxtInstructions.selectAll();
                String test = jtxtInstructions.getText();
                if (Arrays.asList(smplLogic.getCOMMANDS_DEF()).contains(test.substring(0, 3))) {
                    smplLogic.getCommand().add(test);
                    smplLogic.DisplayCommands(test);
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Command!");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    public class InputKeyEvent implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                jtxtUserInput.selectAll();
                try {
                    smplLogic.setUsrValue(Integer.parseInt(jtxtUserInput.getText()));
                    synchronized (smplLogic.getLock()){
                        smplLogic.getLock().notify();
                    }
                } catch (NumberFormatException nfx) {
                    JOptionPane.showMessageDialog(null,"Please Enter a Valid Number!");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}
