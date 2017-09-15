package setlist.shea.setlist.list

import android.content.SharedPreferences
import com.shea.mvp.interactor.BaseInteractor
import io.reactivex.Flowable
import setlist.shea.domain.csv.Parser
import setlist.shea.domain.csv.Writer
import setlist.shea.domain.db.SetListDao
import setlist.shea.domain.db.SongDao
import setlist.shea.domain.model.Song
import javax.inject.Inject

/**
 * Created by Adam on 8/28/2017.
 */
class SetListInteractor @Inject constructor(songDao: SongDao, setListDao: SetListDao, parser: Parser, writer: Writer, sharedPreferences: SharedPreferences): BaseInteractor() {

    private val CURRENT_SET_LIST = "currentSetList"

    private val songDao : SongDao = songDao
    private val setListDao : SetListDao = setListDao
    private val parser : Parser = parser
    private val writer : Writer = writer
    private val sharedPrefs : SharedPreferences = sharedPreferences

    fun getCurrentSetList() : String? {
        return sharedPrefs.getString(CURRENT_SET_LIST, null)
    }

    fun getSetList(currentSetList: String) : Flowable<List<Song>> {
        return songDao.getSetList(currentSetList)
    }
}