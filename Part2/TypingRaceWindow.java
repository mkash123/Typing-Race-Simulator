import java.awt.*;
import javax.swing.*;

public class TypingRaceWindow {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;


    //stores instances of the other pages that will be used
    private JPanel mainMenu;
    private RaceOptions raceOptionsPage;
    private TypistOptions typistOptionsPage;
    private RacePage racePage;
    private raceResults resultsPage;
    private analysisPage analysisPage;

    //initial values, will be changed later 
    private int numTypists = 3;
    private int passageLength = 60;
    private String passageText = "The quick brown fox jumps over the lazy dog.";

    private boolean caffeineOn;
    private boolean nightShiftOn;
    private boolean autoCorrectOn;

    private TypingRace race;
    private Typist[] typists;

    //creates the container for all the other pages
    public TypingRaceWindow() {
        frame = new JFrame("Typing Race Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createMainMenu();

        raceOptionsPage = new RaceOptions(this);
        typistOptionsPage = new TypistOptions(this);

        mainPanel.add(mainMenu, "Main");
        mainPanel.add(raceOptionsPage, "RaceOptions");
        mainPanel.add(typistOptionsPage, "TypistOptions");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createMainMenu() {
        mainMenu = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel title = new JLabel("Typing Race Simulator", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JButton startButton = new JButton("Start Race");
        startButton.addActionListener(e -> showRaceOptionsPage());

        mainMenu.add(title);
        mainMenu.add(startButton);
    }

    public void showMainPage() {
        cardLayout.show(mainPanel, "Main");
    }

    public void showRaceOptionsPage() {
        cardLayout.show(mainPanel, "RaceOptions");
    }

    public void showTypistOptionsPage() {
        cardLayout.show(mainPanel, "TypistOptions");
    }

    public void showRacePage() {
        racePage = new RacePage(this, race, typists);
        mainPanel.add(racePage, "Race");
        cardLayout.show(mainPanel, "Race");
    }

    public void showResultsPage(Typist winner, int wpm) {
        resultsPage = new raceResults(this, winner, wpm);
        mainPanel.add(resultsPage, "Results");
        cardLayout.show(mainPanel, "Results");
    }

    public void showAnalysisPage(Typist player, int wpm) {
        analysisPage = new analysisPage(this, player, wpm);
        mainPanel.add(analysisPage, "Analysis");
        cardLayout.show(mainPanel, "Analysis");
    }

    public void setRaceOptions(int numTypists,
                               String passageText,
                               boolean caffeineOn,
                               boolean nightShiftOn,
                               boolean autoCorrectOn) {

        this.numTypists = numTypists;
        this.passageText = passageText;
        this.passageLength = passageText.length();
        this.caffeineOn = caffeineOn;
        this.nightShiftOn = nightShiftOn;
        this.autoCorrectOn = autoCorrectOn;

        showTypistOptionsPage();
    }

    public void setTypistOptions(Typist player,
                                 String colour,
                                 String keyboard,
                                 String style,
                                 String accessories) {

        typists = new Typist[numTypists];
        typists[0] = player;

        char[] botSymbols = {'★', '⚡', '☂', '♠', '♫'};
        double[] botAccuracies = {0.70, 0.80, 0.65, 0.75, 0.60};

        for (int i = 1; i < numTypists; i++) {
            typists[i] = new Typist(botSymbols[i - 1], "Bot " + i, botAccuracies[i - 1]);
        }

        race = new TypingRace(passageLength, numTypists);

        for (int i = 0; i < typists.length; i++) {
            race.addTypist(typists[i], i + 1);
        }

        race.setAutoCorrect(autoCorrectOn);
        race.setCaffeineMode(caffeineOn);

        if (nightShiftOn) {
            race.applyNightShift();
        }

        showRacePage();
    }

    public int getPassageLength() {
        return passageLength;
    }

    public String getPassageText() {
        return passageText;
    }

    public boolean isCaffeineOn() {
        return caffeineOn;
    }

    public boolean isNightShiftOn() {
        return nightShiftOn;
    }

    public boolean isAutoCorrectOn() {
        return autoCorrectOn;
    }

    public static void startRaceGUI(){
        SwingUtilities.invokeLater(() -> new TypingRaceWindow());
    }

    public static void main(String[] args) {
        startRaceGUI();
    }
}
