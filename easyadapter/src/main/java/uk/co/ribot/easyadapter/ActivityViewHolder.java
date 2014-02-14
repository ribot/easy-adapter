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

import android.app.Activity;

import uk.co.ribot.easyadapter.annotations.FieldAnnotationParser;

/**
 * Extend this class if you want to hold the views of an Activity.
 * Annotate the view fields with {@link uk.co.ribot.easyadapter.annotations.ViewId} i.e @ViewId(R.id.textView1).
 * It will look for the viewId's in the Activity's layout and assign the views to the annotated fields of the subclass.
 * </p>
 * You must call {@link android.app.Activity#setContentView(int)} in the Activity before instantiating any subclass of ActivityViewHolder.
 */
public abstract class ActivityViewHolder {

    private Activity mActivity;

    /**
     * Constructs a ActivityViewHolder using an activity
     *
     * @param activity the Activity where the held views reside
     */
    public ActivityViewHolder(Activity activity) {
        mActivity = activity;
        FieldAnnotationParser.setViewFields(this, activity);
    }

    /**
     * Gets the activity that was used to construct this {@link uk.co.ribot.easyadapter.ActivityViewHolder}
     *
     * @return the Activity that was set with {@link #ActivityViewHolder(android.app.Activity)}
     */
    public Activity getActivity() {
        return mActivity;
    }

}
