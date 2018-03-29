import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronMain extends JFrame{
    //instance vars
    private SimpleTronOperators operatorsPanel;
    private SimpleTronProgramDisplay programDisplay;
    private SimpleTronOutput programOutput;
    private SimpleTronMemoryDump memoryDump;
    private JButton jbtDisplayDump;
    private GridBagConstraints constr;
    private SimpleTronLogic smplLogic;

    public SimpleTronMain(){
        this.setTitle("SIMPLETRON");
        this.setSize(1010,500);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setLayout(new GridBagLayout());
        constr = new GridBagConstraints();
        //instantiate all the panes
        programDisplay = new SimpleTronProgramDisplay();
        programOutput = new SimpleTronOutput();
        memoryDump = new SimpleTronMemoryDump();
        smplLogic = new SimpleTronLogic(programDisplay.getProgramDisplay(), programOutput.getMainOutputPanel(), memoryDump.getMemOut());
        operatorsPanel = new SimpleTronOperators(smplLogic);
        jbtDisplayDump = new JButton("Display Memory Dump");
        //add the action listeners
        AddActionListeners();
        //add all of the panels and the memory dump button to the frame
        addPanels();
        //pack and set the window visible
        pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleTronMain main = new SimpleTronMain();
    }

    public void AddActionListeners(){
        MemoryDumpActionListener memAL = new MemoryDumpActionListener();
        jbtDisplayDump.addActionListener(memAL);
    }
    //using the action listener only to show the memory dump allows
    //the memory dump to be persistent and continuously reload, like a log file
    public class MemoryDumpActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            memoryDump.setVisible(true);
        }
    }

    //add components to main window using gridbag constraints
    public void addPanels() {
        //constraints for programDisplay
        constr.fill = GridBagConstraints.HORIZONTAL;
        //padding
        constr.ipady = 0;
        constr.ipadx = 0;
        //location on the grid
        constr.gridx = 0;
        constr.gridy = 0;
        //size of the obj on the frame
        constr.gridwidth = 1;
        constr.gridheight = 1;
        this.add(programDisplay, constr);

        //constraints for programOutput
        constr.fill = GridBagConstraints.HORIZONTAL;
        //padding
        constr.ipady = 0;
        constr.ipadx = 0;
        //grid location
        constr.gridx = 1;
        constr.gridy = 0;
        //size of the obj on the frame
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.weightx = 1;
        this.add(programOutput, constr);

        //constraints for Memory dump button
        constr.fill = GridBagConstraints.HORIZONTAL;
        //insets are pretty much margins, each number tells how far in on that side to push the content.
        constr.insets = new Insets(0,20,0,30);
        //padding
        constr.ipady = 5;
        constr.ipadx = 10;
        //location on the grid
        constr.gridx = 0;
        constr.gridy = 1;
        //size of the obj on the frame
        constr.gridwidth = 1;
        constr.gridheight = 1;
        constr.weightx = 0;
        this.add(jbtDisplayDump, constr);

        //constraints for operatorsPanel
        constr.fill = GridBagConstraints.HORIZONTAL;
        //insets are pretty much margins, each number tells how far in on that side to push the content.
        constr.insets = new Insets(5,300,10,4);
        //padding
        constr.ipady = 0;
        constr.ipadx = 5;
        //location on the grid
        constr.gridx = 0;
        constr.gridy = 1;
        //size of obj on the frame
        constr.gridwidth = 2;
        constr.weightx = 0;
        this.add(operatorsPanel, constr);


    }


}
