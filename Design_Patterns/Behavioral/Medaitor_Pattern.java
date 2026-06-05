import java.util.*;

/* 
class User {
    private String name;
    private List<User> others;

    public User(String name) {
        this.name = name;
        this.others = new ArrayList<>();
    }

    public void addCollaborator(User user) {
        others.add(user);
    }

    public void makeChange(String change) {
        System.out.println(name+ " made a change: " + change);

        for(User u : others) {
            u.receiveChange(change, this);
        }
    }

    public void receiveChange(String change, User from) {
        System.out.println(name + "received: \"" + change + "\" from " + from.name);
    }
};
*/

class User {
    protected String name;
    protected DocumentSessionMediator mediator;

    public User(String name, DocumentSessionMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    // Method for users to make a change
    public void makeChange(String change) {
        System.out.println(name + " edited the document: " + change);
        mediator.broadcastChange(change, this);
    }

    // Method to receive a change from another user
    public void receiveChange(String change, User sender) {
        System.out.println(name + " saw change from " + sender.name + ": \"" + change + "\"");
    }
}

interface DocumentSessionMediator {
    void broadcastChange(String change, User sender);
    void join(User user);
}

class CollaborativeDocument implements DocumentSessionMediator {
    private List<User> users = new ArrayList<>();

    @Override
    public void join(User user) {
        users.add(user);
    }


    @Override
    public void broadcastChange(String change, User sender) {
        for(User user : users) {
            if(user != sender) {
                user.receiveChange(change, sender);
            }
        }
    }
};

public class Medaitor_Pattern {
    public static void main(String[] args) {
        CollaborativeDocument doc = new CollaborativeDocument();

        // Creating users
        User alice = new User("Alice", doc);
        User bob = new User("Bob", doc);
        User charlie = new User("Charlie", doc);

        // Joining the collaborative document
        doc.join(alice);
        doc.join(bob);
        doc.join(charlie);

        // Users making changes
        alice.makeChange("Added project title");
        bob.makeChange("Corrected grammar in paragraph 2");
    };
};