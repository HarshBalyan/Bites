package harsh.volleytest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.test.ActivityUnitTestCase;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by harsh on 10/14/2015.
 */
public class CustomListAdapter extends BaseAdapter {
    Activity activity;
    List<Restaurant> restaurantList;
    LayoutInflater inflater;
    ImageLoader imageLoader;
    RequestQueue requestQueue;

    public CustomListAdapter(Activity activity,List<Restaurant> restaurantList,RequestQueue requestQueue) {
        this.activity = activity;
        this.restaurantList = restaurantList;
        this.requestQueue = requestQueue;
        imageLoader = new ImageLoader(requestQueue, new LruBitmapCache());
    }
    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.list_item, null);
        }
        if(imageLoader==null)
        {
            imageLoader=new ImageLoader(requestQueue, new LruBitmapCache());
        }
        Restaurant restaurant=restaurantList.get(position);
        NetworkImageView networkImageView=(NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView textView=(TextView) convertView.findViewById(R.id.textView);

        networkImageView.setImageUrl(restaurant.getImgUrl(),imageLoader);
        textView.setText(restaurant.getRest_Name());
        textView.setTextSize(22F);
        textView.setTypeface(Typeface.createFromAsset(activity.getAssets(),"KFC.ttf"));
        return convertView;
    }
}
