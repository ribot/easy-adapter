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
 * Extension of {@link uk.co.ribot.easyadapter.BaseEasyAdapter} that uses a List as data structure and provide methods to set a new list of items or add them to the existing List.
 * Don't worry about implementing your own Adapter, the EasyAdapter will <b>do the tedious work for you.</b>
 * You only have to implement an {@link uk.co.ribot.easyadapter.ItemViewHolder} and pass it into the constructor of this class.
 * @param <T> Data type for items
 */
public class EasyAdapter<T> extends BaseEasyAdapter<T> {

    private List<T> mListItems;

    /**
     * Constructs and EasyAdapter with a Context, an {@link uk.co.ribot.easyadapter.ItemViewHolder} class, and list of items.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link uk.co.ribot.easyadapter.ItemViewHolder} implementation class
     * @param listItems           the list of items to load into the Adapter
     */
    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems) {
        super(context, itemViewHolderClass);
        setItems(listItems);
    }

    /**
     * Constructs and EasyAdapter with a Context and an {@link uk.co.ribot.easyadapter.ItemViewHolder} class.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link uk.co.ribot.easyadapter.ItemViewHolder} implementation class
     */
    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        super(context, itemViewHolderClass);
        mListItems = new ArrayList<T>();
    }

    /**
     * Constructs and EasyAdapter with a Context, an {@link uk.co.ribot.easyadapter.ItemViewHolder} class, a list of items
     * and a generic listener.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     * @param listItems           the list of items to load into the Adapter
     * @param listener            a generic object that you can access from your {@link ItemViewHolder} by calling
     *                            {@link ItemViewHolder#getListener()}, This can be used to pass a listener to the view holder that then you
     *                            can cast and use as a callback.
     */
    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems, Object listener) {
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
    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, Object listener) {
        super(context, itemViewHolderClass, listener);
        mListItems = new ArrayList<>();
    }

    /**
     * Set a new list of items into the Adapter and refresh the {@code AdapterView} by calling
     * {@code notifyDataSetChanged()}.
     * Use {@link #setItemsWithoutNotifying(List)}()} if you don't want to refresh
     * the {@code AdapterView} at this time.
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
     * the {@code AdapterView}.
     *
     * @return the underlying data {@code List}
     */
    public List<T> getItems() {
        return mListItems;
    }

    /**
     * Add a single item and refresh the {@code AdapterView} by calling
     * {@code notifyDataSetChanged()}.
     *
     * @param item item to add
     */
    public void addItem(T item) {
        mListItems.add(item);
        notifyDataSetChanged();
    }

    /**
     * Remove a single item and refresh the {@code AdapterView} by calling
     * {@code notifyDataSetChanged()}.
     *
     * @param item item to add
     * @return true if any data was modified by this operation, false otherwise.
     */
    public boolean removeItem(T item) {
        if (mListItems.remove(item)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    /**
     * Append a collection of items and refresh the {@code AdapterView} by calling
     * {@code notifyDataSetChanged()}.
     *
     * @param items collection of items to append
     * @return true if any data was modified by this operation, false otherwise.
     */
    public boolean addItems(Collection<? extends T> items) {
        if (mListItems.addAll(items)) {
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    /**
     * Remove a collection of items and refresh the {@code AdapterView} by calling
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

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public T getItem(int position) {
        return mListItems.get(position);
    }

}
