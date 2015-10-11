package chipset.techtatva.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import chipset.techtatva.R;

public class ResultAdapter extends BaseAdapter {
    private class ViewHolder {
        TextView name;
        TextView category;
        TextView res;
    }

    private ViewHolder mViewHolder;
    ArrayList<HashMap<String, String>> mResultList = new ArrayList<HashMap<String, String>>();
    Context mContext;

    public ResultAdapter(Context context,
                         ArrayList<HashMap<String, String>> resultList) {
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
        HashMap<String, String> map = new HashMap<String, String>();
        final LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        map = mResultList.get(arg0);
        if (arg1 == null) {
            arg1 = mInflater.inflate(R.layout.res_item, null);
            mViewHolder = new ViewHolder();
            mViewHolder.name = (TextView) arg1.findViewById(R.id.resultName);
            mViewHolder.category = (TextView) arg1.findViewById(R.id.resultCategory);
            mViewHolder.res = (TextView) arg1.findViewById(R.id.resultRes);
            arg1.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) arg1.getTag();
        }
        mViewHolder.name.setText((String) map.get("eventName"));
        mViewHolder.category.setText((String) map.get("categoryName"));
        mViewHolder.res.setText((String) map.get("result"));
        return arg1;

    }
}