package media.thehoard.thirdparty.api.trakt.modules

import media.thehoard.thirdparty.api.trakt.TraktClient
import media.thehoard.thirdparty.api.trakt.authentication.TraktAuthorization
import media.thehoard.thirdparty.api.trakt.enums.TraktGenreType
import media.thehoard.thirdparty.api.trakt.objects.basic.TraktGenre
import media.thehoard.thirdparty.api.trakt.requests.genres.GenresMoviesRequest
import media.thehoard.thirdparty.api.trakt.requests.genres.GenresShowsRequest
import media.thehoard.thirdparty.api.trakt.requests.handler.RequestHandler
import media.thehoard.thirdparty.api.trakt.responses.TraktListResponse
import java.util.concurrent.CompletableFuture

class TraktGenresModule(override val client: TraktClient) : TraktModule {
    fun getMovieGenresAsync(requestAuthorization: TraktAuthorization = client.authorization): CompletableFuture<TraktListResponse<TraktGenre>> {
        return RequestHandler(client).executeListRequestAsync(GenresMoviesRequest(), requestAuthorization).thenApply {
            if (it.hasValue && it.value != null)
                it.value!!.forEach { genre -> genre.type = TraktGenreType.MOVIES }
            return@thenApply it
        }
    }

    fun getShowGenresAsync(requestAuthorization: TraktAuthorization = client.authorization): CompletableFuture<TraktListResponse<TraktGenre>> {
        return RequestHandler(client).executeListRequestAsync(GenresShowsRequest(), requestAuthorization).thenApply {
            if (it.hasValue && it.value != null)
                it.value!!.forEach { genre -> genre.type = TraktGenreType.SHOWS }
            return@thenApply it
        }
    }
}
