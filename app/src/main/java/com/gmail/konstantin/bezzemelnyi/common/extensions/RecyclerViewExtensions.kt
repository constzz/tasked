package com.gmail.konstantin.bezzemelnyi.common.extensions

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gmail.konstantin.bezzemelnyi.common.callback.ItemHorizontalSwipeCallback
import com.gmail.konstantin.bezzemelnyi.common.callback.ItemVerticalDragAndDropCallback
import java.util.*

fun RecyclerView.onDragAndDropMove(
    function: (
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) -> Unit
) {
    ItemTouchHelper(object : ItemVerticalDragAndDropCallback() {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            function(recyclerView, viewHolder, target)
            return true
        }
    }).attachToRecyclerView(this)
}

fun RecyclerView.onItemHorizontalSwipe(function: (holderAdapterPosition: Int) -> Unit) {
    ItemTouchHelper(object : ItemHorizontalSwipeCallback() {
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            function(viewHolder.adapterPosition)
        }

    }).attachToRecyclerView(this)
}

/**Enables drag and drop for a RecyclerView with list of LiveData
 * @param T is the type of elements in LiveData dataList
 **/
//fun <T> RecyclerView.enableDragAndDrop(owner: LifecycleOwner, dataList: LiveData<List<T>>) {
//    this.onDragAndDropMove { _, viewHolder, target ->
//        val fromPosition = viewHolder.adapterPosition
//        val toPosition = target.adapterPosition
//
//        dataList.observeOnce(owner) {
//            val oldItem = it[fromPosition] as Task
//            val newItem = it[toPosition] as Task

//            Collections.swap(it, fromPosition, toPosition)
//            when {
//                fromPosition < toPosition -> {
//                    for (i in fromPosition until toPosition) {
//                        oldItem.position = i.also {
//                            newItem.position = i + 1
//                        }
//                    }
//                }
//                else -> {
//                    for (i in fromPosition downTo toPosition + 1) {
//                        oldItem.position = i.also {
//                            newItem.position = i - 1
//                        }
//                    }
//                }
//            }
//
//        }
//
//        this.adapter?.notifyItemMoved(fromPosition, toPosition)
//    }
//}