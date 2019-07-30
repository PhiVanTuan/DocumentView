package com.example.phivantuan.documentview.di.component;

import android.content.Context;
import android.view.View;

import com.example.phivantuan.documentview.base.BaseViewHolder;
import com.example.phivantuan.documentview.di.module.ItemClickModule;
import com.example.phivantuan.documentview.model.Office;

import dagger.Component;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */
@Component(modules = ItemClickModule.class)
public interface ItemClickComponent {
    void inject(BaseViewHolder viewHolder);

    Office provideOffice();
}
