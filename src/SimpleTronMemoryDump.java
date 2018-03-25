import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SimpleTronMemoryDump extends JFrame {
    private JTextArea memOut;

    public JTextArea getMemOut() {
        return memOut;
    }
    public void setMemOut(JTextArea memOut) {
        this.memOut = memOut;
    }

    public SimpleTronMemoryDump() {
        this.setTitle("MEMORY_DUMP");
        this.setSize(300, 300);

        memOut = new JTextArea();
        memOut.setEditable(false);
        memOut.setText("*** Memory Dump: ***");

        this.add(memOut);
        pack();
    }
}

