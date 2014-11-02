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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
