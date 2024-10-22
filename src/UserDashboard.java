import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.sql.Timestamp;
class Message {
    private int senderId;
    private String messageText;
    private Timestamp sentAt;  // Add sentAt field

    public Message(int senderId, String messageText, Timestamp sentAt) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.sentAt = sentAt;
    }

    // Getters
    public int getSenderId() {
        return senderId;
    }

    public String getMessageText() {
        return messageText;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }
}

public class UserDashboard {

    static JTextArea chatArea = new JTextArea();

    private static int receiverId = 0;

    public static void loadConversationHistory(int senderId, int receiverId) {
        UserRepo userRepository = new UserRepo();

        List<Message> messages = userRepository.getConversationHistory(senderId, receiverId);

        chatArea.setText("");  // Clear the chat area before loading new messages

        // Fetch the names for the sender (you) and the receiver (friend)
        String me = userRepository.getname(receiverId);      // Current user's name
        String friend = userRepository.getname(senderId); // Friend's name

        for (Message message : messages) {
            if (message.getSenderId() == senderId) {
                // If the sender is you
                chatArea.append(me+": " + message.getMessageText() + "\n");
            } else {
                // If the sender is your friend
                chatArea.append(friend + ": " + message.getMessageText() + "\n");
            }
        }
    }

    private static void showLearningPath(String skill , JFrame frame) {

        JDialog learningPathDialog = new JDialog(frame, "Learning Path for " + skill, true);
        learningPathDialog.setSize(400, 300);
        learningPathDialog.setLayout(new BorderLayout());

        JPanel pathPanel = new JPanel();
        pathPanel.setLayout(new GridLayout(0, 1)); // Vertical layout for resources

        String[] resources = {
                skill + " - Resource 1: Introduction to " + skill,
                skill + " - Resource 2: Advanced " + skill,
                skill + " - Resource 3: Practical Exercises on " + skill,
                skill + " - Resource 4: Community and Forums",
                skill + " - Resource 5: Certification Courses"
        };

        // Add each resource as a JLabel
        for (String resource : resources) {
            pathPanel.add(new JLabel(resource));
        }
        learningPathDialog.add(pathPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> learningPathDialog.dispose());
        learningPathDialog.add(closeButton, BorderLayout.SOUTH);

        learningPathDialog.setLocationRelativeTo(frame); // Center on the parent frame
        learningPathDialog.setVisible(true);
    }

    public static void updateProfilePanel(String name){
        // Show Profile Details work pending here
        JOptionPane.showMessageDialog(null, "Profile info for: " + name);
    }

