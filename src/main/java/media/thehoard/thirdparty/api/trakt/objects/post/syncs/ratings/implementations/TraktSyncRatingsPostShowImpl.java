package media.thehoard.thirdparty.api.trakt.objects.post.syncs.ratings.implementations;

import com.google.gson.annotations.SerializedName;
import media.thehoard.thirdparty.api.trakt.objects.get.shows.implementations.TraktShowIdsImpl;

import java.time.Instant;
import java.util.List;

public class TraktSyncRatingsPostShowImpl {
	@SerializedName("rated_at")
	private Instant ratedAt;
	private Integer rating;
	private String title;
	private Integer year;
	private TraktShowIdsImpl ids;
	private List<TraktSyncRatingsPostShowSeasonImpl> seasons;
}
