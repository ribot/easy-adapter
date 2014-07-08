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
        InvalidViewHolderException exception = null;
        try {
            //Should throw a InvalidViewHolderException exception
            EasyAdapterUtil.createViewHolder(view, InvalidItemViewHolder.class);
        }catch (InvalidViewHolderException e) {
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
        }catch (LayoutIdMissingException e) {
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
