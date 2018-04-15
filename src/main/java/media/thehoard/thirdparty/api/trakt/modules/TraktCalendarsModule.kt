package media.thehoard.thirdparty.api.trakt.modules

import media.thehoard.thirdparty.api.trakt.TraktClient
import media.thehoard.thirdparty.api.trakt.authentication.TraktAuthorization
import media.thehoard.thirdparty.api.trakt.objects.get.calendars.TraktCalendarMovie
import media.thehoard.thirdparty.api.trakt.objects.get.calendars.TraktCalendarShow
import media.thehoard.thirdparty.api.trakt.requests.calendars.*
import media.thehoard.thirdparty.api.trakt.requests.handler.RequestHandler
import media.thehoard.thirdparty.api.trakt.requests.parameters.TraktCalendarFilter
import media.thehoard.thirdparty.api.trakt.requests.parameters.TraktExtendedInfo
import media.thehoard.thirdparty.api.trakt.responses.TraktListResponse
import java.time.ZonedDateTime
import java.util.concurrent.CompletableFuture

class TraktCalendarsModule internal constructor(override val client: TraktClient) : TraktModule {
    fun getUserShowsAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarShow>> {
        return RequestHandler(client).executeListRequestAsync(CalendarUserShowsRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getUserNewShowsAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarShow>> {
        return RequestHandler(client).executeListRequestAsync(CalendarUserNewShowsRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getUserSeasonPremiereAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarShow>> {
        return RequestHandler(client).executeListRequestAsync(CalendarUserSeasonPremieresRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getUserMoviesAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarMovie>> {
        return RequestHandler(client).executeListRequestAsync(CalendarUserMoviesRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getUserDVDMoviesAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarMovie>> {
        return RequestHandler(client).executeListRequestAsync(CalendarUserDVDMoviesRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getAllShowsAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarShow>> {
        return RequestHandler(client).executeListRequestAsync(CalendarAllShowsRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getAllNewShowsAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarShow>> {
        return RequestHandler(client).executeListRequestAsync(CalendarAllNewShowsRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getAllSeasonPremieresAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarShow>> {
        return RequestHandler(client).executeListRequestAsync(CalendarAllShowsRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getAllMoviesAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarMovie>> {
        return RequestHandler(client).executeListRequestAsync(CalendarAllMoviesRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }

    fun getAllDVDMoviesAsync(
            startDate: ZonedDateTime? = null,
            days: Int? = null,
            extendedInfo: TraktExtendedInfo? = null,
            filter: TraktCalendarFilter? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktListResponse<TraktCalendarMovie>> {
        return RequestHandler(client).executeListRequestAsync(CalendarAllDVDMoviesRequest(startDate, days, extendedInfo, filter), requestAuthorization)
    }
}
