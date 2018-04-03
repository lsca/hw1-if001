package br.ufpe.cin.if1001.rss;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lucas on 02/04/2018.
 */

public class RSSAdapter extends BaseAdapter {

    private List<ItemRSS> itemRSS;
    private Context c;
    private LayoutInflater layoutInflater;


    public RSSAdapter(Context context,List<ItemRSS> itemRSS) {
        this.itemRSS = itemRSS;
        this.c = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemRSS.size();
    }

    @Override
    public Object getItem(int i) {
        return itemRSS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v = layoutInflater.inflate(R.layout.itemlista,viewGroup,false);
        TextView titulo = (TextView) v.findViewById(R.id.item_titulo);
        TextView conteudo = (TextView) v.findViewById(R.id.item_data);
        titulo.setText(itemRSS.get(i).getTitle());
        conteudo.setText(itemRSS.get(i).getPubDate());
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.layout_item);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("url",itemRSS.get(i).getLink());
                editor.commit();
                Intent it = new Intent(c,WebViewActivity.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(it);
            }
        });

        return v;
    }
}
