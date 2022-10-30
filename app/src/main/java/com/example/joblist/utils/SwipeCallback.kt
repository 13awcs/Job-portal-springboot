package com.example.joblist.utils

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.joblist.R


abstract class SwipeCallback(private val context: Context) :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
    ) {

    /**
     * To disable "swipe" for specific item return 0 here.
     *
     * E.g:
     * - if (viewHolder?.itemViewType == YourAdapter.SOME_TYPE) return 0
     * - if (viewHolder?.adapterPosition == 0) return 0
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int = if (viewHolder.adapterPosition == 10) 0 else super.getMovementFlags(
        recyclerView,
        viewHolder
    )

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        when (actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG -> {
                // todo nothing
            }
            ItemTouchHelper.ACTION_STATE_SWIPE -> {
                val itemView = viewHolder.itemView
                val clearPaint = Paint()
                val background = ColorDrawable()
                var icon: Drawable? = null
                var top = 0
                var bottom = 0
                var left = 0
                var right = 0

                when {
                    dX > 0 && dY == 0f -> { // todo swipe to right
                        icon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_edit_24)!!
                        icon.setTint(Color.WHITE)
                        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

                        if (dX == 0f && !isCurrentlyActive) {
                            return c.drawRect(
                                itemView.left + dX,
                                itemView.top.toFloat(),
                                itemView.left.toFloat(),
                                itemView.bottom.toFloat(),
                                clearPaint
                            )
                        }

                        // todo draw the green background
                        background.color = Color.GREEN
                        background.setBounds(
                            itemView.left + dX.toInt(),
                            itemView.top,
                            itemView.left,
                            itemView.bottom
                        )

                        // todo calculate position of edit icon and draw the edit icon
                        val intrinsicWidth = icon.intrinsicWidth
                        val intrinsicHeight = icon.intrinsicHeight
                        val height = itemView.bottom - itemView.top
                        val margin = (height - intrinsicHeight) / 2

                        top = itemView.top + (height - intrinsicHeight) / 2
                        bottom = top + intrinsicHeight
                        left = itemView.left + margin - intrinsicWidth
                        right = itemView.left + margin
                    }
                    dX < 0 && dY == 0f -> { // todo swipe to left
                        icon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_delete_24)!!
                        icon.setTint(Color.WHITE)
                        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

                        if (dX == 0f && !isCurrentlyActive) {
                            return c.drawRect(
                                itemView.right + dX,
                                itemView.top.toFloat(),
                                itemView.right.toFloat(),
                                itemView.bottom.toFloat(),
                                clearPaint
                            )
                        }

                        // todo draw the red background
                        background.color = Color.RED
                        background.setBounds(
                            itemView.right + dX.toInt(),
                            itemView.top,
                            itemView.right,
                            itemView.bottom
                        )

                        // todo calculate position of delete icon and draw the delete icon
                        val intrinsicWidth = icon.intrinsicWidth
                        val intrinsicHeight = icon.intrinsicHeight
                        val height = itemView.bottom - itemView.top
                        val margin = (height - intrinsicHeight) / 2

                        top = itemView.top + (height - intrinsicHeight) / 2
                        bottom = top + intrinsicHeight
                        left = itemView.right - margin - intrinsicWidth
                        right = itemView.right - margin
                    }
                }

                background.draw(c)
                icon?.setBounds(left, top, right, bottom)
                icon?.draw(c)
            }
            else -> {
                c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            }
        }
    }
}

