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
    private JButton jbtDisplayDump;
    private JLabel jlblEnterInstruction;
    private JLabel jlblUserInput;
    private JTextField jtxtInstructions;
    private JTextField jtxtUserInput;
    private SimpleTronLogic smplLogic;
    private JPanel miniContainer;
    private GridBagConstraints constr;

    private Border solidBorder;

    private final int NUM_ROWS = 2;
    private final int NUM_COLS = 3;


    public SimpleTronOperators(SimpleTronLogic smplLogic) {
        this.smplLogic = smplLogic;
        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1, false);
        //mini container to hold all but memory dump button
        miniContainer = new JPanel();
        miniContainer.setLayout(new GridLayout(NUM_ROWS, NUM_COLS,5,5));

        this.setLayout(new GridBagLayout());
        constr = new GridBagConstraints();

        // buttons
        jbtSubmitInstruction = new JButton("Submit Instruction");
        jbtSubmitInput = new JButton("Submit Input");
        jbtDisplayDump = new JButton("Display Memory Dump");

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

        addActionListeners(); // adds action listeners to the buttons and text

        addObj();
    }
    public void addObj() {
        miniContainer.add(jlblEnterInstruction);
        miniContainer.add(jtxtInstructions);
        miniContainer.add(jbtSubmitInstruction);
        miniContainer.add(jlblUserInput);
        miniContainer.add(jtxtUserInput);
        miniContainer.add(jbtSubmitInput);

        //constraints for Memory dump button
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(0,28,0,410);
        constr.ipady = 5;
        constr.ipadx = 10;
        constr.gridx = 0;
        constr.gridwidth = 1;
        constr.gridy = 0;
        constr.gridheight = 1;
        constr.weightx = 0;
        this.add(jbtDisplayDump, constr);

        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(5,300,10,4);
        constr.ipady = 0;
        constr.ipadx = 5;
        constr.gridx = 1;
        constr.gridwidth = 1;
        constr.gridy = 0;
        constr.weightx = 0;
        this.add(miniContainer);
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
