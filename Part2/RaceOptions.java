import java.awt.*;
import javax.swing.*;

public class RaceOptions extends JPanel {

    private TypingRaceWindow parent;

    private JComboBox<String> passageBox;
    private JTextField customPassageField;
    private JComboBox<Integer> numTypistsBox;

    private JCheckBox autoCorrectToggle;
    private JCheckBox caffeineMode;
    private JCheckBox nightShift;

    public RaceOptions(TypingRaceWindow parent) {
        this.parent = parent;
        createOptionPanel();
    }

    public void createOptionPanel() {
        setLayout(new GridLayout(12, 1, 10, 10));

        //labels and comboboxes for the race options
        JLabel title = new JLabel("Race Options", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel passageLabel = new JLabel("Choose passage");
        passageLabel.setToolTipText("Choose between a length or create your own passage");

        passageBox = new JComboBox<>(new String[]{
            "Short: The quick brown fox jumps over the lazy dog.",
            "Medium: The quick brown fox jumps over the lazy dog while the tortoise slowly ventures the grass.",
            "Long: The quick brown fox jumps over the lazy dog while the tortoise slowly ventures the grass and eats the lettuce found in the bushes. ",
            "Custom"
        });
        passageBox.setToolTipText("Select the passage you want the race to be of");

        customPassageField = new JTextField();
        customPassageField.setBorder(BorderFactory.createTitledBorder("Custom passage"));
        customPassageField.setToolTipText("Type your own passage here");

        JLabel typistLabel = new JLabel("Choose number of typists");
        typistLabel.setToolTipText("Choose how many typists compete");

        numTypistsBox = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6});

        JLabel difficultyModifiers = new JLabel("Difficulty Settings");
        difficultyModifiers.setToolTipText("These modifiers affect every typist in the race");

        autoCorrectToggle = new JCheckBox("Auto Correct");
        autoCorrectToggle.setToolTipText("When enabled, mistypes slide typists back by half the normal amount");

        caffeineMode = new JCheckBox("Caffeine Mode");
        caffeineMode.setToolTipText("Gives a temporary speed boost for the first 10 turns, but increases burnout risk");

        nightShift = new JCheckBox("Night Shift");
        nightShift.setToolTipText("Slightly reduces all typists' accuracy because everyone is tired");

        JButton nextButton = new JButton("Next");
        nextButton.setToolTipText("Save race settings and move to typist setup");

        //button to advance to the typist options
        nextButton.addActionListener(e -> {
            int numTypists = (int) numTypistsBox.getSelectedItem();

            String selectedPassage = (String) passageBox.getSelectedItem();
            String passage;

            if ("Custom".equals(selectedPassage)) {
                passage = customPassageField.getText();

                if (passage.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a custom passage");
                    return;
                }
            } else {
                passage = selectedPassage.substring(selectedPassage.indexOf(":") + 2);
            }

            boolean autoCorrectOn = autoCorrectToggle.isSelected();
            boolean caffeineOn = caffeineMode.isSelected();
            boolean nightShiftOn = nightShift.isSelected();

            parent.setRaceOptions(
                    numTypists,
                    passage,
                    caffeineOn,
                    nightShiftOn,
                    autoCorrectOn
            );
        });

        add(title);
        add(passageLabel);
        add(passageBox);
        add(customPassageField);
        add(typistLabel);
        add(numTypistsBox);
        add(difficultyModifiers);
        add(autoCorrectToggle);
        add(caffeineMode);
        add(nightShift);
        add(nextButton);
    }
}