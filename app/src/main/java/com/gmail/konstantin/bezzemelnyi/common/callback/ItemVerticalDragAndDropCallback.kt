package com.gmail.konstantin.bezzemelnyi.common.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


abstract class ItemVerticalDragAndDropCallback :
    ItemTouchHelper.SimpleCallback(
        DRAG_DIRS,
        0
    ) {
    abstract override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    companion object {
        const val DRAG_DIRS =
            ItemTouchHelper.UP + ItemTouchHelper.DOWN
    }
}

