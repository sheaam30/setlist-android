package setlist.shea.setlist.list.adapter

/**
 * Created by Adam on 10/4/2017.
 */
interface ISongViewHolder<in D> {
    fun bind(data: D)
}