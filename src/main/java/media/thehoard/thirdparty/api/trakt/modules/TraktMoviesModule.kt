package media.thehoard.thirdparty.api.trakt.modules

import media.thehoard.thirdparty.api.trakt.TraktClient
import media.thehoard.thirdparty.api.trakt.authentication.TraktAuthorization
import media.thehoard.thirdparty.api.trakt.enums.TraktCommentSortOrder
import media.thehoard.thirdparty.api.trakt.enums.TraktListSortOrder
import media.thehoard.thirdparty.api.trakt.enums.TraktListType
import media.thehoard.thirdparty.api.trakt.enums.TraktTimePeriod
import media.thehoard.thirdparty.api.trakt.objects.basic.TraktCastAndCrew
import media.thehoard.thirdparty.api.trakt.objects.basic.TraktComment
import media.thehoard.thirdparty.api.trakt.objects.basic.TraktRating
import media.thehoard.thirdparty.api.trakt.objects.basic.TraktStatistics
import media.thehoard.thirdparty.api.trakt.objects.get.movies.*
import media.thehoard.thirdparty.api.trakt.objects.get.users.TraktUser
import media.thehoard.thirdparty.api.trakt.objects.get.users.lists.TraktList
import media.thehoard.thirdparty.api.trakt.requests.handler.RequestHandler
import media.thehoard.thirdparty.api.trakt.requests.movies.*
import media.thehoard.thirdparty.api.trakt.requests.parameters.TraktExtendedInfo
import media.thehoard.thirdparty.api.trakt.requests.parameters.TraktMovieFilter
import media.thehoard.thirdparty.api.trakt.requests.parameters.TraktPagedParameters
import media.thehoard.thirdparty.api.trakt.responses.TraktListResponse
import media.thehoard.thirdparty.api.trakt.responses.TraktPagedResponse
import media.thehoard.thirdparty.api.trakt.responses.TraktResponse
import java.time.ZonedDateTime
import java.util.concurrent.CompletableFuture

class TraktMoviesModule internal constructor(override val client: TraktClient) : TraktModule {
    fun getMovieAsync(
            movieIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktMovie>> {
        return RequestHandler(client).executeSingleItemRequestAsync(MovieSummaryRequest(movieIdOrSlug, extendedInfo), requestAuthorization)
    }

    fun getMultipleMoviesAsync(
            moviesQueryParams: TraktMultipleObjectsQueryParams,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<List<TraktResponse<TraktMovie>>> {
        if (moviesQueryParams.isEmpty())
            return CompletableFuture.completedFuture(listOf())

        var i = 0
        val tasks = Array(moviesQueryParams.size, {
            val queryParam = moviesQueryParams[i++]
            return@Array getMovieAsync(queryParam.idOrSlug, queryParam.extendedInfo, requestAuthorization)
        })

        return CompletableFuture.supplyAsync {
            i = 0
            List(tasks.size, { tasks[i++].get() })
        }
    }

    fun getMovieAliasesAsync(
            movieIdOrSlug: String,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktMovieAlias>> {
        return RequestHandler(client).executeListRequestAsync(MovieAliasesRequest(movieIdOrSlug), requestAuthorization)
    }

    fun getMovieReleasesAsync(
            movieIdOrSlug: String,
            countryCode: String? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktMovieRelease>> {
        return RequestHandler(client).executeListRequestAsync(MovieReleasesRequest(movieIdOrSlug, countryCode), requestAuthorization)
    }

    fun getMovieTranslationsAsync(
            movieIdOrSlug: String,
            languageCode: String? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktMovieTranslation>> {
        return RequestHandler(client).executeListRequestAsync(MovieTranslationsRequest(movieIdOrSlug, languageCode), requestAuthorization)
    }

    fun getMovieCommentsAsync(
            movieIdOrSlug: String,
            commentSortOrder: TraktCommentSortOrder? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktComment>> {
        return RequestHandler(client).executePagedRequestAsync(MovieCommentsRequest(movieIdOrSlug, commentSortOrder, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMovieListsAsync(
            movieIdOrSlug: String,
            listType: TraktListType? = null,
            listSortOrder: TraktListSortOrder? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktList>> {
        return RequestHandler(client).executePagedRequestAsync(MovieListsRequest(movieIdOrSlug, listType, listSortOrder, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMoviePeopleAsync(
            movieIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktCastAndCrew>> {
        return RequestHandler(client).executeSingleItemRequestAsync(MoviePeopleRequest(movieIdOrSlug, extendedInfo), requestAuthorization)
    }

    fun getMovieRatingsAsync(
            movieIdOrSlug: String,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktRating>> {
        return RequestHandler(client).executeSingleItemRequestAsync(MovieRatingsRequest(movieIdOrSlug), requestAuthorization)
    }

    fun getMovieRelatedMoviesAsync(
            movieIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MovieRelatedMoviesRequest(movieIdOrSlug, extendedInfo, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMovieStatisticsAsync(
            movieIdOrSlug: String,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktStatistics>> {
        return RequestHandler(client).executeSingleItemRequestAsync(MovieStatisticsRequest(movieIdOrSlug), requestAuthorization)
    }

    fun getMovieWatchingUsersAsync(
            movieIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktUser>> {
        return RequestHandler(client).executeListRequestAsync(MovieWatchingUsersRequest(movieIdOrSlug, extendedInfo), requestAuthorization)
    }

    fun getTrendingMoviesAsync(
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktMovieFilter? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktTrendingMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesTrendingRequest(extendedInfo, filter, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getPopularMoviesAsync(
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktMovieFilter? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesPopularRequest(extendedInfo, filter, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMostPlayedMoviesAsync(
            period: TraktTimePeriod? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktMovieFilter? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktMostPWCMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesMostPlayedRequest(period, extendedInfo, filter, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMostWatchedMoviesAsync(
            period: TraktTimePeriod? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktMovieFilter? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktMostPWCMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesMostWatchedRequest(period, extendedInfo, filter, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMostCollectedMoviesAsync(
            period: TraktTimePeriod? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktMovieFilter? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktMostPWCMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesMostCollectedRequest(period, extendedInfo, filter, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getMostAnticipatedMoviesAsync(
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktMovieFilter? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktMostAnticipatedMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesMostAnticipatedRequest(extendedInfo, filter, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }

    fun getBoxOfficeMoviesAsync(
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktBoxOfficeMovie>> {
        return RequestHandler(client).executeListRequestAsync(MoviesBoxOfficeRequest(extendedInfo), requestAuthorization)
    }

    fun getRecentlyUpdatedMoviesAsync(
            startDate: ZonedDateTime? = null,
            extendedInfo: TraktExtendedInfo? = null,
            pagedParameters: TraktPagedParameters? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktPagedResponse<TraktRecentlyUpdatedMovie>> {
        return RequestHandler(client).executePagedRequestAsync(MoviesRecentlyUpdatedRequest(startDate, extendedInfo, pagedParameters?.page, pagedParameters?.limit), requestAuthorization)
    }
}
