package com.seekerr.simulator.robot.util;

import java.util.Random;
/**
 *
 * @author  wkerr
 */
public class Rand {
    
    public static Random rand = new Random(System.currentTimeMillis());
    
    /** Creates a new instance of Rand */
    public Rand() {
    }
    
    public static boolean nextBoolean() {
        return rand.nextBoolean();
    }
    
    public static double nextDouble() {
        return rand.nextDouble();
    }
    
    public static int nextInt(int n) {
        return rand.nextInt(n);
    }
    
    public static double ssrand() {
        return ((1.0 + (rand.nextInt()&07775)) / 4096);
    }

    public static int rrand(int min, int max) {
        int nb = max - min;
        return(rand.nextInt(1000000000) % nb + min);
    }
}
