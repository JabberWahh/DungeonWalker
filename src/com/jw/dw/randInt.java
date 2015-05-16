package com.jw.dw;

import java.util.Random;

/**randInt
 * Created by vahma on 03.05.15.
 */
public class randInt {
    public static int GetRandInt(int min, int max){

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt((max - min) + 1) + min;
    }
}
