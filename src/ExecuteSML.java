import javax.swing.*;

public class ExecuteSML implements Runnable {

    private int noInstructions;
    private int accumulator;
    private Object lock;
    private int usrValue;
    private int[] memory;
    private JTextArea jMain;
    private boolean test = false;

    public Object getLock() {
        return lock;
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public int getUsrValue() {
        return usrValue;
    }

    public void setUsrValue(int usrValue) {
        this.usrValue = usrValue;
    }

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

    public ExecuteSML(int noInstructions, int[] memory, JTextArea jMain, Object lock) {
        this.noInstructions = noInstructions;
        this.memory = memory;
        this.jMain = jMain;
        this.lock = lock;
    }

    @Override
    public void run() {
        int instructionCounter = 0;
        int instructionRegister;
        int operand;
        int operationCode=0;

        while(operationCode != 43){
            instructionRegister = memory[instructionCounter];
            operationCode = instructionRegister /100;
            operand = instructionRegister % 100;
            switch (operationCode){
                case (10):
                    DisplayCommand(instructionCounter, instructionRegister);
                    jMain.append("\nPlease enter an integer"); // this tech. works, not the safest, but works
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    StoreValue(operand);
                    break;
                case (11):
                    DisplayCommand(instructionCounter, instructionRegister);
                    DisplayLocation(operand);
                    break;
                case (20):
                    DisplayCommand(instructionCounter, instructionRegister);
                    LoadAccu(operand);
                    break;
                case (21):
                    DisplayCommand(instructionCounter, instructionRegister);
                    PullAccu(operand);
                    break;
                case (30):
                    DisplayCommand(instructionCounter, instructionRegister);
                    Add(operand);
                    break;
                case (31):
                    DisplayCommand(instructionCounter, instructionRegister);
                    Subtract(operand);
                    break;
                case (32):
                    DisplayCommand(instructionCounter, instructionRegister);
                    Divide(operand);
                    break;
                case (33):
                    DisplayCommand(instructionCounter, instructionRegister);
                    Multiply(operand);
                    break;
                case (40):
                    DisplayCommand(instructionCounter, instructionRegister);
                    break;
                case (41):
                    DisplayCommand(instructionCounter, instructionRegister);
                    break;
                case (42):
                    DisplayCommand(instructionCounter, instructionRegister);
                    break;
            }
            instructionCounter++;
        }
    }

    private void DisplayCommand(int instructionCounter, int instructionRegister) {
        jMain.append("\n" + String.format("%02d", instructionCounter) + " ? " + instructionRegister);
    }

    private void StoreValue(int operand) {
        memory[operand] = usrValue;
    }

    private void DisplayLocation(int operand) {
        jMain.append("\n" + memory[operand]);
    }

    private void LoadAccu(int operand) {
        accumulator = memory[operand];
    }

    private void PullAccu(int operand){
        memory[operand] = accumulator;
    }

    private void Add(int operand) {
        accumulator += memory[operand];
    }

    private void Subtract(int operand) {
        accumulator -= memory[operand];
    }

    private void Divide(int operand) {
        accumulator /= memory[operand];
    }

    private void Multiply(int operand) {
        accumulator *= memory[operand];
    }
}
