package uk.co.ribot.easyadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class EasyRecyclerAdapter<T> extends RecyclerView.Adapter<EasyRecyclerAdapter.RecyclerViewHolder> {

    private List<T> mListItems;
    private Class mItemViewHolderClass;
    private LayoutInflater mInflater;
    private Integer mItemLayoutId;

    /**
     * Constructs and EasyAdapter with a Context, an {@link ItemViewHolder} class, and list of items.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     * @param listItems           the list of items to load in the Adapter
     */
    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems) {
        setItems(listItems);
        init(context, itemViewHolderClass);
    }

    /**
     * Constructs and EasyAdapter with a Context and an {@link ItemViewHolder} class.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     */
    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        mListItems = new ArrayList<T>();
        init(context, itemViewHolderClass);
    }

    private void init(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        mItemViewHolderClass = itemViewHolderClass;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemLayoutId = EasyAdapterUtil.parseItemLayoutId(itemViewHolderClass);
    }

    /**
     * Sets a new list of items into the Adapter
     *
     * @param listItems new list of items
     */
    public void setItems(List<T> listItems) {
        mListItems = listItems;
        notifyDataSetChanged();
    }

    /**
     * Adds a single item to the Adapter
     *
     * @param item item to add
     */
    public void addItem(T item) {
        mListItems.add(item);
        notifyItemChanged(mListItems.indexOf(item));
    }

    /**
     * Appends a list of items to the ones already in the Adapter
     *
     * @param listItems list of items to append
     */
    public void addItems(List<T> listItems) {
        mListItems.addAll(listItems);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = mInflater.inflate(mItemLayoutId, parent, false);
        ItemViewHolder<T> itemViewHolder = EasyAdapterUtil.createViewHolder(itemView, mItemViewHolderClass);
        itemViewHolder.onSetListeners();
        return new RecyclerViewHolder(itemViewHolder);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        T item = mListItems.get(position);
        PositionInfo positionInfo = new PositionInfo(position, EasyAdapterUtil.isFirst(position), EasyAdapterUtil.isLast(position, mListItems));
        recyclerViewHolder.itemViewHolder.onSetValues(item, positionInfo);
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        ItemViewHolder itemViewHolder;

        public RecyclerViewHolder(ItemViewHolder itemViewHolder) {
            super(itemViewHolder.getView());
            this.itemViewHolder = itemViewHolder;
        }

    }
}
