import java.util.HashSet;
import java.util.Set;

public class Showtime {
    private final static int MAX_ROW = 2;
    private final static int MAX_SEAT = 2;
    private HashSet<Seat> freeSeats;

    public Showtime() {
        freeSeats = new HashSet<>();
        for (int row = 1; row <= MAX_ROW; row++) {
            for (int seat = 1; seat <= MAX_SEAT; seat++) {
                freeSeats.add(new Seat(row, seat));
            }
        }
    }
    // возвращает набор мест, доступных для бронирования
    // на текущий сеанс
    public synchronized Set<Seat> getFreeSeats() {
        return this.freeSeats;
    }
    // бронирует место на текущий сеанс;
    // возвращает true, если место успешно забронировано
    // или false, если бронирование не удалось
    // (кто-то успел раньше)
    public synchronized boolean bookSeat(Seat seat) {
        if (freeSeats.remove(seat)) {
            System.out.println("Место " + seat.toString() + " забронировано");
            System.out.println("Свобные места:" + getFreeSeats().toString());
            return true;
        } else {
            System.out.println("Место " + seat.toString() + " уже занято");
            return false;
        }
    }
}
