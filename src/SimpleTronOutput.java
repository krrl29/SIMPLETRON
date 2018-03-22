
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronOutput extends JPanel {
        private JTextArea mainOutputPanel;
    private Border solidBorder;

        public SimpleTronOutput() {

            mainOutputPanel = new JTextArea(30, 80);
            mainOutputPanel.setEditable(false);
            solidBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
            mainOutputPanel.setBorder(solidBorder);
            addObj();
        }


        public void addObj(){
            this.add(mainOutputPanel);

        }
}






