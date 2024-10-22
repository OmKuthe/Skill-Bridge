//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class BuildProfilePage extends JPanel {
//    String skillknown;
//    String skillwant;
//
//    private JTextField fullNameField;
//    private JTextField ageField;
//    private JTextField contactField;
//
//    private JRadioButton maleButton;
//    private JRadioButton femaleButton;
//    private JRadioButton otherButton;
//
//    // Checkboxes for Skillsknown
//    private JCheckBox skillsknownjavaCheckBox;
//    private JCheckBox skillsknownjavascriptCheckBox;
//    private JCheckBox skillsknownpythonCheckBox;
//    private JCheckBox skillsknownsqlCheckBox;
//    private JCheckBox skillsknownreactCheckBox;
//    private JCheckBox skillsknownmlCheckBox;
//
//    // Checkboxes for Skillswanted
//    private JCheckBox skillswantedjavaCheckBox;
//    private JCheckBox skillswantedjavascriptCheckBox;
//    private JCheckBox skillswantedpythonCheckBox;
//    private JCheckBox skillswantedsqlCheckBox;
//    private JCheckBox skillswantedreactCheckBox;
//    private JCheckBox skillswantedmlCheckBox;
//
//
//    public JPanel BuildProfilePage(int userId) {
//        setSize(400, 600);
//        setLayout(new GridLayout(0, 1, 10, 10));
//        setBackground(new Color(240, 248, 255)); // Light background
//
//        // Full Name 1 error here is that username and fullname should be same it can b different but it causes errors
//        add(new JLabel("Full Name:"));
//        fullNameField = new JTextField();
//        add(fullNameField);
//
//        // Gender
//        add(new JLabel("Gender:"));
//        maleButton = new JRadioButton("Male");
//        femaleButton = new JRadioButton("Female");
//        otherButton = new JRadioButton("Other");
//        ButtonGroup genderGroup = new ButtonGroup();
//        genderGroup.add(maleButton);
//        genderGroup.add(femaleButton);
//        genderGroup.add(otherButton);
//        JPanel genderPanel = new JPanel(new FlowLayout());
//        genderPanel.setBackground(new Color(240, 248, 255));
//        genderPanel.add(maleButton);
//        genderPanel.add(femaleButton);
//        genderPanel.add(otherButton);
//        add(genderPanel);
//
//        // Age
//        add(new JLabel("Age:"));
//        ageField = new JTextField();
//        add(ageField);
//
//        // Contact Info
//        add(new JLabel("Contact:"));
//        contactField = new JTextField();
//        add(contactField);
//
//        // Skills Checkboxes for Known Skills
//        add(new JLabel("Select Skills Known:"));
//        JPanel knownSkillsPanel = new JPanel();
//        knownSkillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        skillsknownjavaCheckBox = new JCheckBox("Java");
//        skillsknownjavascriptCheckBox = new JCheckBox("JavaScript");
//        skillsknownpythonCheckBox = new JCheckBox("Python");
//        skillsknownsqlCheckBox = new JCheckBox("SQL");
//        skillsknownreactCheckBox = new JCheckBox("React");
//        skillsknownmlCheckBox = new JCheckBox("Machine Learning");
//
//        // Add checkboxes to the panel
//        knownSkillsPanel.add(skillsknownjavaCheckBox);
//        knownSkillsPanel.add(skillsknownjavascriptCheckBox);
//        knownSkillsPanel.add(skillsknownpythonCheckBox);
//        knownSkillsPanel.add(skillsknownsqlCheckBox);
//        knownSkillsPanel.add(skillsknownreactCheckBox);
//        knownSkillsPanel.add(skillsknownmlCheckBox);
//        add(knownSkillsPanel);
//
//        // Skills Checkboxes for Wanted Skills
//        add(new JLabel("Select Skills Wanted:"));
//        JPanel wantedSkillsPanel = new JPanel();
//
//        skillswantedjavaCheckBox = new JCheckBox("Java");
//        skillswantedjavascriptCheckBox = new JCheckBox("JavaScript");
//        skillswantedpythonCheckBox = new JCheckBox("Python");
//        skillswantedsqlCheckBox = new JCheckBox("SQL");
//        skillswantedreactCheckBox = new JCheckBox("React");
//        skillswantedmlCheckBox = new JCheckBox("Machine Learning");
//
//        wantedSkillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        wantedSkillsPanel.add(skillswantedjavaCheckBox);
//        wantedSkillsPanel.add(skillswantedjavascriptCheckBox);
//        wantedSkillsPanel.add(skillswantedpythonCheckBox);
//        wantedSkillsPanel.add(skillswantedsqlCheckBox);
//        wantedSkillsPanel.add(skillswantedreactCheckBox);
//        wantedSkillsPanel.add(skillswantedmlCheckBox);
//        add(wantedSkillsPanel);
//
//        // Submit Button
//        JButton submitButton = new JButton("Submit");
//        submitButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
//        submitButton.setForeground(Color.WHITE);
//        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
//        submitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Collect data and save to database
//                saveProfile(userId);
//
//                }
//        });
//        add(submitButton);
//        return this;
//    }
//
//    public void saveProfile(int userId) {
//
//        String fullName = fullNameField.getText();
//        char gender = 'O';
//        if (maleButton.isSelected()) gender = 'M';
//        else if (femaleButton.isSelected()) gender = 'F';
//        int age = Integer.parseInt(ageField.getText());
//        String contact = contactField.getText();
//
//        StringBuilder skillsWanted = new StringBuilder();
//        if (skillswantedjavaCheckBox.isSelected()) skillsWanted.append("Java, ");
//        if (skillswantedjavascriptCheckBox.isSelected()) skillsWanted.append("JavaScript, ");
//        if (skillswantedpythonCheckBox.isSelected()) skillsWanted.append("Python, ");
//        if (skillswantedsqlCheckBox.isSelected()) skillsWanted.append("SQL, ");
//        if (skillswantedreactCheckBox.isSelected()) skillsWanted.append("React, ");
//        if (skillswantedmlCheckBox.isSelected()) skillsWanted.append("Machine Learning, ");
//
//        // Remove trailing comma and space
//        if (skillsWanted.length() > 0) {
//            skillsWanted.setLength(skillsWanted.length() - 2);
//        }
//
//        skillwant=skillsWanted.toString();
//
//        StringBuilder skillsKnown = new StringBuilder();
//        if (skillsknownjavaCheckBox.isSelected()) skillsKnown.append("Java, ");
//        if (skillsknownjavascriptCheckBox.isSelected()) skillsKnown.append("JavaScript, ");
//        if (skillsknownpythonCheckBox.isSelected()) skillsKnown.append("Python, ");
//        if (skillsknownsqlCheckBox.isSelected()) skillsKnown.append("SQL, ");
//        if (skillsknownreactCheckBox.isSelected()) skillsKnown.append("React, ");
//        if (skillsknownmlCheckBox.isSelected()) skillsKnown.append("Machine Learning, ");
//        if (skillsKnown.length() > 0) {
//            skillsKnown.setLength(skillsKnown.length() - 2);
//        }
//        skillknown=skillsKnown.toString();
//
//        // Save this data to the database
//        int userID = UserRepo.getuserid(fullName);
//        if(UserRepo.saveprofile(fullName,age,gender,contact,skillknown,skillwant,userId)) {
//            JOptionPane.showMessageDialog(this, "Profile created successfully!");
//
//        }
//        // Redirect to Dashboard
//        UserDashboard userDashboard = new UserDashboard();
//        userDashboard.display(userID);
//    }
//}
