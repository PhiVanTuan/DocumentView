package com.example.phivantuan.documentview.di.component;

import android.content.Context;
import android.view.View;

import com.example.phivantuan.documentview.base.BaseViewHolder;
import com.example.phivantuan.documentview.di.module.ItemClickModule;
import com.example.phivantuan.documentview.di.scope.ItemClickInfo;
import com.example.phivantuan.documentview.event.ItemClick;
import com.example.phivantuan.documentview.event.OnMenuItemClick;
import com.example.phivantuan.documentview.model.Office;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */
@Component(modules = ItemClickModule.class)
public interface ItemClickComponent {
    void inject(BaseViewHolder viewHolder);

    OnMenuItemClick provideOnMenuClick();


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder withOffice(Office office);

        @BindsInstance
        Builder withViewHolder(BaseViewHolder viewHolder);

        ItemClickComponent build();

    }
}
