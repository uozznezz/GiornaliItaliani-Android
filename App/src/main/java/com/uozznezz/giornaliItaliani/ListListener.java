package com.uozznezz.giornaliItaliani;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by Giacomo on 28/12/13.
 */
public class ListListener implements AdapterView.OnItemClickListener {

    private List<RssItem> items;
    private Activity activity;

    public ListListener(List<RssItem> items, MainActivity activity) {
        this.items = items;
        this.activity  = activity;
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(items.get(pos).getUrl().toString()));
        activity.startActivity(i);
    }
}
