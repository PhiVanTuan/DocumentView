package com.example.admin.etest.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.etest.R;
import com.example.admin.etest.base.BaseRecycleAdapter;
import com.example.admin.etest.base.BaseViewHolder;
import com.example.admin.etest.model.Home;

import butterknife.BindView;

public class HomeAdapter extends BaseRecycleAdapter<Home, HomeAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.imgFile) ImageView imgFile;
        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindView(int position) {
            Home name=lstItems.get(position);
            tvName.setText(name.getName());
            imgFile.setImageResource(name.getIcon());

        }


    }
}
