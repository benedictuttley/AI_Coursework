package sokoban;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author steven
 */
public class Sokoban {
   
    public static void main(String[] args) throws Exception{
        GameState state = new GameState("levels/level1.txt");
//        sokoban.HumanPlayer player = new sokoban.HumanPlayer(state);
//        SimpleSokobanAstarPlayer player = new SimpleSokobanAstarPlayer(state);
        sokoban.PatternHeuristicAstarPlayer player = new sokoban.PatternHeuristicAstarPlayer(state);
        player.showSolution();
    }
        
}
