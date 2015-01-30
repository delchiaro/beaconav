package micc.beaconav.gui.customList;

import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import micc.beaconav.R;

import java.util.List;

/**
 * Created by Mr_Holmes on 21/01/15.
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    List<ArtListItem> list; //lista di oggetti base della lista (sono questi che si possono modificare a piacimento)

    private class ViewHolder
    {
        ImageView _navButton;
        TextView _artPieceName;
    }

    public ListAdapter(Context context, List<ArtListItem> list) {
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.art_list_item, null);
            holder = new ViewHolder();
            holder._artPieceName = (TextView) convertView.findViewById(R.id.artPieceName);
            holder._navButton = (ImageView) convertView.findViewById(R.id.navButton);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        ArtListItem artListItem = (ArtListItem) getItem(position);

        holder._navButton.setOnClickListener(mOnButtonClickListener);
        holder._artPieceName.setOnClickListener(mOnTextClickListener);
        holder._artPieceName.setText(artListItem.getDescription());
        holder._navButton.setImageResource(artListItem.getImageId());

        return convertView;

    }

    private View.OnClickListener mOnTextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View txt) {

        }
    };

    private View.OnClickListener mOnButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View image) {

        }
    };

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

}