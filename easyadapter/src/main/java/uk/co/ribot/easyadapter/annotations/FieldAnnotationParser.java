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
package uk.co.ribot.easyadapter.annotations;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;


public class FieldAnnotationParser {

    /**
     * Parse {@link ViewId} annotation and try to assign the view with that id to the annotated field.
     * It will throw a {@link ClassCastException} if the field and the view with the given ID have different types.
     *
     * @param object object where the annotation is.
     * @param view   parent view that contains a view with the viewId given in the annotation.
     */
    public static void setViewFields(final Object object, final View view) {
        setViewFields(object, new ViewFinder() {
            @Override
            public View findViewById(int viewId) {
                return view.findViewById(viewId);
            }
        });
    }

    /**
     * Parse {@link ViewId} annotation and try to assign the view with that id to the annotated field.
     * It will throw a {@link ClassCastException} if the field and the view with the given ID have different types.
     *
     * @param object   object where the annotation is.
     * @param activity activity that contains a view with the viewId given in the annotation.
     */
    public static void setViewFields(final Object object, final Activity activity) {
        setViewFields(object, new ViewFinder() {
            @Override
            public View findViewById(int viewId) {
                return activity.findViewById(viewId);
            }
        });
    }

    /**
     * Parse {@link ViewId} annotation and try to assign the view with that id to the annotated field.
     * It will throw a {@link ClassCastException} if the field and the view with the given ID have different types.
     *
     * @param object     object where the annotation is.
     * @param viewFinder callback that provides a way of finding the view by the viewID given in the annotation.
     */
    private static void setViewFields(final Object object, final ViewFinder viewFinder) {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewId.class)) {
                field.setAccessible(true);
                ViewId viewIdAnnotation = field.getAnnotation(ViewId.class);
                try {
                    field.set(object, field.getType().cast(viewFinder.findViewById(viewIdAnnotation.value())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private interface ViewFinder {
        public View findViewById(int viewId);
    }
}
