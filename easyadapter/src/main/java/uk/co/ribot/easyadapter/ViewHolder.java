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
import android.view.View;

import uk.co.ribot.easyadapter.annotations.FieldAnnotationParser;

/**
 * Extend this class if you want to hold the child views of a given view, i.e it can be used to hold the views of a Fragment after inflating it's layout.
 * Annotate the view fields with {@link uk.co.ribot.easyadapter.annotations.ViewId}, i.e @ViewId(R.id.textView1).
 * It will look for the viewId's in the given parent view and will assign the views to the annotated fields of the subclass.
 */
public abstract class ViewHolder {

    private View mView;

    /**
     * @param view the parent view where the held views reside
     */
    public ViewHolder(View view) {
        mView = view;
        FieldAnnotationParser.setViewFields(this, view);
    }

    /**
     * @return the view that was set with {@link #ViewHolder(android.view.View)}
     */
    public View getView() {
        return mView;
    }

    /**
     * Returns the context the held view is running in so it can be used to access resources, etc.
     *
     * @return the context the held view is running in.
     */
    public Context getContext() {
        return mView.getContext();
    }

}
