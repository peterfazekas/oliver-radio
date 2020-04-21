package hu.radio.domain.model;

public class RadioMessage {

    private final int day;
    private final int receiverId;
    private final String message;

    public RadioMessage(int day, int receiverId, String message) {
        this.day = day;
        this.receiverId = receiverId;
        this.message = message;
    }

    public int getDay() {
        return day;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getMessage() {
        return message;
    }

    public boolean contains(String text) {
        return message.contains(text);
    }
}
