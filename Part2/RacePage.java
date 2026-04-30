import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class RacePage extends JPanel {

    private TypingRaceWindow parent;

    private TypingRace race;
    private Typist player;
    private Typist[] typists;

    private String textToType;

    private long startTime;
    private boolean started = false;

    private JTextPane textPane;
    private JTextField inputField;
    private JLabel wpmLabel;

    private JProgressBar[] typistBars;
    private Timer raceTimer;

    public RacePage(TypingRaceWindow parent, TypingRace race, Typist[] typists) {
        this.parent = parent;
        this.race = race;
        this.typists = typists;
        this.player = typists[0];
        this.textToType = parent.getPassageText();

        createRacePage();
        startRaceLoop();
    }

    public void createRacePage() {
        setLayout(new BorderLayout());

        //labels and text area for the typist to enter the text into
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Typing Race!", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setToolTipText("Type the passage in the text area below");

        wpmLabel = new JLabel("WPM: 0", JLabel.RIGHT);

        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(wpmLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());

        textPane = new JTextPane();
        textPane.setText(textToType);
        textPane.setEditable(false);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textPane.setToolTipText("Green letters are correct. Red letters are incorrect");

        centerPanel.add(new JScrollPane(textPane), BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setToolTipText("Type the passage here");
        centerPanel.add(inputField, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        //leaderboard panel to show the typists accuracy so far
        JPanel leaderboardPanel = new JPanel(new GridLayout(typists.length, 1));

        typistBars = new JProgressBar[typists.length];

        for (int i = 0; i < typists.length; i++) {
            typistBars[i] = new JProgressBar(0, textToType.length());
            typistBars[i].setStringPainted(true);
            typistBars[i].setString(typists[i].getSymbol() + " " + typists[i].getName());
            typistBars[i].setToolTipText("Progress for " + typists[i].getName());

            leaderboardPanel.add(typistBars[i]);
        }

        add(leaderboardPanel, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();

        JButton backButton = new JButton("Back");
        backButton.setToolTipText("Return to the main menu");

        JButton finishButton = new JButton("Finish");
        finishButton.setToolTipText("End the race manually");

        bottomPanel.add(backButton);
        bottomPanel.add(finishButton);

        add(bottomPanel, BorderLayout.SOUTH);

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleTyping();
            }
        });

        backButton.addActionListener(e -> parent.showMainPage());
        finishButton.addActionListener(e -> finishRace());

        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    private void startRaceLoop() {
        raceTimer = new Timer(200, e -> {
            race.tick();
            updateLeaderboard();

            Typist winner = race.getWinner();
            if (winner != null) {
                raceTimer.stop();
                finishRace();
            }
        });

        raceTimer.start();
    }

    //method to check the typists accuracy(ie typed right or wrong)
    private void handleTyping() {
        String typed = inputField.getText();

        if (!started) {
            startTime = System.currentTimeMillis();
            started = true;
        }

        StyledDocument doc = textPane.getStyledDocument();

        Style correct = textPane.addStyle("Correct", null);
        StyleConstants.setForeground(correct, Color.GREEN);

        Style incorrect = textPane.addStyle("Incorrect", null);
        StyleConstants.setForeground(incorrect, Color.RED);

        Style normal = textPane.addStyle("Normal", null);
        StyleConstants.setForeground(normal, Color.BLACK);

        player.resetToStart();

        for (int i = 0; i < textToType.length(); i++) {
            if (i < typed.length()) {
                if (typed.charAt(i) == textToType.charAt(i)) {
                    doc.setCharacterAttributes(i, 1, correct, true);
                    player.typeCharacter();
                } else {
                    doc.setCharacterAttributes(i, 1, incorrect, true);

                    if (parent.isAutoCorrectOn()) {
                        player.slideBack(1);
                    } else {
                        player.slideBack(2);
                    }
                }
            } else {
                doc.setCharacterAttributes(i, 1, normal, true);
            }
        }

        updateWPM(player.getProgress());
        updateLeaderboard();

        if (typed.equals(textToType)) {
            finishRace();
        }
    }

    private void updateLeaderboard() {
        for (int i = 0; i < typists.length; i++) {
            typistBars[i].setValue(Math.min(typists[i].getProgress(), textToType.length()));
            typistBars[i].setString(
                    typists[i].getSymbol()
                            + " "
                            + typists[i].getName()
                            + " - "
                            + typists[i].getProgress()
                            + "/"
                            + textToType.length()
            );
        }
    }

    private void updateWPM(int charsTyped) {
        if (!started) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        double minutes = (currentTime - startTime) / 60000.0;

        if (minutes > 0) {
            int wpm = (int) ((charsTyped / 5.0) / minutes);
            wpmLabel.setText("WPM: " + wpm);
        }
    }

    private void finishRace() {
        if (raceTimer != null) {
            raceTimer.stop();
        }

        if (!started) {
            startTime = System.currentTimeMillis();
            started = true;
        }

        long currentTime = System.currentTimeMillis();
        double minutes = (currentTime - startTime) / 60000.0;

        int finalWPM = 0;

        if (minutes > 0) {
            finalWPM = (int) ((player.getProgress() / 5.0) / minutes);
        }

        Typist winner = race.getWinner();

        if (winner == null) {
            winner = player;
        }

        parent.showResultsPage(winner, finalWPM);
    }
}