import javax.swing.*;
/**
 * The page where all the race options are chosen.
 * The typist chooses the length of the race and the extra difficulty options.
 * @author Muhammad Khan
 */


//use setToolTipText to add a hover feature to see extra info about a field in all classes.
public class RaceOptions extends JPanel {
    TypingRaceWindow parent;
    JComboBox<Integer> passageLength;
    JComboBox<Integer> numTypistsBox;

    JCheckBox autoCorrectToggle;
    JCheckBox caffieneMode;
    JCheckBox nightShift;
    
    

    @SuppressWarnings("OverridableMethodCallInConstructor")
     public RaceOptions(TypingRaceWindow parent) {
        this.parent = parent;

        createOptionPanel();
    }

    public void createOptionPanel(){
        JLabel lengthOption = new JLabel("Choose your length");
    
        passageLength = new JComboBox<>(new Integer[]{30,60,100});

        JLabel typistLabel = new JLabel("Choose number of typists");

        numTypistsBox = new JComboBox<>(new Integer[]{2, 3, 4, 5, 6});

        JLabel difficultyModifiers = new JLabel("Choose the extra difficulty settings");

        autoCorrectToggle = new JCheckBox("Auto Correct On?");
        caffieneMode = new JCheckBox("Caffiene mode on?");
        nightShift = new JCheckBox("Night mode on?");



        JButton nextButton = new JButton("Advance to Typist Settings");


        nextButton.addActionListener(e->{
            int numTypists = (int) numTypistsBox.getSelectedItem();
            int length = (int) passageLength.getSelectedItem();

            boolean autoCorrectOn = autoCorrectToggle.isSelected();
            boolean caffieneOn = caffieneMode.isSelected();
            boolean nightShiftOn = nightShift.isSelected();

            //fill this in with variables above
            parent.setRaceOptions(numTypists, length, caffieneOn, nightShiftOn, autoCorrectOn);
        });

        add(difficultyModifiers);
        add(typistLabel);
        add(numTypistsBox);
        add(lengthOption);
        add(passageLength);
        add(nextButton);
        add(autoCorrectToggle);
        add(caffieneMode);
        add(nightShift);

    }    
}
