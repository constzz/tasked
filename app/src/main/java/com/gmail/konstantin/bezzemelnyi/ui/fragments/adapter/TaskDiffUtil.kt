package com.gmail.konstantin.bezzemelnyi.ui.fragments.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gmail.konstantin.bezzemelnyi.data.model.Task

class TaskDiffUtil(
    private val oldList: List<Task>,
    private val newList: List<Task>,
) : DiffUtil.Callback(
) {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.content == newItem.content &&
                oldItem.checked == newItem.checked &&
                oldItem.position == newItem.position
//        val (oldContent: String, oldChecked: Boolean, oldPosition: Int) = oldList[oldItemPosition,old ]
//        val (newContent: String,  newChecked: Boolean, newPosition) = newList[newItemPosition, newChecked, newPosition]
//        return oldContent == newContent
//                && oldChecked == newChecked
    }

}