package chipset.techtatva.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import chipset.techtatva.R;
import chipset.techtatva.model.events.DrawerItem;

/**
 * Created by Anuraag on 23-Aug-15.
 */

public class DrawerAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<DrawerItem> mDrawerItems;

    public DrawerAdapter(Context context, ArrayList<DrawerItem> drawerItems) {
        mContext = context;
        mDrawerItems = drawerItems;
    }

    @Override
    public int getCount() {
        return mDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        } else {
            view = convertView;
        }

        TextView categoryNameTextView = (TextView) view.findViewById(R.id.category_name_text_view);
        ImageView categoryIconImageView = (ImageView) view.findViewById(R.id.category_icon_image_view);

        categoryNameTextView.setText(mDrawerItems.get(position).categoryName);
        categoryIconImageView.setImageResource(mDrawerItems.get(position).categoryIcon);

        return view;
    }
}