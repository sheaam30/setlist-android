package setlist.shea.setlist.song_list.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper



/**
 * Created by adamshea on 1/14/18.
 */
class MyItemTouchHelperCallback(private var callbackItemTouch: CallbackItemTouch) : ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean = false
    override fun isItemViewSwipeEnabled(): Boolean = false

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN // movements drag
        return ItemTouchHelper.Callback.makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, dragFlags) // as parameter, action drag and flags drag
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if (viewHolder.itemViewType != target.itemViewType) {
            return false
        }

        callbackItemTouch.itemTouchOnMove(viewHolder.adapterPosition, target.adapterPosition) // information to the interface
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // swiped disabled
    }
}