/*
 * Copyright 2016-present Thomas Leplus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.leplus.ristretto.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

/**
 * Tests for the {@link IdentityEnum} enum.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class TestIdentityEnum {

  /** Default constructor. */
  public TestIdentityEnum() {
    super();
  }

  /** Checks that IdentityEnum.IT is equal (==) to a deep clone of itself. */
  @Test
  public void testEqualDeepClone() {
    assertTrue(IdentityEnum.IT == SerializationUtils.clone(IdentityEnum.IT));
  }

  /** Checks that IdentityEnum.IT equals() itself. */
  @Test
  public void testEquals() {
    assertEquals(IdentityEnum.IT, IdentityEnum.IT);
    assertNotEquals(IdentityEnum.IT, new DuplicityObject());
  }

  /** Checks that IdentityEnum.IT equals() a deep clone of itself. */
  @Test
  public void testEqualsDeepClone() {
    assertSame(IdentityEnum.IT, SerializationUtils.clone(IdentityEnum.IT));
    assertSame(SerializationUtils.clone(IdentityEnum.IT), IdentityEnum.IT);
  }

  /** Checks that IdentityEnum.IT passes guava's equality tests. */
  @Test
  public void testEqualsGuava() {
    new EqualsTester().addEqualityGroup(IdentityEnum.IT, IdentityEnum.IT).testEquals();
  }

  /** Checks IdentityEnum.IT's hashcode remains constant. */
  @Test
  public void testHashCode() {
    assertEquals(IdentityEnum.IT.hashCode(), IdentityEnum.IT.hashCode());
    assertEquals(IdentityEnum.IT.hashCode(), SerializationUtils.clone(IdentityEnum.IT).hashCode());
  }

  /** Checks that IdentityEnum.IT passes guava's serialization tests. */
  @Test
  public void testSerializeGuava() {
    SerializableTester.reserializeAndAssert(IdentityEnum.IT);
  }
}
