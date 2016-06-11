package com.brain.revanth.sampleapplication2.chat;

/**
 * Created by Hari Prahlad on 23-05-2016.
 */
public class ChatCommonClass {

    private String chatId;
    private String chatLeadName;

    private String ImageChat;
    public ChatCommonClass(){

    }
    public ChatCommonClass(String chatId, String chatLeadName,String ImageChat) {
        this.chatId = chatId;
        this.chatLeadName = chatLeadName;
        this.ImageChat = ImageChat;
    }
    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatLeadName() {
        return chatLeadName;
    }

    public void setChatLeadName(String chatLeadName) {
        this.chatLeadName = chatLeadName;
    }

    public String getImageChat() {
        return ImageChat;
    }

    public void setImageChat(String imageChat) {
        ImageChat = imageChat;
    }

}
