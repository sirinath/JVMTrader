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

package com.susico.utils;

import sun.misc.Unsafe;
import uk.co.real_logic.agrona.IoUtil;
import uk.co.real_logic.agrona.UnsafeAccess;
import uk.co.real_logic.agrona.concurrent.UnsafeBuffer;

import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.BitSet;

/**
 * Created by sirin_000 on 02/09/2015.
 */

public final class Utils {
    public final static class ArrayUtils {
        public final static class ArrayAccess {
            public final static ArrayAccess CHECKED = new ArrayAccess(true);
            public final static ArrayAccess UNCHECKED = new ArrayAccess(false);

            private final boolean SHOULD_BOUNDS_CHECK;

            private final static Unsafe UNSAFE = UnsafeAccess.UNSAFE;

            private ArrayAccess(final boolean SHOULD_BOUNDS_CHECK) {
                this.SHOULD_BOUNDS_CHECK = SHOULD_BOUNDS_CHECK;
            }

            public static ArrayAccess checked(final boolean checked) {
                return checked ? CHECKED : UNCHECKED;
            }

            public static ArrayAccess checked() {
                return CHECKED;
            }

            public static ArrayAccess unchecked() {
                return UNCHECKED;
            }

            public final <T> T get(final T[] buff, final long i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[(int) i];
                else
                    return (T) UNSAFE.getObject(buff, UNSAFE.ARRAY_OBJECT_BASE_OFFSET + i * UNSAFE.ARRAY_OBJECT_INDEX_SCALE);
            }

            public final <T> T get(final T[] buff, final int i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[i];
                else
                    return (T) UNSAFE.getObject(buff,  (long) UNSAFE.ARRAY_OBJECT_BASE_OFFSET + i * UNSAFE.ARRAY_OBJECT_INDEX_SCALE);
            }

            public final <T, U extends T> void put(final T[] buff, final long i, final U obj) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[(int) i] = obj;
                else
                    UNSAFE.putObject(buff, UNSAFE.ARRAY_OBJECT_BASE_OFFSET + i * UNSAFE.ARRAY_OBJECT_INDEX_SCALE, obj);
            }

            public final <T, U extends T> void put(final T[] buff, final int i, final U obj) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[i] = obj;
                else
                    UNSAFE.putObject(buff, (long) UNSAFE.ARRAY_OBJECT_BASE_OFFSET + i * UNSAFE.ARRAY_OBJECT_INDEX_SCALE, obj);
            }

            public final boolean get(final boolean[] buff, final long i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[(int) i];
                else
                    return UNSAFE.getBoolean(buff, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + i * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE);
            }

            public final boolean get(final boolean[] buff, final int i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[i];
                else
                    return UNSAFE.getBoolean(buff, (long) UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + i * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE);
            }

