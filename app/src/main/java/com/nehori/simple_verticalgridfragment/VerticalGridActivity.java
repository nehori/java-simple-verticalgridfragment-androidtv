package com.nehori.simple_verticalgridfragment;

import android.os.Bundle;
import android.app.Activity;

import com.corochann.androidtvapptutorial.R;

public class VerticalGridActivity extends Activity {

    private static final String TAG = VerticalGridActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_grid);
    }
}
