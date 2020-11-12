package com.example.demo.Repositories;

import com.example.demo.Models.Message;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {
    ProfileRepository rp = new ProfileRepository();
    List<Message> allMessages = new ArrayList<>();

    public void sendMessage(int senderId, int receiverId, String message) throws SQLException {
        PreparedStatement ps = rp.establishConnection().prepareStatement("INSERT INTO messages(senderId,receiverId,message) VALUES(?,?,?)");
        ps.setInt(1,senderId);
        ps.setInt(2,receiverId);
        ps.setString(3,message);

        ps.executeUpdate();
    }

    public List<Message> seeMessage(int id) throws SQLException {
        allMessages.clear();
        PreparedStatement ps = rp.establishConnection().prepareStatement("select senderId, receiverId, message from messages where senderId = ? or receiverId = ?");
        ps.setInt(1,id);
        ps.setInt(2,id);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Message temp = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3));
            allMessages.add(temp);
        }
        return allMessages;
    }

    public String getNameFromId(int id) throws SQLException{
        PreparedStatement ps = rp.establishConnection().prepareStatement("SELECT name FROM profiles WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        String res = null;
        while(rs.next()){
            res = rs.getString(1);
        }
        System.out.println(res);
        return res;
    }
}
