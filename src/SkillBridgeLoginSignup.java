import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillBridgeLoginSignup extends JFrame {

    private CardLayout cardLayout = new CardLayout();

    public JPanel mainPanel = new JPanel(cardLayout);

    BuildProfilePage bfp = new BuildProfilePage();

    public SkillBridgeLoginSignup() {
            setTitle("SkillBridge");
            setSize(400, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            mainPanel.add(createWelcomePanel(), "Welcome");
            mainPanel.add(createLoginPanel(), "Login");
            mainPanel.add(createSignupPanel(), "Signup");

            add(mainPanel);
            setVisible(true);
        }

        private JPanel createWelcomePanel() {

            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(245, 245, 245));

            JLabel titleLabel = new JLabel("SkillBridge");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
            titleLabel.setForeground(new Color(0x2F80ED));

            JButton loginButton = new JButton("Login");
            JButton signupButton = new JButton("Create an Account");
            loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
            signupButton.setFont(new Font("Arial", Font.PLAIN, 20));

            loginButton.setPreferredSize(new Dimension(250, 50));
            signupButton.setPreferredSize(new Dimension(250, 50));

            loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
            signupButton.addActionListener(e -> cardLayout.show(mainPanel, "Signup"));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(20, 20, 20, 20);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;
            panel.add(loginButton, gbc);

            gbc.gridy++;
            panel.add(signupButton, gbc);

            return panel;
        }

        private JPanel createLoginPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(245, 245, 245));

            JLabel titleLabel = new JLabel("Login");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
            titleLabel.setForeground(new Color(0x2F80ED));

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            JTextField usernameField = new JTextField(15);
            usernameField.setPreferredSize(new Dimension(250, 40));

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            JPasswordField passwordField = new JPasswordField(15);
            passwordField.setPreferredSize(new Dimension(250, 40));

            JButton loginButton = new JButton("Login");
            loginButton.setFont(new Font("Arial", Font.PLAIN, 20));
            loginButton.setPreferredSize(new Dimension(250, 50));

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Arial", Font.PLAIN, 16));
            backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(usernameLabel, gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(passwordLabel, gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            panel.add(loginButton, gbc);

            gbc.gridy++;
            panel.add(backButton, gbc);

            loginButton.addActionListener(e->{
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserRepo userRepo = new UserRepo();
                int userid=userRepo.CheckLogin(username,password);
                if(userid!=-1){
                    JOptionPane.showMessageDialog(null,"Login Succesfull!!");

                    //check if userprofile exist
                    int profileuserid=userRepo.checkuserprofile(username);
                    System.out.println(profileuserid);
                    if (profileuserid==-1) {
                        mainPanel.add(bfp.BuildProfilePage(userid), "BuildProfile");
                        cardLayout.show(mainPanel, "BuildProfile");
                    }else{
                        UserDashboard userDashboard = new UserDashboard();
                        this.dispose();
                        userDashboard.display(profileuserid);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"Invalid Username or Password");
                }

            });

            return panel;
        }

        private JPanel createSignupPanel() {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(new Color(245, 245, 245));

            JLabel titleLabel = new JLabel("Create an Account");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
            titleLabel.setForeground(new Color(0x2F80ED));

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            JTextField usernameField = new JTextField(15);
            usernameField.setPreferredSize(new Dimension(250, 40));

            JLabel emailLabel = new JLabel("Email:");
            emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            JTextField emailField = new JTextField(15);
            emailField.setPreferredSize(new Dimension(250, 40));

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            JPasswordField passwordField = new JPasswordField(15);
            passwordField.setPreferredSize(new Dimension(250, 40));

            JButton signupButton = new JButton("Sign Up");
            signupButton.setFont(new Font("Arial", Font.PLAIN, 20));
            signupButton.setPreferredSize(new Dimension(250, 50));

            JButton backButton = new JButton("Back");
            backButton.setFont(new Font("Arial", Font.PLAIN, 16));
            backButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(titleLabel, gbc);

            gbc.gridy++;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(usernameLabel, gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(usernameField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(emailLabel, gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(emailField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(passwordLabel, gbc);
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(passwordField, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 2;
            panel.add(signupButton, gbc);

            gbc.gridy++;
            panel.add(backButton, gbc);

            signupButton.addActionListener(e->{
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                UserRepo userRepo =new UserRepo();
                userRepo.createuser(username,email,password);


                JOptionPane.showMessageDialog(null,"Account Created SuccesFully!!");
                cardLayout.show(mainPanel,"Login");
            });

            return panel;
        }

        //bfp
    public class BuildProfilePage extends JPanel {
        String skillknown;
        String skillwant;

        private JTextField fullNameField;
        private JTextField ageField;
        private JTextField contactField;

        private JRadioButton maleButton;
        private JRadioButton femaleButton;
        private JRadioButton otherButton;

        // Checkboxes for Skillsknown
        private JCheckBox skillsknownjavaCheckBox;
        private JCheckBox skillsknownjavascriptCheckBox;
        private JCheckBox skillsknownpythonCheckBox;
        private JCheckBox skillsknownsqlCheckBox;
        private JCheckBox skillsknownreactCheckBox;
        private JCheckBox skillsknownmlCheckBox;

        // Checkboxes for Skillswanted
        private JCheckBox skillswantedjavaCheckBox;
        private JCheckBox skillswantedjavascriptCheckBox;
        private JCheckBox skillswantedpythonCheckBox;
        private JCheckBox skillswantedsqlCheckBox;
        private JCheckBox skillswantedreactCheckBox;
        private JCheckBox skillswantedmlCheckBox;


        public JPanel BuildProfilePage(int userId) {
            setSize(400, 600);
            setLayout(new GridLayout(0, 1, 10, 10));
            setBackground(new Color(240, 248, 255)); // Light background

            // Full Name 1 error here is that username and fullname should be same it can b different but it causes errors
            add(new JLabel("Full Name:"));
            fullNameField = new JTextField();
            add(fullNameField);

            // Gender
            add(new JLabel("Gender:"));
            maleButton = new JRadioButton("Male");
            femaleButton = new JRadioButton("Female");
            otherButton = new JRadioButton("Other");
            ButtonGroup genderGroup = new ButtonGroup();
            genderGroup.add(maleButton);
            genderGroup.add(femaleButton);
            genderGroup.add(otherButton);
            JPanel genderPanel = new JPanel(new FlowLayout());
            genderPanel.setBackground(new Color(240, 248, 255));
            genderPanel.add(maleButton);
            genderPanel.add(femaleButton);
            genderPanel.add(otherButton);
            add(genderPanel);

            // Age
            add(new JLabel("Age:"));
            ageField = new JTextField();
            add(ageField);

            // Contact Info
            add(new JLabel("Contact:"));
            contactField = new JTextField();
            add(contactField);

            // Skills Checkboxes for Known Skills
            add(new JLabel("Select Skills Known:"));
            JPanel knownSkillsPanel = new JPanel();
            knownSkillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            skillsknownjavaCheckBox = new JCheckBox("Java");
            skillsknownjavascriptCheckBox = new JCheckBox("JavaScript");
            skillsknownpythonCheckBox = new JCheckBox("Python");
            skillsknownsqlCheckBox = new JCheckBox("SQL");
            skillsknownreactCheckBox = new JCheckBox("React");
            skillsknownmlCheckBox = new JCheckBox("Machine Learning");

            // Add checkboxes to the panel
            knownSkillsPanel.add(skillsknownjavaCheckBox);
            knownSkillsPanel.add(skillsknownjavascriptCheckBox);
            knownSkillsPanel.add(skillsknownpythonCheckBox);
            knownSkillsPanel.add(skillsknownsqlCheckBox);
            knownSkillsPanel.add(skillsknownreactCheckBox);
            knownSkillsPanel.add(skillsknownmlCheckBox);
            add(knownSkillsPanel);

            // Skills Checkboxes for Wanted Skills
            add(new JLabel("Select Skills Wanted:"));
            JPanel wantedSkillsPanel = new JPanel();

            skillswantedjavaCheckBox = new JCheckBox("Java");
            skillswantedjavascriptCheckBox = new JCheckBox("JavaScript");
            skillswantedpythonCheckBox = new JCheckBox("Python");
            skillswantedsqlCheckBox = new JCheckBox("SQL");
            skillswantedreactCheckBox = new JCheckBox("React");
            skillswantedmlCheckBox = new JCheckBox("Machine Learning");

            wantedSkillsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            wantedSkillsPanel.add(skillswantedjavaCheckBox);
            wantedSkillsPanel.add(skillswantedjavascriptCheckBox);
            wantedSkillsPanel.add(skillswantedpythonCheckBox);
            wantedSkillsPanel.add(skillswantedsqlCheckBox);
            wantedSkillsPanel.add(skillswantedreactCheckBox);
            wantedSkillsPanel.add(skillswantedmlCheckBox);
            add(wantedSkillsPanel);

            // Submit Button
            JButton submitButton = new JButton("Submit");
            submitButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
            submitButton.setForeground(Color.WHITE);
            submitButton.setFont(new Font("Arial", Font.BOLD, 14));
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Collect data and save to database
                    saveProfile(userId);

                }
            });
            add(submitButton);
            return this;
        }

        public void saveProfile(int userId) {

            String fullName = fullNameField.getText();
            char gender = 'O';
            if (maleButton.isSelected()) gender = 'M';
            else if (femaleButton.isSelected()) gender = 'F';
            int age = Integer.parseInt(ageField.getText());
            String contact = contactField.getText();

            StringBuilder skillsWanted = new StringBuilder();
            if (skillswantedjavaCheckBox.isSelected()) skillsWanted.append("Java, ");
            if (skillswantedjavascriptCheckBox.isSelected()) skillsWanted.append("JavaScript, ");
            if (skillswantedpythonCheckBox.isSelected()) skillsWanted.append("Python, ");
            if (skillswantedsqlCheckBox.isSelected()) skillsWanted.append("SQL, ");
            if (skillswantedreactCheckBox.isSelected()) skillsWanted.append("React, ");
            if (skillswantedmlCheckBox.isSelected()) skillsWanted.append("Machine Learning, ");

            // Remove trailing comma and space
            if (skillsWanted.length() > 0) {
                skillsWanted.setLength(skillsWanted.length() - 2);
            }

            skillwant=skillsWanted.toString();

            StringBuilder skillsKnown = new StringBuilder();
            if (skillsknownjavaCheckBox.isSelected()) skillsKnown.append("Java, ");
            if (skillsknownjavascriptCheckBox.isSelected()) skillsKnown.append("JavaScript, ");
            if (skillsknownpythonCheckBox.isSelected()) skillsKnown.append("Python, ");
            if (skillsknownsqlCheckBox.isSelected()) skillsKnown.append("SQL, ");
            if (skillsknownreactCheckBox.isSelected()) skillsKnown.append("React, ");
            if (skillsknownmlCheckBox.isSelected()) skillsKnown.append("Machine Learning, ");
            if (skillsKnown.length() > 0) {
                skillsKnown.setLength(skillsKnown.length() - 2);
            }
            skillknown=skillsKnown.toString();

            // Save this data to the database
            int userID = UserRepo.getuserid(fullName);
            if(UserRepo.saveprofile(fullName,age,gender,contact,skillknown,skillwant,userId)) {
                JOptionPane.showMessageDialog(this, "Profile created successfully!");
                mainPanel.setVisible(false);
                this.setVisible(false);
            }
            // Redirect to Dashboard
            UserDashboard userDashboard = new UserDashboard();
            userDashboard.display(userID);
        }
    }


}



