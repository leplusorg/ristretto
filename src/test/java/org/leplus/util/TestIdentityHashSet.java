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
 * @author Thomas Leplus
 * @since 1.0.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestIdentityHashSet.GuavaTests.class, TestIdentityHashSet.AdditionalTests.class })
public class TestIdentityHashSet {

	/**
	 * @author Thomas Leplus
	 * @since 1.0.0
	 */
	public static class AdditionalTests {

		/**
		 * 
		 */
		@Test
		public void testClear() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			final Dumb d = new Dumb();
			set.add(d);
			assertFalse(set.isEmpty());
			assertEquals(1, set.size());
			set.clear();
			assertTrue(set.isEmpty());
			assertEquals(0, set.size());
		}

		/**
		 * 
		 */
		@Test
		public void testClone() {
			final IdentityHashSet<Dumb> set1 = new IdentityHashSet<Dumb>();
			set1.add(new Dumb());
			@SuppressWarnings("unchecked")
			final IdentityHashSet<Dumb> set2 = (IdentityHashSet<Dumb>) set1.clone();
			assertEquals(set1, set2);
			assertTrue(set1.iterator().next() == set2.iterator().next());
			set1.clear();
			assertEquals(0, set1.size());
			assertEquals(1, set2.size());
		}

		/**
		 * 
		 */
		@Test
		public void testEqualsOneObject() {
			final Dumb d = new Dumb();
			final Set<Dumb> set1 = new IdentityHashSet<Dumb>();
			set1.add(d);
			final Set<Dumb> set2 = new IdentityHashSet<Dumb>();
			set2.add(d);
			assertEquals(set1, set2);
		}

		/**
		 * 
		 */
		@Test
		public void testEqualsTwoObjects() {
			final Set<Dumb> set1 = new IdentityHashSet<Dumb>();
			set1.add(new Dumb());
			final Set<Dumb> set2 = new IdentityHashSet<Dumb>();
			set2.add(new Dumb());
			assertFalse(set1.equals(set2));
		}

		/**
		 * 
		 */
		@Test
		public void testHashCode() {
			final Dumb a = new Dumb();
			final Dumb b = new Dumb();
			final Dumb c = new Dumb();
			final Set<Dumb> set1 = new IdentityHashSet<Dumb>(Arrays.asList(a, b, c));
			final Set<Dumb> set2 = new IdentityHashSet<Dumb>(Arrays.asList(b, c, a));
			assertEquals(set1.hashCode(), set2.hashCode());
		}

		/**
		 * 
		 */
		@Test
		public void testIsEmpty() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			assertTrue(set.isEmpty());
		}

		/**
		 * 
		 */
		@Test
		public void testNotEmpty() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			set.add(new Dumb());
			assertFalse(set.isEmpty());
			assertEquals(1, set.size());
		}

		/**
		 * 
		 */
		@Test
		public void testOneObject() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			final Dumb d = new Dumb();
			set.add(d);
			set.add(d);
			assertFalse(set.isEmpty());
			assertEquals(1, set.size());
			assertTrue(set.contains(d));
			assertFalse(set.contains(new Dumb()));
			set.remove(d);
			assertFalse(set.contains(d));
			assertTrue(set.isEmpty());
			assertEquals(0, set.size());
		}

		/**
		 * 
		 */
		@Test
		public void testTwoObjects() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			set.add(new Dumb());
			set.add(new Dumb());
			assertFalse(set.isEmpty());
			assertEquals(2, set.size());
			assertFalse(set.contains(new Dumb()));
		}

		/**
		 * 
		 */
		@Test
		public void testEquals() {
			final Dumb d = new Dumb();
			new EqualsTester().addEqualityGroup(new IdentityHashSet<Dumb>(0), new IdentityHashSet<Dumb>()).testEquals();
			new EqualsTester().addEqualityGroup(new IdentityHashSet<Dumb>(Arrays.asList(d)), new IdentityHashSet<Dumb>(Arrays.asList(d))).testEquals();
			new EqualsTester().addEqualityGroup(new IdentityHashSet<Dumb>(Arrays.asList(d, d, d)), new IdentityHashSet<Dumb>(Arrays.asList(d, d, d))).testEquals();
		}
		
	}

	/**
	 * @author Thomas Leplus
	 * @since 1.0.0
	 */
	public static class GuavaTests {

		/**
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