import java.util.*;

class PaymentService {
    public void makePayment(String accountId, double amount) {
        System.out.println("Payment of Rs " + amount + " successful to account " + accountId);
    }
};

class SeatReservationService {
    public void resereSeat(String movieId, String seatNumber) {
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
};

class NotificationService {
    public void sendBookingConfirmation(String userEmail) {
        System.out.println("Booking confirmation sent to " + userEmail);
    }
};

class LoyaltyPointService {
    public void addPoints(String accountId, int points) {
        System.out.println(points + " loyalty points added to account " + accountId);
    }
};

class TicketService {
    public void generateTicket(String movieId, String seatNumber) {
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
};

class MovieBookingManager {
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointService loyaltyPointService;
    private TicketService ticketService;

    public MovieBookingManager(PaymentService paymentService, SeatReservationService seatReservationService,
            NotificationService notificationService, LoyaltyPointService loyaltyPointService,
            TicketService ticketService) {
        this.paymentService = paymentService;
        this.seatReservationService = seatReservationService;
        this.notificationService = notificationService;
        this.loyaltyPointService = loyaltyPointService;
        this.ticketService = ticketService;
    }

    public void processBooking(String accountId, String userEmail, String movieId, String seatNumber, double amount) {
        paymentService.makePayment(accountId, amount);
        seatReservationService.resereSeat(movieId, seatNumber);
        notificationService.sendBookingConfirmation(userEmail);
        loyaltyPointService.addPoints(accountId, 50);
        ticketService.generateTicket(movieId, seatNumber);

        System.out.println("Movie ticket booking completed successfully!");
    }
};

class MovieBookingFacade {
    private MovieBookingManager movieBookingManager;

    public MovieBookingFacade() {
        movieBookingManager = new MovieBookingManager(new PaymentService(), new SeatReservationService(),
                new NotificationService(), new LoyaltyPointService(), new TicketService());
    }

    public void bookMovieTicket(String accountId, String userEmail, String movieId, String seatNumber, double amount) {
        movieBookingManager.processBooking(accountId, userEmail, movieId, seatNumber, amount);
    }
};

public class Facade {
    public static void main(String[] args) {
        // Booking a ticket manually
        /*
         * PaymentService paymentService = new PaymentService();
         * paymentService.makePayment("user123", 100.0);
         * 
         * SeatReservationService seatReservationService = new SeatReservationService();
         * seatReservationService.resereSeat("movie456", "A10");
         * 
         * NotificationService notificationService = new NotificationService();
         * notificationService.sendBookingConfirmation("user123@example.com");
         * 
         * LoyaltyPointService loyaltyPointService = new LoyaltyPointService();
         * loyaltyPointService.addPoints("user123", 50);
         * 
         * TicketService ticketService = new TicketService();
         * ticketService.generateTicket("movie456", "A10");
         */

        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMovieTicket("user123", "user123@example.com", "movie456", "A10", 100.0);
    }
};
