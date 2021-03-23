/*
 * Copyright 2016-present Thomas Leplus
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.leplus.ristretto.util;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * This class provides utility method to convert different primitives from/to UUID.
 * All the methods in this class are reversible.
 * While this is not a recommended way to generate UUIDs in general, it can be useful
 * in situations where you need to temporarily assign a UUID to an object based on
 * another unique ID.
 * 
 * For example if you need to convert a legacy object that has a integer or long ID
 * into a new object that uses UUIDs, you could just generate a new UUID
 * for the new object using {@link java.util.UUID#randomUUID()} but if you need to later link back
 * the legacy object from the new one, you might not have a place to store the legacy ID
 * on the new object. If you use this class's toUUID() methods to convert the legacy
 * object's ID into the new object's UUID, you will be able to later convert back
 * the UUID into the legacy ID. Provided the legacy IDs are unique (at least for the legacy
 * object type), the new UUIDs produced will be as unique.
 * 
 * Another use case could be to temporarily convert a legacy object into a new object
 * temporarily (for example for processing via a new method) and then need to convert
 * the result back into a legacy object. Then you can similarly use the methods in
 * this class to go back and forth between legacy IDs and UUIDs.
 * 
 * @author Thomas Leplus
 * @since 1.0.0
 */
public class UUIDConvertor {

	/**
	 * Number of bytes in a UUID.
	 */
	public static final int UUID_BYTES = 16;

	/**
	 * Maximum number of bytes that can be converted into a UUID.
	 */
	public static final int MAX_BYTES = UUID_BYTES / Byte.BYTES;
	
	/**
	 * Maximum number of shorts that can be converted into a UUID.
	 */
	public static final int MAX_SHORTS = UUID_BYTES / Short.BYTES;
	
	/**
	 * Maximum number of doubles that can be converted into a UUID.
	 */
	public static final int MAX_DOUBLES = UUID_BYTES / Double.BYTES;
	
	/**
	 * Maximum number of floats that can be converted into a UUID.
	 */
	public static final int MAX_FLOATS = UUID_BYTES / Float.BYTES;
	
	/**
	 * Maximum number of characters that can be converted into a UUID.
	 */
	public static final int MAX_CHARS = UUID_BYTES / Character.BYTES;
	
	/**
	 * Maximum number of integers that can be converted into a UUID.
	 */
	public static final int MAX_INTS = UUID_BYTES / Integer.BYTES;
	
	/**
	 * Maximum number of longs that can be converted into a UUID.
	 */
	public static final int MAX_LONGS = UUID_BYTES / Long.BYTES;

	private UUIDConvertor() {
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

	/**
	 * Converts a UUID into an array of {@value #MAX_BYTES} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static byte[] toBytes(final UUID uuid) {
		return toByteBuffer(uuid).array();
	}

	/**
	 * Converts a UUID into an array of {@value #MAX_CHARS} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static char[] toChars(final UUID uuid) {
		final ByteBuffer byteBuffer = toByteBuffer(uuid);
		final char[] result = new char[MAX_CHARS];
		for (int i = 0; i < result.length; i++) {
			result[i] = byteBuffer.getChar();
		}
		return result;
	}

	/**
	 * Converts a UUID into an array of {@value #MAX_DOUBLES} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static double[] toDoubles(final UUID uuid) {
		final ByteBuffer byteBuffer = toByteBuffer(uuid);
		final double[] result = new double[MAX_DOUBLES];
		for (int i = 0; i < result.length; i++) {
			result[i] = byteBuffer.getDouble();
		}
		return result;
	}

	/**
	 * Converts a UUID into an array of {@value #MAX_FLOATS} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static float[] toFloats(final UUID uuid) {
		final ByteBuffer byteBuffer = toByteBuffer(uuid);
		final float[] result = new float[MAX_FLOATS];
		for (int i = 0; i < result.length; i++) {
			result[i] = byteBuffer.getFloat();
		}
		return result;
	}

	/**
	 * Converts a UUID into an array of {@value #MAX_INTS} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static int[] toInts(final UUID uuid) {
		final ByteBuffer byteBuffer = toByteBuffer(uuid);
		final int[] result = new int[MAX_INTS];
		for (int i = 0; i < result.length; i++) {
			result[i] = byteBuffer.getInt();
		}
		return result;
	}

	/**
	 * Converts a UUID into an array of {@value #MAX_LONGS} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static long[] toLongs(final UUID uuid) {
		final ByteBuffer byteBuffer = toByteBuffer(uuid);
		final long[] result = new long[MAX_LONGS];
		for (int i = 0; i < result.length; i++) {
			result[i] = byteBuffer.getLong();
		}
		return result;
	}

	/**
	 * Converts a UUID into an array of {@value #MAX_SHORTS} bytes.
	 * 
	 * @param uuid the UUID to convert.
	 * @return the resulting array.
	 */
	public static short[] toShorts(final UUID uuid) {
		final ByteBuffer byteBuffer = toByteBuffer(uuid);
		final short[] result = new short[MAX_SHORTS];
		for (int i = 0; i < result.length; i++) {
			result[i] = byteBuffer.getShort();
		}
		return result;
	}

	/**
	 * Converts an array of up to {@value #MAX_BYTES} bytes into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param bytes the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_BYTES}.
	 */
	public static UUID toUUID(final byte... bytes) throws ArrayIndexOutOfBoundsException {
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

	/**
	 * Converts an array of up to {@value #MAX_CHARS} characters into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param chars the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_CHARS}.
	 */
	public static UUID toUUID(final char... chars) throws ArrayIndexOutOfBoundsException {
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

	/**
	 * Converts an array of up to {@value #MAX_DOUBLES} doubles into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param doubles the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_DOUBLES}.
	 */
	public static UUID toUUID(final double... doubles) throws ArrayIndexOutOfBoundsException {
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

	/**
	 * Converts an array of up to {@value #MAX_FLOATS} floats into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param floats the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_FLOATS}.
	 */
	public static UUID toUUID(final float... floats) throws ArrayIndexOutOfBoundsException {
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

	/**
	 * Converts an array of up to {@value #MAX_INTS} integers into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param ints the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_INTS}.
	 */
	public static UUID toUUID(final int... ints) throws ArrayIndexOutOfBoundsException {
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

	/**
	 * Converts an array of up to {@value #MAX_LONGS} longs into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param longs the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_LONGS}.
	 */
	public static UUID toUUID(final long... longs) throws ArrayIndexOutOfBoundsException {
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

	/**
	 * Converts an array of up to {@value #MAX_SHORTS} shorts into an UUID.
	 * If the array is shorter than the maximum length, it will be padded with 0s.
	 * If the array is longer than the maximum length, this method will throw an ArrayIndexOutOfBoundsException.
	 * 
	 * @param shorts the array to convert.
	 * @return the resulting UUID.
	 * @throws ArrayIndexOutOfBoundsException if the provided array is longer than {@value #MAX_SHORTS}.
	 */
	public static UUID toUUID(final short... shorts) throws ArrayIndexOutOfBoundsException {
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
