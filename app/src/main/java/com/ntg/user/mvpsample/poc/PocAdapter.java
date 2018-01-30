package com.ntg.user.mvpsample.poc;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ntg.user.mvpsample.R;
import com.ntg.user.mvpsample.poc.data.Poc;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;



public class PocAdapter extends RecyclerView.Adapter<PocAdapter.PocViewHolder> {
    PocItemListener pocItemListener;
    List<Poc> pocList;
    public PocAdapter(List<Poc> pocList, PocItemListener pocItemListener) {
        setPocList(pocList);
        this.pocItemListener = checkNotNull(pocItemListener);
    }
    public void replaceData(List<Poc> pocList) {
        setPocList(pocList);
        notifyDataSetChanged();
    }
    private void setPocList(List<Poc> pocList) {
        this.pocList = checkNotNull(pocList);
    }

    @Override
    public PocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poc_item, parent, false);
        return new PocViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final PocViewHolder holder, final int position) {

                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pocItemListener.onPocClicked(pocList.get(position));
            }
        });
        holder.title.setText(pocList.get(position).getTitle());
        if(pocList.get(position).isCompleted()){
            holder.view.setBackgroundColor(Color.GREEN);

        }else {
            holder.view.setBackgroundColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return pocList.size();
    }

    public class PocViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView title;
        View view;
        public PocViewHolder(View itemView) {
            super(itemView);

            view=itemView;
            title = itemView.findViewById(R.id.title);
            linearLayout =itemView.findViewById(R.id.itemLayout);


        }
    }
}
