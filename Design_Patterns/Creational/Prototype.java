import java.util.*;

interface EmailTemplate extends Cloneable {
    EmailTemplate clone();
    void setContent(String content);
    void setSubject(String subject);
    void send(String to);
};

/*class WelcomeEmail implements EmailTemplate {
    private String subject;
    private String content;

    public WelcomeEmail() {
        this.subject = "Welcome to TUF Plus!";
        this.content = "Dear User,\n\nThank you for joining us.\n\nBest Regards,\nTeam";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println();
        System.out.println("Sending Email...");
        System.out.println("Subject: " + subject);
        System.out.println("Content: " + content);
    }
}; */


class WelcomeEmail implements EmailTemplate {
    private String subject;
    private String content;

    public WelcomeEmail() {
        this.subject = "Welcome to TUF Plus!";
        this.content = "Dear User,\n\nThank you for joining us.\n\nBest Regards,\nTeam";
    }

    @Override
    public WelcomeEmail clone() {
        try {
            return (WelcomeEmail) super.clone();
        } catch (Exception e) {
            throw new RuntimeException("Cloning not supported");
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void send(String to) {
        System.out.println();
        System.out.println("Sending Email...");
        System.out.println();
        System.out.println("From: tuf@gmail.com" );
        System.out.println("Subject: " + subject);
        System.out.println("To: " + to);
        System.out.println();
        System.out.println(content);
    }
};

class EmailTemplateRegistry {
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        WelcomeEmail welcomeEmail = new WelcomeEmail();
        templates.put("welcome", welcomeEmail);
    }

    public static EmailTemplate getTemplate(String templateName) {
        EmailTemplate template = templates.get(templateName);
        return template != null ? template.clone() : null;
    }
}


class Prototype {
    public static void main(String[] args) {
        /* WelcomeEmail welcomeEmailTuf = new WelcomeEmail();
        welcomeEmailTuf.setContent("Welcome to TUF");
        WelcomeEmail welcomeEmailTufPlus = new WelcomeEmail();
        welcomeEmailTufPlus.send("tufplus@gmail.com"); */

        EmailTemplate welcomeEmail = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail.setSubject("Welcome to TUF");
        welcomeEmail.send("lakshay@gmail.com");

        EmailTemplate welcomeEmailTufPlus = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmailTufPlus.send("abc@gmail.com");
    }
}
