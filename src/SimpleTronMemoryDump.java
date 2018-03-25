import javax.swing.*;

public class SimpleTronMemoryDump extends JFrame {
    private JTextArea memOut;


    public JTextArea getMemOut() {
        return memOut;
    }
    public void setMemOut(JTextArea memOut) {
        this.memOut = memOut;
    }

    public SimpleTronMemoryDump(String out) {
        this.setTitle("MEMORY_DUMP");
        this.setSize(300, 300);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));

        memOut = new JTextArea();
        memOut.setText(out);
        this.add(memOut);
        pack();
        //this.setVisible(true);
    }
}
