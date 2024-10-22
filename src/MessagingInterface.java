import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessagingInterface {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("1-on-1 Messaging");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Left side - User list
        JPanel userListPanel = new JPanel();
        userListPanel.setPreferredSize(new Dimension(200, 600));
        userListPanel.setLayout(new BoxLayout(userListPanel, BoxLayout.Y_AXIS));

        // Simulate some users
        String[] users = {"John", "Alice", "Bob", "Mike"};
        for (String user : users) {
            JButton userButton = new JButton(user);
            userButton.setPreferredSize(new Dimension(180, 50));
            userButton.setMaximumSize(new Dimension(180, 50));
            userButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            userListPanel.add(userButton);
        }
        frame.add(userListPanel, BorderLayout.WEST);

        // Right side - Chat panel
        JPanel chatPanel = new JPanel(new BorderLayout());

        // Chat display area (messages will be shown here)
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setMargin(new Insets(10, 10, 10, 10));

        // Message bubble styles
        chatArea.setBackground(new Color(245, 245, 245));
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Bottom panel - Message input area
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField messageInputField = new JTextField();
        JButton sendButton = new JButton("Send");
        inputPanel.add(messageInputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        // Message sending action
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageInputField.getText().trim();
                if (!message.isEmpty()) {
                    String timestamp = new SimpleDateFormat("HH:mm").format(new Date());
                    chatArea.append("\nMe [" + timestamp + "]: " + message);
                    messageInputField.setText("");  // Clear the input field
                    // Simulate a response from the other user
                    chatArea.append("\n" + users[0] + ": Thanks for your message! [" + timestamp + "]");
                }
            }
        });

        frame.add(chatPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

