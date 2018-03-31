package media.thehoard.thirdparty.api.trakt.objects.post.scrobbles.implementations

import com.google.gson.annotations.SerializedName
import media.thehoard.thirdparty.api.trakt.objects.get.movies.implementations.TraktMovieImpl
import media.thehoard.thirdparty.api.trakt.objects.post.scrobbles.TraktMovieScrobblePost

data class TraktMovieScrobblePostImpl(
        override var progress: Float = 0f,
        @SerializedName("app_version")
        override var appVersion: String = "",
        @SerializedName("app_date")
        override var appDate: String = "",
        override var movie: TraktMovieImpl = TraktMovieImpl()
) : TraktMovieScrobblePost