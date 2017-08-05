package omidheshmatinia.github.com.concentrationgame.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import omidheshmatinia.github.com.concentrationgame.R;
import omidheshmatinia.github.com.concentrationgame.model.ScoreHistory;

/**
 * Created by Omid Heshmatinia on 8/5/17.
 */

public class ScoreHistoryAdapter extends RecyclerView.Adapter<ScoreHistoryAdapter.ItemHolder>{

    List<ScoreHistory> items = new ArrayList<>();

    public ScoreHistoryAdapter(List<ScoreHistory> items) {
        this.items = items;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_history,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ScoreHistory item = items.get(position);
        holder.row.setText(String.valueOf(position+1 + "."));
        holder.title.setText(item.getUserName());
        holder.score.setText(String.valueOf(item.getMilliSeconds()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ItemHolder extends RecyclerView.ViewHolder {

        TextView row;
        TextView title;
        TextView score;
        View wrapper;
        ItemHolder(View itemView) {
            super(itemView);
            row = itemView.findViewById(R.id.textview_item_score_history_row);
            title = itemView.findViewById(R.id.textview_item_score_history_title);
            score = itemView.findViewById(R.id.textview_item_score_history_score);
            wrapper = itemView;
        }
    }
}
