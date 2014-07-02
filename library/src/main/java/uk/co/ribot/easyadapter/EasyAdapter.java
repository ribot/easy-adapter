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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import uk.co.ribot.easyadapter.EasyAdapterUtil;
import uk.co.ribot.easyadapter.InvalidViewHolderException;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.LayoutIdMissingException;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.ClassAnnotationParser;

/**
 * Extension of BaseAdapter. Don't worry about implementing your own Adapter, the EasyAdapter will <b>do the tedious work for you.</b>
 * You just have to implement an {@link uk.co.ribot.easyadapter.ItemViewHolder} and pass it into the constructor of this class.
 * </p>
 * It implements the <a href="http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder">"view holder"</a> design pattern so your ListView will scroll smoothly.
 */
public class EasyAdapter<T> extends BaseAdapter {

    private List<T> mListItems;
    private Class<? extends ItemViewHolder> mItemViewHolderClass;
    private LayoutInflater mInflater;
    private Integer mItemLayoutId;

    /**
     * Constructs and EasyAdapter with a Context, an {@link uk.co.ribot.easyadapter.ItemViewHolder} class, and list of items.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link uk.co.ribot.easyadapter.ItemViewHolder} implementation class
     * @param listItems           the list of items to load in the Adapter
     */
    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass, List<T> listItems) {
        setItems(listItems);
        init(context, itemViewHolderClass);
    }

    /**
     * Constructs and EasyAdapter with a Context and an {@link uk.co.ribot.easyadapter.ItemViewHolder} class.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link uk.co.ribot.easyadapter.ItemViewHolder} implementation class
     */
    public EasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
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
        notifyDataSetChanged();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder<T> holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemLayoutId, parent, false);
            //Create a new view holder using reflection
            holder = EasyAdapterUtil.createViewHolder(convertView, mItemViewHolderClass);
            holder.onSetListeners();
            if (convertView != null)
                convertView.setTag(holder);
        } else {
            //Reuse the view holder
            holder = (ItemViewHolder) convertView.getTag();
        }

        T item = getItem(position);
        holder.setItem(item);
        PositionInfo positionInfo = new PositionInfo(position, EasyAdapterUtil.isFirst(position), EasyAdapterUtil.isLast(position, mListItems));
        holder.onSetValues(item, positionInfo);


        return convertView;
    }

}
