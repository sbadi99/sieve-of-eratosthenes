/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.primesieve.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.primesieve.data.SieveData;

import java.util.ArrayList;
import java.util.List;
import com.primesieve.R;

/**
 * SieveGridViewFragment that displays list of integers flagged using Sieve of Erotosthenes approach
 */
public class SieveGridViewFragment extends Fragment {

    private static final String TAG = SieveGridViewFragment.class.getName();
    private static final int SPAN_COUNT = 5;
    private static final int DATASET_COUNT = 60;

    private RecyclerView recyclerView;
    private List<SieveData.Primes> listWithPrimesFlagged = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        // Initialize data. Typically in a real world scenarios for large sets of data you would
        // do this on a background thread using Retrofit/Volley or AsyncTask e.t.c.
        initializeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        setRecyclerViewLayoutManager();
        SieveGridViewAdapter adapter = new SieveGridViewAdapter( listWithPrimesFlagged );

        // Set SieveGridViewAdapter as the adapter for RecyclerView.
        recyclerView.setAdapter( adapter );

        recyclerView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);

                        //Animate in preDraw listener here
                        animateOnLaunch();
                        return true;
                    }
                });
        return rootView;
    }

    /**
     * Animate the gridview as items appear in the grid on initial launch
     */
    private void animateOnLaunch() {

        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View v = recyclerView.getChildAt(i);
            v.setAlpha(0.0f);
            v.animate().alpha(1.0f)
                    .setDuration(400)
                    .setStartDelay(i * 80)
                    .start();
        }
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     */
    private void setRecyclerViewLayoutManager() {

        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        GridLayoutManager layoutManager = new CustomGridLayoutManager( getActivity(), SPAN_COUNT );

        recyclerView.setLayoutManager( layoutManager );
        recyclerView.scrollToPosition(scrollPosition);
    }

    /**
     * Fetch of list of integers with primes flagged using Sieve of Erotosthenes method
     */
    private void initializeData() {

        //if empty than fetch it from SieveData
        if(listWithPrimesFlagged.isEmpty()){
                listWithPrimesFlagged = SieveData.primeSieve(DATASET_COUNT);
        }

    }

}
