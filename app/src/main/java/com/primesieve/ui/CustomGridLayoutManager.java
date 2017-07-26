package com.primesieve.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

/**
 * This class disables native grid scrolling since we want the grid to auto populate and animate on initial launch
 * without any user interaction required in in our context of our application
 */

public class CustomGridLayoutManager extends GridLayoutManager {

    public CustomGridLayoutManager(@NonNull final Context context, final int spanCount) {
        super(context, spanCount);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }
}
