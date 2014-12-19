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

import android.view.View;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import uk.co.ribot.easyadapter.annotations.LayoutId;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class EasyAdapterUtilTest {

    private static final int LAYOUT_ID = 1;

    @Test public void testCreateViewHolder() throws Exception {
        View view = new View(Robolectric.application);
        ItemViewHolder<?> itemViewHolder = EasyAdapterUtil.createViewHolder(view, ValidItemViewHolder.class);
        Assert.assertNotNull(itemViewHolder);
    }

    @Test public void testCreateInvalidViewHolder() throws Exception {
        View view = new View(Robolectric.application);
        RuntimeException exception = null;
        try {
            //Should throw a RuntimeException because the constructor is invalid
            EasyAdapterUtil.createViewHolder(view, InvalidItemViewHolder.class);
        } catch (RuntimeException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }

    @Test public void testParseItemLayoutId() throws Exception {
        Integer layoutId = EasyAdapterUtil.parseItemLayoutId(ItemViewHolderWithLayoutId.class);
        Assert.assertTrue(layoutId.equals(LAYOUT_ID));
    }

    @Test public void testParseItemLayoutIdWithoutLayoutId() throws Exception {
        LayoutIdMissingException exception = null;
        try {
            EasyAdapterUtil.parseItemLayoutId(ItemViewHolderWithoutLayoutId.class);
        } catch (LayoutIdMissingException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }

    /*** Test ItemViewHolders ***/

    private static class ValidItemViewHolder extends ItemViewHolder<Object> {

        public ValidItemViewHolder(View view) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }

    }

    //This ItemViewHolder is invalid because it has another parameter in the constructor
    private static class InvalidItemViewHolder extends ItemViewHolder<Object> {

        public InvalidItemViewHolder(View view, int anotherParam) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }

    }

    @LayoutId(LAYOUT_ID)
    private static class ItemViewHolderWithLayoutId extends ItemViewHolder<Object> {

        ItemViewHolderWithLayoutId(View view) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }
    }

    //This view holders doesn't have a LayoutId annotation
    private static class ItemViewHolderWithoutLayoutId extends ItemViewHolder<Object> {

        ItemViewHolderWithoutLayoutId(View view) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }
    }
}
