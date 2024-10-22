import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {

    public  void createuser(String username ,String email ,String password ){
        String sql ="INSERT INTO users (username,email,password) VALUES (?,?,?)";

        try(Connection con = Database.getconnection();
           PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2,email);
            ps.setString(3,password);
            ps.executeUpdate();

        }catch (SQLException e ){
            System.out.println("ERROR IN CREATING A USER "+e.getMessage());
        }
    }

    public  int CheckLogin(String username , String password ){
        String sql = "Select * FROM USERS WHERE username = ? AND password =?;";
        try(Connection con = Database.getconnection();
         PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();

            return getuserid(username);
        }
        catch(SQLException e){
            System.out.println("Error While Checking Login :"+e.getMessage());
            return -1;
        }

    }

    public static boolean saveprofile(String fullname ,int age,char gender , String contact , String skillsknown ,String skillswanted,int userID){
    String sql ="INSERT INTO userprofile (full_name,age,gender,contact,skills_known,skills_want,user_id) values (?,?,?,?,?,?,?)";
    try(Connection con = Database.getconnection()){
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,fullname);
        ps.setInt(2,age);
        ps.setString(3,String.valueOf(gender));
        ps.setString(4,contact);
        ps.setString(5,skillsknown);
        ps.setString(6,skillswanted);
        ps.setInt(7,userID);
        ps.executeUpdate();
        return  true;
    }catch(SQLException e ){
        System.out.println("Error While Saving Profile "+ e.getMessage());
        return false;
    }


    }

    public static  int getuserid(String name){

        int user_id=-1;
        try(Connection con = Database.getconnection()){
            String sql="SELECT id FROM users WHERE username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                user_id=rs.getInt("id");
            }

        }catch (SQLException e){
            System.out.println("\nERROR IN Getting User ID "+e.getMessage());
        }

        return user_id ;
    }

    public  static  int   checkuserprofile(String name){

        String sql = "SELECT user_id FROM userprofile WHERE full_name=?";
        int userid=-1;
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                userid = rs.getInt("user_id");
            }

        }catch (SQLException e){
            System.out.println("Error while checking if profile exist"+e.getMessage());
        }
        return userid;
    }

    public static String getname(int id){
        String name="";
        String sql = "SELECT full_name FROM userprofile WHERE user_id = ?";
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                name=rs.getString("full_name");
            }
        }catch (SQLException e){
            System.out.println("ERROR WHILE GETTING NAME " + e.getMessage());
        }

        return name;
    }

    public static String getskill(int id){
        String skills="";
        String sql = "SELECT skills_known FROM userprofile WHERE user_id = ?";
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                skills=rs.getString("skills_known");
            }
        }catch (SQLException e){
            System.out.println("ERROR WHILE GETTING NAME " + e.getMessage());
        }

        return skills;
    }

    public static String getcontact(int id){
        String contact="";
        String sql = "SELECT contact FROM userprofile WHERE user_id = ?";
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                contact=rs.getString("contact");
            }
        }catch (SQLException e){
            System.out.println("ERROR WHILE GETTING NAME " + e.getMessage());
        }

        return contact;
    }

    public static ArrayList<String> searchForUsers(String username) {
        ArrayList<String> users = new ArrayList<>();
        String sql = "SELECT full_name FROM userprofile WHERE full_name=?" ;
        try(Connection con = Database.getconnection()){
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                users.add(rs.getString("full_name"));
            }
        }catch(SQLException e ){
            System.out.println("Error while Getting the user with name " + username);
        }

        return users;
    }

    public static Boolean sendreq(int sender_id , int reciver_id ){
        String sql = "INSERT INTO ConnectionRequests (sender_id, receiver_id) VALUES (?, ?);";
        try (Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,sender_id);
            ps.setInt(2,reciver_id);

            int result = ps.executeUpdate();
            if(result>0){
                return true ;
            }

        }catch (SQLException e ){
            System.out.println("Error wile sending request : " + e.getMessage());
        }
        return false;
    }

    public static boolean getcon(int me,int freind){

        String sql="SELECT * FROM Connections WHERE (user_id1=? AND user_id2 = ?) OR (user_id2=? AND user_id1= ?)";
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,me);
            ps.setInt(2,freind);
            ps.setInt(3,freind);
            ps.setInt(4,me);

            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }

        }
    catch(SQLException e){

        System.out.println("Error while finding Connection "+e.getMessage());
    }
        return false;
    }

    public static int getsenderid(int req){
        String sql = "SELECT sender_id FROM ConnectionRequests WHERE request_id=?";
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,req);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("sender_id");
            }

        }catch(SQLException e){
            System.out.println("Error while getting sender id " + e.getMessage());
        }
        return 0;
    }

    public static int getreciverid(int req){
        String sql = "SELECT receiver_id FROM ConnectionRequests WHERE request_id=?";
        try(Connection con = Database.getconnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,req);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("receiver_id");
            }

        }catch(SQLException e){
            System.out.println("Error while getting reciver id " + e.getMessage());
        }
        return 0;
    }

    public static void acceptConnection(int requestId) {
        String updateRequestSql = "UPDATE ConnectionRequests " +
                "SET status = 'accepted', responded_at = CURRENT_TIMESTAMP " +
                "WHERE request_id = ?";
            int sender =getsenderid(requestId);
            int reciver=getreciverid(requestId);
        try (Connection con = Database.getconnection();
             PreparedStatement ps = con.prepareStatement(updateRequestSql)) {

            ps.setInt(1, requestId);
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Connection request accepted successfully.");
                addConnection(sender,reciver);
            } else {
                System.out.println("No such connection request found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while accepting connection request: " + e.getMessage());
        }
    }

    public Map<String, Integer> fetchRequestMap(int profileId) {
        Map<String, Integer> requestMap = new HashMap<>();
        String sql = "SELECT request_id, sender_id FROM ConnectionRequests WHERE receiver_id = ? AND status = 'pending'";

        try (Connection con = Database.getconnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, profileId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int requestId = rs.getInt("request_id");
                int senderId = rs.getInt("sender_id");
                String name = getname(senderId);
                String requestDetail = "Request from " +name ;  // Example of request string
                requestMap.put(requestDetail, requestId);  // Map request string to request_id
            }
        } catch (SQLException e) {
            System.out.println("Error fetching requests: " + e.getMessage());
        }

        return requestMap;
    }

    public static void addConnection(int senderId, int receiverId) {
        String sql = "INSERT INTO Connections (user_id1, user_id2, name1, name2) VALUES (?, ?, ?, ?)";

        try (Connection con = Database.getconnection()) {
            // Fetch names for sender and receiver
            String name1 = getUserName(senderId);
            String name2 = getUserName(receiverId);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setString(3, name1);
            ps.setString(4, name2);

            ps.executeUpdate();
            System.out.println("Connection added between user " + senderId + " (" + name1 + ") and user " + receiverId + " (" + name2 + ")");
        } catch (SQLException e) {
            System.out.println("Error while adding connection: " + e.getMessage());
        }
    }

    public static String getUserName(int userId) {
        String name = "";
        String sql = "SELECT username FROM users WHERE id = ?";  // Assuming there's a Users table

        try (Connection con = Database.getconnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                name = rs.getString("username");  // Adjust based on your actual column name
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user name: " + e.getMessage());
        }

        return name;
    }

    public Map<Integer, String> fetchConnections(int profileId) {
        Map<Integer, String> connectionsMap = new HashMap<>();
        String sql = "SELECT user_id1, user_id2, name1, name2 FROM Connections WHERE user_id1 = ? OR user_id2 = ?";

        try (Connection con = Database.getconnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, profileId);
            ps.setInt(2, profileId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId1 = rs.getInt("user_id1");
                int userId2 = rs.getInt("user_id2");
                String name1 = rs.getString("name1");
                String name2 = rs.getString("name2");

                // Determine which user is the current profile and add the connection accordingly
                if (userId1 == profileId) {
                    connectionsMap.put(userId2, name2); // Store userId2 and name2 in the map
                } else {
                    connectionsMap.put(userId1, name1); // Store userId1 and name1 in the map
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching connections: " + e.getMessage());
        }

        return connectionsMap; // Return the map containing connections
    }

    public void sendmsgtodb(String msg,int reciver,int sender){

        String sql="INSERT INTO messages (sender_id,reciver_id,message_text) VALUES (?,?,?);";
        try(Connection con = Database.getconnection()){
        PreparedStatement ps =con.prepareStatement(sql);
        ps.setInt(1,sender);
        ps.setInt(2,reciver);
        ps.setString(3,msg);

        int rs =ps.executeUpdate();

        if(rs>0){
            System.out.println("message added to db succesfully");
        }

        }catch(SQLException e){
            System.out.println("Error while adding msg to db : "+e.getMessage());
        }

    }

    public List<Message> getConversationHistory(int senderId, int receiverId) {
        List<Message> messages = new ArrayList<>();

        try (Connection con = Database.getconnection()) {
            // Updated query to include ordering by 'sent_at' timestamp
            String query = "SELECT sender_id, message_text, sent_at FROM Messages WHERE " +
                    "(sender_id = ? AND reciver_id = ?) OR (sender_id = ? AND reciver_id = ?) " +
                    "ORDER BY sent_at";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, senderId);    // Current user as sender, friend as receiver
            statement.setInt(2, receiverId);  // Friend as receiver
            statement.setInt(3, receiverId);  // Friend as sender, current user as receiver
            statement.setInt(4, senderId);    // Current user as receiver

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int messageSenderId = resultSet.getInt("sender_id");
                String messageText = resultSet.getString("message_text");
                // Optional: If you want to show the timestamp as well
                Timestamp sentAt = resultSet.getTimestamp("sent_at");
                messages.add(new Message(messageSenderId, messageText, sentAt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

}
