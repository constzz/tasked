package com.gmail.konstantin.bezzemelnyi.common.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class ItemHorizontalSwipeCallback : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.START + ItemTouchHelper.END
) {
    final override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    abstract override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
}