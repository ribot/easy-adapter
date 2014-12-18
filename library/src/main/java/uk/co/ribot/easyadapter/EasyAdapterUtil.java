package uk.co.ribot.easyadapter;

import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import uk.co.ribot.easyadapter.annotations.ClassAnnotationParser;

class EasyAdapterUtil {

    //Create a new ItemViewHolder using Java reflection
    public static ItemViewHolder createViewHolder(View view, Class<? extends ItemViewHolder> itemViewHolderClass) {
        try {
            Constructor<? extends ItemViewHolder> constructor = itemViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find a public constructor that takes an argument View in " +
                    itemViewHolderClass.getSimpleName(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to instantiate " + itemViewHolderClass.getSimpleName(),  e);
        }
    }

    //Parses the layout ID annotation form the itemViewHolderClass
    public static Integer parseItemLayoutId(Class<? extends ItemViewHolder> itemViewHolderClass) {
        Integer itemLayoutId = ClassAnnotationParser.getLayoutId(itemViewHolderClass);
        if (itemLayoutId == null) {
            throw new LayoutIdMissingException();
        }
        return itemLayoutId;
    }

}
