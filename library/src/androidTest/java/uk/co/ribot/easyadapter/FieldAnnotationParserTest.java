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

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import uk.co.ribot.easyadapter.annotations.FieldAnnotationParser;
import uk.co.ribot.easyadapter.annotations.ViewId;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1) //Disable because this code will run inside robolectric with api level 17+
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class FieldAnnotationParserTest {

    private static final int TEXT_VIEW_ID = 1000;
    private static final int IMAGE_VIEW_ID = 2000;

    @Test public void testSetViewFields() throws Exception {
        TestObjectWithAnnotations testObject = new TestObjectWithAnnotations();
        FieldAnnotationParser.setViewFields(testObject, createTestLinearLayout());
        Assert.assertNotNull(testObject.textView);
        Assert.assertNotNull(testObject.imageView);
    }

    @Test public void testSetViewFieldsActivity() throws Exception {
        Activity testActivity = Robolectric.buildActivity(TestActivity.class).create().get();
        TestObjectWithAnnotations testObject = new TestObjectWithAnnotations();;
        FieldAnnotationParser.setViewFields(testObject, testActivity);
        Assert.assertNotNull(testObject.textView);
        Assert.assertNotNull(testObject.imageView);
    }

    @SuppressWarnings("ResourceType") //Because of warning when setting a hardcoded ID into the view
    private static LinearLayout createTestLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(Robolectric.application);
        TextView textView = new TextView(Robolectric.application);
        textView.setId(TEXT_VIEW_ID);
        linearLayout.addView(textView);
        ImageView imageView = new ImageView(Robolectric.application);
        imageView.setId(IMAGE_VIEW_ID);
        linearLayout.addView(imageView);
        return linearLayout;
    }

    private class TestObjectWithAnnotations {

        @ViewId(TEXT_VIEW_ID)
        TextView textView;
        @ViewId(IMAGE_VIEW_ID)
        ImageView imageView;

    }

    static class TestActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(createTestLinearLayout());
        }
    }
}
