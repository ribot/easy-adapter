/*
 * Copyright (C) 2014 Ribot Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.ribot.easyadapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Extension of {@link uk.co.ribot.easyadapter.BaseEasyRecyclerAdapter} that uses a List as data structure and provide methods
 * to set a new list of items or add them to the existing List.
 * Don't worry about implementing your own Adapter, the EasyRecyclerAdapter will <b>do the tedious work for you.</b>
 * You only have to implement an {@link uk.co.ribot.easyadapter.ItemViewHolder} and pass it into the constructor of this class.
 *
 * @param <T> Data type for items
 */
public class EasyRecyclerAdapter<T> extends BaseEasyRecyclerAdapter<T> {

    private List<T> mListItems;

    /**
     * Constructs and EasyAdapter with a Context, an {@link ItemViewHolder} class, and list of items.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     * @param listItems           the list of items to load in the Adapter
     */
    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems) {
        super(context, itemViewHolderClass);
        setItems(listItems);
    }

    /**
     * Constructs and EasyAdapter with a Context and an {@link ItemViewHolder} class.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     */
    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        super(context, itemViewHolderClass);
        mListItems = new ArrayList<T>();
    }

    /**
     * Constructs and EasyAdapter with a Context, an {@link ItemViewHolder} class, list of items and a generic listener.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     * @param listItems           the list of items to load in the Adapter
     * @param listener            a generic object that you can access from your {@link ItemViewHolder} by calling
     *                            {@link ItemViewHolder#getListener()}, This can be used to pass a listener to the view holder that then you
     *                            can cast and use as a callback.
     */
    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems, Object listener) {
        super(context, itemViewHolderClass, listener);
        setItems(listItems);
    }

    /**
     * Constructs and EasyAdapter with a Context, an {@link ItemViewHolder} class and a generic listener.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     * @param listener            a generic object that you can access from your {@link ItemViewHolder} by calling
     *                            {@link ItemViewHolder#getListener()}, This can be used to pass a listener to the view holder that then you
     *                            can cast and use as a callback.
     */
    public EasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, Object listener) {
        super(context, itemViewHolderClass, listener);
        mListItems = new ArrayList<T>();
    }

    /**
     * Set a new list of items into the Adapter and refresh the {@code RecyclerView} by calling
     * {@code notifyDataSetChanged()}.
     * Use {@link #setItemsWithoutNotifying(List)}()} if you don't want to refresh
     * the {@code RecyclerView} at this time.
     *
     * @param listItems new List of items to use as the underlying data structure
     */
    public void setItems(List<T> listItems) {
        mListItems = listItems;
        notifyDataSetChanged();
    }

    /**
     * Set a new list of items into the Adapter.
     *
     * @param listItems new List of items to use as the underlying data structure
     */
    public void setItemsWithoutNotifying(List<T> listItems) {
        mListItems = listItems;
    }

    /**
     * Retrieve the {@code List} of items. Changes to this {@code List} directly affect the data displayed on
     * the {@code RecyclerView}.
     *
     * @return the underlying data {@code List}
     */
    public List<T> getItems() {
        return mListItems;
    }

    /**
     * Add a single item and refresh the {@code RecyclerView} by calling
     * {@code notifyItemInserted()}.
     *
     * @param item item to add
     */
    public void addItem(T item) {
        mListItems.add(item);
        notifyItemInserted(mListItems.indexOf(item));
    }

    /**
     * Remove a single item and refresh the {@code RecyclerView} by calling
     * {@code notifyItemRemoved()}.
     *
     * @param item item to add
     * @return true if any data was modified by this operation, false otherwise.
     */
    public boolean removeItem(T item) {
        int index = mListItems.indexOf(item);
        if (index < 0) return false;
        mListItems.remove(index);
        notifyItemRemoved(index);
        return true;
    }

    /**
     * Append a collection of items and refresh the {@code RecyclerView} by calling
     * {@code notifyItemRangeInserted()}.
     *
     * @param items collection of items to append
     * @return true if any data was modified by this operation, false otherwise.
     */
    public boolean addItems(Collection<T> items) {
        if (items.isEmpty()) return false;
        int previousSize = getItemCount();
        if (mListItems.addAll(items)) {
            notifyItemRangeInserted(previousSize, items.size());
            return true;
        }
        return false;
    }

    /**
     * Remove a collection of items and refresh the {@code RecyclerView} by calling
     * {@code notifyDataSetChanged()}.
     *
     * @param items {@code Collection} of items to remove
     * @return true if any data was modified by this operation, false otherwise.
     */
    public boolean removeItems(Collection<? extends T> items) {
        if (mListItems.removeAll(items)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    /**
     * Access a data item
     *
     * @param position the position of the item
     * @return the data item in the given position
     */
    @Override
    public T getItem(int position) {
        return mListItems.get(position);
    }

    /**
     * Access the size of the adapter
     *
     * @return the number of items in the adapter.
     */
    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
