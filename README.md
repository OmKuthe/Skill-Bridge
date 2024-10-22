SkillBridge
SkillBridge is a platform designed to facilitate skill-sharing and professional connections. Users can send connection requests, accept or decline them, and communicate with their connections via a built-in messaging system. The platform aims to bridge the gap between people looking to learn new skills and those offering them.

Features
User Authentication: Secure login and registration system.
Connection Requests: Users can send and receive connection requests, accept or decline them.
Messaging: Users can send messages to their accepted connections.
Tabbed Interface: A clean and intuitive interface using JTabbedPane for easy navigation between connections, requests, and messages.
Tech Stack
Frontend: Java Swing for GUI
Backend: Java (core application logic)
Database: MySQL for storing user data, connection requests, and messages
Version Control: Git (integrated with GitHub)
How to Run the Project
Prerequisites
Java (version 8 or later)
MySQL (for the database)
Git (for version control)
Setup
Clone the repository:

bash
Copy code
git clone https://github.com/OmKuthe/SkillBridge.git
cd SkillBridge
Database Setup:

Create a MySQL database and configure the connection in the project.
Use the provided SQL script (if any) to set up the necessary tables, including ConnectionRequests, Users, and Messages.
Build and Run:

Open the project in IntelliJ IDEA or any preferred Java IDE.
Build the project and run the main class.
Usage:

Register a new user account.
Send connection requests to other users.
Accept or decline incoming requests.
Start messaging your connections once a request is accepted.
Database Schema
Tables
Users: Stores user information.
ConnectionRequests: Manages connection request statuses (pending, accepted, declined).
Messages: Stores the messages sent between users.
Future Enhancements
User Profiles: Add profiles for users to showcase their skills and interests.
Skill Marketplace: Enable users to post their available skills and search for others.
File Sharing: Add functionality for sharing files between users.
Notifications: Real-time notifications for new connection requests and messages.
Contributing
Contributions are welcome! Feel free to fork this repository, make your changes, and submit a pull request. For major changes, please open an issue first to discuss what you would like to modify.

License
This project is licensed under the MIT License - see the LICENSE file for details.
