package chipset.techtatva.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import chipset.techtatva.R;

public class RobowarsResultAdapter extends BaseAdapter {
    private class ViewHolder {
        TextView name;
        TextView college;
        TextView rank;
        TextView points;
        TextView wonLost;
        TextView weight;
        TextView dimension;
        LinearLayout detailLayout;
        CardView roboResCard;
    }

    private ViewHolder mViewHolder;
    ArrayList<HashMap<String, String>> mResultList = new ArrayList<>();
    Context mContext;

    public RobowarsResultAdapter(Context context, ArrayList<HashMap<String, String>> resultList) {
        mContext = context;
        mResultList = resultList;
    }

    @Override
    public int getCount() {
        return mResultList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mResultList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        HashMap<String, String> map;
        final LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        map = mResultList.get(arg0);
        if (arg1 == null) {
            arg1 = mInflater.inflate(R.layout.robo_res_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.name = (TextView) arg1.findViewById(R.id.teamName);
            mViewHolder.rank = (TextView) arg1.findViewById(R.id.roboRank);
            mViewHolder.college = (TextView) arg1.findViewById(R.id.collegeName);
            mViewHolder.points = (TextView) arg1.findViewById(R.id.roboPoints);
            mViewHolder.dimension = (TextView) arg1.findViewById(R.id.roboDim);
            mViewHolder.weight = (TextView) arg1.findViewById(R.id.roboWeight);
            mViewHolder.wonLost = (TextView) arg1.findViewById(R.id.roboWonLost);
            mViewHolder.detailLayout = (LinearLayout) arg1.findViewById(R.id.detailsLayout);
            mViewHolder.roboResCard = (CardView) arg1.findViewById(R.id.roboCard);
            arg1.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) arg1.getTag();
        }
        mViewHolder.name.setText("Team name: " + map.get("name"));
        mViewHolder.college.setText("College name: " + map.get("college"));
        if (!map.get("name").equalsIgnoreCase("sorry")) {
            mViewHolder.rank.setText("Rank: " + map.get("rank"));
            mViewHolder.wonLost.setText("Won: " + map.get("won") + " Lost: " + map.get("lost"));
            mViewHolder.weight.setText("Weight: " + map.get("weight"));
            mViewHolder.dimension.setText("Dimensions: " + map.get("dimension"));
            mViewHolder.points.setText("Points: " + map.get("points"));
            String color = map.get("status");
            if (color.equalsIgnoreCase("green"))
                mViewHolder.roboResCard.setBackgroundColor(mContext.getResources().getColor(R.color.green));
            else
                mViewHolder.roboResCard.setBackgroundColor(mContext.getResources().getColor(R.color.red));
        }
        return arg1;

    }
}