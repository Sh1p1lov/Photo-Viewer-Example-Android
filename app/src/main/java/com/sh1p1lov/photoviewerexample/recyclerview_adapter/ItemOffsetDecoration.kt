package com.sh1p1lov.photoviewerexample.recyclerview_adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(context: Context, @DimenRes itemOffsetDimen: Int) : RecyclerView.ItemDecoration() {

    private val mItemOffset: Int

    init {
        mItemOffset = context.resources.getDimensionPixelSize(itemOffsetDimen)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset)
    }
}