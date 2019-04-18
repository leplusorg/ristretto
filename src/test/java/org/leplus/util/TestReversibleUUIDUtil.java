package org.leplus.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.security.SecureRandom;

import org.junit.Test;
import org.leplus.util.UUIDs.ReversibleUUIDGenerator;

public class TestUUIDs {

	private static final ReversibleUUIDGenerator REVERSIBLE = UUIDs.reversible();

	@Test
	public void testNullBytes() {
		assertNull(REVERSIBLE.toUUID((byte[]) null));
	}

	@Test
	public void testRandomBytes() {
		final SecureRandom r = new SecureRandom();
		for (int i = 0; i < REVERSIBLE.MAX_BYTES; i++) {
			final byte[] input = new byte[i];
			r.nextBytes(input);
			final byte[] output = REVERSIBLE.toBytes(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j]);
			}
			for (int j = i; j < REVERSIBLE.MAX_BYTES; j++) {
				assertEquals(0, output[j]);
			}
		}
	}

	@Test
	public void testRandomShorts() {
		for (int i = 0; i < REVERSIBLE.MAX_SHORTS; i++) {
			final short[] input = new short[i];
			for (int j = 0; j < i; j++) {
				input[j] = (short) (Math.random() * Short.MIN_VALUE);
			}
			final short[] output = REVERSIBLE.toShorts(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < REVERSIBLE.MAX_SHORTS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
	}

	@Test
	public void testRandomDoubles() {
		for (int i = 0; i < REVERSIBLE.MAX_DOUBLES; i++) {
			final double[] input = new double[i];
			for (int j = 0; j < i; j++) {
				input[j] = (long) (Math.random() * Long.MIN_VALUE);
			}
			final double[] output = REVERSIBLE.toDoubles(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < REVERSIBLE.MAX_DOUBLES; j++) {
				assertEquals(0, output[j], 0);
			}
		}
	}

	@Test
	public void testRandomFloats() {
		for (int i = 0; i < REVERSIBLE.MAX_FLOATS; i++) {
			final float[] input = new float[i];
			for (int j = 0; j < i; j++) {
				input[j] = (float) (Math.random() * Float.MIN_VALUE);
			}
			final float[] output = REVERSIBLE.toFloats(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < REVERSIBLE.MAX_FLOATS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
	}

	@Test
	public void testRandomChars() {
		for (int i = 0; i < REVERSIBLE.MAX_CHARS; i++) {
			final char[] input = new char[i];
			for (int j = 0; j < i; j++) {
				input[j] = (char) (Math.random() * Character.MIN_VALUE);
			}
			final char[] output = REVERSIBLE.toChars(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < REVERSIBLE.MAX_CHARS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
	}

	@Test
	public void testRandomInts() {
		for (int i = 0; i < REVERSIBLE.MAX_INTS; i++) {
			final int[] input = new int[i];
			for (int j = 0; j < i; j++) {
				input[j] = (int) (Math.random() * Integer.MIN_VALUE);
			}
			final int[] output = REVERSIBLE.toInts(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < REVERSIBLE.MAX_INTS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
	}

	@Test
	public void testRandomLongs() {
		for (int i = 0; i < REVERSIBLE.MAX_LONGS; i++) {
			final long[] input = new long[i];
			for (int j = 0; j < i; j++) {
				input[j] = (long) (Math.random() * Long.MIN_VALUE);
			}
			final long[] output = REVERSIBLE.toLongs(REVERSIBLE.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < REVERSIBLE.MAX_LONGS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
	}

}
