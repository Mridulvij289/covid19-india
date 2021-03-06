/*
 * Copyright 2021 MRIDUL VIJ. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.covid19india.ui.states;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19india.R;

public class ItemClickSupport {
    private final RecyclerView mRecyclerView ;
    private OnItemClickListener mOnItemClickListener;

    private OnItemLongClickListener mOnItemLongClickListener;

    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            if (mOnItemClickListener != null) {

                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);

                mOnItemClickListener.onItemClicked
                        (mRecyclerView, holder.getAdapterPosition(), v);
            } }
    };

    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {

        @Override

        public boolean onLongClick(View v) {

            if (mOnItemLongClickListener != null) {

                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);

                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);

            }

            return false;

        }

    };

    private RecyclerView.OnChildAttachStateChangeListener mAttachListener

            = new RecyclerView.OnChildAttachStateChangeListener() {

        @Override

        public void onChildViewAttachedToWindow(@NonNull View view) {

            if (mOnItemClickListener != null) {

                view.setOnClickListener(mOnClickListener);

            }

            if (mOnItemLongClickListener != null) {

                view.setOnLongClickListener(mOnLongClickListener);

            }

        }



        @Override

        public void onChildViewDetachedFromWindow(@NonNull View view) {

        }

    };



    private ItemClickSupport(RecyclerView recyclerView) {

        mRecyclerView = recyclerView;

        mRecyclerView.setTag(R.id.item_click_support, this);

        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);

    }

    public ItemClickSupport() {
        mRecyclerView = null;
    }


    public static ItemClickSupport addTo(RecyclerView view) {

        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);

        if (support == null) {

            support = new ItemClickSupport(view);

        }

        return support;

    }



    public static ItemClickSupport removeFrom(RecyclerView view) {

        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);

        if (support != null) {

            support.detach(view);

        }

        return support;

    }



    public void setOnItemClickListener(OnItemClickListener listener) {

        mOnItemClickListener = listener;

    }



    public void setOnItemLongClickListener(OnItemLongClickListener listener) {

        mOnItemLongClickListener = listener;

    }



    private void detach(RecyclerView view) {

        view.removeOnChildAttachStateChangeListener(mAttachListener);

        view.setTag(R.id.item_click_support, null);

    }



    public interface OnItemClickListener {

        void onItemClicked(RecyclerView recyclerView, int position, View v);

    }



    public interface OnItemLongClickListener {

        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);

    }
}
