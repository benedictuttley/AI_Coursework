package sokoban;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sokoban.GameState;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author steven
 */
public class PatternDatabase {

    GameState st;
    int stepsMatrix[][];
    GameState complexStateCopy;

    // Constructor needs to contain [1] initialisation and [2] population
    public PatternDatabase(GameState st) {
        this.st = st;
        // Need to keep a copy of the complex state:
        this.complexStateCopy = new GameState(st);

        // Matrix initialisation with dimension lengths equal to level width and height:
        this.stepsMatrix = new int[st.getMaxX()][st.getMaxY()];
        System.out.format("Level Width is %d, level height is %d\n",st.getMaxX(), st.getMaxY());
        System.out.println("The state is" + st);

        // Generate the relaxed state:
        // Let block position be i=3,j=3
        // Let player position be i=3, j=2
        // Is player position meant to be altered as well?
        // Test State:

            // Iterate over each row of the level:
            for (int row=0; row<st.getMaxX(); row++){
                // Iterate over each column of the level:
                for (int col=0; col<st.getMaxY(); col++){
                    //[1] SET UP THE RELAXED STATE
                    try {
                        this.st.setRelaxedState(row, col, st.playerX, st.playerY);
                        //[2] COMPUTE THE NUMBER OF STEPS TO SOLUTION
                        sokoban.SimpleSokobanAstarPlayer player = new sokoban.SimpleSokobanAstarPlayer(st);
                        //[3] INSERT REULT INTO stepsMatrix
                        stepsMatrix[row][col] = player.findPathToGoal().size();

                    }
                    catch (Exception e) {
                        // If the relaxed state is invalid, add infinity to corresponding db entry, represent infinity as (0) in the stepsMatrix
                        System.out.println("THIS IS AN INVALID RELAXED STATE!!");
                        stepsMatrix[row][col] = 0;
                    }
                }
            }

        System.out.println("Pattern Database Matrix:");
        for (int[] row:
             stepsMatrix) {
            System.out.println(Arrays.toString(row));

        }

        // Replace  mutated state with copy of original complex state:
        st = complexStateCopy;
        System.out.println(this.st);


//
//
//            this.st.setRelaxedState(3,3,4,3);
//            // Run Astar on this relaxed problem
//            sokoban.SimpleSokobanAstarPlayer player = new sokoban.SimpleSokobanAstarPlayer(st);
//            //player.showSolution();
//            System.out.println("NUMBER OF MOVES TO BE MADE: " + player.findPathToGoal().size());
//
//            // Attempt to populate matric with computed steps value trial:
//            stepsMatrix[3][3] = player.findPathToGoal().size();
//            System.out.println("WHAT IS GOING ON?");
//            System.out.format("Value stored in stepsMatrix is %d\n",stepsMatrix[3][3]);


            // At end need to revert state back to input (complex) state


        // Simple sokoban player
        //SimpleSokobanAstarPlayer relaxedPlayer = new SimpleSokobanAstarPlayer(STATE TO TEST);

    }

    //This is the method which you need to implement
    public int getEstimatedDistanceToGoal(List<Position> positions) {

      return 0;
    }
}


// TODO:
// [1] CHECK IF STATE CHANGE IS TRANSFERRED
// [2] ATTEMPT WITCH IN USE OF THE COPIED STATE