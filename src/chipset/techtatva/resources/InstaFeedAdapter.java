package chipset.techtatva.resources;

import static chipset.techtatva.resources.Constants.INSTA_CAPTION_TEXT;
import static chipset.techtatva.resources.Constants.INSTA_COMMENTS;
import static chipset.techtatva.resources.Constants.INSTA_IMAGE_URL;
import static chipset.techtatva.resources.Constants.INSTA_LIKES;
import static chipset.techtatva.resources.Constants.INSTA_USER_PROFILE;
import static chipset.techtatva.resources.Constants.INSTA_USER_USERNAME;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import chipset.techtatva.R;

import com.squareup.picasso.Picasso;

@SuppressLint("InflateParams")
public class InstaFeedAdapter extends BaseAdapter {

	ArrayList<HashMap<String, String>> Data = new ArrayList<HashMap<String, String>>();
	Context Cont;
	LayoutInflater inflater;

	public InstaFeedAdapter(Context context,
			ArrayList<HashMap<String, String>> data) {
		Data = data;
		Cont = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return Data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return Data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.insta_feed_item, null);

		}
		ImageView instaImage = (ImageView) arg1.findViewById(R.id.instaImage);
		ImageView instaUserPic = (ImageView) arg1
				.findViewById(R.id.instaUserImage);
		TextView instaUsername = (TextView) arg1
				.findViewById(R.id.instaUsername);
		TextView instaLikes = (TextView) arg1.findViewById(R.id.instaLikes);
		TextView instaComments = (TextView) arg1
				.findViewById(R.id.instaComments);
		TextView instaText = (TextView) arg1.findViewById(R.id.instaText);

		String image, like, comment, text, username, profile_picture;

		image = (String) Data.get(arg0).get(INSTA_IMAGE_URL);
		like = (String) Data.get(arg0).get(INSTA_LIKES);
		comment = (String) Data.get(arg0).get(INSTA_COMMENTS);
		text = (String) Data.get(arg0).get(INSTA_CAPTION_TEXT);
		username = (String) Data.get(arg0).get(INSTA_USER_USERNAME);
		profile_picture = (String) Data.get(arg0).get(INSTA_USER_PROFILE);

		Picasso.with(Cont).load(image).placeholder(R.drawable.ic_launcher)
				.into(instaImage);
		Picasso.with(Cont).load(profile_picture)
				.placeholder(R.drawable.ic_launcher).into(instaUserPic);

		instaText.setText(text);
		instaUsername.setText("@" + username);
		instaLikes.setText(like);
		instaComments.setText(comment);
		return arg1;
	}
}	