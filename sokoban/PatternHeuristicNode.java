package sokoban;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sokoban.*;

/**
 *
 * @author steven
 */
public class PatternHeuristicNode extends AstarNode {
    
     PatternDatabase pdb;
    
    public PatternHeuristicNode(GameState st, Node previousNode, Action lastAction, PatternDatabase pdb) {
        super(st,previousNode,lastAction);
        this.pdb=pdb;
    }
        
    int getEstimatedDistanceToGoal()
    {

        return pdb.getEstimatedDistanceToGoal(((GameState)st).getBlockPositions());
    }   
}
