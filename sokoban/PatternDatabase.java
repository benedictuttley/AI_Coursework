package sokoban;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sokoban.GameState;

import java.lang.reflect.Array; // TODO: Try to use this
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author steven
 */
public class PatternDatabase {

    GameState st;
    int stepsMatrix[][];
    GameState complexStateCopy;
    static int o = 0;



    // Constructor needs to contain [1] initialisation and [2] population
    public PatternDatabase(GameState st) {
        this.st = st;
        // Need to keep a copy of the complex state:
        this.complexStateCopy = new GameState(st);

        // Matrix initialisation with dimension lengths equal to level width and height:
        this.stepsMatrix = new int[st.getMaxX()][st.getMaxY()];

        // Generate the relaxed state:
        // Let block position be i=3,j=3
        // Let player position be i=3, j=2
        // Is player position meant to be altered as well?
        // Test State:

        // Iterate over each row of the level:
        for (int row=0; row<this.complexStateCopy.getMaxX(); row++){
            // Iterate over each column of the level:
            for (int col=0; col<this.complexStateCopy.getMaxY(); col++){
                //[1] SET UP THE RELAXED STATE

                // Currently setting player position to current player position:

                // Check if block move to that position is allowed:

                Integer[] adjacentPositionResults = {1000,1000,1000,1000};

                if(((row+1) < this.complexStateCopy.getMaxX()-1) && !this.complexStateCopy.isWall(row+1, col)){
                // Then run relaxed simulation
                adjacentPositionResults[0] = runRelaxedSimulation(row, col, row+1, col);


                }

                if((row > 0)  && !this.complexStateCopy.isWall(row-1, col)){
                    // Then run relaxed simulation
                    adjacentPositionResults[1] = runRelaxedSimulation(row, col, row-1, col);


                }

                if((col < this.complexStateCopy.getMaxY()-1) &&!this.complexStateCopy.isWall(row, col+1)){
                    // Then run relaxed simulation
                    adjacentPositionResults[2] = runRelaxedSimulation(row, col, row, col+1);


                }

                if((col > 0) && !this.complexStateCopy.isWall(row, col-1)){
                    // Then run relaxed simulation
                    adjacentPositionResults[3] = runRelaxedSimulation(row, col, row, col-1);

                }
                //}


//                        this.complexStateCopy.setRelaxedState(row, col, st.playerX, st.playerY);
//                        //[2] COMPUTE THE NUMBER OF STEPS TO SOLUTION
//                        sokoban.SimpleSokobanAstarPlayer player = new sokoban.SimpleSokobanAstarPlayer(complexStateCopy, false);
//                        //[3] INSERT REULT INTO stepsMatrix

                int pathCostForOptimalPlayerPostion = Collections.min(Arrays.asList(adjacentPositionResults));
                stepsMatrix[row][col] = pathCostForOptimalPlayerPostion;

            }
        }

        System.out.println("Pattern Database Matrix:");
        for (int[] row : stepsMatrix) {
            System.out.println(Arrays.toString(row));
        }

        // Replace  mutated state with copy of original complex state:
        //st = this.complexStateCopy;
        //System.out.println("THE COMPLEX STATE COPY IS: " + this.complexStateCopy);
        // System.out.println(this.st);


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

    public int runRelaxedSimulation(int blockX, int blockY, int playerX, int playerY) {



        try {
            // Currently setting player position to current player position:


            // CHECK THIS...
            this.complexStateCopy.setRelaxedState(blockX, blockY, playerX, playerY);
            sokoban.SimpleSokobanAstarPlayer player = new sokoban.SimpleSokobanAstarPlayer(this.complexStateCopy, false);
            return player.findPathToGoal().size()-1;

        }
        catch (Exception e) {
            // If the relaxed state is invalid, add infinity to corresponding db entry, represent infinity as (0) in the stepsMatrix
            return Integer.MAX_VALUE;
        }




    }

    //This is the method which you need to implement
    public int getEstimatedDistanceToGoal(List<Position> positions) {


        int sumCost = 0;
        for (Position position:
             positions) {
           sumCost += stepsMatrix[position.getX()][position.getY()];
        }

      return sumCost;
    }
}