    public static void display(int profileid){
        // Create main JFrame
        final String[] username = new String[1];
        JFrame frame = new JFrame("SkillBridge Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Profile Section
        JPanel profilePanel = new JPanel();
        profilePanel.setPreferredSize(new Dimension(250, 600)); // Adjust to 1/3rd of total width
        profilePanel.setBackground(new Color(173, 216, 230)); // Light blue color
        profilePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel profileHeading = new JLabel("Profile");
        profileHeading.setFont(new Font("Arial", Font.BOLD, 20));
        profileHeading.setVerticalAlignment(SwingConstants.CENTER);
        profileHeading.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        profilePanel.add(profileHeading, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;

        UserRepo userRepo=new UserRepo();
        String[] keys = {"Name:", "Skills:", "Number:", "ID:"};
        ArrayList<String> values = new ArrayList<>();
        values.add(userRepo.getname(profileid));
        values .add(userRepo.getskill(profileid));
        values.add(userRepo.getcontact(profileid));
        values.add(Integer.toString(profileid));

        for (int i = 0; i < keys.length; i++) {
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.WEST;
            profilePanel.add(new JLabel(keys[i]), gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.EAST;
            profilePanel.add(new JLabel(values.get(i)), gbc);

            gbc.gridy++;
        }

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        profilePanel.add(logoutButton, gbc);
        logoutButton.addActionListener(e->{
            int response = JOptionPane.showConfirmDialog(
                    null, // Parent component, null means it's centered on screen
                    "Do you want to Log-Out?", // Message
                    "Confirm", // Title
                    JOptionPane.YES_NO_OPTION, // Option type (Yes/No)
                    JOptionPane.QUESTION_MESSAGE // Message type
            );

            if (response == JOptionPane.YES_OPTION) {
                frame.dispose();
                SkillBridgeLoginSignup newpage = new SkillBridgeLoginSignup();
                newpage.setVisible(true);
            } else if (response == JOptionPane.NO_OPTION) {
                System.out.println("user said no to logout");
            } else {
                System.out.println("User closed the log out dialog");
            }
        });
        frame.add(profilePanel, BorderLayout.WEST);

        // Middle Section
        JTabbedPane middleTabbedPane = new JTabbedPane();
        SkillBarGraph sbg =new SkillBarGraph();
        middleTabbedPane.add("Indemand Skills",sbg.createSkillsPanel());

        // Search Users Section
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);

        JPanel searchInputPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchInputPanel.add(new JLabel("Search for Users: "));
        searchInputPanel.add(searchField);
        searchInputPanel.add(searchButton);
        searchButton.addActionListener(e -> {
            username[0] = searchField.getText().trim();
            int userid2= userRepo.getuserid(username[0]);
            if (!username[0].isEmpty()) {
                // Clear previous search results
                searchPanel.removeAll();
                searchPanel.setLayout(new BorderLayout());

                // Add search input panel again at the top(refresh k badd bhi search panel dikhna chaiye )
                JPanel searchInputPanel2 = new JPanel();
                searchInputPanel2.add(new JLabel("Search for Users: "));
                searchInputPanel2.add(searchField);
                searchInputPanel2.add(searchButton);
                searchPanel.add(searchInputPanel2, BorderLayout.NORTH);

                ArrayList<String> searchResults = userRepo.searchForUsers(username[0]);  // Ye  matching users ko array list m store karega
                //yaha ye problem hai ki hum bas aadha naam dal k search kre to kuch nhi ata
                if (searchResults.isEmpty()) {
                    // If no users found
                    JLabel noResultsLabel = new JLabel("No users found with the username: " + username[0]);
                    noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    searchPanel.add(noResultsLabel, BorderLayout.CENTER);
                } else {
                    // Create a panel to display the search results
                    JPanel resultPanel = new JPanel();
                    resultPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for flexible layout
                    GridBagConstraints gbc2 = new GridBagConstraints();
                    gbc2.fill = GridBagConstraints.HORIZONTAL;  // Fill horizontally
                    gbc2.insets = new Insets(5, 5, 5, 5);  // Add padding between elements

                    int row = 0;  // Row counter for layout

                    for (String user : searchResults) {
                        // Create a label for the username
                        JLabel userNameLabel = new JLabel(user);
                        userNameLabel.setBackground(new Color(173, 216, 230));

                        // Create a button for "Connect"
                        JButton connectButton = new JButton("Connect");
                        connectButton.setPreferredSize(new Dimension(100, 40));  // Optional preferred size
                        connectButton.addActionListener(e1  -> {

                            UserRepo userRepo1 = new UserRepo();
                            int sender_id = profileid ;
                            int reciever_id =userRepo1.getuserid(user);
                            if(userRepo1.sendreq(sender_id,reciever_id)){
                                JOptionPane.showMessageDialog(frame, "Connection request sent to: " + user);
                            }else{
                                JOptionPane.showMessageDialog(frame, "Failed to send Request to : " + user);
                            }
                        });

                        //"View Profile"
                        JButton viewProfileButton = new JButton("Inspect");
                        viewProfileButton.setPreferredSize(new Dimension(100, 40));
                        viewProfileButton.addActionListener(e1 -> {
                            // YAHA KAM BAKI H
                            JOptionPane.showMessageDialog(frame, "Opening profile for: " + user);
                            updateProfilePanel(user);
                        });

                        // Add the username label to the result panel
                        gbc2.gridx = 0;
                        gbc2.gridy = row;
                        resultPanel.add(userNameLabel, gbc2);

                        // Add the "Connect" button next to the username
                        gbc2.gridx = 1;
                        if(!userRepo.getcon(profileid,userid2)){

                        resultPanel.add(connectButton, gbc2);
                        }else{
                            resultPanel.remove(connectButton);

                        }

                        // Add the "View Profile" button next to the "Connect" button
                        gbc2.gridx = 2;
                        resultPanel.add(viewProfileButton, gbc2);

                        // Move to the next row for the next user
                        row++;
                    }
                    // Add the result panel to a scroll pane
                    JScrollPane scrollPane = new JScrollPane(resultPanel);
                    searchPanel.add(scrollPane, BorderLayout.CENTER);
                }
                // Refresh the UI to show search results
                searchPanel.revalidate();
                searchPanel.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a username to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        searchPanel.add(searchInputPanel, BorderLayout.NORTH);
        middleTabbedPane.addTab("Search Users", searchPanel);


        // Skills Overview Section (YAHA KAM BAKI H YE PURA STATIC BANA K CHOD DIYA )
        JPanel skillsOverviewPanel = new JPanel();
        skillsOverviewPanel.setLayout(new GridLayout(0, 2, 15, 15)); // Grid layout with dynamic rows and 2 columns
        skillsOverviewPanel.setBackground(Color.WHITE);
        String[] skills = {"Java", "JavaScript", "Machine Learning", "SQL", "Python", "Artificial Intelligence"};
        for (String skill : skills) {
            JButton skillButton = new JButton(skill);
            skillButton.setPreferredSize(new Dimension(150, 100));
            skillButton.setBackground(new Color(240, 240, 240));
            skillButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            skillButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showLearningPath(skill,frame);
                }
            });
            skillsOverviewPanel.add(skillButton);
        }
        JScrollPane skillsScrollPane = new JScrollPane(skillsOverviewPanel);
        middleTabbedPane.addTab("Skills Overview", skillsScrollPane);

        // Add middle panel to frame
        frame.add(middleTabbedPane, BorderLayout.CENTER);


        JPanel msgPanel = new JPanel(new BorderLayout());
        msgPanel.setPreferredSize(new Dimension(250, 600)); // Wider for social media feel
        msgPanel.setBackground(new Color(255, 255, 255)); // White background for cleaner interface

        // Tabs for switching between "Connections," "Requests," and "Messages"
        JTabbedPane tabbedPane = new JTabbedPane();
        // Sidebar for displaying connections
        JPanel connectionsPanel = new JPanel(new BorderLayout());
        connectionsPanel.setPreferredSize(new Dimension(100, 600)); // Sidebar width
        connectionsPanel.setBackground(new Color(240, 240, 240)); // Light gray background for the sidebar
        // Add header to sidebar
        JLabel connectionsLabel = new JLabel("Connections", JLabel.CENTER);
        connectionsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        connectionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        connectionsPanel.add(connectionsLabel, BorderLayout.NORTH);
        // Connections list
        DefaultListModel<String> connectionsModel = new DefaultListModel<>();
        JList<String> connectionsList = new JList<>(connectionsModel);
        connectionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        connectionsList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        connectionsPanel.add(new JScrollPane(connectionsList), BorderLayout.CENTER);

        // Define connectionsMap
        Map<Integer, String> connectionsMap = userRepo.fetchConnections(profileid);

        SwingUtilities.invokeLater(() -> {
            System.out.println("Fetched connections: " + connectionsMap); // Debugging line
            // Clear the model first
            connectionsModel.clear();
            for (Map.Entry<Integer, String> entry : connectionsMap.entrySet()) {
                connectionsModel.addElement(entry.getValue()); // Add the connection names to the model
            }
            connectionsList.setModel(connectionsModel);
            connectionsPanel.revalidate(); // Refresh the panel
            connectionsPanel.repaint();
        });

        // Requests panel
        JPanel requestsPanel = new JPanel(new BorderLayout());
        requestsPanel.setBackground(new Color(240, 240, 240)); // Light gray background for consistency

        // Add header to requests panel
        JLabel requestsLabel = new JLabel("Requests", JLabel.CENTER);
        requestsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        requestsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        requestsPanel.add(requestsLabel, BorderLayout.NORTH);

        // Requests list (with Accept/Decline buttons)
        UserRepo userRepo1 = new UserRepo();
        Map<String, Integer> requestMap = userRepo1.fetchRequestMap(profileid);
        final DefaultListModel<String> requestsModel = new DefaultListModel<>();
        for (String requestDetail : requestMap.keySet()) {
            requestsModel.addElement(requestDetail);  // Add each request detail to the list model
        }
        JList<String> requestsList = new JList<>(requestsModel);
        requestsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        requestsPanel.add(new JScrollPane(requestsList), BorderLayout.CENTER);

        // Panel for buttons below requests list
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton acceptButton = new JButton("Accept");
        JButton declineButton = new JButton("Decline");
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);
        requestsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for Accept button
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    Integer requestId = requestMap.get(selectedRequest);  // Fetch the request_id from the map
                    if (requestId != null) {
                        userRepo1.acceptConnection(requestId);  // Pass the request_id to acceptConnection()
                        System.out.println("Accepted request with request_id: " + requestId);
                        requestsModel.removeElement(selectedRequest);  // Remove the request from the list
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a request to accept.");
                }
            }
        });

        // Action listener for Decline button
        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = requestsList.getSelectedValue();
                if (selectedRequest != null) {
                    System.out.println("Declined request from: " + selectedRequest);
                    requestsModel.removeElement(selectedRequest); // Remove the request
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a request to decline.");
                }
            }
        });

        // Messages panel for displaying chat history and input area
        JPanel messagesPanel = new JPanel(new BorderLayout());
        messagesPanel.setBackground(new Color(255, 255, 255)); // White background

        // Chat header (to display currently selected connection)
        JLabel chatHeader = new JLabel("Select a connection to start chatting", JLabel.CENTER);
        chatHeader.setFont(new Font("Arial", Font.BOLD, 16));
        chatHeader.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        messagesPanel.add(chatHeader, BorderLayout.NORTH);

        // Message area (chat history display)
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setText("No conversation selected."); // Default text

        // Scroll pane for chat area
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        messagesPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Message input area (for typing and sending messages)
        JPanel messageInputPanel = new JPanel(new BorderLayout());
        messageInputPanel.setBackground(new Color(245, 245, 245));
        JTextField messageInputField = new JTextField();
        JButton sendButton = new JButton("Send");

        messageInputPanel.add(messageInputField, BorderLayout.CENTER);
        messageInputPanel.add(sendButton, BorderLayout.EAST);
        messagesPanel.add(messageInputPanel, BorderLayout.SOUTH);

        // Add tabs to the main panel
        tabbedPane.addTab("Connections", connectionsPanel);
        tabbedPane.addTab("Requests", requestsPanel);
        tabbedPane.addTab("Messages", messagesPanel);
        msgPanel.add(tabbedPane, BorderLayout.CENTER);

        // Action listener for selecting a connection from the sidebar
        connectionsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedConnectionName = connectionsList.getSelectedValue();
                chatHeader.setText("Chat with " + selectedConnectionName);
                chatArea.setText(selectedConnectionName + ": Hey! How's it going?\n");
                // Placeholder message
                messageInputField.setText("");
                if (selectedConnectionName != null) {
                    // Find the receiver ID from the connectionsMap based on the selected name
                    for (Map.Entry<Integer, String> entry : connectionsMap.entrySet()) {
                        if (entry.getValue().equals(selectedConnectionName)) {
                            receiverId = entry.getKey();
                            System.out.println("Selected Receiver ID: " + receiverId);
                            break;
                        }
                    }
                }
                loadConversationHistory(profileid,receiverId);
            }
        });

        // Action listener for sending a message
        sendButton.addActionListener(e -> {
            String message = messageInputField.getText().trim();
            String name = userRepo.getname(profileid);
            if (!message.isEmpty()) {
                String timestamp = LocalTime.now().withNano(0).toString(); // Get current time
                chatArea.append("\n[" + timestamp + "]"+name+": " + message); // Append the sent message to the chat area
                messageInputField.setText(""); // Clear the input field
                userRepo1.sendmsgtodb(message,profileid,receiverId);
            }
        });
        frame.add(middleTabbedPane, BorderLayout.CENTER);

        frame.add(msgPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
}
