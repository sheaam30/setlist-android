package setlist.shea.setlist.list.mvp

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import android.widget.ViewSwitcher
import com.shea.mvp.activity.BaseActivity
import com.shea.mvp.view.BaseView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import setlist.shea.domain.model.SetList
import setlist.shea.domain.model.Song
import setlist.shea.setlist.R
import setlist.shea.setlist.list.adapter.RecyclerViewAdapter

/**
 * Created by Adam on 8/28/2017.
 */
open class SetView(activity: BaseActivity<*>?) : BaseView<SetListContract.Presenter>(activity), SetListContract.View {

    lateinit var recyclerView : RecyclerView
    lateinit var viewSwitcher : ViewSwitcher
    lateinit var adapter : RecyclerViewAdapter
    lateinit var fab : FloatingActionButton

    override fun onSetupViews(savedInstanceState: Bundle?) {
        recyclerView = bind(R.id.recyclerview)
        adapter = RecyclerViewAdapter(presenter?.getListActionListener())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewSwitcher = bind(R.id.view_switcher)
        fab = bind(R.id.fab)
        fab.setOnClickListener { _ -> presenter?.onAddListFabClicked() }
    }

    override fun showEmptyState() {
        viewSwitcher.displayedChild = 0
    }

    override fun showListState() {
        viewSwitcher.displayedChild = 1
    }

    override fun displaySongs(songs: List<Song>) {
        adapter.songs = songs
        adapter.notifyDataSetChanged()
    }

    override fun showAddListDialog() {
        val editText = EditText(context)

        dialogBuilder
                .setView(editText)
                .setTitle(context.getString(R.string.new_setlist_dialog_title))
                .setPositiveButton(context.getString(R.string.ok), (DialogInterface.OnClickListener
                    { _, _ -> presenter?.addSetList(SetList(editText.text.toString()))}))
                .setNegativeButton(context.getString(R.string.cancel), (DialogInterface.OnClickListener
                    { _, _ ->  }))
                .show()
    }

    override fun showSetList(setList: Flowable<List<Song>>) {
        setList
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list -> adapter.songs = list }
    }

    override fun showErrorState() {}
}