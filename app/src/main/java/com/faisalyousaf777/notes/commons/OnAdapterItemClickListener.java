package com.faisalyousaf777.notes.commons;

import android.view.View;

public interface OnAdapterItemClickListener {
    void onItemClicked(View itemView, int position);
    void onItemLongClicked(View itemView, int position);
}
