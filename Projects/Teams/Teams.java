package Projects.Teams;
import java.util.*;


abstract class DocumentItem {
    protected String content;

    protected DocumentItem(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    abstract public void renderContent();
};

class TextItem extends DocumentItem {
    public TextItem(String content) {
        super(content);
    }

    @Override
    public void renderContent() {
        System.out.println(content);
    }
};

class ImageItem extends DocumentItem {
    public ImageItem(String path) {
        super(path);
    }

    @Override
    public void renderContent() {
        System.out.println("Rendering image -> " + content);
    }
};

class VideoItem extends DocumentItem {
    public VideoItem(String path) {
        super(path);
    }

    @Override
    public void renderContent() {
        System.out.println("Rendering video -> " + content);
    }
};

class DocumentEditor {
    private final List<DocumentItem> items;

    public DocumentEditor() {
        this.items = new ArrayList<>();
    }

    public void addContent(DocumentItem item) {
        items.add(item);
    }

    public void readContent() {
        for (DocumentItem item : items) {
            item.renderContent();
        }
    }

    public void deleteContent(DocumentItem item) {
        items.remove(item);
    }
};

public class Teams {
    public static void main(String args[]) {
        DocumentEditor editor = new DocumentEditor();
        editor.addContent(new TextItem("Hello my name is Lakshay"));
        editor.addContent(new TextItem("This is first LLD Project"));
        editor.addContent(new ImageItem("/images/image"));
        editor.addContent(new VideoItem("/videos/video.mp3"));
        editor.readContent();
    }
}
