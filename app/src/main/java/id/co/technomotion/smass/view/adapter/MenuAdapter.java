package id.co.technomotion.smass.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.technomotion.smass.R;
import id.co.technomotion.smass.model.ItemMenu;

/**
 * Created by omayib on 12/25/14.
 */
public class MenuAdapter extends ArrayAdapter<ItemMenu>{
    private int resourceLayout;
    public MenuAdapter(Context context, int resource, List<ItemMenu> objects) {
        super(context, resource, objects);
        this.resourceLayout=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemMenu currentMenu=getItem(position);


        ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(resourceLayout,parent,false);
            holder=new ViewHolder();
            holder.tvName= (TextView) convertView.findViewById(R.id.menu_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tvName.setText(currentMenu.getName());

        return convertView;
    }

    private static class ViewHolder{
        TextView tvName;
    }
}
