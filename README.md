# SkillBridge

<p><strong>SkillBridge</strong> is a platform designed to facilitate skill-sharing and professional connections. Users can send connection requests, accept or decline them, and communicate with their connections via a built-in messaging system. The platform aims to bridge the gap between people looking to learn new skills and those offering them.</p>

## Features

<ul>
  <li><strong>User Authentication</strong>: Secure login and registration system.</li>
  <li><strong>Connection Requests</strong>: Users can send and receive connection requests, accept or decline them.</li>
  <li><strong>Messaging</strong>: Users can send messages to their accepted connections.</li>
  <li><strong>Tabbed Interface</strong>: A clean and intuitive interface using <code>JTabbedPane</code> for easy navigation between connections, requests, and messages.</li>
</ul>

## Tech Stack

<ul>
  <li><strong>Frontend</strong>: Java Swing for GUI</li>
  <li><strong>Backend</strong>: Java (core application logic)</li>
  <li><strong>Database</strong>: MySQL for storing user data, connection requests, and messages</li>
  <li><strong>Version Control</strong>: Git (integrated with GitHub)</li>
</ul>

## How to Run the Project

### Prerequisites
<ul>
  <li>Java (version 8 or later)</li>
  <li>MySQL (for the database)</li>
  <li>Git (for version control)</li>
</ul>

### Setup

<ol>
  <li><strong>Clone the repository</strong>:
    <pre><code>git clone https://github.com/OmKuthe/SkillBridge.git
cd SkillBridge</code></pre>
  </li>

  <li><strong>Database Setup</strong>:
    <ul>
      <li>Create a MySQL database and configure the connection in the project.</li>
      <li>Use the provided SQL script (if any) to set up the necessary tables, including <code>ConnectionRequests</code>, <code>Users</code>, and <code>Messages</code>.</li>
    </ul>
  </li>

  <li><strong>Build and Run</strong>:
    <ul>
      <li>Open the project in IntelliJ IDEA or any preferred Java IDE.</li>
      <li>Build the project and run the main class.</li>
    </ul>
  </li>

  <li><strong>Usage</strong>:
    <ul>
      <li>Register a new user account.</li>
      <li>Send connection requests to other users.</li>
      <li>Accept or decline incoming requests.</li>
      <li>Start messaging your connections once a request is accepted.</li>
    </ul>
  </li>
</ol>

## Database Schema

### Tables

<ul>
  <li><strong>Users</strong>: Stores user information.</li>
  <li><strong>ConnectionRequests</strong>: Manages connection request statuses (pending, accepted, declined).</li>
  <li><strong>Messages</strong>: Stores the messages sent between users.</li>
</ul>
## Contributing

<p>Contributions are welcome! Follow these steps to contribute:</p>

<ol>
  <li><strong>Fork the repository</strong>: Click the "Fork" button at the top-right of this page.</li>
  <li><strong>Create a new branch</strong>:
    <pre><code>git checkout -b feature-branch-name</code></pre>
  </li>
  <li><strong>Make your changes</strong>: Implement your new feature or fix a bug.</li>
  <li><strong>Commit your changes</strong>:
    <pre><code>git commit -m "Description of your changes"</code></pre>
  </li>
  <li><strong>Push to the branch</strong>:
    <pre><code>git push origin feature-branch-name</code></pre>
  </li>
  <li><strong>Submit a pull request</strong>: Go to your forked repository on GitHub, and click the "New pull request" button.</li>
</ol>

<p>For major changes, please open an issue first to discuss what you would like to modify. This ensures that your work aligns with the project's goals.</p>
