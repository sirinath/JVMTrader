package com.susico.utils.io;

import uk.co.real_logic.agrona.IoUtil;

import java.nio.MappedByteBuffer;

/**
 * Created by sirin_000 on 25/09/2015.
 */
public class IOUtils {
    public static void saveAndUnmap(final MappedByteBuffer buff) {
        Throwable suppressedException = null;
        try {
            buff.force();
        } catch (Throwable t) {
            suppressedException = t;
        } finally {
            try {
                IoUtil.unmap(buff);
            } catch (Throwable t) {
                RuntimeException exception;
                if (suppressedException != null) {
                    exception = new RuntimeException("Error in saving and un-mapping", t);
                    exception.addSuppressed(t);
                } else {
                    exception = new RuntimeException("Error in un-mapping", t);
                }

                throw exception;
            }
        }
    }

    public static void discardAndUnmap(final MappedByteBuffer buff) {
        try {
            IoUtil.unmap(buff);
        } catch (Throwable t) {
            throw new RuntimeException("Error in un-mapping", t);
        }
    }
}
