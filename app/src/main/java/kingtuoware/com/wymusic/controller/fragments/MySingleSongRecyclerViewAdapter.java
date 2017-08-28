package kingtuoware.com.wymusic.controller.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import kingtuoware.com.wymusic.R;
import kingtuoware.com.wymusic.controller.fragments.dummy.DummyContent.DummyItem;
import kingtuoware.com.wymusic.model.beans.Mp3Info;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link SingleSongFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySingleSongRecyclerViewAdapter extends RecyclerView.Adapter<MySingleSongRecyclerViewAdapter.ViewHolder> {

    private final List<Mp3Info> mValues;
    private final SingleSongFragment.OnListFragmentInteractionListener mListener;

    public MySingleSongRecyclerViewAdapter(List<Mp3Info> items, SingleSongFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_singlesong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTvTitle.setText(mValues.get(position).getTitle());
        holder.mTvAuthor.setText(mValues.get(position).getArtist()+"-"+mValues.get(position).getAlbum());
        if (mValues.get(position).isPlaying()){
            holder.mIvSound.setVisibility(View.VISIBLE);
        }else{
            holder.mIvSound.setVisibility(View.GONE);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIvSound;
        public final FrameLayout mFlAction;
        public final TextView mTvTitle;
        public final TextView mTvAuthor;
        public Mp3Info mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTvTitle = (TextView) view.findViewById(R.id.tv_title);
            mTvAuthor = (TextView) view.findViewById(R.id.tv_author);
            mIvSound = (ImageView) view.findViewById(R.id.iv_sound);
            mFlAction = (FrameLayout) view.findViewById(R.id.fl_action);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTvTitle.getText() + "'"+mTvAuthor.getText()+"'";
        }
    }
}
