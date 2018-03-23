import javax.swing.*;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

public class SimpleTronLogic {

    private ArrayList<String> command;
    private int accumulator;
    public boolean test=false;
    private int usrValue;
    private int noInstructions;
    private int[] memory;
    private JTextArea jOut;
    private JTextArea jMain;

    public int getUsrValue() {
        return usrValue;
    }

    public void setUsrValue(int usrValue) {
        this.usrValue = usrValue;
    }

    public ArrayList<String> getCommand() {
        return command;
    }

    public void setCommand(ArrayList<String> command) {
        this.command = command;
    }


    public SimpleTronLogic(JTextArea PrgDisplay, JTextArea MainDisplay) {
        memory = new int[100];
        command = new ArrayList<>();
        jOut = PrgDisplay;
        jMain = MainDisplay;
    }

    public void DisplayCommands(String commandTxt){
        jOut.append("\n" + commandTxt);
        CheckIfEnd();
    }

    // checks if the programming has ended and it is time to execute
    private void CheckIfEnd() {
        if (command.contains("99999")){
            command.remove(command.get(command.size() -1));
            LoadMemory();
        }
    }

    private void LoadMemory(){
        noInstructions =0;
        for(int i = 0; i < command.size(); i++){
            if(!command.get(i).equals("99999")){
                String op = command.get(i).substring(1,5);
                memory[i] = Integer.parseInt(op);
                noInstructions++;
            }
        }
        ExeSML();
    }

    private void ExeSML() {
        ExecuteSML exe = new ExecuteSML(noInstructions,memory,jMain);
        Thread t = new Thread(exe);
        t.start();
    }

    private void StoreValue(int operand) {
        memory[operand] = usrValue;
        usrValue = 0;
    }
}
