import java.io.*;

class Logger {
    private String path;

    Logger(String path) throws IOException {
        this.path = path;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void log(String message) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.append(message);
            bw.newLine();
        } catch(IOException e) {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }
}

public class LoggerClass {
    public static void main(String[] args) {
        try {
            Logger logger = new Logger("log.txt");
            logger.log("This is a log message.");
        } catch (IOException e) {
            System.out.println("Failed to initialize logger: " + e.getMessage());
        }
    }
}
