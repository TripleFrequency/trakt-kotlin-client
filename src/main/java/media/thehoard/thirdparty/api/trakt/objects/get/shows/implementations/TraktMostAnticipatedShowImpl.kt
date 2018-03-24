package media.thehoard.thirdparty.api.trakt.objects.get.shows.implementations

import com.google.gson.annotations.SerializedName
import media.thehoard.thirdparty.api.trakt.objects.get.shows.TraktMostAnticipiatedShow
import media.thehoard.thirdparty.api.trakt.objects.get.shows.TraktShow

data class TraktMostAnticipatedShowImpl(@SerializedName("list_count")
                                        override var listCount: Int? = null,
                                        override var show: TraktShowImpl = TraktShowImpl()) : TraktMostAnticipiatedShow {
    override var title: String
        get() = show.title
        set(title) {
            show.title = title
        }
    override var year: Int?
        get() = show.year
        set(year) {
            show.year = year
        }
    override var ids: TraktShowIdsImpl
        get() = show.ids
        set(ids) {
            show.ids = ids
        }
}