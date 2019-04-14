import java.util.HashSet;
import java.util.Set;

public class Showtime {
    private final static int MAX_ROW = 5;
    private final static int MAX_SEAT = 5;
    private HashSet<Seat> freeSeats;
    // возвращает набор мест, доступных для бронирования
    // на текущий сеанс

    public Showtime() {
        freeSeats = new HashSet<>();
        for (int row = 1; row <= MAX_ROW; row++) {
            for (int seat = 1; seat <= MAX_SEAT; seat++) {
                freeSeats.add(new Seat(row, seat));
            }
        }
    }

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
            return true;
        } else {
            System.out.println("Место " + seat.toString() + " уже занято");
            return false;
        }
    }
    /*if (row < 1 || row > MAX_ROW || seat < 1 || seat > MAX_ROW) {
        throw new IllegalArgumentException("Такого места нет в зале");
    }*/
}
