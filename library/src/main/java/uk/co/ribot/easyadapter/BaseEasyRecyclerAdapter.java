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

/**
 * Abstract extension of {@link android.support.v7.widget.RecyclerView.Adapter} that allows you to use
 * {@link uk.co.ribot.easyadapter.ItemViewHolder} in a {@link android.support.v7.widget.RecyclerView}.
 * You should extend this class if your Adapter requires a data structure different to a List or it needs to provide some extra functionality
 * to handle the data, i.e if you need a method to add items at the beginning of the list.
 * If not simply use the provided implementation {@link uk.co.ribot.easyadapter.EasyRecyclerAdapter}
 *
 * @param <T> Data type for items
 */
public abstract class BaseEasyRecyclerAdapter<T> extends RecyclerView.Adapter<BaseEasyRecyclerAdapter.RecyclerViewHolder> {

    private Class mItemViewHolderClass;
    private LayoutInflater mInflater;
    private Integer mItemLayoutId;

    /**
     * Constructs a BaseEasyRecyclerAdapter with a Context and an {@link ItemViewHolder} class.
     *
     * @param context             a valid Context
     * @param itemViewHolderClass your {@link ItemViewHolder} implementation class
     */
    public BaseEasyRecyclerAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        init(context, itemViewHolderClass);
    }

    private void init(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        mItemViewHolderClass = itemViewHolderClass;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemLayoutId = EasyAdapterUtil.parseItemLayoutId(itemViewHolderClass);
    }

    public abstract T getItem(int position);

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = mInflater.inflate(mItemLayoutId, parent, false);
        ItemViewHolder<T> itemViewHolder = EasyAdapterUtil.createViewHolder(itemView, mItemViewHolderClass);
        itemViewHolder.onSetListeners();
        return new RecyclerViewHolder(itemViewHolder);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        T item = getItem(position);
        ItemViewHolder<T> itemViewHolder = recyclerViewHolder.itemViewHolder;
        PositionInfo positionInfo = new PositionInfo(position, position == 0, position == getItemCount() - 1);
        itemViewHolder.setItem(item);
        itemViewHolder.onSetValues(item, positionInfo);
    }

    // A RecyclerView.ViewHolder that wraps an ItemViewHolder
    static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        ItemViewHolder itemViewHolder;

        public RecyclerViewHolder(ItemViewHolder itemViewHolder) {
            super(itemViewHolder.getView());
            this.itemViewHolder = itemViewHolder;
        }

    }
}
