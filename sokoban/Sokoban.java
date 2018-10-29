package sokoban;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author steven
 */
public class Sokoban {

    // TODO - TEST ON SIMPLE LEVEL:

    public static void main(String[] args) throws Exception{
        GameState state = new GameState("levels/level1.txt");
//        HumanPlayer player = new HumanPlayer(state);
      SimpleSokobanAstarPlayer player = new SimpleSokobanAstarPlayer(state);
       //PatternHeuristicAstarPlayer player = new PatternHeuristicAstarPlayer(state);
        player.showSolution();

    }
        
}
