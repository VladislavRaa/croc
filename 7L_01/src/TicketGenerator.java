public class TicketGenerator extends Thread {
    private final Showtime showtime;

    public TicketGenerator(Showtime showtime) {
        this.showtime = showtime;
    }

    @Override
    public void run() {
        synchronized (showtime) {
            while (!showtime.getFreeSeats().isEmpty()) {
                showtime.bookSeat(new Seat((int) (Math.random() * 2 + 1), (int) (Math.random() * 2 + 1)));
            }
        }
    }
}
