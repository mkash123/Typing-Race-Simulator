import java.awt.CardLayout;
import javax.swing.*;


/**
 * This is the main class for the GUI of the Typing Race Simulator.
 * It acts as a holder for all the other key components.
 * @author Muhammad Khan
 */
public class TypingRaceWindow {
    
    JFrame mainFrame;
    CardLayout manager;
    JPanel mainPanel;


    int passageLength;
    int numTypists;
    boolean caffieneOn;
    boolean nightShiftOn;
    boolean autoCorrectOn;

    TypingRace race;

    public static void main(String args[]) {
        TypingRaceWindow app = new TypingRaceWindow();
        app.createGUI();
    }

    //creates the main container for all the pages
    public void createGUI(){
        mainFrame = new JFrame();
        mainFrame.setTitle("Typing Race Simulator");
        mainFrame.setSize(600,600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        manager = new CardLayout();
        mainPanel = new JPanel(manager);

        //create panels
        JPanel configPanel = new JPanel();
        configPanel.add(new JLabel("Configuration Screen"));
        
        JButton startButton = new JButton("Start Race");

        //button that switches screen
        startButton.addActionListener(e -> {
            startRace(new TypingRace(50)); 
        });

        configPanel.add(startButton);

        JPanel racePanel = new JPanel();
        racePanel.add(new JLabel("Race Screen"));

        JPanel resultsPanel = new JPanel();
        resultsPanel.add(new JLabel("Results Screen"));

        //add panels to card layout
        mainPanel.add(configPanel, "config");
        mainPanel.add(racePanel, "race");
        mainPanel.add(resultsPanel, "results");

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

        //show first screen
        showConfig();
    }

    //changes to the configuration page
    public void showConfig(){
        manager.show(mainPanel, "config");
    }

    //starts the race by changing to the start race page
    public void startRace(TypingRace race){
        this.race = race;
        manager.show(mainPanel, "race");
    }

    //cdisplays the panel for the results
    public void showResults(){
        manager.show(mainPanel, "results");
    }

    //returns the current race
    public TypingRace getRace(){
        return race;
    }


    public void showTypistPage(){
        System.out.print("Hello");
    }

    //finish this method with the race config variables assigned at the top 
    public void setRaceOptions(int passageLength,
                                int numTypists,
                                boolean caffieneOn,
                                boolean nightShiftOn,
                                boolean autoCorrectOn)
    {



    }


    public void setTypistOptions(String selectedColour, 
                                 String typistKeyboardSelected, 
                                 String selectedSymbol, 
                                 String typistStyleSelected, 
                                 String acceoriesSelected)
        {
            
        }


    


}