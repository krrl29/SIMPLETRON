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
        ExecuteSML();
    }

    private void ExecuteSML() {
        int instructionCounter = 0;
        int instructionRegister;
        while(instructionCounter < noInstructions){
            instructionRegister = memory[instructionCounter];
            int operationCode = instructionRegister /100;
            int operand = instructionRegister % 100;
            switch (operationCode){
                case (10):
                    jMain.append("\n\n" + instructionCounter + " ? " + instructionRegister);
                    jMain.append("\nPlease enter an integer");
                    // I cannot figure out how to get this
                    // f**king thing to wait for user input -DSH
                    StoreValue(operand);
                    break;
                case (11):

                    break;
                case (20):

                    break;
                case (21):

                    break;
                case (30):

                    break;
                case (31):

                    break;
                case (32):

                    break;
                case (33):

                    break;
                case (40):

                    break;
                case (41):

                    break;
                case (42):

                    break;
                case (43):

                    break;
            }
            instructionCounter++;
        }
    }

    private void StoreValue(int operand) {
        memory[operand] = usrValue;
        usrValue = 0;
    }
}
