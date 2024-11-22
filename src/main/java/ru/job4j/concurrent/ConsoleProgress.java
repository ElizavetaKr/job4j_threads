package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        try {
            var process = new char[]{'-', '\\', '|', '/'};
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                if (i != process.length) {
                    char ch = process[i];
                    System.out.print("\rLoading ... " + ch + ".");
                    Thread.sleep(500);
                    i++;
                } else {
                    i = 0;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
