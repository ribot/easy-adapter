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

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ItemViewHolderTest {

    @Test public void testGeListener() throws Exception {
        View view = new View(Robolectric.application);
        ItemViewHolder itemViewHolder = new ValidItemViewHolder(view);
        itemViewHolder.setListener(new ValidItemViewHolder.ValidItemViewHolderListener() {
            @Override
            public void onClicked() {

            }
        });
        Assert.assertNotNull(itemViewHolder.getListener());
        Assert.assertNotNull(itemViewHolder.getListener(ValidItemViewHolder.ValidItemViewHolderListener.class));
    }

    @Test public void testGeListenerWithInvalidType() throws Exception {
        View view = new View(Robolectric.application);
        ItemViewHolder itemViewHolder = new ValidItemViewHolder(view);
        itemViewHolder.setListener(new ValidItemViewHolder.ValidItemViewHolderListener() {
            @Override
            public void onClicked() {

            }
        });
        //Should crash because the type is not the same as the one se in the listener.
        ClassCastException exception = null;
        try {
            Assert.assertNotNull(itemViewHolder.getListener(String.class));
        } catch (ClassCastException e) {
            exception = e;
        }
        Assert.assertNotNull(exception);
    }

    @Test public void testGeListenerWhenEmpty() throws Exception {
        View view = new View(Robolectric.application);
        ItemViewHolder itemViewHolder = new ValidItemViewHolder(view);
        Assert.assertNull(itemViewHolder.getListener());
        Assert.assertNull(itemViewHolder.getListener(ValidItemViewHolder.ValidItemViewHolderListener.class));
    }

    private static class ValidItemViewHolder extends ItemViewHolder<Object> {

        public ValidItemViewHolder(View view) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }

        public interface ValidItemViewHolderListener {
            public void onClicked();
        }

    }

}
