import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimpleTronMemoryDump extends JFrame {
    private JTextArea memOut;
    private JScrollPane larryEllisonSucks;
    public JTextArea getMemOut() {
        return memOut;
    }
    private Border solidBorder;
    public void setMemOut(JTextArea memOut) {
        this.memOut = memOut;
    }

    public SimpleTronMemoryDump() {
        this.setTitle("MEMORY_DUMP");
        this.setSize(600, 600);
        this.setFont(new Font("Courier New", Font.PLAIN, 12));
        memOut = new JTextArea();
        memOut.setEditable(false);
        memOut.setText("*** Memory Dump: ***");
        larryEllisonSucks = new JScrollPane(memOut,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        solidBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
        larryEllisonSucks.setBorder(solidBorder);
        this.add(larryEllisonSucks);
        //pack();
    }
}

