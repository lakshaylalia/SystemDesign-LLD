import java.io.*;
import java.util.*;

public class FileHandling {
    public static void main(String[] args) throws IOException {
        /* BufferedReader bw = null;
        try {
            bw = new BufferedReader(new FileWriter("test.txt"));
            bw.write("Hey i am writing");
            // bw.flush();
            bw.newLine();
            bw.write("New data into file");
            // bw.flush();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            if (bw != null) {
                bw.close();
            }
        } */

            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader("test.txt"));
                int c;
                // String line;
                // while((line = br.readLine()) !=  null) {
                //     System.out.println(line);
                // }
                while((c = br.read()) != -1) {
                    System.out.print((char)c + " ");
                }

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            } finally {
                if(br != null) {
                    br.close();
                }
            }
    }
};