            public final void put(final boolean[] buff, final long i, final boolean value) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[(int) i] = value;
                else
                    UNSAFE.putBoolean(buff, UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + i * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE, value);
            }

            public final void put(final boolean[] buff, final int i, final boolean value) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[i] = value;
                else
                    UNSAFE.putBoolean(buff, (long) UNSAFE.ARRAY_BOOLEAN_BASE_OFFSET + i * UNSAFE.ARRAY_BOOLEAN_INDEX_SCALE, value);
            }

            public final byte get(final byte[] buff, final long i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[(int) i];
                else
                    return UNSAFE.getByte(buff, UNSAFE.ARRAY_BYTE_BASE_OFFSET + i * UNSAFE.ARRAY_BYTE_INDEX_SCALE);
            }

            public final byte get(final byte[] buff, final int i) {
                if (SHOULD_BOUNDS_CHECK)
                    return buff[i];
                else
                    return UNSAFE.getByte(buff, (long) UNSAFE.ARRAY_BYTE_BASE_OFFSET + i * UNSAFE.ARRAY_BYTE_INDEX_SCALE);
            }

            public final void put(final byte[] buff, final long i, final byte value) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[(int) i] = value;
                else
                    UNSAFE.putByte(buff, UNSAFE.ARRAY_BYTE_BASE_OFFSET + i * UNSAFE.ARRAY_BYTE_INDEX_SCALE, value);
            }

            public final void put(final byte[] buff, final int i, final byte value) {
                if (SHOULD_BOUNDS_CHECK)
                    buff[i] = value;
                else
                    UNSAFE.putByte(buff, (long) UNSAFE.ARRAY_BYTE_BASE_OFFSET + i * UNSAFE.ARRAY_BYTE_INDEX_SCALE, value);
            }
        }
    }

    public final static class BitUtils {
        public final static long PAGE_SIZE = UnsafeAccess.UNSAFE.pageSize();

        public static long roundUpToPageSize(long i) {
            return roundUpTo(i, PAGE_SIZE);
        }

        public static long roundUpTo(long i, long multiple) {
            return (i + multiple - 1L) & ~(multiple - 1L);
        }

        public static int roundUpToPageSize(int i) {
            return roundUpTo(i, (int) PAGE_SIZE);
        }

        public static int roundUpTo(int i, int multiple) {
            return (i + multiple - 1) & ~(multiple - 1);
        }

        public static long packToLong(final ByteOrder byteOrder, final int position, final byte... bytes) {
            final int len = bytes.length > position ? bytes.length - position : 0;

            long id = 0;

            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                switch (len) {
                    default:
                    case 8:
                        id += bytes[position + 8 - 1] << ((1 - 1) * 8);
                    case 7:
                        id += bytes[position + 7 - 1] << ((2 - 1) * 8);
                    case 6:
                        id += bytes[position + 6 - 1] << ((3 - 1) * 8);
                    case 5:
                        id += bytes[position + 5 - 1] << ((4 - 1) * 8);
                    case 4:
                        id += bytes[position + 4 - 1] << ((5 - 1) * 8);
                    case 3:
                        id += bytes[position + 3 - 1] << ((6 - 1) * 8);
                    case 2:
                        id += bytes[position + 2 - 1] << ((7 - 1) * 8);
                    case 1:
                        id += bytes[position + 1 - 1] << ((8 - 1) * 8);
                    case 0:
                        return id;
                }
            } else {
                switch (len) {
                    default:
                    case 8:
                        id += bytes[position + 8 - 1] << ((8 - 1) * 8);
                    case 7:
                        id += bytes[position + 7 - 1] << ((7 - 1) * 8);
                    case 6:
                        id += bytes[position + 6 - 1] << ((6 - 1) * 8);
                    case 5:
                        id += bytes[position + 5 - 1] << ((5 - 1) * 8);
                    case 4:
                        id += bytes[position + 4 - 1] << ((4 - 1) * 8);
                    case 3:
                        id += bytes[position + 3 - 1] << ((3 - 1) * 8);
                    case 2:
                        id += bytes[position + 2 - 1] << ((2 - 1) * 8);
                    case 1:
                        id += bytes[position + 1 - 1] << ((1 - 1) * 8);
                    case 0:
                        return id;
                }
            }
        }

        public static long packToLong(final byte... bytes) {
            return packToLong(ByteOrder.nativeOrder(), 0, bytes);
        }

        public static void unpack(final ByteOrder byteOrder, final long value, final int position,  final byte[] bytes) {
            final int len = bytes.length > position ? bytes.length - position : 0;

            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                switch (len) {
                    default:
                    case 8:
                        bytes[position + 8 - 1] = (byte) ((value & 0x00000000000000FFL) >>> ((1 - 1) * 8));
                    case 7:
                        bytes[position + 7 - 1] = (byte) ((value & 0x000000000000FF00L) >>> ((2 - 1) * 8));
                    case 6:
                        bytes[position + 6 - 1] = (byte) ((value & 0x0000000000FF0000L) >>> ((3 - 1) * 8));
                    case 5:
                        bytes[position + 5 - 1] = (byte) ((value & 0x00000000FF000000L) >>> ((4 - 1) * 8));
                    case 4:
                        bytes[position + 4 - 1] = (byte) ((value & 0x000000FF00000000L) >>> ((5 - 1) * 8));
                    case 3:
                        bytes[position + 3 - 1] = (byte) ((value & 0x0000FF0000000000L) >>> ((6 - 1) * 8));
                    case 2:
                        bytes[position + 2 - 1] = (byte) ((value & 0x00FF000000000000L) >>> ((7 - 1) * 8));
                    case 1:
                        bytes[position + 1 - 1] = (byte) ((value & 0xFF00000000000000L) >>> ((8 - 1) * 8));
                    case 0:
                        return;
                }
            } else {
                switch (len) {
                    default:
                    case 8:
                        bytes[position + 8 - 1] = (byte) ((value & 0xFF00000000000000L) >>> ((8 - 1) * 8));
                    case 7:
                        bytes[position + 7 - 1] = (byte) ((value & 0x00FF000000000000L) >>> ((7 - 1) * 8));
                    case 6:
                        bytes[position + 6 - 1] = (byte) ((value & 0x0000FF0000000000L) >>> ((6 - 1) * 8));
                    case 5:
                        bytes[position + 5 - 1] = (byte) ((value & 0x000000FF00000000L) >>> ((5 - 1) * 8));
                    case 4:
                        bytes[position + 4 - 1] = (byte) ((value & 0x00000000FF000000L) >>> ((4 - 1) * 8));
                    case 3:
                        bytes[position + 3 - 1] = (byte) ((value & 0x0000000000FF0000L) >>> ((3 - 1) * 8));
                    case 2:
                        bytes[position + 2 - 1] = (byte) ((value & 0x000000000000FF00L) >>> ((2 - 1) * 8));
                    case 1:
                        bytes[position + 1 - 1] = (byte) ((value & 0x00000000000000FFL) >>> ((1 - 1) * 8));
                    case 0:
                        return;
                }
            }
        }

        public static void unpack(final long value,  final byte[] bytes) {
            unpack(ByteOrder.nativeOrder(), value, 0, bytes);
        }

        public static int packToInt(final ByteOrder byteOrder, final int position, final byte ... bytes) {
            final int len = bytes.length > position ? bytes.length - position : 0;

            int id = 0;

            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                switch (len) {
                    default:
                    case 4:
                        id += bytes[position + 4 - 1] << ((1 - 1) * 8);
                    case 3:
                        id += bytes[position + 3 - 1] << ((2 - 1) * 8);
                    case 2:
                        id += bytes[position + 2 - 1] << ((3 - 1) * 8);
                    case 1:
                        id += bytes[position + 1 - 1] << ((4 - 1) * 8);
                    case 0:
                        return id;
                }
            } else {
                switch (len) {
                    default:
                    case 4:
                        id += bytes[position + 4 - 1] << ((4 - 1) * 8);
                    case 3:
                        id += bytes[position + 3 - 1] << ((3 - 1) * 8);
                    case 2:
                        id += bytes[position + 2 - 1] << ((2 - 1) * 8);
                    case 1:
                        id += bytes[position + 1 - 1] << ((1 - 1) * 8);
                    case 0:
                        return id;
                }
            }
        }

        public static int packToInt(final byte... bytes) {
            return packToInt(ByteOrder.nativeOrder(), 0, bytes);
        }

        public static void unpack(final ByteOrder byteOrder, final int value, final int position,  final byte[] bytes) {
            final int len = bytes.length > position ? bytes.length - position : 0;

            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                switch (len) {
                    default:
                    case 4:
                        bytes[position + 4 - 1] = (byte) ((value & 0x000000FF) >>> ((1 - 1) * 8));
                    case 3:
                        bytes[position + 3 - 1] = (byte) ((value & 0x0000FF00) >>> ((2 - 1) * 8));
                    case 2:
                        bytes[position + 2 - 1] = (byte) ((value & 0x00FF0000) >>> ((3 - 1) * 8));
                    case 1:
                        bytes[position + 1 - 1] = (byte) ((value & 0xFF000000) >>> ((4 - 1) * 8));
                    case 0:
                        return;
                }
            } else {
                switch (len) {
                    default:
                    case 4:
                        bytes[position + 4 - 1] = (byte) ((value & 0xFF000000) >>> ((4 - 1) * 8));
                    case 3:
                        bytes[position + 3 - 1] = (byte) ((value & 0x00FF0000) >>> ((3 - 1) * 8));
                    case 2:
                        bytes[position + 2 - 1] = (byte) ((value & 0x0000FF00) >>> ((2 - 1) * 8));
                    case 1:
                        bytes[position + 1 - 1] = (byte) ((value & 0x000000FF) >>> ((1 - 1) * 8));
                    case 0:
                        return;
                }
            }
        }

        public static void unpack(final int value,  final byte[] bytes) {
            unpack(ByteOrder.nativeOrder(), value, 0, bytes);
        }

        public static class Packer extends ThreadLocal<Packer> {
            public final static int BUFF_LEN = Long.BYTES;
            protected final byte[] buff = new byte[BUFF_LEN];
            protected final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(buff);

            @Override
            protected Packer initialValue() {
                return new Packer();
            }

            public int packToInt(final ByteOrder byteOrder, final int position, final byte ... bytes) {
                final int len = bytes.length > position ? bytes.length - position : 0;

                unsafeBuffer.putLong(0, 0L);

                final int intBuffOffset = byteOrder == ByteOrder.LITTLE_ENDIAN ? 0 : Integer.BYTES - len;

                if (len > Integer.BYTES)
                    unsafeBuffer.putBytes(intBuffOffset, bytes, position, Integer.BYTES);
                else
                    unsafeBuffer.putBytes(intBuffOffset, bytes, position, len);

                return unsafeBuffer.getInt(intBuffOffset, byteOrder);
            }

            public long packToLong(final ByteOrder byteOrder, final int position, final byte ... bytes) {
                final int len = bytes.length > position ? bytes.length - position : 0;

                unsafeBuffer.putLong(0, 0L);

                final int longBuffOffset = Long.BYTES - len;

                if (len > Long.BYTES)
                    unsafeBuffer.putBytes(longBuffOffset, bytes, position, Long.BYTES);
                else
                    unsafeBuffer.putBytes(longBuffOffset, bytes, position, len);

                return unsafeBuffer.getLong(longBuffOffset, byteOrder);
            }
        }
    }

    public static class IOUtils {
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
}
