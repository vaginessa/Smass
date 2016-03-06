package id.co.technomotion.smass.view.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import id.co.technomotion.smass.R;
import id.co.technomotion.smass.model.ItemMenu;
import id.co.technomotion.smass.model.Category;

/**
 * Created by omayib on 12/25/14.
 */
public class CategoriesAdapter extends ArrayAdapter<Category>{
    private int resourceLayout;
    private SparseBooleanArray chekcer=new SparseBooleanArray();
    private OnFollowListener onFollowListener;

    public interface OnFollowListener{
        void onFollowChecked(Category category);
        void onFollowUnchecked(Category category);
    }
    public CategoriesAdapter(Context context, int resource, List<Category> objects) {
        super(context, resource, objects);
        this.resourceLayout=resource;
    }
    public void setOnFollowListener(OnFollowListener followListener){
        this.onFollowListener=followListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Category currentCategory=getItem(position);
        chekcer.put(currentCategory.getId(), currentCategory.isFollowed());

        final ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(resourceLayout,parent,false);
            holder=new ViewHolder();
            holder.tvName= (TextView) convertView.findViewById(R.id.category_name);
            holder.tbFollow= (ToggleButton) convertView.findViewById(R.id.category_follow_btn);

            holder.tbFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.tbFollow.isChecked()){
                        System.out.println("on click 1");//turn on
                        onFollowListener.onFollowChecked(currentCategory);
                    }else{
                        System.out.println("on click 2");//turn off
                        onFollowListener.onFollowUnchecked(currentCategory);
                    }
                }
            });
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        if(chekcer.size()>0){
            if(chekcer.get(currentCategory.getId(),false)){
                holder.tbFollow.setChecked(true);
            }else{
                holder.tbFollow.setChecked(false);
            }
        }


        holder.tvName.setText(currentCategory.getName());
        if(currentCategory.isFollowed()){
            holder.tbFollow.setChecked(true);
        }else{
            holder.tbFollow.setChecked(false);
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView tvName;
        ToggleButton tbFollow;
    }
}
