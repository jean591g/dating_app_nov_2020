package com.example.demo.Models;

import java.sql.SQLException;
import com.example.demo.Repositories.MessageRepository;

public class Message {
    MessageRepository mp = new MessageRepository();

    private int senderId;
    private int receiverId;
    private String msg;

    public Message(int senderId, int receiverId, String msg) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.msg = msg;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        try {
            return "Besked{" +
                    "Fra: " + mp.getNameFromId(senderId) +
                    ", til: " + mp.getNameFromId(receiverId) +
                    ", besked: '" + msg + '\'' +
                    '}';
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "fejl";
    }
}
