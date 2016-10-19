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

import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestIdentityHashSet.GuavaTests.class, TestIdentityHashSet.AdditionalTests.class, })
public class TestIdentityHashSet {

	public static class AdditionalTests {
	
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
	
		@Test
		public void testEqualsOneObject() {
			final Dumb d = new Dumb();
			final Set<Dumb> set1 = new IdentityHashSet<Dumb>();
			set1.add(d);
			final Set<Dumb> set2 = new IdentityHashSet<Dumb>();
			set2.add(d);
			assertEquals(set1, set2);
		}
	
		@Test
		public void testEqualsTwoObjects() {
			final Set<Dumb> set1 = new IdentityHashSet<Dumb>();
			set1.add(new Dumb());
			final Set<Dumb> set2 = new IdentityHashSet<Dumb>();
			set2.add(new Dumb());
			assertFalse(set1.equals(set2));
		}
	
		@Test
		public void testIsEmpty() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			assertTrue(set.isEmpty());
		}
	
		@Test
		public void testNotEmpty() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			set.add(new Dumb());
			assertFalse(set.isEmpty());
			assertEquals(1, set.size());
		}
	
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
	
		@Test
		public void testTwoObjects() {
			final Set<Dumb> set = new IdentityHashSet<Dumb>();
			set.add(new Dumb());
			set.add(new Dumb());
			assertFalse(set.isEmpty());
			assertEquals(2, set.size());
			assertFalse(set.contains(new Dumb()));
		}
		
	}

	public static class GuavaTests {

		public static TestSuite suite() {
			return SetTestSuiteBuilder.using(new TestStringSetGenerator() {

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
							CollectionFeature.KNOWN_ORDER,
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
					.createTestSuite();
		}

	}
	
}