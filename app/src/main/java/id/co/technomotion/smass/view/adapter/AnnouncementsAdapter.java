package id.co.technomotion.smass.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.technomotion.smass.R;
import id.co.technomotion.smass.model.Announcement;

/**
 * Created by omayib on 12/25/14.
 */
public class AnnouncementsAdapter extends ArrayAdapter<Announcement>{
    private int resourceLayout;

    public AnnouncementsAdapter(Context context, int resource, List<Announcement> objects) {
        super(context, resource, objects);
        this.resourceLayout=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Announcement currentAnnouncement=getItem(position);


        ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(resourceLayout,parent,false);
            holder=new ViewHolder();
            holder.tvDate= (TextView) convertView.findViewById(R.id.ann_date);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.ann_title);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tvTitle.setText(currentAnnouncement.getTitle());
        holder.tvDate.setText(currentAnnouncement.getDate());

        return convertView;
    }

    private static class ViewHolder{
        TextView tvDate, tvTitle;

    }
}
