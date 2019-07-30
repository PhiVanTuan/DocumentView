package com.example.admin.etest.adapter;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.etest.R;
import com.example.admin.etest.base.BaseRecycleAdapter;
import com.example.admin.etest.base.BaseViewHolder;
import com.example.admin.etest.model.Office;

import butterknife.BindView;

public class FileAdapter extends BaseRecycleAdapter<Office, FileAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file,parent,false));
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imgFile) ImageView imgFile;
        @BindView(R.id.imgMore) ImageView imgMore;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvDate) TextView tvDate;
        public ViewHolder(View itemView) {
            super(itemView);
        }


        @Override
        public void bindView(int position) {
            Office office=lstItems.get(position);
            if(office!=null){
                imgFile.setImageResource(office.getIconFile());
                tvName.setText(office.getName());
                if (TextUtils.isEmpty(office.getLastTimeOpen())){
                    tvDate.setVisibility(View.GONE);
                }else {
                    tvDate.setVisibility(View.VISIBLE);
                    tvDate.setText(office.getLastTimeOpen());
                }
                imgMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        }
    }
}
