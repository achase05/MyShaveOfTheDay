package com.mysotd.android.myshaveoftheday;
/**
 * @author: Deependra Pc
 * Created: 3/28/2016.
 */
public class Items
{
    // variables items name, items images ID and items price
    private String name;
    private int imageId;
    private String price;

    //default constructor
    public Items() {}
    // constructor with parameter image Id, name and price.
    public Items(int imageId, String name, String price) {
        this.name = name;
        this.imageId = imageId;
        this.price = price;
    }
    // return the name of the products
    public String getName()
    {
        return name;
    }
    // sets name of a items
    public void setName(String name)
    {
        this.name = name;
    }
    // returns images ID
    public int getImageId()
    {
        return imageId;
    }
    // sets ID for every images
    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }
    // return items price
    public String getPrice()
    {
        return price;
    }
    // sets items price
    public void setPrice(String price)
    {
        this.price = price;
    }
}
