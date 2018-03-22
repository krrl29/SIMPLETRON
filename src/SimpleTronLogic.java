public class SimpleTronLogic {


    private String[] command;
    private int[] memory;

    public SimpleTronLogic(){

        command = new String[100];
        memory = new int[100];

        //when submit instruction button pressed
        int increment = 0;
        //accept commands till given -99999
        while (jtxtSubmitInstruction.Text != "-99999"){
            command[increment] = jtxtSubmitInstruction.Text;
            increment++;
        }



    }

}
