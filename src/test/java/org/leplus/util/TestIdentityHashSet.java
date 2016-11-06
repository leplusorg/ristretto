package org.leplus.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.google.common.collect.testing.SetTestSuiteBuilder;
import com.google.common.collect.testing.TestStringSetGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.SetFeature;
import com.google.common.collect.testing.testers.SetHashCodeTester;
import com.google.common.testing.EqualsTester;

import junit.framework.TestSuite;

/**
 * Tests the {@link org.leplus.util.IdentityHashSet} class.
 * 
 * @author Thomas Leplus
 * @since 1.0.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestIdentityHashSet.GuavaTests.class, TestIdentityHashSet.AdditionalTests.class })
public class TestIdentityHashSet {

	/**
	 * Some additional tests.
	 * 
	 * @author Thomas Leplus
	 * @since 1.0.0
	 */
	public static class AdditionalTests {

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.clear()}.
		 */
		@Test
		public void testClear() {
			final Set<DuplicityObject> set = new IdentityHashSet<DuplicityObject>();
			final DuplicityObject d = new DuplicityObject();
			set.add(d);
			assertFalse(set.isEmpty());
			assertEquals(1, set.size());
			set.clear();
			assertTrue(set.isEmpty());
			assertEquals(0, set.size());
		}

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.clone()}.
		 */
		@Test
		public void testClone() {
			final IdentityHashSet<DuplicityObject> set1 = new IdentityHashSet<DuplicityObject>();
			set1.add(new DuplicityObject());
			@SuppressWarnings("unchecked")
			final IdentityHashSet<DuplicityObject> set2 = (IdentityHashSet<DuplicityObject>) set1.clone();
			assertEquals(set1, set2);
			assertTrue(set1.iterator().next() == set2.iterator().next());
			set1.clear();
			assertEquals(0, set1.size());
			assertEquals(1, set2.size());
		}

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.equals()} with same object in  two set.
		 */
		@Test
		public void testEqualsOneObject() {
			final DuplicityObject d = new DuplicityObject();
			final Set<DuplicityObject> set1 = new IdentityHashSet<DuplicityObject>();
			set1.add(d);
			final Set<DuplicityObject> set2 = new IdentityHashSet<DuplicityObject>();
			set2.add(d);
			assertEquals(set1, set2);
		}

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.equals()} with different object in  two set.
		 */
		@Test
		public void testEqualsTwoObjects() {
			final Set<DuplicityObject> set1 = new IdentityHashSet<DuplicityObject>();
			set1.add(new DuplicityObject());
			final Set<DuplicityObject> set2 = new IdentityHashSet<DuplicityObject>();
			set2.add(new DuplicityObject());
			assertFalse(set1.equals(set2));
		}

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.hashCode()}.
		 */
		@Test
		public void testHashCode() {
			final DuplicityObject a = new DuplicityObject();
			final DuplicityObject b = new DuplicityObject();
			final DuplicityObject c = new DuplicityObject();
			final Set<DuplicityObject> set1 = new IdentityHashSet<DuplicityObject>(Arrays.asList(a, b, c));
			final Set<DuplicityObject> set2 = new IdentityHashSet<DuplicityObject>(Arrays.asList(b, c, a));
			assertEquals(set1.hashCode(), set2.hashCode());
		}

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.isEmpty()}.
		 */
		@Test
		public void testIsEmpty() {
			final Set<DuplicityObject> set = new IdentityHashSet<DuplicityObject>();
			assertTrue(set.isEmpty());
			assertEquals(0, set.size());
		}

		/**
		 * Tests {@link org.leplus.util.IdentityHashSet.size()}.
		 */
		@Test
		public void testNotEmpty() {
			final Set<DuplicityObject> set = new IdentityHashSet<DuplicityObject>();
			set.add(new DuplicityObject());
			assertFalse(set.isEmpty());
			assertEquals(1, set.size());
		}

		/**
		 * Various tests with single object.
		 */
		@Test
		public void testOneObject() {
			final Set<DuplicityObject> set = new IdentityHashSet<DuplicityObject>();
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

		/**
		 * Various tests with two different objects.
		 */
		@Test
		public void testTwoObjects() {
			final Set<DuplicityObject> set = new IdentityHashSet<DuplicityObject>();
			set.add(new DuplicityObject());
			set.add(new DuplicityObject());
			assertFalse(set.isEmpty());
			assertEquals(2, set.size());
			assertFalse(set.contains(new DuplicityObject()));
		}

		/**
		 * Various equality tests.
		 */
		@Test
		public void testEquals() {
			final DuplicityObject d = new DuplicityObject();
			new EqualsTester().addEqualityGroup(new IdentityHashSet<DuplicityObject>(0), new IdentityHashSet<DuplicityObject>()).testEquals();
			new EqualsTester().addEqualityGroup(new IdentityHashSet<DuplicityObject>(Arrays.asList(d)), new IdentityHashSet<DuplicityObject>(Arrays.asList(d))).testEquals();
			new EqualsTester().addEqualityGroup(new IdentityHashSet<DuplicityObject>(Arrays.asList(d, d, d)), new IdentityHashSet<DuplicityObject>(Arrays.asList(d, d, d))).testEquals();
		}
		
	}

	/**
	 * Guava collection testing.
	 * 
	 * @author Thomas Leplus
	 * @since 1.0.0
	 */
	public static class GuavaTests {

		/**
		 * Creates the test suite.
		 * 
		 * @return
		 */
		public static TestSuite suite() {
			return SetTestSuiteBuilder.using(new TestStringSetGenerator() {

				/* (non-Javadoc)
				 * @see com.google.common.collect.testing.TestStringSetGenerator#create(java.lang.String[])
				 */
				@Override
				protected Set<String> create(String[] elements) {
					return new IdentityHashSet<String>(Arrays.asList(elements));
				}

			}).named("IdentityHashSet tests")
					.withFeatures(
							SetFeature.GENERAL_PURPOSE,
							CollectionFeature.ALLOWS_NULL_QUERIES,
							CollectionFeature.ALLOWS_NULL_VALUES,
							CollectionFeature.DESCENDING_VIEW,
							CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION,
							CollectionFeature.GENERAL_PURPOSE,
							CollectionFeature.REMOVE_OPERATIONS,
							CollectionFeature.RESTRICTS_ELEMENTS,
							CollectionFeature.SUBSET_VIEW,
							CollectionFeature.SUPPORTS_ADD,
							CollectionFeature.SUPPORTS_ITERATOR_REMOVE,
							CollectionFeature.SUPPORTS_REMOVE,
							CollectionSize.ANY,
							CollectionSize.ONE,
							CollectionSize.SEVERAL,
							CollectionSize.ZERO
							)
					.suppressing(SetHashCodeTester.getHashCodeMethods())
					.createTestSuite();
		}

	}

}