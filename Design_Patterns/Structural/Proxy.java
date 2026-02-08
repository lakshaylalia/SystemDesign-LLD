import java.util.*;

// Subject Interface
interface VideoDownloader {
    String downloadVideo(String videoUrl);
};

// Real Subject
class RealVideoDownloader implements VideoDownloader {
    @Override
    public String downloadVideo(String videoUrl) {
        System.out.println("Downloading video from URL: " + videoUrl);
        return "Video content from " + videoUrl;
    }
};

// Proxy Layer with caching
class CachedVideoDownloader implements VideoDownloader {
    private RealVideoDownloader realDownloader;
    private static Map<String, String> cache = new HashMap<>();

    public CachedVideoDownloader() {
        this.realDownloader = new RealVideoDownloader();
    }

    @Override
    public String downloadVideo(String videoUrl) {
        if (cache.containsKey(videoUrl)) {
            System.out.println("Fetching video from cache for URL: " + videoUrl);
            return cache.get(videoUrl);
        }  
        System.out.println("Video not in cache. Downloading...");     
        String videoContent = realDownloader.downloadVideo(videoUrl);
        cache.put(videoUrl, videoContent);
        return videoContent;
        
    }
};


public class Proxy {
    public static void main(String[] args) {
        /* RealVideoDownloader realDownloader1 = new RealVideoDownloader();
        realDownloader1.downloadVideo("proxy-pattern");

        RealVideoDownloader realDownloader2 = new RealVideoDownloader();
        realDownloader2.downloadVideo("proxy-pattern"); */ 


        VideoDownloader cachedDownloader = new CachedVideoDownloader();
        cachedDownloader.downloadVideo("proxy-pattern"); // Downloads and caches the video

        VideoDownloader cachedDownloader2 = new CachedVideoDownloader();
        cachedDownloader2.downloadVideo("proxy-pattern"); // Fetches from cache
    }
};