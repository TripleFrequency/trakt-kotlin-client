package media.thehoard.thirdparty.api.trakt.objects.post.syncs.ratings.implementations;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TraktSyncRatingsPostShowEpisodeImpl {
	@SerializedName("rated_at")
	private Date ratedAt;
	private Integer rating;
	private int number;

	public TraktSyncRatingsPostShowEpisodeImpl(int number) {
		this.number = number;
	}
}