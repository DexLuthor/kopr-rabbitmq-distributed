package com.github.dexluthor.common;

import lombok.val;

import java.math.BigDecimal;

public class Utils {
    public static String randomGoal() {
        String[] values = {
                "COFFEE", "LESSON", "MEDICINE", "GIFT", "THANKS", "MEALS", "TOY",
                "DELIVERY", "BOOK", "GAME", "DOG", "SHELF", "GUN", "POSTER", "FLOWER"
        };
        return values[(int) (Math.random() * values.length)];
    }

    public static BigDecimal randomBill() {
        val rand = Math.random() * 10_000;
        val point = String.valueOf(rand).indexOf('.');
        val rand2p = (rand + "00").substring(0, point + 3);

        return BigDecimal.valueOf(Double.parseDouble(rand2p));
    }

    public static String randomLocation() {
        String[] locations = {
                "Backer street", "Compton", "Brooklyn", "Odessa", "Kosice", "Bank Go",
                "Trenchtown", "Saint Martin Bank", "Tatra", "First National", "Big Bank", "Bank Go"
        };
        return locations[(int) (Math.random() * locations.length)];
    }
}
