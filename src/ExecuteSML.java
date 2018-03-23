import javax.swing.*;

public class ExecuteSML implements Runnable {

    private int noInstructions;
    private int[] memory;
    private JTextArea jMain;
    private boolean test = false;

    public int getNoInstructions() {
        return noInstructions;
    }

    public void setNoInstructions(int noInstructions) {
        this.noInstructions = noInstructions;
    }

    public int[] getMemory() {
        return memory;
    }

    public void setMemory(int[] memory) {
        this.memory = memory;
    }

    public JTextArea getjMain() {
        return jMain;
    }

    public void setjMain(JTextArea jMain) {
        this.jMain = jMain;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public ExecuteSML(int noInstructions, int[] memory, JTextArea jMain ) {
        this.noInstructions = noInstructions;
        this.memory = memory;
        this.jMain = jMain;
    }

    @Override
    public void run() {
        int instructionCounter = 0;
        int instructionRegister;
        while(instructionCounter < noInstructions){
            instructionRegister = memory[instructionCounter];
            int operationCode = instructionRegister /100;
            int operand = instructionRegister % 100;
            switch (operationCode){
                case (10):
                    jMain.append("\n\n" + instructionCounter + " ? " + instructionRegister);
                    jMain.append("\nPlease enter an integer"); // this tech. works, not the safest, but works
                    while (!test){

                    }
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

    }
}
