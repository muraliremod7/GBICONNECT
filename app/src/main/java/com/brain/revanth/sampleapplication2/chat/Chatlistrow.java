package com.brain.revanth.sampleapplication2.chat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.brain.revanth.sampleapplication2.R;

import java.util.List;

/**
 * Created by Hari Prahlad on 23-05-2016.
 */

public class Chatlistrow extends BaseAdapter {
    private final Activity activity;
    public List<ChatCommonClass> chatlist;
    TextView chatId,chatLeadName;
    ImageView chatimage;

    public Chatlistrow(Activity activity, List<ChatCommonClass> chatlist) {

        this.activity = activity;
        this.chatlist = chatlist;
    }

    @Override
    public int getCount() {
        return chatlist.size();
    }

    @Override
    public Object getItem(int location) {
        return chatlist.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview= inflater.inflate(R.layout.chatlistrow, null, true);

        chatId = (TextView) convertview.findViewById(R.id.chatid);
        chatLeadName = (TextView)convertview.findViewById(R.id.chatleadname);
        chatimage = (ImageView)convertview.findViewById(R.id.chatprofilepic);
        ChatCommonClass chatCommonClass = (ChatCommonClass)getItem(position);

        chatId.setText(chatCommonClass.getChatId());
        chatLeadName.setText(chatCommonClass.getChatLeadName());
        chatimage.setImageResource(R.drawable.user);
        return convertview;
    }
}
