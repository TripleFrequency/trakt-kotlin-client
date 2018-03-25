package media.thehoard.thirdparty.api.trakt.objects.post.syncs.responses.implementations

import media.thehoard.thirdparty.api.trakt.objects.post.syncs.responses.TraktSyncPostResponseGroup

data class TraktSyncPostResponseGroupImpl(
        override var movies: Int? = null,
        override var shows: Int? = null,
        override var seasons: Int? = null,
        override var episodes: Int? = null
) : TraktSyncPostResponseGroup
