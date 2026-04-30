import java.awt.*;
import javax.swing.*;

public class analysisPage extends JPanel {

    private TypingRaceWindow parent;

    private Typist player;
    private int wpm;

    public analysisPage(TypingRaceWindow parent, Typist player, int wpm) {
        this.parent = parent;
        this.player = player;
        this.wpm = wpm;

        createAnalysisPage();
    }

    public void createAnalysisPage() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Race Analysis", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));

        JLabel nameLabel = new JLabel("Player: " + player.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel symbolLabel = new JLabel("Symbol: " + player.getSymbol(), JLabel.CENTER);

        JLabel accuracyLabel = new JLabel("Accuracy: " + player.getAccuracy(), JLabel.CENTER);

        JLabel wpmLabel = new JLabel("WPM: " + wpm, JLabel.CENTER);

        String performance;
        if (wpm < 30) {
            performance = "Beginner level";
        } else if (wpm < 60) {
            performance = "Intermediate level";
        } else {
            performance = "Advanced level";
        }

        JLabel performanceLabel = new JLabel("Performance: " + performance, JLabel.CENTER);


        centerPanel.add(nameLabel);
        centerPanel.add(symbolLabel);
        centerPanel.add(accuracyLabel);
        centerPanel.add(wpmLabel);
        centerPanel.add(performanceLabel);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton backButton = new JButton("Back to Results");
        JButton mainMenuButton = new JButton("Main Menu");

        backButton.addActionListener(e -> {
            parent.showResultsPage(player, wpm);
        });

        mainMenuButton.addActionListener(e -> {
            parent.showMainPage();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(mainMenuButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}