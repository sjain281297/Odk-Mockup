package com.example.shubham.odk_mockup;

import android.view.View;

/**
 * Created by shubham on 22/3/17.
 */

public interface RecyclerClick_listener {

    /**
     * Interface for Recycler View Click listener
     **/

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}