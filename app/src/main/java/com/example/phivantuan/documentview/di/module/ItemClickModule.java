package com.example.phivantuan.documentview.di.module;

import android.content.Context;

import com.example.phivantuan.documentview.base.BaseViewHolder;
import com.example.phivantuan.documentview.di.scope.ItemClickInfo;
import com.example.phivantuan.documentview.event.ItemClick;
import com.example.phivantuan.documentview.model.Office;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */
@Module
public class ItemClickModule {

    @Provides
    @ItemClickInfo
    Office provideOffice(Office office) {
        return office;
    }

    @Provides
    Context provideContext(BaseViewHolder viewHolder) {
        return viewHolder.itemView.getContext();
    }

    @Provides
    ItemClick provideItemClick(@ItemClickInfo Office office, Context context) {
        return new ItemClick(context, office);
    }
}
