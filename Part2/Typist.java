/**
 * Write a description of class Typist here.
 *
 * Starter code generously abandoned by Ty Posaurus, your predecessor,
 * who typed with two fingers and considered that "good enough".
 * He left a sticky note: "the slide-back thing is optional probably".
 * It is not optional. Good luck.
 *
 * @author (Muhammad Khan)
 * @version (10/04/2026)
 */
public class Typist
{
    // Fields of class Typist
    // Hint: you will need six fields. Think carefully about their types.
    // One of them tracks how far along the passage the typist has reached.
    // Another tracks whether the typist is currently burnt out.
    // A third tracks HOW MANY turns of burnout remain (not just whether they are burnt out).
    // The remaining three should be fairly obvious.

    private final String typistName;
    private char typistSymbol;
    private double typistAccuracy;
    private int typistProgress;
    private boolean isBurntOut;
    private int burnoutTurnsRemaining;

    // Constructor of class Typist
    /**
     * Constructor for objects of class Typist.
     * Creates a new typist with a given symbol, name, and accuracy rating.
     *
     * @param typistSymbol  a single Unicode character representing this typist (e.g. '①', '②', '③')
     * @param typistName    the name of the typist (e.g. "TURBOFINGERS")
     * @param typistAccuracy the typist's accuracy rating, between 0.0 and 1.0
     */
    public Typist(char typistSymbol, String typistName, double typistAccuracy)
    {
        this.typistSymbol = typistSymbol;
        this.typistName = typistName;

        if (typistAccuracy < 0.0) {
            this.typistAccuracy = 0.0;
        } else if (typistAccuracy > 1.0) {
            this.typistAccuracy = 1.0;
        } else {
            this.typistAccuracy = typistAccuracy;
        }
    }


    // Methods of class Typist

    /**
     * Sets this typist into a burnout state for a given number of turns.
     * A burnt-out typist cannot type until their burnout has worn off.
     *
     * @param turns the number of turns the burnout will last
     */
    public void burnOut(int turns)
    {
        if (turns > 0) {
            this.isBurntOut = true;
            this.burnoutTurnsRemaining = turns;
        }
        

    }

    /**
     * Reduces the remaining burnout counter by one turn.
     * When the counter reaches zero, the typist recovers automatically.
     * Has no effect if the typist is not currently burnt out.
     */
    public void recoverFromBurnout()
    {
        if (burnoutTurnsRemaining > 0){
            burnoutTurnsRemaining = burnoutTurnsRemaining - 1;
        }

        if (burnoutTurnsRemaining == 0) {
            isBurntOut = false;
        } 
    }

    /**
     * Returns the typist's accuracy rating.
     *
     * @return accuracy as a double between 0.0 and 1.0
     */
    public double getAccuracy()
    {
        return typistAccuracy; 
    }

    /**
     * Returns the typist's current progress through the passage.
     * Progress is measured in characters typed correctly so far.
     * Note: this value can decrease if the typist mistypes.
     *
     * @return progress as a non-negative integer
     */
    public int getProgress()
    {
        return typistProgress; 
    }

    /**
     * Returns the name of the typist.
     *
     * @return the typist's name as a String
     */
    public String getName()
    {
        return typistName; 
    }

    /**
     * Returns the character symbol used to represent this typist.
     *
     * @return the typist's symbol as a char
     */
    public char getSymbol()
    {
        return typistSymbol; 
    }

    /**
     * Returns the number of turns of burnout remaining.
     * Returns 0 if the typist is not currently burnt out.
     *
     * @return burnout turns remaining as a non-negative integer
     */
    public int getBurnoutTurnsRemaining()
    {
        return burnoutTurnsRemaining; 
    }

    /**
     * Resets the typist to their initial state, ready for a new race.
     * Progress returns to zero, burnout is cleared entirely.
     */
    public void resetToStart()
    {
        typistProgress = 0;
        burnoutTurnsRemaining = 0;
        isBurntOut = false;

    }

    /**
     * Returns true if this typist is currently burnt out, false otherwise.
     *
     * @return true if burnt out
     */
    public boolean isBurntOut()
    {
        return isBurntOut; 
    }

