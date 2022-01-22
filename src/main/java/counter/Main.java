package counter;

import counter.collection.IpAddrArray;
import counter.service.CounterService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static final String fullPathToFile = "/media/teemitze/15DA90397DF969B9/ip_addresses_task/ip_addresses";

    public static void main(String[] args) {
        CounterService counterService = new CounterService();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(counterService);

        counterService.readFromFile(fullPathToFile);

        while (true) {
            if (CounterService.isEnd && CounterService.queue.isEmpty()) {
                executorService.shutdown();
                System.out.println("TOTAL unique IP Address: " + IpAddrArray.unique_count);
                break;
            }
        }
    }
}
