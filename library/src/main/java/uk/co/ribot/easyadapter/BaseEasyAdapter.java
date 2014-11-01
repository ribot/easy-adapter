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

/**
 *  Abstract extension of BaseAdapter that implements the <a href="http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder">"view holder"</a> design pattern.
 *  You should extend this class if your Adapter requires a data structure different to a List or it needs to provide some extra functionality
 *  to handle the data, i.e if you need a method to add items at the beginning of the list. If not simply use the provided implementation {@link uk.co.ribot.easyadapter.EasyAdapter}
 * @param <T> Data type for items
 */
public abstract class BaseEasyAdapter<T> extends BaseAdapter {

    private Class<? extends ItemViewHolder> mItemViewHolderClass;
    private LayoutInflater mInflater;
    private Integer mItemLayoutId;

    /**
     * Constructs and EasyAdapter with a Context and an {@link uk.co.ribot.easyadapter.ItemViewHolder} class.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link uk.co.ribot.easyadapter.ItemViewHolder} implementation class
     */
    public BaseEasyAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        init(context, itemViewHolderClass);
    }

    private void init(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        mItemViewHolderClass = itemViewHolderClass;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemLayoutId = EasyAdapterUtil.parseItemLayoutId(itemViewHolderClass);
    }

    @Override
    public abstract T getItem(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder<T> holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemLayoutId, parent, false);
            //Create a new view holder using reflection
            holder = EasyAdapterUtil.createViewHolder(convertView, mItemViewHolderClass);
            holder.onSetListeners();
            if (convertView != null) convertView.setTag(holder);
        } else {
            //Reuse the view holder
            holder = (ItemViewHolder) convertView.getTag();
        }

        T item = getItem(position);
        holder.setItem(item);
        PositionInfo positionInfo = new PositionInfo(position, position == 0, position == getCount() - 1);
        holder.onSetValues(item, positionInfo);

        return convertView;
    }
}
