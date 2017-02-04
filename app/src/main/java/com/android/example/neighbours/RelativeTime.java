package com.android.example.neighbours;

import java.util.Date;

/**
 * Created by Jaskirat on 02-02-2017.
 */

public class RelativeTime
{
    public static String GetRelativeTime(Date original,Date current)
    {
        String val;
        long temp;

        long now=current.getTime();
        long time=original.getTime();

        final long diff = now - time;  //now you have a date interval representing with mileseconds.

        if (diff <1000*10)
        {
            val="Just now";
            return val;
        }
        else if (diff <1000*60)
        {
            val="Less than a minute ago";
            return val;
        }
        else if (diff <1000*60*60)
        {
            //less than 1 hour
            temp=diff/(1000*60);
            val=temp+"";
            val+=" minutes ago";
            return val;
        }
        else if (diff < 1000*60*60*24)
        {
            //less than one day
            temp=diff/(1000*60*60);
            val=temp+"";
            val+=" hours ago";
            return val;
        }
        else if (diff < 1000*60*60*24*30)
        {
            //less than month
            temp=diff/(1000*60*60*24);
            val=temp+"";
            val+=" days ago";
            return val;
        }
        else if (diff < 1000*60*60*24*365)
        {
            temp=diff/((1000*60*60*24*30));
            val=temp+"";
            val+=" months ago";
            return val;
        }

        return(" ");
    }
}
