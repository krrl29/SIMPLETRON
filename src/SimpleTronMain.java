import javax.swing.*;
import java.awt.*;

public class SimpleTronMain extends JFrame{
    private SimpleTronOperators operatorsPanel;


    public SimpleTronMain(){
        this.setTitle("SIMPLETRON");
        this.setSize(1000,500);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setLayout(new BorderLayout());
        operatorsPanel = new SimpleTronOperators();

        addPanels();

        this.setVisible(true);
    }

    public static void main(String[] args) {
        SimpleTronMain main = new SimpleTronMain();
    }


    public void addPanels() {
        this.add(operatorsPanel, BorderLayout.SOUTH);
    }


}
