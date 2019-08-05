package com.example.phivantuan.documentview.di.module;

import android.content.Context;

import com.example.phivantuan.documentview.base.BaseViewHolder;
import com.example.phivantuan.documentview.di.scope.ItemClickInfo;
import com.example.phivantuan.documentview.event.ItemClick;
import com.example.phivantuan.documentview.event.OnMenuItemClick;
import com.example.phivantuan.documentview.model.Office;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */
@Module
public class ItemClickModule {
    private BaseViewHolder viewHolder;

//    public ItemClickModule(BaseViewHolder viewHolder) {
//        this.viewHolder = viewHolder;
//    }
//
//    @Provides
//    @ItemClickInfo
//    BaseViewHolder provideViewHolder(){
//        return viewHolder;
//    }

    @Provides
    @ItemClickInfo
    Office provideOffice(Office office) {
        return office;
    }

    @Provides
    @ItemClickInfo
    Context provideContext(BaseViewHolder viewHolder) {
        return viewHolder.itemView.getContext();
    }

    @Provides
    @Inject
    ItemClick provideItemClick(@ItemClickInfo  Context context,@ItemClickInfo Office office) {
        return new ItemClick(context, office);
    }

    @Provides
    OnMenuItemClick provideOnMenuClick(ItemClick itemClick){
        return new OnMenuItemClick(itemClick);
    }
}
