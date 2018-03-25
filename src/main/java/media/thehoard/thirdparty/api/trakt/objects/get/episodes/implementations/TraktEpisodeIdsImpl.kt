package media.thehoard.thirdparty.api.trakt.objects.get.episodes.implementations

import media.thehoard.thirdparty.api.trakt.core.TraktDefaultIds
import media.thehoard.thirdparty.api.trakt.objects.get.episodes.TraktEpisodeIds
import media.thehoard.thirdparty.api.trakt.utils.TraktUtils

data class TraktEpisodeIdsImpl(
        override var trakt: Int = 0,
        override var tvdb: Int? = null,
        override var imdb: String = "",
        override var tmdb: Int? = null
) : TraktEpisodeIds {

    override fun hasAnyId(): Boolean {
        return getBestId().isNotEmpty()
    }

    override fun getBestId(): String {
        return when {
            isValid(trakt) -> trakt.toString()
            isValid(tvdb) -> tvdb.toString()
            isValid(imdb) -> imdb
            isValid(tmdb) -> tmdb.toString()
            else -> ""
        }
    }

    override fun hasIdMatch(ids: TraktDefaultIds): Boolean {
        return if (ids is TraktEpisodeIdsImpl) {
            val (trakt1, tvdb1, imdb1, tmdb1) = ids
            return ((isValid(trakt) && isValid(trakt1) && trakt == trakt1) ||
                    (isValid(tvdb) && isValid(tvdb1) && tvdb == tvdb1) ||
                    (isValid(imdb) && isValid(imdb1) && imdb == imdb1) ||
                    (isValid(tmdb) && isValid(tmdb1) && tmdb == tmdb1))
        } else
            false
    }
}
