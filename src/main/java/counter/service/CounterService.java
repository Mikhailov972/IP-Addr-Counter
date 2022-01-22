package counter.service;

import counter.collection.IpAddrArray;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CounterService implements Runnable {

    long lines = 1;

    final IpAddrArray ipAddrArray = new IpAddrArray();

    public static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(500_000);

    public static boolean isEnd = false;

    public void readFromFile(String fullPathToFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(fullPathToFile))) {
            String line = br.readLine();
            while (line != null) {
                if (lines % 5_000_000 == 0) {
                    System.out.println("Lines read: " + lines);
                }
                lines++;
                queue.put(line);
                line = br.readLine();
            }

            isEnd = true;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (!isEnd || !queue.isEmpty()) {
                ipAddrArray.add(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
