import javax.swing.*;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

public class SimpleTronLogic {

    private final String[] COMMANDS_DEF = {
            "+10",
            "+11",
            "+20",
            "+21",
            "+30",
            "+31",
            "+32",
            "+33",
            "+40",
            "+41",
            "+42",
            "+43",
            "-99",
            "+00"
    };
    private ArrayList<String> command;
    private Object lock = new Object();
    private int accumulator;
    private ExecuteSML exe;
    private int usrValue;
    private int noInstructions;
    private int[] memory;
    private JTextArea jOut;
    private JTextArea jMain;
    private JTextArea jDump;

    public String[] getCOMMANDS_DEF() {
        return COMMANDS_DEF;
    }

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public void setUsrValue(int usrValue) {
        this.usrValue = usrValue;
        exe.setUsrValue(usrValue);
    }

    public ArrayList<String> getCommand() {
        return command;
    }

    public void setCommand(ArrayList<String> command) {
        this.command = command;
    }


    public SimpleTronLogic(JTextArea PrgDisplay, JTextArea MainDisplay, JTextArea MemoryDumpDisplay) {
        memory = new int[100];
        command = new ArrayList<>();
        jOut = PrgDisplay;
        jMain = MainDisplay;
        jDump = MemoryDumpDisplay;
    }

    public void DisplayCommands(String commandTxt){
        jOut.append("\n" + commandTxt);
        CheckIfEnd();
    }

    // checks if the programming has ended and it is time to execute
    private void CheckIfEnd() {
        if (command.contains("-99990")){
            jMain.append("\n\n*** Code Entry Completed ***\n*** Execution Will Begin ***\n");
            command.remove(command.get(command.size() -1));
            LoadMemory();
        }
    }

    private void LoadMemory(){
        noInstructions =0;
        for(int i = 0; i < command.size(); i++){
            if(!command.get(i).equals("-99990")){
                String op = command.get(i).substring(1,5);
                memory[i] = Integer.parseInt(op);
                noInstructions++;
            }
        }
        ExeSML();
    }

    private void ExeSML() {
        exe = new ExecuteSML(noInstructions,memory,jMain, jDump, this.lock);
        Thread t = new Thread(exe);
        t.start();
    }

    private void StoreValue(int operand) {
        memory[operand] = usrValue;
        usrValue = 0;
    }
}
