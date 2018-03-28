
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTronOutput extends JPanel {
    //instance vars
    private JTextArea mainOutputPanel;
    private JScrollPane FYouLarryEllison;
    private Border solidBorder;

    //gets and sets
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
            //set up output panel
            mainOutputPanel = new JTextArea(30, 70);
            mainOutputPanel.setEditable(false);
            mainOutputPanel.setText("*** Welcome to SimpleTron! ***"+ '\n' +
                    "*** Please enter your program one instruction ***"+ '\n' +
                    "*** (or data word) at a time when prompted ***" + '\n' +
                    "*** I will display the location ***" + '\n' +
                    "*** number and a question mark (?).  You then ***" + '\n' +
                    "*** type the word for that location.  Enter ***" + '\n' +
                    "*** -99990 to stop entering your program. **");
            //larry ellison sucks because text areas are not scrollable, so we have to do this dumb thing
            FYouLarryEllison = new JScrollPane(mainOutputPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            solidBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
            FYouLarryEllison.setBorder(solidBorder);
            addObj();
        }


        public void addObj(){
            this.add(FYouLarryEllison);
        }
}






