package com.example.phivantuan.documentview.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;

public class PathUtil {
    @SuppressLint("NewApi")
    public static String getPath(Context context, Uri uri)  {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{ split[1] };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    public static String getPathFromMail(Context context,Uri uri) {
        String fullPath = Environment.getExternalStorageDirectory() + "/DocumentView/" + getContentName(context.getContentResolver(), uri);
        if (uri != null) {
            try {
                InputStream attachment = context.getContentResolver().openInputStream(uri);
                if (attachment == null) {
                    Log.e("GMAIL ATTACHMENT", "Mail attachment failed to resolve");
                } else {
                    FileOutputStream tmp = new FileOutputStream(fullPath);
                    byte[] buffer = new byte[1024];
                    while (attachment.read(buffer) > 0) {
                        tmp.write(buffer);
                    }
                    tmp.close();
                    attachment.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fullPath;
    }

    private static String getContentName(ContentResolver resolver, Uri uri) {
        Cursor cursor = resolver.query(uri, new String[]{"_display_name"}, null, null, null);
        cursor.moveToFirst();
        int nameIndex = cursor.getColumnIndex(cursor.getColumnNames()[0]);
        if (nameIndex >= 0) {
            return cursor.getString(nameIndex);
        }
        return null;
    }

}
