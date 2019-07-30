package com.example.admin.etest.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycleAdapter<M,V extends BaseViewHolder> extends RecyclerView.Adapter<V> {
    protected List<M> lstItems;
    private ItemClickListener mItemClickListener;

    public BaseRecycleAdapter() {
        lstItems=new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return lstItems.size();
    }

    public ItemClickListener getItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void loadData(List<M> lstItems){
        if (lstItems!=null&&lstItems.size()>0){
            this.lstItems.clear();
            this.lstItems.addAll(lstItems);
            notifyDataSetChanged();
        }
    }

    public M getItem(int position){
        if (lstItems!=null&&lstItems.size()>position){
            return lstItems.get(position);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull V holder, final int position) {
        holder.bindView(position);

        if (mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(position);
                }
            });
        }
    }

    public interface ItemClickListener{
        void onItemClick(int position);
    }
}
