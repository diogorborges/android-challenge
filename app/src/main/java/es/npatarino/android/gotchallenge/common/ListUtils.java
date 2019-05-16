package es.npatarino.android.gotchallenge.common;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListUtils {
    public static <T> T[] toArray(Class<T> typeClass, ArrayList<T> list) {
        T[] array = (T[]) Array.newInstance(typeClass, list.size());
        return list.toArray(array);
    }
}