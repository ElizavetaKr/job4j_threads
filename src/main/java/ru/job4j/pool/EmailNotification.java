package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Сервис для рассылки почты
 * emailTo(User user) - через ExecutorService отправляет почту
 * close() - закрывает pool
 * send(String subject, String body, String email) - пустой
 */
public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void send(String subject, String body, String email) {

    }

    public void emailTo(User user) {
        String email = user.email();
        String subject = String.format("Notification %s to email %s.", user.username(), email);
        String body = String.format("Add a new event to %s.", user.username());
        pool.submit(() -> send(subject, body, email));
    }

    public void close() {
        pool.shutdown();
    }
}
