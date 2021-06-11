package de.cfp.bordergames.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Quest {

    GETSUGAR,
    GETLAVABUCKET,
    GETIRON;

    // From stackoverflow
    private static final List<Quest> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Quest randomQuest()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

}
