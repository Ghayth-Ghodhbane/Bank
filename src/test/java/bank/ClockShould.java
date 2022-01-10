package bank;

import org.junit.jupiter.api.Test;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

class ClockShould {

    @Test
    void get_current_local_date() {
        Clock clock = new Clock();
        assertThat(clock.getDate()).isEqualTo(now());
    }
}