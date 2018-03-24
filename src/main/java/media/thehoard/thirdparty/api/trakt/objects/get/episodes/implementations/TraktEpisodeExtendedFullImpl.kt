package media.thehoard.thirdparty.api.trakt.objects.get.episodes.implementations

import com.google.gson.annotations.SerializedName
import media.thehoard.thirdparty.api.trakt.objects.get.episodes.TraktEpisode
import media.thehoard.thirdparty.api.trakt.objects.get.episodes.TraktEpisodeExtendedFull
import java.time.Instant

data class TraktEpisodeExtendedFullImpl(override var season: Int? = null,
                                        override var number: Int? = null,
                                        override var title: String = "",
                                        override var ids: TraktEpisodeIdsImpl = TraktEpisodeIdsImpl(),
                                        @SerializedName("number_abs")
                                        override var numberAbsolute: Int? = null,
                                        override var overview: String = "",
                                        @SerializedName("first_aired")
                                        override var firstAired: Instant? = null,
                                        @SerializedName("updated_at")
                                        override var updatedAt: Instant? = null,
                                        override var rating: Float? = null,
                                        override var votes: Int? = null,
                                        @SerializedName("comment_count")
                                        override var commentCount: Int? = null,
                                        @SerializedName("available_translations")
                                        override var availableTranslations: List<String> = listOf(),
                                        override var runtime: Int? = null) : TraktEpisodeExtendedFull {
    constructor(traktEpisode: TraktEpisode) : this(traktEpisode.season, traktEpisode.number, traktEpisode.title, traktEpisode.ids)
}