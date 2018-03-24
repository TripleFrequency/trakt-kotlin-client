package media.thehoard.thirdparty.api.trakt.objects.get.episodes.implementations

import com.google.gson.annotations.SerializedName
import media.thehoard.thirdparty.api.trakt.objects.get.episodes.TraktEpisodeCollectionProgress

import java.time.Instant

data class TraktEpisodeCollectionProgressImpl(
        override var number: Int? = null,
        override var completed: Boolean? = null,
        @SerializedName("collected_at")
        override var collectedAt: Instant? = null) : TraktEpisodeCollectionProgress