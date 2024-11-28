package ru.job4j.thread;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        File file = new File("tmp.xml");
        long startAt = 0;
        long finishAt = 0;
        try (InputStream input = new URL(url).openStream();
             OutputStream output = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                startAt = System.nanoTime();
                output.write(dataBuffer, 0, bytesRead);
                finishAt = System.nanoTime();
            }
            long downloadTime = finishAt - startAt;
            long pause = downloadTime / speed;
            Thread.sleep(pause);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validation(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The wrong number of parameters");
        }
        try {
            new URL(args[0]);
        } catch (MalformedURLException e) {
            System.out.println("The link is not set correctly");
        }
        try {
            Integer.parseInt(args[1]);
        } catch (IllegalArgumentException e) {
            System.out.println("The speed is not set correctly");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validation(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}