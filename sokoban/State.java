package sokoban;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author steven
 * 
 */
public interface State {
    
    public List<Action> getLegalActions();
    public boolean isSolved();
    public void printState();
    public boolean isLegal(Action action);
    public State doAction(Action action);
    public boolean equals(Object obj);
}
