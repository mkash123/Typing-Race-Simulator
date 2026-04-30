import java.awt.*;
import javax.swing.*;

public class raceResults extends JPanel {

    private TypingRaceWindow parent;

    private Typist winner;
    private int winnerWPM;

    public raceResults(TypingRaceWindow parent, Typist winner, int winnerWPM) {
        this.parent = parent;
        this.winner = winner;
        this.winnerWPM = winnerWPM;

        createResultsPage();
    }

    public void createResultsPage() {
        setLayout(new BorderLayout());
        //creates the labels to show the results of the race(who won)
        JLabel titleLabel = new JLabel("Race Results", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));

        JLabel winnerLabel = new JLabel("Winner: " + winner.getName(), JLabel.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel symbolLabel = new JLabel("Symbol: " + winner.getSymbol(), JLabel.CENTER);

        JLabel accuracyLabel = new JLabel("Accuracy: " + winner.getAccuracy(), JLabel.CENTER);

        JLabel wpmLabel = new JLabel("WPM: " + winnerWPM, JLabel.CENTER);

        centerPanel.add(winnerLabel);
        centerPanel.add(symbolLabel);
        centerPanel.add(accuracyLabel);
        centerPanel.add(wpmLabel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton backButton = new JButton("Back to Main Menu");
        JButton statsButton = new JButton("View Stats");

        backButton.addActionListener(e -> parent.showMainPage());

        statsButton.addActionListener(e -> 
            parent.showAnalysisPage(winner, winnerWPM)
        );

        buttonPanel.add(backButton);
        buttonPanel.add(statsButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}