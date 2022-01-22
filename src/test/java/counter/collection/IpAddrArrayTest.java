package counter.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IpAddrArrayTest {

    IpAddrArray ipAddrArray = new IpAddrArray();

    @Test
    void addTest() {
        // от -2 147 483 648 до -4 (на входе делаем положительным и -4)
        int ipAddr = -2_147_483_648;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.first_array[ipAddrArray.createFirstArrayIndex(ipAddr)]);

        ipAddr = -4;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.first_array[ipAddrArray.createFirstArrayIndex(ipAddr)]);

        // от -3 до -1 (на входе -1)
        ipAddr = -3;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.second_array[ipAddrArray.createSecondArrayIndex(ipAddr)]);

        ipAddr = -2;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.second_array[ipAddrArray.createSecondArrayIndex(ipAddr)]);

        ipAddr = -1;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.second_array[ipAddrArray.createSecondArrayIndex(ipAddr)]);

        // от 0 до 2 147 483 644
        ipAddr = 0;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.third_array[ipAddr]);

        ipAddr = 2_147_483_644;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.third_array[ipAddr]);

        // от 2 147 483 645 до 2 147 483 647 (на входе - 2 147 483 645)
        ipAddr = 2_147_483_645;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.fourth_array[ipAddrArray.createFourthArrayIndex(ipAddr)]);

        ipAddr = 2_147_483_646;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.fourth_array[ipAddrArray.createFourthArrayIndex(ipAddr)]);

        ipAddr = 2_147_483_647;
        ipAddrArray.add(convertIntToStringIP(ipAddr));
        assertTrue(IpAddrArray.fourth_array[ipAddrArray.createFourthArrayIndex(ipAddr)]);

        ipAddrArray.add("0.0.0.69");
        assertTrue(IpAddrArray.third_array[69]);

        // repeat
        ipAddrArray.add("0.0.0.69");

        ipAddrArray.add("151.10.61.84");
        assertTrue(IpAddrArray.first_array[1760936616]);

        ipAddrArray.add("151.10.61.84");

        assertEquals(12, IpAddrArray.unique_count);
    }

    @Test
    void convertStringIPtoIntTest() {
        String firstBinary = Integer.toBinaryString(ipAddrArray.convertStringIPtoInt("0.0.0.0"));
        assertEquals("0", firstBinary);

        String secondBinary = Integer.toBinaryString(ipAddrArray.convertStringIPtoInt("192.168.0.1"));
        assertEquals("11000000101010000000000000000001", secondBinary);

        String thirdBinary = Integer.toBinaryString(ipAddrArray.convertStringIPtoInt("255.255.255.255"));
        assertEquals("11111111111111111111111111111111", thirdBinary);
    }

    private String convertIntToStringIP(int binaryIpAddr) {
        String binaryIpAddrString = Integer.toBinaryString(binaryIpAddr);

        if (binaryIpAddrString.length() < 32) {
            binaryIpAddrString = "0".repeat(32 - binaryIpAddrString.length()) + binaryIpAddrString;
        }

        String firstOctet = binaryIpAddrString.substring(0, 8);
        String secondOctet = binaryIpAddrString.substring(8, 16);
        String thirdOctet = binaryIpAddrString.substring(16, 24);
        String fourthOctet = binaryIpAddrString.substring(24, 32);

        return Integer.parseInt(firstOctet, 2) + "." +
                Integer.parseInt(secondOctet, 2) + "." +
                Integer.parseInt(thirdOctet, 2) + "." +
                Integer.parseInt(fourthOctet, 2);
    }
}
