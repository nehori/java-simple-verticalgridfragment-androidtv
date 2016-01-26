package com.nehori.simple_verticalgridfragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.Log;

import java.util.List;

/**
 * VerticalGridFragment shows contents with vertical alignment
 */
public class VerticalGridFragment extends android.support.v17.leanback.app.VerticalGridFragment {

    private static final String TAG = VerticalGridFragment.class.getSimpleName();
    private static final int NUM_COLUMNS = 5;
    private List<ResolveInfo> mApps;
    private ArrayObjectAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setTitle("App Launcher");
        loadApps();
        setupFragment();
        setupEventListeners();
    }

    private void loadApps() {
        Intent leanbackLauncherIntent = new Intent(Intent.ACTION_MAIN, null);
        leanbackLauncherIntent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER);

        mApps = getActivity().getPackageManager().queryIntentActivities(leanbackLauncherIntent,
				getActivity().getPackageManager().GET_META_DATA | getActivity().getPackageManager().GET_ACTIVITIES);
    }

    private void setupFragment() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        setGridPresenter(gridPresenter);
        mAdapter = new ArrayObjectAdapter(new CardPresenter());

        for (int i = 0; i < mApps.size(); i++) {
             ResolveInfo info = mApps.get(i);
              mAdapter.add(info);
         }
        setAdapter(mAdapter);
    }

    private void setupEventListeners() {
        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            ResolveInfo info = (ResolveInfo)item;
            String pkg = info.activityInfo.packageName;
            String cls = info.activityInfo.name;
            ComponentName component = new ComponentName(pkg, cls);

            Intent i = new Intent();
            i.setComponent(component);
            startActivity(i);
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
        }
    }
}