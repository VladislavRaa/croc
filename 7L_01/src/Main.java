public class Main {

    public static void main(String[] args) {
        Showtime show = new Showtime();
        TicketGenerator user1 = new TicketGenerator(show);
        TicketGenerator user2 = new TicketGenerator(show);
        user1.run();
        user2.run();

    }
}
