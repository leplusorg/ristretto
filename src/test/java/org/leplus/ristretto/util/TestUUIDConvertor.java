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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.security.SecureRandom;

import org.junit.Test;

public class TestUUIDConvertor {

	@Test
	public void testRandomBytes() {
		final SecureRandom r = new SecureRandom();
		for (int i = 0; i < UUIDConvertor.MAX_BYTES; i++) {
			final byte[] input = new byte[i];
			r.nextBytes(input);
			final byte[] output = UUIDConvertor.toBytes(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j]);
			}
			for (int j = i; j < UUIDConvertor.MAX_BYTES; j++) {
				assertEquals(0, output[j]);
			}
		}
		assertNull(UUIDConvertor.toUUID((byte[]) null));
	}

	@Test
	public void testRandomShorts() {
		for (int i = 0; i < UUIDConvertor.MAX_SHORTS; i++) {
			final short[] input = new short[i];
			for (int j = 0; j < i; j++) {
				input[j] = (short) (Math.random() * Short.MIN_VALUE);
			}
			final short[] output = UUIDConvertor.toShorts(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < UUIDConvertor.MAX_SHORTS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
		assertNull(UUIDConvertor.toUUID((short[]) null));
	}

	@Test
	public void testRandomDoubles() {
		for (int i = 0; i < UUIDConvertor.MAX_DOUBLES; i++) {
			final double[] input = new double[i];
			for (int j = 0; j < i; j++) {
				input[j] = (long) (Math.random() * Long.MIN_VALUE);
			}
			final double[] output = UUIDConvertor.toDoubles(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < UUIDConvertor.MAX_DOUBLES; j++) {
				assertEquals(0, output[j], 0);
			}
		}
		assertNull(UUIDConvertor.toUUID((double[]) null));
	}

	@Test
	public void testRandomFloats() {
		for (int i = 0; i < UUIDConvertor.MAX_FLOATS; i++) {
			final float[] input = new float[i];
			for (int j = 0; j < i; j++) {
				input[j] = (float) (Math.random() * Float.MIN_VALUE);
			}
			final float[] output = UUIDConvertor.toFloats(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < UUIDConvertor.MAX_FLOATS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
		assertNull(UUIDConvertor.toUUID((float[]) null));
	}

	@Test
	public void testRandomChars() {
		for (int i = 0; i < UUIDConvertor.MAX_CHARS; i++) {
			final char[] input = new char[i];
			for (int j = 0; j < i; j++) {
				input[j] = (char) (Math.random() * Character.MIN_VALUE);
			}
			final char[] output = UUIDConvertor.toChars(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < UUIDConvertor.MAX_CHARS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
		assertNull(UUIDConvertor.toUUID((char[]) null));
	}

	@Test
	public void testRandomInts() {
		for (int i = 0; i < UUIDConvertor.MAX_INTS; i++) {
			final int[] input = new int[i];
			for (int j = 0; j < i; j++) {
				input[j] = (int) (Math.random() * Integer.MIN_VALUE);
			}
			final int[] output = UUIDConvertor.toInts(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < UUIDConvertor.MAX_INTS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
		assertNull(UUIDConvertor.toUUID((int[]) null));
	}

	@Test
	public void testRandomLongs() {
		for (int i = 0; i < UUIDConvertor.MAX_LONGS; i++) {
			final long[] input = new long[i];
			for (int j = 0; j < i; j++) {
				input[j] = (long) (Math.random() * Long.MIN_VALUE);
			}
			final long[] output = UUIDConvertor.toLongs(UUIDConvertor.toUUID(input));
			for (int j = 0; j < i; j++) {
				assertEquals(input[j], output[j], 0);
			}
			for (int j = i; j < UUIDConvertor.MAX_LONGS; j++) {
				assertEquals(0, output[j], 0);
			}
		}
		assertNull(UUIDConvertor.toUUID((long[]) null));
	}

}
