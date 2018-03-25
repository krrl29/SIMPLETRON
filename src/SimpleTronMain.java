import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimpleTronMain extends JFrame{
    private SimpleTronOperators operatorsPanel;
    private SimpleTronProgramDisplay programDisplay;
    private SimpleTronOutput programOutput;
    private JButton jbtDisplayDump;
    private GridBagConstraints constr;
    private SimpleTronLogic smplLogic;

    public SimpleTronMain(){
        this.setTitle("SIMPLETRON");
        this.setSize(1010,500);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setLayout(new GridBagLayout());
        constr = new GridBagConstraints();


        programDisplay = new SimpleTronProgramDisplay();
        programOutput = new SimpleTronOutput();
        smplLogic = new SimpleTronLogic(programDisplay.getProgramDisplay(), programOutput.getMainOutputPanel());
        operatorsPanel = new SimpleTronOperators(smplLogic);
        //jbtDisplayDump = new JButton("Display Memory Dump");
        addPanels();
        pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleTronMain main = new SimpleTronMain();
    }


    public void addPanels() {


        //constraints for programDisplay
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.ipady = 0;
        constr.ipadx = 0;
        constr.gridx = 0;
        constr.gridwidth = 1;
        constr.gridy = 0;
        constr.gridheight = 1;
        this.add(programDisplay, constr);

        //constraints for programOutput
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.ipady = 0;
        constr.ipadx = 0;
        constr.gridx = 1;
        constr.gridwidth = 1;
        constr.gridy = 0;
        constr.gridheight = 1;
        constr.weightx = 1;
        this.add(programOutput, constr);

        /*//constraints for Memory dump button
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(0,20,0,30);
        constr.ipady = 5;
        constr.ipadx = 10;
        constr.gridx = 0;
        constr.gridwidth = 1;
        constr.gridy = 1;
        constr.gridheight = 1;
        constr.weightx = 0;
        this.add(jbtDisplayDump, constr);*/

        //constraints for operatorsPanel
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(0,0,5,4);
        constr.ipady = 0;
        constr.ipadx = 5;
        constr.gridx = 0;
        constr.gridwidth = 2;
        constr.gridy = 1;
        constr.weightx = 0;
        this.add(operatorsPanel, constr);


    }


}
