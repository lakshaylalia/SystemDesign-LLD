package models;

import java.util.ArrayList;
import java.util.List;

public class Question extends Post {
    private List<String> tags;

    public Question(User author, String statement) {
        super(author, statement);
        this.tags = new ArrayList<>();
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public List<String> getTags() {
        return tags;
    }
}