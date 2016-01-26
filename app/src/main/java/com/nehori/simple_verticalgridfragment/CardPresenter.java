package com.nehori.simple_verticalgridfragment;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.BaseCardView;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CardPresenter extends Presenter {
	
    private static final String TAG = CardPresenter.class.getSimpleName();

    private static int CARD_WIDTH = 320;
    private static int CARD_HEIGHT = 180;
    private Context mContext;

    static class ViewHolder extends Presenter.ViewHolder {
        private ImageCardView mCardView;

        public ViewHolder(View view) {
            super(view);
            Log.d(TAG, "ViewHolder");
            mCardView = (ImageCardView) view;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "onCreateViewHolder");
        mContext = parent.getContext();
        ImageCardView cardView = new ImageCardView(mContext);

        cardView.setCardType(BaseCardView.CARD_TYPE_INFO_UNDER);
        cardView.setInfoVisibility(BaseCardView.CARD_REGION_VISIBLE_ALWAYS);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        ResolveInfo info = (ResolveInfo) item;

        Log.d(TAG, "onBindViewHolder");
         Drawable banner = info.activityInfo.loadBanner(mContext.getPackageManager());
                  if (banner == null) {
                      Log.d(TAG, "loadBanner is null.");
                      banner = info.activityInfo.loadLogo(mContext.getPackageManager());
                  }
                  if (banner == null) {
                      Log.d(TAG, "loadLogo is null.");
                      banner = info.activityInfo.loadIcon(mContext.getPackageManager());
                  }
                String appName = info.loadLabel(mContext.getPackageManager()).toString();

            ((ViewHolder) viewHolder).mCardView.setTitleText(appName);
            ((ViewHolder) viewHolder).mCardView.setContentText("Test");
            ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
            ((ViewHolder) viewHolder).mCardView.setMainImage(banner);
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Log.d(TAG, "onUnbindViewHolder");
    }

    @Override
    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    }
}
