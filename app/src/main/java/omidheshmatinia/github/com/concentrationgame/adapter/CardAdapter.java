package omidheshmatinia.github.com.concentrationgame.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.model.PictureCard;
import omidheshmatinia.github.com.concentrationgame.utils.ColorHelper;

/**
 * Created by Omid Heshmatinia on 8/3/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ItemHolder>{

    List<PictureCard> items = new ArrayList<>();
    private View.OnClickListener mListener;

    public CardAdapter(List<PictureCard> items, View.OnClickListener mListener) {
        this.items = items;
        this.mListener = mListener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        PictureCard item = items.get(position);

        holder.cardNumber.setText(String.valueOf(position+1));
        holder.cardView.setBackgroundColor(ColorHelper.getInstance().getColorFromList(position));
        ImageLoader.getInstance().displayImage(item.getImageUrl(),holder.image);
        if(mListener!=null){
            holder.wrapper.setTag(item);
            holder.wrapper.setOnClickListener(mListener);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView cardNumber;
        CardView cardView;
        ImageView image;
        View wrapper;
        ItemHolder(View itemView) {
            super(itemView);
            cardNumber = itemView.findViewById(R.id.textview_item_card_number);
            cardView =  itemView.findViewById(R.id.cardview_item_card);
            image =  itemView.findViewById(R.id.imageview_item_card_picture);
            wrapper = itemView;
        }
    }
}
