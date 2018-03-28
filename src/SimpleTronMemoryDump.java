import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimpleTronMemoryDump extends JFrame {
    //instance vars
    private JTextArea memOut;
    private JScrollPane larryEllisonSucks;
    private Border solidBorder;

    //get and set
    public JTextArea getMemOut() {
        return memOut;
    }
    public void setMemOut(JTextArea memOut) {
        this.memOut = memOut;
    }

    public SimpleTronMemoryDump() {
        //set up memory dump window
        this.setTitle("MEMORY_DUMP");
        this.setSize(600, 600);
        this.setFont(new Font("Courier New", Font.PLAIN, 12));
        memOut = new JTextArea();
        memOut.setEditable(false);
        memOut.setText("*** Memory Dump: ***");
        //larry ellison sucks because text areas are not scrollable, so we have to do this dumb thing
        larryEllisonSucks = new JScrollPane(memOut,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        larryEllisonSucks.setBorder(solidBorder);
        this.add(larryEllisonSucks);

    }
}

