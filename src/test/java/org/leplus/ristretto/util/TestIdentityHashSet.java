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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.testing.SetTestSuiteBuilder;
import com.google.common.collect.testing.TestStringSetGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.SetFeature;
import com.google.common.testing.EqualsTester;
import java.util.Arrays;
import java.util.Set;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests the {@link org.leplus.ristretto.util.IdentityHashSet} class.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  TestIdentityHashSet.GuavaTests.class,
  TestIdentityHashSet.AdditionalTests.class
})
public final class TestIdentityHashSet {

  /** Default constructor. */
  public TestIdentityHashSet() {
    super();
  }

  /**
   * Some additional tests.
   *
   * @author Thomas Leplus
   * @since 1.0.0
   */
  public static class AdditionalTests {

    /** Default constructor. */
    public AdditionalTests() {
      super();
    }

    /** Tests {@link IdentityHashSet#clear()}. */
    @Test
    public void testClear() {
      final Set<DuplicityObject> set = new IdentityHashSet<>();
      final DuplicityObject d = new DuplicityObject();
      set.add(d);
      assertFalse(set.isEmpty());
      assertEquals(1, set.size());
      set.clear();
      assertTrue(set.isEmpty());
      assertEquals(0, set.size());
    }

    /** Tests {@link IdentityHashSet#clone()}. */
    @Test
    public void testClone() {
      final IdentityHashSet<DuplicityObject> set1 = new IdentityHashSet<>();
      set1.add(new DuplicityObject());
      @SuppressWarnings("unchecked")
      final IdentityHashSet<DuplicityObject> set2 = (IdentityHashSet<DuplicityObject>) set1.clone();
      assertEquals(set1, set2);
      assertSame(set1.iterator().next(), set2.iterator().next());
      set1.clear();
      assertEquals(0, set1.size());
      assertEquals(1, set2.size());
    }

    /** Tests {@link IdentityHashSet#equals(Object)} with same object in two sets. */
    @Test
    public void testEqualsOneObject() {
      final DuplicityObject d = new DuplicityObject();
      final Set<DuplicityObject> set1 = new IdentityHashSet<>();
      set1.add(d);
      final Set<DuplicityObject> set2 = new IdentityHashSet<>();
      set2.add(d);
      assertEquals(set1, set2);
    }

    /** Tests {@link IdentityHashSet#equals(Object)} with different object in two sets. */
    @Test
    public void testEqualsTwoObjects() {
      final Set<DuplicityObject> set1 = new IdentityHashSet<>();
      set1.add(new DuplicityObject());
      final Set<DuplicityObject> set2 = new IdentityHashSet<>();
      set2.add(new DuplicityObject());
      assertNotEquals(set1, set2);
    }

    /** Tests {@link IdentityHashSet#hashCode()}. */
    @Test
    public void testHashCode() {
      final DuplicityObject a = new DuplicityObject();
      final DuplicityObject b = new DuplicityObject();
      final DuplicityObject c = new DuplicityObject();
      final Set<DuplicityObject> set1 = new IdentityHashSet<>(Arrays.asList(a, b, c));
      final Set<DuplicityObject> set2 = new IdentityHashSet<>(Arrays.asList(b, c, a));
      assertEquals(set1.hashCode(), set2.hashCode());
    }

    /** Tests {@link IdentityHashSet#isEmpty()}. */
    @Test
    public void testIsEmpty() {
      final Set<DuplicityObject> set = new IdentityHashSet<>();
      assertTrue(set.isEmpty());
      assertEquals(0, set.size());
    }

    /** Tests {@link IdentityHashSet#size()}. */
    @Test
    public void testNotEmpty() {
      final Set<DuplicityObject> set = new IdentityHashSet<>();
      set.add(new DuplicityObject());
      assertFalse(set.isEmpty());
      assertEquals(1, set.size());
    }

    /** Various tests with single object. */
    @Test
    public void testOneObject() {
      final Set<DuplicityObject> set = new IdentityHashSet<>();
      final DuplicityObject d = new DuplicityObject();
      set.add(d);
      set.add(d);
      assertFalse(set.isEmpty());
      assertEquals(1, set.size());
      assertTrue(set.contains(d));
      assertFalse(set.contains(new DuplicityObject()));
      set.remove(d);
      assertFalse(set.contains(d));
      assertTrue(set.isEmpty());
      assertEquals(0, set.size());
    }

    /** Various tests with two different objects. */
    @Test
    public void testTwoObjects() {
      final Set<DuplicityObject> set = new IdentityHashSet<>();
      set.add(new DuplicityObject());
      set.add(new DuplicityObject());
      assertFalse(set.isEmpty());
      assertEquals(2, set.size());
      assertFalse(set.contains(new DuplicityObject()));
    }

    /** Various equality tests. */
    @Test
    public void testEquals() {
      final DuplicityObject d = new DuplicityObject();
      new EqualsTester()
          .addEqualityGroup(
              new IdentityHashSet<DuplicityObject>(0), new IdentityHashSet<DuplicityObject>())
          .testEquals();
      new EqualsTester()
          .addEqualityGroup(
              new IdentityHashSet<>(Arrays.asList(d)), new IdentityHashSet<>(Arrays.asList(d)))
          .testEquals();
      new EqualsTester()
          .addEqualityGroup(
              new IdentityHashSet<>(Arrays.asList(d, d, d)),
              new IdentityHashSet<>(Arrays.asList(d, d, d)))
          .testEquals();
    }
  }

  /**
   * Guava collection testing.
   *
   * @author Thomas Leplus
   * @since 1.0.0
   */
  public static final class GuavaTests {

    /** Private constructor. */
    private GuavaTests() {
      super();
    }

    /**
     * Creates the test suite.
     *
     * @return the test suite
     */
    public static TestSuite suite() {
      return SetTestSuiteBuilder.using(
              new TestStringSetGenerator() {

                /**
                 * @see com.google.common.collect.testing. TestStringSetGenerator#create(java.lang.
                 *     String[])
                 */
                @Override
                protected Set<String> create(final String[] elements) {
                  return new IdentityHashSet<>(Arrays.asList(elements));
                }
              })
          .named("IdentityHashSet tests")
          .withFeatures(
              SetFeature.GENERAL_PURPOSE,
              CollectionFeature.ALLOWS_NULL_QUERIES,
              CollectionFeature.ALLOWS_NULL_VALUES,
              CollectionFeature.DESCENDING_VIEW,
              CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION,
              CollectionFeature.GENERAL_PURPOSE,
              // Sets are not ordered
              // CollectionFeature.KNOWN_ORDER,
              // Duplicates used in this test are not ==
              // CollectionFeature.REJECTS_DUPLICATES_AT_CREATION,
              CollectionFeature.REMOVE_OPERATIONS,
              CollectionFeature.RESTRICTS_ELEMENTS,
              // Sets are not == after serialization
              // CollectionFeature.SERIALIZABLE,
              // Sets are not == after serialization
              // CollectionFeature.SERIALIZABLE_INCLUDING_VIEWS,
              CollectionFeature.SUBSET_VIEW,
              CollectionFeature.SUPPORTS_ADD,
              CollectionFeature.SUPPORTS_ITERATOR_REMOVE,
              CollectionFeature.SUPPORTS_REMOVE,
              CollectionSize.ANY,
              CollectionSize.ONE,
              CollectionSize.SEVERAL,
              CollectionSize.ZERO)
          .createTestSuite();
    }
  }
}
