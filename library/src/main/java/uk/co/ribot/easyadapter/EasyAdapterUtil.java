package uk.co.ribot.easyadapter;

import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import uk.co.ribot.easyadapter.annotations.ClassAnnotationParser;

class EasyAdapterUtil {

    //Create a new ItemViewHolder using Java reflection
    public static ItemViewHolder createViewHolder(View view, Class<? extends ItemViewHolder> itemViewHolderClass) {
        try {
            Constructor<? extends ItemViewHolder> constructor = itemViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (IllegalAccessException e) {
            throw new InvalidViewHolderException();
        } catch (NoSuchMethodException e) {
            throw new InvalidViewHolderException();
        } catch (InvocationTargetException e) {
            throw new InvalidViewHolderException();
        } catch (InstantiationException e) {
            throw new InvalidViewHolderException();
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
