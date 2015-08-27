import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by sirin_000 on 25/08/2015.
 */
public class UnSafe {
    private static final Unsafe UNSAFE = getUnSafe();

    public static final Unsafe get() {
        return UNSAFE;
    }

    private static final Unsafe getUnSafe() {
        try {
            final Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return  (Unsafe) f.get(null);
        } catch (NoSuchFieldException|IllegalAccessException e) {
            throw new RuntimeException("Cannot access theUnsafe", e);
        }
    }
}
