package api.linguo.sockets.pojo;

//import api.linguo.models.User;

public class MessagePojo {

    private String message;
    //private User sender;


    public MessagePojo(final String message) {
        this.message = message;
        //this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    //public User getSender() {
    //    return sender;
    //}
}