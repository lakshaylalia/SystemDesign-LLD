import java.util.*;

/* class EmailNotification {
    public void send(String to, String message) {
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating email recipents: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);

        // Compose Email
        String composedMessage = "<html><body><p?>" + formatted + "</p?</body></html>";
        // Send Email
        System.out.println("Sending EMAIL to " + to + " with content:\n" + composedMessage);

        // Analytics
        System.out.println("Analytics updated for: " + to);
    }
}; 

class SMSNotification {
    public void send(String to, String message) {
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating phone number: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);

        // Compose SMS
        String composedMessage = "[SMS]" + formatted;
        // Send SMS
        System.out.println("Sending SMS to " + to + " with content:\n" + composedMessage);

        // Analytics
        System.out.println("Custom SMS analytics for: " + to);
    }
}; */


abstract class NotificationSender {

    // Final template method
    public final void send(String to, String rawMessage) {

        // common logic or concrete operations
        rateLimitCheck(to);
        validateRecipient(to);
        String formattedMessage = formatMessage(rawMessage);
        preSendAuidtLog(to, formattedMessage);

        // logic specific to the type of notification or Primitive operations
        String composedMessage = composeMessage(formattedMessage);
        sendMessage(to, composedMessage);

        // common logic or Hooks
        postSendAnalytics(to);
    }

    // common step 1
    private void rateLimitCheck(String to) {
        System.out.println("Checking rate limits for: " + to);
    }

    // common step 2
    private void validateRecipient(String to) {
        System.out.println("Validating email recipents: " + to);
    }

    // common step 3
    private String formatMessage(String rawMessage) {
        return rawMessage.trim();
    }

    // common step 4
    private void preSendAuidtLog(String to, String message) {
        System.out.println("Logging before send: " + message + " to " + to);
    }

    // Hook for subclasses
    protected abstract String composeMessage(String formattedMessage);

    protected abstract void sendMessage(String to, String composedMessage);

    // common step 5 (optional hook)
    protected void postSendAnalytics(String to) {
        System.out.println("Analytics updated for: " + to);
    }
};

class EmailNotification extends NotificationSender {

    @Override
    protected String composeMessage(String formattedMessage) {
        return "<html><body><p?>" + formattedMessage + "</p?</body></html>";
    }

    @Override
    protected void sendMessage(String to, String composedMessage) {
        System.out.println("Sending EMAIL to " + to + " with content:\n" + composedMessage);
    }
};

class SMSNotification extends NotificationSender {

    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS]" + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String composedMessage) {
        System.out.println("Sending SMS to " + to + " with content:\n" + composedMessage);
    }

    // Optional hook override
    @Override
    protected void postSendAnalytics(String to) {
        System.out.println("Custom SMS analytics for: " + to);
    }
};

public class Template_Pattern {
    public static void main(String args[]) {
        NotificationSender emailSender = new EmailNotification();
        emailSender.send("lakshay@gmail", "Hello Lakshay, how are you?");

        System.out.println("\n\n");

        NotificationSender smsSender = new SMSNotification();
        smsSender.send("9876543210", "Your OTP is 123456");
    }
}
