package com.fanwe.www.cache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fanwe.library.cache.SDDiskCache;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    private String key = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SDDiskCache.init(this);
        SDDiskCache.setGlobalObjectConverter(new JsonObjectConverter());

        SDDiskCache.open().putInt(key, 100);
        printInt();
        SDDiskCache.open().removeInt(key);
        printInt();

        SDDiskCache.open().putLong(key, 200);
        printLong();
        SDDiskCache.open().removeLong(key);
        printLong();

        SDDiskCache.open().putFloat(key, 100.123f);
        printFloat();
        SDDiskCache.open().removeFloat(key);
        printFloat();

        SDDiskCache.open().putDouble(key, 200.123345d);
        printDouble();
        SDDiskCache.open().removeDouble(key);
        printDouble();

        SDDiskCache.open().putString(key, "hello");
        printString();
        SDDiskCache.open().removeString(key);
        printString();
    }

    private void printInt()
    {
        Log.i(TAG, "int:" + SDDiskCache.open().getInt(key, 0));
    }

    private void printLong()
    {
        Log.i(TAG, "long:" + SDDiskCache.open().getLong(key, 0));
    }

    private void printFloat()
    {
        Log.i(TAG, "float:" + SDDiskCache.open().getFloat(key, 0));
    }

    private void printDouble()
    {
        Log.i(TAG, "double:" + SDDiskCache.open().getDouble(key, 0));
    }

    private void printString()
    {
        Log.i(TAG, "string:" + SDDiskCache.open().getString(key));
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        SDDiskCache.open().delete();
        SDDiskCache.openCache().delete();
    }
}
