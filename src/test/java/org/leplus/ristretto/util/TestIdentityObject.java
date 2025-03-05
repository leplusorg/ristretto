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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

/**
 * Tests for the {@link IdentityObject} class.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class TestIdentityObject {

  /** Public constructor. */
  public TestIdentityObject() {
    super();
  }

  /** Checks that IdentityObject.IT is equal (==) to a shallow clone of itself. */
  @Test
  public void testEqualClone() {
    assertSame(IdentityObject.IT, IdentityObject.IT.clone());
  }

  /** Checks that IdentityObject.IT is equal (==) to a deep clone of itself. */
  @Test
  public void testEqualDeepClone() {
    assertSame(IdentityObject.IT, SerializationUtils.clone(IdentityObject.IT));
  }

  /** Checks that IdentityObject.IT equals() itself. */
  @Test
  public void testEquals() {
    assertEquals(IdentityObject.IT, IdentityObject.IT);
    assertNotEquals(IdentityObject.IT, new DuplicityObject());
  }

  /** Checks that IdentityObject.IT passes guava's equality tests. */
  @Test
  public void testEqualsGuava() {
    new EqualsTester().addEqualityGroup(IdentityObject.IT, IdentityObject.IT).testEquals();
  }

  /** Checks that IdentityObject.IT equals() a shallow clone of itself. */
  @Test
  public void testEqualsClone() {
    assertEquals(IdentityObject.IT, IdentityObject.IT.clone());
    assertEquals(IdentityObject.IT.clone(), IdentityObject.IT);
  }

  /** Checks that IdentityObject.IT equals() a deep clone of itself. */
  @Test
  public void testEqualsDeepClone() {
    assertEquals(IdentityObject.IT, SerializationUtils.clone(IdentityObject.IT));
    assertEquals(SerializationUtils.clone(IdentityObject.IT), IdentityObject.IT);
  }

  /** Checks IdentityObject.IT's hashcode remains constant. */
  @Test
  public void testHashCode() {
    assertEquals(IdentityObject.IT.hashCode(), IdentityObject.IT.hashCode());
    assertEquals(IdentityObject.IT.hashCode(), IdentityObject.IT.clone().hashCode());
    assertEquals(
        IdentityObject.IT.hashCode(), SerializationUtils.clone(IdentityObject.IT).hashCode());
  }

  /** Checks that IdentityObject.IT passes guava's serialization tests. */
  @Test
  public void testSerializeGuava() {
    SerializableTester.reserializeAndAssert(IdentityObject.IT);
  }
}
