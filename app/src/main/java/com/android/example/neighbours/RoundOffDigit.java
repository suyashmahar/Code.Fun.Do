package com.android.example.neighbours;

/**
 * Created by Jaskirat on 03-02-2017.
 */

public class RoundOffDigit
{
    public static String getRoundedOffDigits(long exact)
    {
        String val;

        if(exact==0)
           val="0";
        else if (exact < 1000)
           val=exact+"";
        else if (exact < 1000000)
        {
            val=exact/1000 + "";
            exact-=(exact/1000)*1000;
            exact+=50;
            exact/=100;
            val+=".";
            val+=Long.toString(exact);
            val+="k";
        }
        else
        {
            val=exact/1000000 + "";
            exact-=(exact/1000000)*1000000;
            exact+=50000;
            exact/=100000;
            val+=".";
            val+=Long.toString(exact);
            val+="M";
        }

        return val;
    }
}
