package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        String message_text = message.getMessage_text();
        if (message_text == null || message_text.trim().isEmpty() || message_text.length() > 255) {
            return null;
        }
        return this.messageDAO.insertMessage(message);
    }

    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id){
        return this.messageDAO.getMessageById(message_id);
    }

    public Message deleteMessage(int message_id){
        if (this.messageDAO.getMessageById(message_id) == null) {
            return null;
        }
        try {
            Message message = this.messageDAO.getMessageById(message_id);
            if (message != null) {
                this.messageDAO.deleteMessage(message_id);
            }
            return message;
        } catch (Exception e) {
            return null;
        }
    }

    public Message updateMessage(int message_id, Message message){
        String message_text = message.getMessage_text();
        if (message_text == null || message_text.trim().isEmpty() || message_text.length() > 255) {
            return null;
        }
        if (this.messageDAO.getMessageById(message_id) == null) {
            return null;
        }
        try {
            this.messageDAO.updateMessage(message_id, message);
            return this.messageDAO.getMessageById(message_id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Message> getMessagesByAccount(int posted_by){
        return this.messageDAO.getMessagesByAccount(posted_by);
    }

}
