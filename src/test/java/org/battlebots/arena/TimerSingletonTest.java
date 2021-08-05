package org.battlebots.arena;

import org.junit.jupiter.api.Test;

import java.util.Timer;

import static org.assertj.core.api.Assertions.assertThat;

class TimerSingletonTest {
    @Test
    public void alwaysCreatesSameTimer() {
        final Timer timer1 = TimerSingleton.getInstance();
        final Timer timer2 = TimerSingleton.getInstance();
        assertThat(timer1).isNotNull();
        assertThat(timer2).isNotNull();
        assertThat(timer1).isSameAs(timer2);
        timer1.cancel();
    }
}