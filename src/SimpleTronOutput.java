
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
            mainOutputPanel.setText("*** Welcome to SimpleTron! ***"+ '\n' +
                    "*** Please enter your program one instruction ***"+ '\n' +
                    "*** (or data word) at a time when prompted ***" + '\n' +
                    "*** I will display the location ***" + '\n' +
                    "*** number and a question mark (?).  You then ***" + '\n' +
                    "*** type the word for that location.  Enter ***" + '\n' +
                    "*** -99999 to stop entering your program. **");

            addObj();
        }


        public void addObj(){
            this.add(mainOutputPanel);

        }
}






