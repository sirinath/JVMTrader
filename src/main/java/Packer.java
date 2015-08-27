/**
 * Created by sirin_000 on 25/08/2015.
 */
final public class Packer {
    public static final long packLong(final byte ... bytes) {
        long result = 0;

        if (bytes == null)
            return result;

        switch (bytes.length) {
            case 8:
                result  = bytes[8];
            case 7:
                result |= bytes[7] << (8 * 1);
            case 6:
                result |= bytes[6] << (8 * 2);
            case 5:
                result |= bytes[5] << (8 * 3);
            case 4:
                result |= bytes[4] << (8 * 4);
            case 3:
                result |= bytes[3] << (8 * 5);
            case 2:
                result |= bytes[2] << (8 * 6);
            case 1:
                result |= bytes[1] << (8 * 7);
            case 0:
                break;

            default:
                throw new IllegalArgumentException("Size restricted to 8 bytes");
        }

        return result;
    }

    public static final long packLong(final char ... chars) {
        long result = 0;

        if (chars == null)
            return result;

        switch (chars.length) {
            case 8:
                result  = ((byte) chars[8]);
            case 7:
                result |= ((byte) chars[7]) << (8 * 1);
            case 6:
                result |= ((byte) chars[6]) << (8 * 2);
            case 5:
                result |= ((byte) chars[5]) << (8 * 3);
            case 4:
                result |= ((byte) chars[4]) << (8 * 4);
            case 3:
                result |= ((byte) chars[3]) << (8 * 5);
            case 2:
                result |= ((byte)chars[2]) << (8 * 6);
            case 1:
                result |= ((byte) chars[1]) << (8 * 7);
            case 0:
                break;

            default:
                throw new IllegalArgumentException("Size restricted to 8 chars");
        }

        return result;
    }


    public static final int packInt(final byte ... bytes) {
        int result = 0;

        if (bytes == null)
            return result;

        switch (bytes.length) {
            case 4:
                result  = bytes[4];
            case 3:
                result |= bytes[3] << (8 * 1);
            case 2:
                result |= bytes[2] << (8 * 2);
            case 1:
                result |= bytes[1] << (8 * 3);
            case 0:
                break;

            default:
                throw new IllegalArgumentException("Size restricted to 4 bytes");
        }

        return result;
    }

    public static final int packInt(final char ... chars) {
        int result = 0;

        if (chars == null)
            return result;

        switch (chars.length) {
            case 4:
                result  = ((byte) chars[4]);
            case 3:
                result |= ((byte)chars[3]) << (8 * 1);
            case 2:
                result |= ((byte)chars[2]) << (8 * 2);
            case 1:
                result |= ((byte)chars[1]) << (8 * 3);
            case 0:
                break;

            default:
                throw new IllegalArgumentException("Size restricted to 4 chars");
        }

        return result;
    }
}
