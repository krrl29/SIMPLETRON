
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronOutput extends JPanel {
        private JTextArea mainOutputPanel;

        public SimpleTronOutput() {

            mainOutputPanel = new JTextArea(30, 80);
            mainOutputPanel.setEditable(false);

            addObj();
        }


        public void addObj(){
            this.add(mainOutputPanel);

        }
}






