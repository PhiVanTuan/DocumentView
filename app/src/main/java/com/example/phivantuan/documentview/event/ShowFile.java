package com.example.phivantuan.documentview.event;

import android.content.Intent;

/**
 * Created by Phi Van Tuan on 30/07/2019
 */

public interface ShowFile {
    void showFileFromUri(Intent intent);

    void showFileFromApp(Intent intent);
}
