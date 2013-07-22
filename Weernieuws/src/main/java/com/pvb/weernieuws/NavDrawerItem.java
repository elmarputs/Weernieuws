package com.pvb.weernieuws;

public class NavDrawerItem
{
    private int imageID;
    private String itemText;

    public NavDrawerItem(String itemText, int imageID)
    {
        this.itemText = itemText;
        this.imageID = imageID;
    }

    public void setItemText(String itemText)
    {
        this.itemText = itemText;
    }

    public void setImageID(int imageID)
    {
        this.imageID = imageID;
    }

    public String getItemText()
    {
        return itemText;
    }

    public int getImageID()
    {
        return imageID;
    }
}
