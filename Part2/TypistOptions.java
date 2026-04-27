import javax.swing.*;
/**
 * The page where all the typist settings are done
 * On this page, the typist can select their colour, sybmol and other keyboard settings
 * @author Muhammad Khan
 */

public class TypistOptions extends JPanel{
    @SuppressWarnings("unused")
    TypingRaceWindow parent;

    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public TypistOptions(TypingRaceWindow parent){
        this.parent = parent;
        createTypistPanel();
    }

    public void createTypistPanel(){
            JLabel typistOptionsLabel = new JLabel("Choose the options for the typist below");

            //only 6 colours and 6 symbols needed as that is max number of typists

            //could be improved later with a proper colour picker?
            JLabel chooseColour = new JLabel("Choose the typists colour");
            String[] colours = {"Red", "Blue", "Orange", "Green", "Purple", "Yellow"};
            JComboBox<String> colourBox = new JComboBox<>(colours);
        
            JLabel keyboardSelection = new JLabel("Choose the keyboard type");
            String[] keyboardOptions = {"Membrane", "Mechanical", "Touchscreen"};
            JComboBox<String> typistBox = new JComboBox<>(keyboardOptions);

            JLabel chooseSymbol = new JLabel("Choose a symbol");
            String[] symbolsOptions = {"☀", "★", "♠", "♫", "☂", "⚡"};
            JComboBox<String> symbolBox = new JComboBox<>(symbolsOptions);
            
            
            JLabel typistStyle = new JLabel("Choose typist style");
            String[] typistStyleOptions = {"Touch Typist","Hunt and Peck","Phone Thumbs","Voice-To-Text"};
            JComboBox<String> typistStyleBox = new JComboBox<>(typistStyleOptions);

            JLabel typistAccesories = new JLabel("Choose typists accesories");
            String[] accesoriesOptions = {"Wrist support", "Energy drink", "Noise cancelling headphones"};
            JComboBox<String> accesoriesBox = new JComboBox<>(accesoriesOptions);

            JButton submitButtonOptions = new JButton("Submit and move onto the race");

            submitButtonOptions.addActionListener(e -> {
                String selectedColour = (String) colourBox.getSelectedItem();
                String typistKeyboardSelected = (String) typistBox.getSelectedItem();
                String selectedSymbol = (String) symbolBox.getSelectedItem();
                String typistStyleSelected = (String) typistStyleBox.getSelectedItem();
                String acceoriesSelected = (String) accesoriesBox.getSelectedItem();

                parent.setTypistOptions(selectedColour, typistKeyboardSelected, selectedSymbol, typistStyleSelected, acceoriesSelected);
            });

            //add setToolTipText to the options pages to highlight what each thing does, have a label at the top that explains this

            add(typistOptionsLabel);

            add(chooseColour);
            add(colourBox);

            add(chooseSymbol);
            add(symbolBox);

            add(keyboardSelection);
            add(typistBox);

            add(typistStyle);
            add(typistStyleBox);

            add(typistAccesories);
            add(accesoriesBox);

            add(submitButtonOptions);

    }
}


