package bank;

import java.time.LocalDate;

import static java.time.LocalDate.now;

public class Clock {

    private final LocalDate date = now();

    public LocalDate getDate() {
        return date;
    }
}
