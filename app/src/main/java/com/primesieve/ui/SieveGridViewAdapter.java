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

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import com.primesieve.R;
import com.primesieve.data.SieveData;

/**
 * SieveGridViewAdapter for the Sieve GridView using the RecylerView Adapter
 */
public class SieveGridViewAdapter extends RecyclerView.Adapter<SieveGridViewAdapter.ViewHolder> {

    private static final String TAG = SieveGridViewAdapter.class.getName();
    private final List<SieveData.Primes> numberList;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView integers;

        public ViewHolder(View v) {
            super(v);
            integers = (TextView) v.findViewById(R.id.numbers);
        }

        public TextView getIntegers() {
            return integers;
        }
    }


    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the numberList to populate views to be used by RecyclerView.
     */
    public SieveGridViewAdapter(List<SieveData.Primes> dataSet) {
        numberList = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element: " + position);

        // Get element from your dataset at this position
        // and replace the contents of the view with that element
        viewHolder.getIntegers().setText(numberList.get(position).getNumber() + "");

        if(numberList.get(position).isPrime()) {
            //provide background drawable for primes
            viewHolder.getIntegers().setBackgroundResource(R.drawable.selection_circle);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return numberList.size();
    }

}
