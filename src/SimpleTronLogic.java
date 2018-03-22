import javax.swing.*;
import java.util.ArrayList;

public class SimpleTronLogic {

    private ArrayList<String> command;
    private int[] memory;
    private JTextArea jOut;

    public ArrayList<String> getCommand() {
        return command;
    }

    public void setCommand(ArrayList<String> command) {
        this.command = command;
    }

    public int[] getMemory() {
        return memory;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public SimpleTronLogic(JTextArea PrgDisplay) {
        memory = new int[100];
        command = new ArrayList<String>();
        jOut = PrgDisplay;
    }

    public void DisplayCommands(String commandTxt){
        jOut.append("\n" + commandTxt);
    }
}
