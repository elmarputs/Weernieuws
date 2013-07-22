package com.pvb.weernieuws;

import android.app.Activity;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

public class NavDrawerListAdapter extends BaseAdapter
{
    private Activity context;
    private ArrayList<NavDrawerItem> navDrawerItems;

    static class ViewHolder
    {
        TextView itemText;
        ImageView image;
    }

    public NavDrawerListAdapter(Activity context, ArrayList<NavDrawerItem> navDrawerItems)
    {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return navDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.nav_drawer_list_item, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.itemText = (TextView) convertView.findViewById(R.id.item_text);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.item_image);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.itemText.setText(navDrawerItems.get(position).getItemText());
        viewHolder.image.setImageResource(navDrawerItems.get(position).getImageID());
        return convertView;
    }
}
