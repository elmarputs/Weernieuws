package com.pvb.weernieuws;

import android.util.Log;

public class XmlParser
{
    private String file;
    private int fileLength;

    public XmlParser(String fileToParse) // Constructor
    {
        this.file = fileToParse;
        this.fileLength = file.length();
    }

    public String readTag(String tag) // Reads XML file until specified tag is found; then returns content
    {
        int pos = file.lastIndexOf("<neerslag3>");
        Log.e("Index: ", Integer.toString(pos));
        char[] buffer = new char[5];
        for(int i = 1; i < 6; i++)
        {
            buffer[i-1] = file.charAt(pos + i);
        }
        return new String(buffer);
    }
}
