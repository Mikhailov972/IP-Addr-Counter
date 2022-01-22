package counter.collection;

public class IpAddrArray {

    // от -2 147 483 648 до -4
    public static final boolean[] first_array = new boolean[Integer.MAX_VALUE - 2];
    // от -3 до -1
    public static final boolean[] second_array = new boolean[3];
    // от 0 до 2 147 483 644
    public static final boolean[] third_array = new boolean[Integer.MAX_VALUE - 2];
    // от 2 147 483 645 до 2 147 483 647
    public static final boolean[] fourth_array = new boolean[3];

    public static long unique_count = 0;

    public void add(String stringIpAddr) {
        int ipAddr = convertStringIPtoInt(stringIpAddr);
        if (ipAddr <= -4) {
            synchronized (first_array) {
                if (!first_array[createFirstArrayIndex(ipAddr)]) {
                    first_array[createFirstArrayIndex(ipAddr)] = true;
                    unique_count++;
                }
            }
        } else if (ipAddr <= -1) {
            synchronized (second_array) {
                if (!second_array[createSecondArrayIndex(ipAddr)]) {
                    second_array[createSecondArrayIndex(ipAddr)] = true;
                    unique_count++;
                }
            }
        } else if (ipAddr <= 2_147_483_644) {
            synchronized (third_array) {
                if (!third_array[ipAddr]) {
                    third_array[ipAddr] = true;
                    unique_count++;
                }
            }
        } else {
            synchronized (fourth_array) {
                if (!fourth_array[createFourthArrayIndex(ipAddr)]) {
                    fourth_array[createFourthArrayIndex(ipAddr)] = true;
                    unique_count++;
                }
            }
        }
    }

    public int createFirstArrayIndex(int ipAddr) {
        return (ipAddr * -1) - 4;
    }

    public int createSecondArrayIndex(int ipAddr) {
        return (ipAddr * -1) - 1;
    }

    public int createFourthArrayIndex(int ipAddr) {
        return ipAddr - 2_147_483_645;
    }

    /**
     * Конвертируем строку в int, так как он весит меньше, чем строка
     *
     * @param line строка
     * @return int
     */
    public int convertStringIPtoInt(String line) {
        String[] octets = line.split("\\.");

        int binaryIpAddr = Integer.parseInt(octets[0]) << 24;
        binaryIpAddr += Integer.parseInt(octets[1]) << 16;
        binaryIpAddr += Integer.parseInt(octets[2]) << 8;
        binaryIpAddr += Integer.parseInt(octets[3]);

        return binaryIpAddr;
    }
}
