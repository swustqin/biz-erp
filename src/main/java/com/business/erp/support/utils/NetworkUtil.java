package com.business.erp.support.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author tuchuntong and jadenQin
 */
public class NetworkUtil {

    private static Logger log = LoggerFactory.getLogger(NetworkUtil.class);

    /**
     * Get current IP and MAC address
     *
     * @return IP and MAC address string separated by '_'
     */
    public static String getConfig() {
        String[] s = new String[2];
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            s[0] = address.getHostAddress();
            Formatter formatter = new Formatter();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    s[1] = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                            (i < mac.length - 1) ? "-" : "").toString();

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return s[1] == null ? s[0] : s[0] + "_" + s[1];
    }


    public static void main(String[] args) {
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                System.out.println("DisplayName:" + ni.getDisplayName());
                System.out.println("Name:" + ni.getName());
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    System.out.println("IP:" + ips.nextElement().getHostAddress());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
