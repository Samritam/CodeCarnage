/*
 * Copyright (c) 2017. Licensed under the Apache License 2.0.
 * For full copyright, licensing, and sourcing information,
 * please refer to the CodeCarnage GitHub repository's README.md file
 * (found on https://github.com/j3kstrum/CodeCarnage).
 */

package interpreter;

import utilties.models.*;

/**
 * Class used to compare data, in order to evaluate if a function should be allowed to proceed.
 *
 * How to use: after parsing the GUI for conditional input, create appropriate enums, and pass them
 * into the constructor of the Check class. Then, create a list of all of the checks, and pass them
 * into the appropriate command class.
 * For user input, use the appropriate constructor with an integer input.
 *
 * The method 'conditionIsTrue' is used by the AbstractCommand to determine if the command should proceed.
 * Each check has to return true for the command to execute.
 *
 */
public class Check {

    private Data data1;
    private Data data2;
    private Operator operator;

    /**
     *
     * Default constructor when not dealing with user input.
     *
     * @param data1 First data element
     * @param data2 Second data element
     * @param operator  Operator for comparison
     */
    public Check(Data data1, Data data2, Operator operator) {
        this.data1 = data1;
        this.data2 = data2;
        this.operator = operator;
    }


    /**
     * Based on the current game state data, returns if condition is true.
     *
     * @param game takes in the model
     * @return if defined condition is true
     */
    public boolean conditionIsTrue(Game game){

        int data1 = getData(this.data1, game);
        int data2 = getData(this.data2, game);

        switch (this.operator){
            case LessThan:
                return data1 < data2;
            case GreaterThan:
                return data1 > data2;
            case LessThanOrEqualTo:
                return data1 <= data2;
            case GreaterThanOrEqualTo:
                return data1 >= data2;
            case EqualTo:
                return data1 == data2;
            default:
                return false;  // should never happen, but Java is Java
        }
    }


    /**
     * Based on the current game state data, returns if condition is true.
     *
     * @param data takes in the data to be returned
     * @param game is the pointer to the game model
     * @return the integer value of the game data that has been looked up
     */
    public int getData(Data data, Game game){
        switch(data){
            case UserHealth:
                return game.getPlayer(Game.PLAYER_ID).getHealth();
            case OpponentHealth:
                return game.getPlayer(Game.OPPONENT_ID).getHealth();
            case DistanceFromOpponent:
                return (int)Math.round(game.distanceToOpponent(Game.PLAYER_ID, Game.OPPONENT_ID));
            default:
                return -1;  //This "should" never happen, if it does, fix it!
        }
    }
}
