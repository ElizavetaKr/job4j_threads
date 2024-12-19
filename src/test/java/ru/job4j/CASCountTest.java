package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {
    @Test
    public void when100Inc0InTwoThreadsThen200() throws InterruptedException {
        CASCount count = new CASCount(0);
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        count.increment();
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        count.increment();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(count.get()).isEqualTo(200);
    }
}