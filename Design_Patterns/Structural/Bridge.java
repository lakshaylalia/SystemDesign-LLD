import java.util.*;

/* interface PlayQuality{
    void play(String title);
};

class WebHDPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in HD.");
    }
};

class MobileHDPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in HD.");
    }
};

class SmartTVUltraHDPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Smart TV Player: Playing " + title + " in Ultra HD.");
    }
};

class Web4KPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in 4K.");
    }
};

class Mobile4KPlayer implements PlayQuality {
    @Override
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in 4K.");
    }
}; */

interface VideoQuality {
    void load(String title);
};

class SDQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in SD quality.");
    }
};

class HDQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in HD quality.");
    }
};
class UltraHDQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in Ultra HD quality.");
    }
};

class FourKQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in 4K quality.");
    }
};

class EightKQuality implements VideoQuality {
    @Override
    public void load(String title) {
        System.out.println("Streaming " + title + " in 8K quality.");
    }
};

abstract class VideoPlayer {
    protected VideoQuality quality;

    public VideoPlayer(VideoQuality quality) {
        this.quality = quality;
    }

    public abstract void play(String title);
};

class WebPlayer extends VideoPlayer {
    public WebPlayer(VideoQuality quality) {
        super(quality);
    }

    public void play(String title) {
        System.out.println("Web Platform:");
        quality.load(title);
    }
};

class MobilePlayer extends VideoPlayer {
    public MobilePlayer(VideoQuality quality) {
        super(quality);
    }

    public void play(String title) {
        System.out.println("Mobile Platform:");
        quality.load(title);
    }
};

public class Bridge {
    public static void main(String[] args) {
        VideoPlayer player1 = new WebPlayer(new HDQuality());
        player1.play("Interstellar");
    }
};