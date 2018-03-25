
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronOutput extends JPanel {
    private JTextArea mainOutputPanel;
    private JScrollPane fuckYouLarry;
    private Border solidBorder;

    public JTextArea getMainOutputPanel() {
        return mainOutputPanel;
    }

    public void setMainOutputPanel(JTextArea mainOutputPanel) {
        this.mainOutputPanel = mainOutputPanel;
    }

    public Border getSolidBorder() {
        return solidBorder;
    }

    public void setSolidBorder(Border solidBorder) {
        this.solidBorder = solidBorder;
    }

    public SimpleTronOutput() {

            mainOutputPanel = new JTextArea(30, 70);
            mainOutputPanel.setEditable(false);
            mainOutputPanel.setText("*** Welcome to SimpleTron! ***"+ '\n' +
                    "*** Please enter your program one instruction ***"+ '\n' +
                    "*** (or data word) at a time when prompted ***" + '\n' +
                    "*** I will display the location ***" + '\n' +
                    "*** number and a question mark (?).  You then ***" + '\n' +
                    "*** type the word for that location.  Enter ***" + '\n' +
                    "*** -99990 to stop entering your program. **");

            fuckYouLarry = new JScrollPane(mainOutputPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            solidBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
            fuckYouLarry.setBorder(solidBorder);
            addObj();
        }


        public void addObj(){
            this.add(fuckYouLarry);
        }
}






