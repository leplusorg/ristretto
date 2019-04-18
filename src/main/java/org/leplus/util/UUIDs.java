package org.leplus.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class UUIDs {

	public static final int UUID_BYTES = 16;

	private UUIDs() {
		super();
	}

	private static final ReversibleUUIDGenerator revInstance = new ReversibleUUIDGenerator();

	public static ReversibleUUIDGenerator reversible() {
		return revInstance;
	}

	private static final DeterministicUUIDGenerator detInstance = new DeterministicUUIDGenerator();

	public static DeterministicUUIDGenerator deterministic() {
		return detInstance;
	}

	public static class ReversibleUUIDGenerator {

		public static final int MAX_BYTES = UUID_BYTES / Byte.BYTES;
		public static final int MAX_SHORTS = UUID_BYTES / Short.BYTES;
		public static final int MAX_DOUBLES = UUID_BYTES / Double.BYTES;
		public static final int MAX_FLOATS = UUID_BYTES / Float.BYTES;
		public static final int MAX_CHARS = UUID_BYTES / Character.BYTES;
		public static final int MAX_INTS = UUID_BYTES / Integer.BYTES;
		public static final int MAX_LONGS = UUID_BYTES / Long.BYTES;

		private ReversibleUUIDGenerator() {
			super();
		}

		private static ByteBuffer allocateByteBuffer() {
			return ByteBuffer.allocate(UUID_BYTES);
		}

		private static ByteBuffer toByteBuffer(final UUID uuid) {
			final ByteBuffer buffer = ByteBuffer.allocate(UUID_BYTES);
			buffer.asLongBuffer().put(uuid.getMostSignificantBits()).put(uuid.getLeastSignificantBits());
			return buffer;
		}

		public static byte[] toBytes(final UUID uuid) {
			return toByteBuffer(uuid).array();
		}

		public static char[] toChars(final UUID uuid) {
			final ByteBuffer byteBuffer = toByteBuffer(uuid);
			final char[] result = new char[MAX_CHARS];
			for (int i = 0; i < result.length; i++) {
				result[i] = byteBuffer.getChar();
			}
			return result;
		}

		public static double[] toDoubles(final UUID uuid) {
			final ByteBuffer byteBuffer = toByteBuffer(uuid);
			final double[] result = new double[MAX_DOUBLES];
			for (int i = 0; i < result.length; i++) {
				result[i] = byteBuffer.getDouble();
			}
			return result;
		}

		public static float[] toFloats(final UUID uuid) {
			final ByteBuffer byteBuffer = toByteBuffer(uuid);
			final float[] result = new float[MAX_FLOATS];
			for (int i = 0; i < result.length; i++) {
				result[i] = byteBuffer.getFloat();
			}
			return result;
		}

		public static int[] toInts(final UUID uuid) {
			final ByteBuffer byteBuffer = toByteBuffer(uuid);
			final int[] result = new int[MAX_INTS];
			for (int i = 0; i < result.length; i++) {
				result[i] = byteBuffer.getInt();
			}
			return result;
		}

		public static long[] toLongs(final UUID uuid) {
			final ByteBuffer byteBuffer = toByteBuffer(uuid);
			final long[] result = new long[MAX_LONGS];
			for (int i = 0; i < result.length; i++) {
				result[i] = byteBuffer.getLong();
			}
			return result;
		}

		public static short[] toShorts(final UUID uuid) {
			final ByteBuffer byteBuffer = toByteBuffer(uuid);
			final short[] result = new short[MAX_SHORTS];
			for (int i = 0; i < result.length; i++) {
				result[i] = byteBuffer.getShort();
			}
			return result;
		}

		public static UUID toUUID(final byte... bytes) {
			if (bytes == null) {
				return null;
			}
			if (bytes.length > MAX_BYTES) {
				throw new ArrayIndexOutOfBoundsException(bytes.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.put(bytes);
			return toUUID(byteBuffer);
		}

		private static UUID toUUID(final ByteBuffer bytes) {
			bytes.position(0);
			return new UUID(bytes.getLong(), bytes.getLong());
		}

		public static UUID toUUID(final char... chars) {
			if (chars == null) {
				return null;
			}
			if (chars.length > MAX_CHARS) {
				throw new ArrayIndexOutOfBoundsException(chars.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.asCharBuffer().put(chars);
			return toUUID(byteBuffer);
		}

		public static UUID toUUID(final double... doubles) {
			if (doubles == null) {
				return null;
			}
			if (doubles.length > MAX_DOUBLES) {
				throw new ArrayIndexOutOfBoundsException(doubles.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.asDoubleBuffer().put(doubles);
			return toUUID(byteBuffer);
		}

		public static UUID toUUID(final float... floats) {
			if (floats == null) {
				return null;
			}
			if (floats.length > MAX_FLOATS) {
				throw new ArrayIndexOutOfBoundsException(floats.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.asFloatBuffer().put(floats);
			return toUUID(byteBuffer);
		}

		public static UUID toUUID(final int... ints) {
			if (ints == null) {
				return null;
			}
			if (ints.length > MAX_INTS) {
				throw new ArrayIndexOutOfBoundsException(ints.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.asIntBuffer().put(ints);
			return toUUID(byteBuffer);
		}

		public static UUID toUUID(final long... longs) {
			if (longs == null) {
				return null;
			}
			if (longs.length > MAX_LONGS) {
				throw new ArrayIndexOutOfBoundsException(longs.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.asLongBuffer().put(longs);
			return toUUID(byteBuffer);
		}

		public static UUID toUUID(final short... shorts) {
			if (shorts == null) {
				return null;
			}
			if (shorts.length > MAX_SHORTS) {
				throw new ArrayIndexOutOfBoundsException(shorts.length);
			}
			final ByteBuffer byteBuffer = allocateByteBuffer();
			byteBuffer.asShortBuffer().put(shorts);
			return toUUID(byteBuffer);
		}

	}

	public static class DeterministicUUIDGenerator {

		private static final String MD5 = "MD5";
		private static final int BUFFER_SIZE = 8192;

		private DeterministicUUIDGenerator() {
			super();
		}

		public static UUID toUUID(final String s) {
			if (s == null) {
				return null;
			}
			return toUUID(s.getBytes(StandardCharsets.UTF_8));
		}

		public static UUID toUUID(final byte... bytes) {
			if (bytes == null) {
				return null;
			}
			final MessageDigest md = createDigest();
			md.update(bytes);
			return digest(md);
		}

		public static UUID toUUID(final ByteBuffer buffer) {
			if (buffer == null) {
				return null;
			}
			final MessageDigest md = createDigest();
			md.update(buffer);
			return digest(md);
		}

		private static UUID digest(final MessageDigest md) {
			final byte[] md5Bytes = md.digest();
			md5Bytes[6] &= 0x0f;
			md5Bytes[6] |= 0x30;
			md5Bytes[8] &= 0x3f;
			md5Bytes[8] |= 0x80;
			return toUUID(md5Bytes);
		}

		private static MessageDigest createDigest() {
			MessageDigest md;
			try {
				md = MessageDigest.getInstance(MD5);
			} catch (final NoSuchAlgorithmException e) {
				throw new InternalError(MD5 + " not supported", e);
			}
			return md;
		}

		public static UUID toUUID(final InputStream input) throws IOException {
			if (input == null) {
				return null;
			}
			final MessageDigest md = createDigest();
			try (final DigestInputStream dis = new DigestInputStream(input, md)) {
				final byte[] buffer = new byte[BUFFER_SIZE];
				while (dis.read(buffer) >= 0) {
				}
			}
			return digest(md);
		}

	}

}
