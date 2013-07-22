package com.pvb.weernieuws;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity
{

    private ListView navDrawerList;
    private ArrayList<NavDrawerItem> navDrawerItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navDrawerList = (ListView) findViewById(R.id.nav_drawer);
        navDrawerItems = getNavDrawerItemsList();
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(this, navDrawerItems);
        navDrawerList.setAdapter(adapter);
        new xmlLoaderTask().execute("http://androidapp.weernieuws.info/xml-weernieuws-vooruitzichten.php");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public String getXml(URL url) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try
        {
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Reader inputReader = new InputStreamReader(inputStream);
            char[] charBuffer = new char[3000];
            inputReader.read(charBuffer);
            return new String(charBuffer);
        }
        finally
        {
            connection.disconnect();
        }
    }

    private ArrayList<NavDrawerItem> getNavDrawerItemsList()
    {
        ArrayList<NavDrawerItem> list = new ArrayList<NavDrawerItem>();
        list.add(new NavDrawerItem("Home", R.drawable.ic_launcher));
        list.add(new NavDrawerItem("Vooruitzichten", R.drawable.ic_launcher));
        return list;
    }

    private class xmlLoaderTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url = new URL(strings[0]);
                return getXml(url);
            }
            catch(IOException ioException)
            {
                return "Faal!";
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            TextView tv = (TextView) findViewById(R.id.textViewXML);
            tv.setText(s);
            XmlParser parser = new XmlParser(s);
            Log.e("Char: ", parser.readTag("neerslag3"));
        }
    }
}
