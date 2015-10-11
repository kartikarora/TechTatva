package chipset.techtatva.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import chipset.techtatva.R;

/**
 * Created by saketh on 18/9/15.
 */
public class EventViewHolder extends RecyclerView.ViewHolder {
    CardView eventCard;
    TextView textName;
    TextView textLocation;
    TextView textTime;
    TextView textDate;
    TextView textMaxSize;
    TextView textContact;
    TextView textFav;
    ImageView eventInfoImageView;
    RelativeLayout descriptionLayout;

    public EventViewHolder(View itemView) {
        super(itemView);
        eventCard = (CardView) itemView;
        textName = (TextView) itemView.findViewById(R.id.eventName);
        textLocation = (TextView) itemView.findViewById(R.id.eventLocation);
        textTime = (TextView) itemView.findViewById(R.id.eventTime);
        textDate = (TextView) itemView.findViewById(R.id.eventDate);
        textMaxSize = (TextView) itemView.findViewById(R.id.maxSize);
        textContact = (TextView) itemView.findViewById(R.id.eventContact);
        textFav = (TextView) itemView.findViewById(R.id.favorite);
        eventInfoImageView = (ImageView) itemView.findViewById(R.id.eventInfo);
        descriptionLayout = (RelativeLayout) itemView.findViewById(R.id.description);
    }
}
