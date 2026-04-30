import java.awt.GridLayout;
import javax.swing.*;

public class TypistOptions extends JPanel {

    private TypingRaceWindow parent;

    public TypistOptions(TypingRaceWindow parent) {
        this.parent = parent;
        createTypistPanel();
    }

    public void createTypistPanel() {
        setLayout(new GridLayout(14, 1, 10, 10));

        //labels and combo boxes for the typist options
        JLabel title = new JLabel("Typist Setup", JLabel.CENTER);
        title.setToolTipText("How many typists you want in your race");

        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Enter Name"));

        JLabel colourLabel = new JLabel("Colour");
        colourLabel.setToolTipText("Sets the colour for a typist");

        String[] colours = {"Red", "Blue", "Orange", "Green", "Purple", "Yellow"};
        JComboBox<String> colourBox = new JComboBox<>(colours);

        JLabel symbolLabel = new JLabel("Symbol");
        symbolLabel.setToolTipText("A symbol used to represent the typist");

        String[] symbolsOptions = {"☀", "★", "♠", "♫", "☂", "⚡"};
        JComboBox<String> symbolBox = new JComboBox<>(symbolsOptions);
        symbolBox.setToolTipText("Symbol appears on screen during the race");

        JLabel keyboardLabel = new JLabel("Keyboard");
        keyboardLabel.setToolTipText("Keyboard type affects accuracy and typing performance");

        String[] keyboardOptions = {"Membrane", "Mechanical", "Touchscreen", "Stenography"};
        JComboBox<String> keyboardBox = new JComboBox<>(keyboardOptions);
        keyboardBox.setToolTipText("Mechanical improves accuracy, Touchscreen lowers it, Stenography gives a larger boost");

        JLabel styleLabel = new JLabel("Typing Style");
        styleLabel.setToolTipText("Typing style controls the typist's base accuracy");

        String[] typistStyleOptions = {"Touch Typist", "Hunt and Peck", "Phone Thumbs", "Voice-To-Text"};
        JComboBox<String> styleBox = new JComboBox<>(typistStyleOptions);
        styleBox.setToolTipText("Touch Typist is reliable, Hunt and Peck is slower, Voice-To-Text is highly accurate");

        JLabel accessoriesLabel = new JLabel("Accessories");
        accessoriesLabel.setToolTipText("Accessories provide small performance effects");

        String[] accessoriesOptions = {"None", "Wrist Support", "Energy Drink", "Noise-Cancelling Headphones"};
        JComboBox<String> accessoriesBox = new JComboBox<>(accessoriesOptions);
        accessoriesBox.setToolTipText("Wrist Support reduces burnout, Energy Drink boosts accuracy slightly, Headphones reduce mistakes");

        JButton submit = new JButton("Start Race");
        submit.setToolTipText("Create your typist and start the race.");

        //button to store values and move to the actual race
        submit.addActionListener(e -> {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                name = "Player";
            }

            char symbol = symbolBox.getSelectedItem().toString().charAt(0);
            String style = (String) styleBox.getSelectedItem();
            String keyboard = (String) keyboardBox.getSelectedItem();
            String accessory = (String) accessoriesBox.getSelectedItem();

            double accuracy = getAccuracyFromStyle(style);
            accuracy = applyKeyboardEffect(accuracy, keyboard);
            accuracy = applyAccessoryEffect(accuracy, accessory);

            Typist player = new Typist(symbol, name, accuracy);

            parent.setTypistOptions(
                    player,
                    (String) colourBox.getSelectedItem(),
                    keyboard,
                    style,
                    accessory
            );
        });

        add(title);
        add(nameField);
        add(colourLabel);
        add(colourBox);
        add(symbolLabel);
        add(symbolBox);
        add(keyboardLabel);
        add(keyboardBox);
        add(styleLabel);
        add(styleBox);
        add(accessoriesLabel);
        add(accessoriesBox);
        add(submit);
    }

    //method to apply the accuracy from chosen style
    private double getAccuracyFromStyle(String style) {
        switch (style) {
            case "Touch Typist":
                return 0.88;
            case "Hunt and Peck":
                return 0.58;
            case "Phone Thumbs":
                return 0.68;
            case "Voice-To-Text":
                return 0.92;
            default:
                return 0.70;
        }
    }


    //method to apply the keyboard effects
    private double applyKeyboardEffect(double accuracy, String keyboard) {
        if ("Mechanical".equals(keyboard)) {
            accuracy = accuracy + 0.04;
        } else if ("Touchscreen".equals(keyboard)) {
            accuracy = accuracy - 0.05;
        } else if ("Stenography".equals(keyboard)) {
            accuracy = accuracy + 0.08;
        }

        return limitAccuracy(accuracy);
    }

    //method to apply the accesories affects
    private double applyAccessoryEffect(double accuracy, String accessory) {
        if ("Energy Drink".equals(accessory)) {
            accuracy = accuracy + 0.03;
        } else if ("Noise-Cancelling Headphones".equals(accessory)) {
            accuracy = accuracy + 0.05;
        }

        return limitAccuracy(accuracy);
    }

    //method to ensure the accuracy can not go below 0 or above 1
    private double limitAccuracy(double accuracy) {
        if (accuracy < 0.0) {
            return 0.0;
        }

        if (accuracy > 1.0) {
            return 1.0;
        }

        return accuracy;
    }
}