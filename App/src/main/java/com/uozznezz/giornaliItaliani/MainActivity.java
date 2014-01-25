package com.uozznezz.giornaliItaliani;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {

        @Override
        protected List<RssItem> doInBackground(String... urls) {
            Log.d(TAG, "Asynch thread: " + Thread.currentThread().getName());

            try {
                RssReader rssReader = new RssReader(urls[0]);
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> items) {

            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(
                    mainActivity,
                    android.R.layout.simple_list_item_1,
                    items);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new ListListener(items, mainActivity));

        }
    }

    private final String TAG = "GiIt";
    private MainActivity mainActivity;

    private LinearLayout contentPane;
    private Button loadNews1, loadNews2, loadNews3, loadNews4;
    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.linear_layout);

        mainActivity = this;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentPane, new PlaceholderFragment())
                    .commit();
        }

        contentPane = (LinearLayout)findViewById(R.id.contentPane);
        loadNews1 = (Button) findViewById(R.id.loadNews1);
        loadNews2 = (Button) findViewById(R.id.loadNews2);
        loadNews3 = (Button) findViewById(R.id.loadNews3);
        loadNews4 = (Button) findViewById(R.id.loadNews4);
        textView = (TextView) findViewById(R.id.feed);
        listView = (ListView) findViewById(R.id.news);

        loadNews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAsyncTask("Il Sole 24 Ore", "http://www.ilsole24ore.com/rss/primapagina.xml");
            }
        });

        loadNews2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAsyncTask("La Repubblica", "http://www.repubblica.it/rss/homepage/rss2.0.xml");
            }
        });

        loadNews3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAsyncTask("Corriere della Sera", "http://xml.corriereobjects.it/rss/homepage.xml");
            }
        });

        loadNews4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAsyncTask("La Stampa", "http://www.lastampa.it/rss.xml");
            }
        });

        Log.d(TAG, "Main thread: " + Thread.currentThread().getName());
    }

    private void startAsyncTask(String feedLabel, String feedUrl) {
        textView.setText(feedLabel);
        GetRSSDataTask task = new GetRSSDataTask();
        task.execute(feedUrl);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
