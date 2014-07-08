package uk.co.ribot.easyadapter;

import android.view.View;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import uk.co.ribot.easyadapter.annotations.ClassAnnotationParser;
import uk.co.ribot.easyadapter.annotations.LayoutId;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ClassAnnotationParserTest {

    private static final int LAYOUT_ID = 1;

    @Test public void testGetLayoutId() throws Exception{
        Integer layoutId = ClassAnnotationParser.getLayoutId(ItemViewHolderWithLayoutId.class);
        Assert.assertTrue(layoutId.equals(LAYOUT_ID));
    }

    @Test public void testGetLayoutIdWithoutAnnotation() throws Exception{
        Integer layoutId = ClassAnnotationParser.getLayoutId(ItemViewHolderWithoutLayoutId.class);
        Assert.assertNull(layoutId);
    }

    /*** Test ItemViewHolders ***/

    @LayoutId(LAYOUT_ID)
    private static class ItemViewHolderWithLayoutId extends ItemViewHolder<Object> {

        ItemViewHolderWithLayoutId(View view) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }
    }

    private static class ItemViewHolderWithoutLayoutId extends ItemViewHolder<Object> {

        ItemViewHolderWithoutLayoutId(View view) {
            super(view);
        }

        @Override
        public void onSetValues(Object item, PositionInfo positionInfo) {

        }
    }
}
