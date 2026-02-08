
class CustomException extends Exception {
    CustomException(String message) {
        super(message);
    }
};

public class ErrorHandling {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        try {
            System.out.println("Accessing element at index 3:" + arr[3]);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
