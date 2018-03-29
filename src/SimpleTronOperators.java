import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class SimpleTronOperators extends JPanel {
    //instance vars
    private JButton jbtSubmitInstruction;
    private JButton jbtSubmitInput;
    private JLabel jlblEnterInstruction;
    private JLabel jlblUserInput;
    private JTextField jtxtInstructions;
    private JTextField jtxtUserInput;
    private SimpleTronLogic smplLogic;

    //border for panel
    private Border solidBorder;
    //const for gridlayout
    private final int NUM_ROWS = 2;
    private final int NUM_COLS = 3;


    public SimpleTronOperators(SimpleTronLogic smplLogic) {
        //instantiate all the things
        this.setLayout(new GridLayout(NUM_ROWS, NUM_COLS,5,5));
        this.smplLogic = smplLogic;
        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        // buttons
        jbtSubmitInstruction = new JButton("Submit Instruction");
        jbtSubmitInput = new JButton("Submit Input");
        // labels
        jlblUserInput = new JLabel("User Input:");
        jlblEnterInstruction = new JLabel("Enter Instruction:");
        jlblUserInput.setHorizontalAlignment(SwingConstants.RIGHT);
        jlblEnterInstruction.setHorizontalAlignment(SwingConstants.RIGHT);
        // text fields
        jtxtInstructions = new JTextField();
        jtxtUserInput = new JTextField();
        //adding borders
        jtxtInstructions.setBorder(solidBorder);
        jtxtUserInput.setBorder(solidBorder);

        addActionListeners(); // adds action listeners to the buttons and text
        //add the objects to the operator panel
        addObj();
    }
    //add objects
    public void addObj() {
        this.add(jlblEnterInstruction);
        this.add(jtxtInstructions);
        this.add(jbtSubmitInstruction);
        this.add(jlblUserInput);
        this.add(jtxtUserInput);
        this.add(jbtSubmitInput);

    }
    //listen for all the things
    private void addActionListeners() {
        //button listeners
        SubmitInstructionActionListener SubInstAL = new SubmitInstructionActionListener();
        SubmitInputActionListener SubInAL = new SubmitInputActionListener();
        jbtSubmitInput.addActionListener(SubInAL);
        jbtSubmitInstruction.addActionListener(SubInstAL);
        //key listeners for enter press
        InputKeyEvent inputKeyEvent = new InputKeyEvent();
        InstructionKeyEvent instructionKeyEvent = new InstructionKeyEvent();
        jtxtInstructions.addKeyListener(instructionKeyEvent);
        jtxtUserInput.addKeyListener(inputKeyEvent);
    }

    // submits the instructions to the program when this action listener is invoked
    public class SubmitInstructionActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String test = jtxtInstructions.getText();
            if(Arrays.asList(smplLogic.getCOMMANDS_DEF()).contains(test.substring(0,3))){ // checks to see if the command is in the list of approved commands
                smplLogic.getCommand().add(test); // adds the command to an array list
                smplLogic.DisplayCommands(test); // displays the input command in the correct JTextArea on the screen
            } else {
                JOptionPane.showMessageDialog(null,"Please Enter a Valid Command!"); // if not a valid command messagebox
            }
        }
    }

    public class SubmitInputActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                smplLogic.setUsrValue(Integer.parseInt(jtxtUserInput.getText())); // parses the user input
                synchronized (smplLogic.getLock()){ // once user input is submitted notify the waiting thread to continue
                    smplLogic.getLock().notify();
                }
            } catch (NumberFormatException nfx) {
                JOptionPane.showMessageDialog(null,"Please Enter a Valid Number!"); // if the input is invalid messagebox
            }
        }
    }

    public class InstructionKeyEvent implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        // allows the user to press enter to submit the button
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER) {
                String test = jtxtInstructions.getText();
                jtxtInstructions.selectAll();
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

    // allows the user to press enter to submit the button
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
