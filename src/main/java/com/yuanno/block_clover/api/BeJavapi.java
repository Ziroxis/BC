package com.yuanno.block_clover.api;

import java.util.Random;

public class BeJavapi {


    public static String randomizer(String[] values)
    {
        int random = RNG(values.length);
        String randomizedString = values[random];
        return randomizedString;
    }

    public static double randomWithRange(int min, int max)
    {
        return new Random().nextInt(max + 1 - min) + min;
    }

    public static boolean RNGboolean()
    {
        Random rd = new Random();
        return rd.nextBoolean();
    }
    public static int RNG(int cap)
    {
        Random rand = new Random();
        int int_random = rand.nextInt(cap);

        return int_random;
    }

    public static boolean isNullOrEmpty(String str)
    {
        if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
            return false;
        return true;
    }

    public static String getResourceName(String text)
    {
        return text
                .replaceAll("[ \\t]+$", "")
                .replaceAll("\\(","")
                .replaceAll("\\)","")
                .replaceAll("\\s+", "_")
                .replaceAll("[\\'\\:\\-\\,\\#]", "")
                .replaceAll("\\&", "and").toLowerCase();
    }
}
