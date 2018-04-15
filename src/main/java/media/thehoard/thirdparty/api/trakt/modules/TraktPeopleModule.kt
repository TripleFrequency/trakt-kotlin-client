package media.thehoard.thirdparty.api.trakt.modules

import media.thehoard.thirdparty.api.trakt.TraktClient
import media.thehoard.thirdparty.api.trakt.authentication.TraktAuthorization
import media.thehoard.thirdparty.api.trakt.objects.get.people.TraktPerson
import media.thehoard.thirdparty.api.trakt.objects.get.people.credits.TraktPersonMovieCredits
import media.thehoard.thirdparty.api.trakt.objects.get.people.credits.TraktPersonShowCredits
import media.thehoard.thirdparty.api.trakt.requests.handler.RequestHandler
import media.thehoard.thirdparty.api.trakt.requests.parameters.TraktExtendedInfo
import media.thehoard.thirdparty.api.trakt.requests.people.PersonMovieCreditsRequest
import media.thehoard.thirdparty.api.trakt.requests.people.PersonShowCreditsRequest
import media.thehoard.thirdparty.api.trakt.requests.people.PersonSummaryRequest
import media.thehoard.thirdparty.api.trakt.responses.TraktResponse
import java.util.concurrent.CompletableFuture

class TraktPeopleModule internal constructor(override val client: TraktClient) : TraktModule {
    fun getPersonAsync(
            personIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktPerson>> {
        return RequestHandler(client).executeSingleItemRequestAsync(PersonSummaryRequest(personIdOrSlug, extendedInfo), requestAuthorization)
    }

    fun getMultiplePersonsAsync(
            personsQueryParams: TraktMultipleObjectsQueryParams,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<List<TraktResponse<TraktPerson>>> {
        if (personsQueryParams.isEmpty())
            return CompletableFuture.completedFuture(listOf())

        var i = 0
        val tasks = Array(personsQueryParams.size, {
            val queryParam = personsQueryParams[i++]
            return@Array getPersonAsync(queryParam.idOrSlug, queryParam.extendedInfo, requestAuthorization)
        })

        return CompletableFuture.supplyAsync {
            i = 0
            List(tasks.size, { tasks[i++].get() })
        }
    }

    fun getPersonMovieCreditsAsync(
            personIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktPersonMovieCredits>> {
        return RequestHandler(client).executeSingleItemRequestAsync(PersonMovieCreditsRequest(personIdOrSlug, extendedInfo), requestAuthorization)
    }

    fun getPersonShowCreditsAsync(
            personIdOrSlug: String,
            extendedInfo: TraktExtendedInfo? = null,
            requestAuthorization: TraktAuthorization = client.authorization
    ): CompletableFuture<TraktResponse<TraktPersonShowCredits>> {
        return RequestHandler(client).executeSingleItemRequestAsync(PersonShowCreditsRequest(personIdOrSlug, extendedInfo), requestAuthorization)
    }
}
