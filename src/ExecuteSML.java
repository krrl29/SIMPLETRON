import javax.swing.*;

public class ExecuteSML implements Runnable {

    private int noInstructions;
    private int errorCode;
    private int instructionCounter;
    private int operationCode;
    private int accumulator;
    private Object lock;
    private int usrValue;
    private int[] memory;
    private JTextArea jMain;
    private JTextArea jDump;
    private boolean test = false;

    private SimpleTronMemoryDump memoryDump;

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

    public ExecuteSML(int noInstructions, int[] memory, JTextArea jMain, JTextArea jDump,Object lock) {
        this.noInstructions = noInstructions;
        this.memory = memory;
        this.jMain = jMain;
        this.lock = lock;
        this.jDump = jDump;
    }

    /*************************************************************
     * Author: Doug Heasley
     * Purpose: Runs the simulation while the halt command is not reached,
     * as well as when there is no error code returned by an operation
     ************************************************************/
    @Override
    public void run() {
        instructionCounter = 0;
        int instructionRegister;
        int operand;
        operationCode=0;

        while(operationCode != 43 && operationCode != -1){
            instructionRegister = memory[instructionCounter];
            operationCode = instructionRegister /100;
            operand = instructionRegister % 100;
            switch (operationCode){
                case (10): // read user value
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    jMain.append("\nPlease enter an integer");

                    /* Logic for being able to accept user input from the GUI and pausing operations in this
                    * class while that is happening -DSH */
                    synchronized (lock) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    StoreValue(operand);
                    break;
                case (11): // write to main output -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    DisplayLocation(operand);
                    break;
                case (20): // load from memory into accum. -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    LoadAccu(operand);
                    break;
                case (21): // store from accum. into memory -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    PullAccu(operand);
                    break;
                case (30): // add -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    Add(operand);
                    break;
                case (31): // subtract -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    Subtract(operand);
                    break;
                case (32): // divide -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    Divide(operand);
                    break;
                case (33): // multiply  -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    Multiply(operand);
                    break;
                case (40): // branch to a location in memory -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    Branch(operand);
                    break;
                case (41): // branch to a location in memory when accum. is neg. -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    NegativeConditionalBranch(operand);
                    break;
                case (42): // branch to a location in memory when accum. is zero -DSH
                    DisplayDump(accumulator, instructionCounter, memory);
                    DisplayCommand(instructionCounter, instructionRegister);
                    ZeroConditionalBranch(operand);
                    break;
            }
            instructionCounter++;
        }
        if(operationCode == -1) {
            DisplayError();
            DisplayDump(accumulator, instructionCounter, memory);
        }
        DisplayDump(accumulator, instructionCounter, memory);
    }

    // displays the commands in main when they are being exe. -DSH
    private void DisplayCommand(int instructionCounter, int instructionRegister) {
        jMain.append("\n" + String.format("%02d", instructionCounter) + " ? " + instructionRegister);
    }

    // stores user value in memory -DSH
    private void StoreValue(int operand) {
        memory[operand] = usrValue;
    }

    // displays a location in mem. to the screen -DSH
    private void DisplayLocation(int operand) {
        jMain.append("\n" + memory[operand]);
    }

    // loads a value in accum. from memory -DSH
    private void LoadAccu(int operand) {
        accumulator = memory[operand];
    }

    // loads a location in mem from the accum -DSH
    private void PullAccu(int operand){
        memory[operand] = accumulator;
    }

    // adds, leaves sum in accum. -DSH
    private void Add(int operand) {
        accumulator += memory[operand];
        if(accumulator < -9999 || accumulator > 9999){
//             break out of loop, fatal error -DSH
            errorCode = 30;
            operationCode = -1;
        }
    }

    // subtracts, leaves difference in accum. -DSH
    private void Subtract(int operand) {
        accumulator -= memory[operand];
        if(accumulator < -9999 || accumulator > 9999){
            // break out of loop, fatal error -DSH
            errorCode = 31;
            operationCode = -1;
        }
    }

    // divides, leaves result in accum. -DSH
    private void Divide(int operand) {
        if(operand == 0) {
            // break out of loop fatal division by zero error -DSH
            errorCode = 32;
            operationCode = -1;
        }
        accumulator /= memory[operand];
    }

    // multiplies, leaves product in accum. -DSH
    private void Multiply(int operand) {
        accumulator *= memory[operand];
        if(accumulator < -9999 || accumulator > 9999){
            // break out of loop, fatal error -DSH
            errorCode = 33;
            operationCode = -1;
        }
    }

    // branches to a location in mem. -DSH
    private void Branch(int operand) {
        instructionCounter = operand-1;
    }

    // branches to a location in mem. when accum. is zero -DSH
    private void ZeroConditionalBranch(int operand) {
        if(accumulator == 0) {
            instructionCounter = operand-1;
        }
    }

    // branches to a location in mem. when accum. is negative -DSH
    private void NegativeConditionalBranch(int operand) {
        if(accumulator < 0){
            instructionCounter = operand-1;
        }
    }

    // displays any errors that may have occurred -DSH
    private void DisplayError() {
        switch(errorCode){
            case(30):
                jMain.append("\n*** Attempt to add resulted in Integer overflow ***\n");
                jMain.append("*** SimpleTron execution abnormally terminated ***");
                break;
            case(31):
                jMain.append("\n*** Attempt to subtract resulted in Integer overflow ***\n");
                jMain.append("*** SimpleTron execution abnormally terminated ***");
                break;
            case(32):
                jMain.append("\n*** Attempt to divide by zero ***\n");
                jMain.append("*** SimpleTron execution abnormally terminated ***");
                break;
            case(33):
                jMain.append("\n*** Attempt to multiply resulted in Integer overflow ***\n");
                jMain.append("*** SimpleTron execution abnormally terminated ***");
                break;

        }
    }

    // adds the pertinent information to the memory dump window
    private void DisplayDump(int accumulator, int instructionCounter, int[] memory) {
        int instructionRegister = memory[instructionCounter];
        operationCode = instructionRegister /100;
        int operand = instructionRegister % 100;

        jDump.append("\nREGISTERS:\n");
        jDump.append("accumulator            " + accumulator + '\n');
        jDump.append("instructionCounter       " + instructionCounter + '\n');
        jDump.append("instructionRegister    +" + memory[instructionCounter] + '\n');
        jDump.append("operationCode             " + operationCode + '\n');
        jDump.append("operand                   " + operand + "\n\n");

        jDump.append("MEMORY:  \n");
        jDump.append("              0         1         2         3         4         5         6         7         8         9 \n  ");
        int count = 0;
        int memCount = 0;
        while (count < 100) {
            jDump.append(count + " " );
            count+= 10;
            while (memCount < count){
                jDump.append(" +" +String.format("%04d", memory[memCount]));
                memCount++;
            }
            jDump.append("\n");
        }

    }
}
