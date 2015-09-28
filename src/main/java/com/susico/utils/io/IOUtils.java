/*
 * Copyright (c) 2015. Suminda Sirinath Salpitikorala Dharmasena
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
