package me.brennan.antivirtualmachine.check.impl;

import me.brennan.antivirtualmachine.check.AbstractCheck;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author Brenann
 * @since 4/12/2020
 **/
public class MacAddressCheck extends AbstractCheck {
    private String[] macAddresses = new String[] {
            //VMWare
            "\\x00\\x05\\x69",
            "00:05:69",
            "\\x00\\x0C\\x29",
            "00:0C:29",
            "\\x00\\x1C\\x14",
            "00:1C:14",
            "\\x00\\x50\\x56",
            "00:50:56",
            //VirtualBox
            "08:00:27",
            //Xen
            "\\x00\\x16\\x3E",
            //Parallels
            "\\x00\\x1C\\x42",
            //Hybrid Analysis
            "\\x0A\\x00\\x27"
    };

    public MacAddressCheck() {
        super(CheckType.FLAG);
    }

    @Override
    public boolean check() {
        for(String mac : macAddresses) {
            return getMacAddress().equalsIgnoreCase(mac);
        }

        return false;
    }

    private String getMacAddress() {
        final StringBuilder macAddressBuilder = new StringBuilder();

        try {
            final InetAddress ipAddress = InetAddress.getLocalHost();
            final NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
            final byte[] macAddressBytes = networkInterface.getHardwareAddress();

            for (int macAddressByteIndex = 0; macAddressByteIndex < macAddressBytes.length; macAddressByteIndex++) {
                final String macAddressHexByte = String.format("%02X", macAddressBytes[macAddressByteIndex]);
                macAddressBuilder.append(macAddressHexByte);

                if (macAddressByteIndex != macAddressBytes.length - 1)
                    macAddressBuilder.append(":");
            }
        } catch (UnknownHostException | SocketException ignored) { }

        return macAddressBuilder.toString();
    }
}
