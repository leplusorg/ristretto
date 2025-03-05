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

import java.util.Random;
import org.junit.Test;

/**
 * Tests for the {@link org.leplus.ristretto.util.UUIDConvertor} class.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class TestUUIDConvertor {

  /** Default constructor. */
  public TestUUIDConvertor() {
    super();
  }

  /** The random number generator. */
  private static final Random PRNG = new Random();

  /** Test. */
  @Test
  public void testRandomBytes() {
    for (int i = 0; i < UUIDConvertor.MAX_BYTES; i++) {
      final byte[] input = new byte[i];
      PRNG.nextBytes(input);
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

  /** Test. */
  @Test
  public void testRandomShorts() {
    for (int i = 0; i < UUIDConvertor.MAX_SHORTS; i++) {
      final short[] input = new short[i];
      for (int j = 0; j < i; j++) {
        input[j] = (short) PRNG.nextInt(Short.MAX_VALUE + 1);
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

  /** Test. */
  @Test
  public void testRandomDoubles() {
    for (int i = 0; i < UUIDConvertor.MAX_DOUBLES; i++) {
      final double[] input = new double[i];
      for (int j = 0; j < i; j++) {
        input[j] = PRNG.nextLong();
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

  /** Test. */
  @Test
  public void testRandomFloats() {
    for (int i = 0; i < UUIDConvertor.MAX_FLOATS; i++) {
      final float[] input = new float[i];
      for (int j = 0; j < i; j++) {
        input[j] = PRNG.nextFloat();
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

  /** Test. */
  @Test
  public void testRandomChars() {
    for (int i = 0; i < UUIDConvertor.MAX_CHARS; i++) {
      final char[] input = new char[i];
      for (int j = 0; j < i; j++) {
        input[j] = (char) PRNG.nextInt(Character.MAX_VALUE + 1);
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

  /** Test. */
  @Test
  public void testRandomInts() {
    for (int i = 0; i < UUIDConvertor.MAX_INTS; i++) {
      final int[] input = new int[i];
      for (int j = 0; j < i; j++) {
        input[j] = PRNG.nextInt();
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

  /** Test. */
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

  /** Test. */
  @Test
  public void testRandomStrings() {
    for (int i = 0; i < UUIDConvertor.MAX_CHARS; i++) {
      final char[] chars = new char[i];
      for (int j = 0; j < i; j++) {
        chars[j] = (char) PRNG.nextInt(Character.MAX_VALUE + 1);
      }
      final String input = String.valueOf(chars);
      final String output = UUIDConvertor.toString(UUIDConvertor.toUUID(input));
      final char[] padding = new char[UUIDConvertor.MAX_CHARS - i];
      assertEquals(input + String.valueOf(padding), output);
    }
    assertNull(UUIDConvertor.toUUID((String) null));
  }
}
