package com.example.phivantuan.documentview.event;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import com.example.phivantuan.documentview.R;
import com.example.phivantuan.documentview.model.Office;

import javax.inject.Inject;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public class OnMenuItemClick implements PopupMenu.OnMenuItemClickListener {
    private ItemClick mItemClick;

    public OnMenuItemClick(ItemClick mItemClick) {
        this.mItemClick = mItemClick;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send:
                break;
            case R.id.delete:
                break;
            case R.id.information:
                mItemClick.showFile();
                break;
            case R.id.rename:
                break;
        }
        return false;
    }
}
