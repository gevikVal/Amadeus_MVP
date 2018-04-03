package fair.com.example.gevik.amadeus.network;

/**
 * Created by gevik on 3/5/2018.
 */

public class MessageEvent {
    int responseNumber;
    String message;

    public MessageEvent(int responseNumber, String message) {
        this.responseNumber = responseNumber;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseNumber() {
        return responseNumber;
    }

    public void setResponseNumber(int responseNumber) {
        this.responseNumber = responseNumber;
    }
}
