package media.thehoard.thirdparty.api.trakt.objects.basic.implementations

import com.google.gson.annotations.SerializedName
import media.thehoard.thirdparty.api.trakt.objects.basic.TraktComment
import media.thehoard.thirdparty.api.trakt.objects.get.users.TraktUser
import media.thehoard.thirdparty.api.trakt.objects.get.users.implementations.TraktUserImpl
import java.time.ZonedDateTime

data class TraktCommentImpl(
        override var id: Int = -1,
        override var parentId: Int? = null,
        @SerializedName("created_at")
        override var createdAt: ZonedDateTime = ZonedDateTime.now(),
        @SerializedName("updated_at")
        override var updatedAt: ZonedDateTime? = null,
        override var comment: String = "",
        override var isSpoiler: Boolean = false,
        override var isReview: Boolean = false,
        override var replies: Int? = null,
        override var likes: Int? = null,
        @SerializedName("user_rating")
        override var userRating: Float? = null,
        override var user: TraktUser = TraktUserImpl()
) : TraktComment
