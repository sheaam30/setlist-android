package setlist.shea.setlist.list.adapter

/**
 * Created by adamshea on 10/6/17.
 */
sealed class SongItemActions {
    class AddSongAction : SongItemActions()
    class RemoveSongAction : SongItemActions()
}