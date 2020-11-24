package com.github.dexluthor.common;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.math.BigDecimal;

@UtilityClass
public class Utils {
    public String randomGoal() {
        final String[] values = {
                "COFFEE", "LESSON", "MEDICINE", "GIFT", "THANKS", "MEALS", "TOY",
                "DELIVERY", "BOOK", "GAME", "DOG", "SHELF", "GUN", "POSTER", "FLOWER"
        };
        return values[(int) (Math.random() * values.length)];
    }

    public BigDecimal randomBill() {
        val rand = Math.random() * 10_000;
        val point = String.valueOf(rand).indexOf('.');
        val rand2p = (rand + "00").substring(0, point + 3);

        return BigDecimal.valueOf(Double.parseDouble(rand2p));
    }

    public String randomLocation() {
        final String[] locations = {
                "Backer street", "Compton", "Brooklyn", "Odessa", "Kosice", "Bank Go",
                "Trenchtown", "Saint Martin Bank", "Tatra", "First National", "Big Bank", "Bank Go"
        };
        return locations[(int) (Math.random() * locations.length)];
    }
}