    /**
     * Advances the typist forward by one character along the passage.
     * Should only be called when the typist is not burnt out.
     */
    public void typeCharacter()
    {
        if (isBurntOut == false){
            typistProgress++;
        }

    }

    /**
     * Moves the typist backwards by a given number of characters (a mistype).
     * Progress cannot go below zero — the typist cannot slide off the start.
     *
     * @param amount the number of characters to slide back (must be positive)
     */
    public void slideBack(int amount)
    {
        typistProgress = typistProgress - amount;

        if (typistProgress < 0) {
            typistProgress = 0;
        }

    }

    /**
     * Sets the accuracy rating of the typist.
     * Values below 0.0 should be set to 0.0; values above 1.0 should be set to 1.0.
     *
     * @param newAccuracy the new accuracy rating
     */
    public void setAccuracy(double newAccuracy)
    {
        if (newAccuracy<0){
            typistAccuracy = 0;
        }
        else if(newAccuracy>1){
            typistAccuracy = 1;
        }
        else{
            typistAccuracy = newAccuracy;
        }

    }

    /**
     * Sets the symbol used to represent this typist.
     *
     * @param newSymbol the new symbol character
     */
    public void setSymbol(char newSymbol)
    {
        typistSymbol = newSymbol;
    }


    /**
     * A main class used to test the functionality of the Typist class.
     * @param args not used
     */
    public static void main(String[] args) {

        Typist t = new Typist('W', "Muhammad", 0.5);

        //test 1: progress cannot go below 0.
        System.out.println ("TEST 1: slideBack() cannot go below zero");
        t.resetToStart();
        t.slideBack(5);
        System.out.println("Progress (should be 0): " + t.getProgress());

        //test 2: setting burnouts and state. 
        System.out.println("TEST 2: burnout countdown and clearing");
        t.resetToStart();
        t.burnOut(3);
        System.out.println("Burnout turns remaining: " + t.getBurnoutTurnsRemaining());
        System.out.println("Is the typist burnt out? : " + t.isBurntOut());

        //recovering from the three turns of burnout
        t.recoverFromBurnout();
        System.out.println("1 turn has passed: " + t.getBurnoutTurnsRemaining());

        t.recoverFromBurnout();
        System.out.println("2 turns have passed:  " + t.getBurnoutTurnsRemaining());

        t.recoverFromBurnout();
        System.out.println("3 turns have passed: " + t.getBurnoutTurnsRemaining());
        System.out.println("Is burnt out?: " + t.isBurntOut());

        //test 3: checking to see if reset works
        System.out.println("TEST 3: resetToStart (clears progress and burnout)");
        t.typeCharacter();
        t.typeCharacter();
        t.burnOut(2);

        t.resetToStart();
        System.out.println("Progress: " + t.getProgress());
        System.out.println("Burnout turns: " + t.getBurnoutTurnsRemaining());
        System.out.println("Is the typist burnt out?: " + t.isBurntOut());

        //test 4: checking to see if the accuracy sets only between 0 and 1.
        System.out.println("TEST 4: accuracy bounds (0.0 - 1.0) ");
        //testing something too high
        t.setAccuracy(1.5);
        System.out.println("Accuracy ()>1) set: " + t.getAccuracy());

        //testing something too low
        t.setAccuracy(-0.5);
        System.out.println("Accuracy (<0) set: " + t.getAccuracy());

        //testing something valid
        t.setAccuracy(0.75);
        System.out.println("Valid accuracy: " + t.getAccuracy());

        //test 5: ensuring progress works as intended
        System.out.println("TEST 5: normal forward movement ");
        t.resetToStart();
        t.typeCharacter();
        t.typeCharacter();
        t.typeCharacter();
        System.out.println("Progress after typing 3 times: " + t.getProgress());

        //test 6: during burnout the typist should not be able to increase progress
        System.out.println("TEST 6: typing does NOT work during burnout");
        t.burnOut(2);
        t.typeCharacter(); // should not increase
        System.out.println("Progress during burnout: " + t.getProgress());
    }

} 



