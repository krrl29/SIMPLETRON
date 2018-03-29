import javax.swing.*;
import java.util.ArrayList;
import java.lang.String;
import java.util.List;

public class SimpleTronLogic {

    /************************************
     * Author: Doug Heasley
     * Purpose: This is a list of commands that are valid for input into the SimpTron
     ************************************/
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
    private ExecuteSML exe;
    private int usrValue;
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

    // modified mutator that sets the user value in the exe
    // class as well -DSH
    public void setUsrValue(int usrValue) {
        this.usrValue = usrValue;
        exe.setUsrValue(usrValue);
    }

    public ArrayList<String> getCommand() {
        return command;
    }

    /**************************************
     * Author: Doug Heasley
     * Purpose: Constructor, gets a ref. to the main, output, and memory dump textareas,
     * as well as creates the ArrayList that stores the input commands, and the memory array
    * */
    public SimpleTronLogic(JTextArea PrgDisplay, JTextArea MainDisplay, JTextArea MemoryDumpDisplay) {
        memory = new int[100];
        command = new ArrayList<>();
        jOut = PrgDisplay;
        jMain = MainDisplay;
        jDump = MemoryDumpDisplay;
    }

    /************************************************
     * Author: Doug Heasley
     * Purpose: Displays the commands as the user types them in
     * in the output window, also checks to see if the user has
     * ended input
     * **********************************************/
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

    /***********************************************************************
     * Author: Doug Heasley
     * Purpose: Loads the commands into memory by dropping the first char(+), and
     * converting to an integer
     * *********************************************************************/
    private void LoadMemory(){
        for(int i = 0; i < command.size(); i++){
            if(!command.get(i).equals("-99990")){
                String op = command.get(i).substring(1,5);
                memory[i] = Integer.parseInt(op);
            }
        }
        ExeSML();
    }

    /************************************************************************
     * Author: Doug Heasley
     * Purpose: Creates a new thread where the SML will be executed
     * passes all of the necessary information to the class via the constructor
     * ***********************************************************************/
    private void ExeSML() {
        exe = new ExecuteSML(memory,jMain, jDump, this.lock);
        Thread t = new Thread(exe);
        t.start();
    }
}
