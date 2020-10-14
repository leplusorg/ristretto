package org.leplus.util;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.TestStringListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;

import junit.framework.TestSuite;

/**
 * Tests the {@link org.leplus.util.ArrayListVector} class (and therefore the
 * {@link org.leplus.util.VectorAdapter} class too).
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestArrayListVector.GuavaTests.class, TestArrayListVector.VectorCompatibilityTests.class,
		TestArrayListVector.AdditionalTests.class })
public class TestArrayListVector {

	private TestArrayListVector() {
		super();
	}

	/**
	 * Some additional tests.
	 *
	 * @author Thomas Leplus
	 * @since 1.0.0
	 */
	public static class AdditionalTests {

		/**
		 * A bunch of tests.
		 */
		@Test
		public void test() {
			final Vector<String> actual = new ArrayListVector<>(Arrays.asList("a", "b", "c"));
			final Vector<String> expected = new ArrayListVector<>(Arrays.asList("a", "b", "c"));
			assertThat(actual, is(expected));
			assertThat(actual.hashCode(), is(expected.hashCode()));
			assertThat(actual, hasItems("b"));
			assertThat(actual, hasSize(3));
			assertThat(actual.size(), is(3));
			assertThat(actual, contains("a", "b", "c"));
			assertThat(actual, containsInAnyOrder("c", "b", "a"));
			assertThat(actual, not(IsEmptyCollection.empty()));
			assertThat(new ArrayListVector<String>(), IsEmptyCollection.empty());
			@SuppressWarnings("unchecked")
			final Vector<String> clone = (Vector<String>) actual.clone();
			assertThat(clone, is(expected));
			assertThat(clone.hashCode(), is(expected.hashCode()));
			assertThat(clone, hasItems("b"));
			assertThat(clone, hasSize(3));
			assertThat(clone.size(), is(3));
			assertThat(clone, contains("a", "b", "c"));
			assertThat(clone, containsInAnyOrder("c", "b", "a"));
			assertThat(clone, not(IsEmptyCollection.empty()));
			clone.remove("a");
			assertThat(clone, not(hasItems("a")));
			assertThat(clone, hasItems("b"));
			assertThat(clone, hasSize(2));
			assertThat(clone.size(), is(2));
			assertThat(clone, contains("b", "c"));
			assertThat(clone, containsInAnyOrder("c", "b"));
			assertThat(clone, not(IsEmptyCollection.empty()));
			assertThat(actual, is(expected));
			assertThat(actual, hasItems("b"));
			assertThat(actual, hasSize(3));
			assertThat(actual.size(), is(3));
			assertThat(actual, contains("a", "b", "c"));
			assertThat(actual, containsInAnyOrder("c", "b", "a"));
			assertThat(actual, not(IsEmptyCollection.empty()));
			assertThat(clone.elementAt(0), is("b"));
			assertThat(clone.firstElement(), is("b"));
			assertThat(clone.elements().nextElement(), is("b"));
			clone.insertElementAt("a", 0);
			assertThat(clone, is(expected));
			clone.addElement("d");
			assertThat(clone.lastElement(), is("d"));
			assertThat(clone, hasSize(4));
			assertThat(clone.size(), is(4));
			assertThat(clone.indexOf("d", 1), is(3));
			assertThat(clone.lastIndexOf("b", 3), is(1));
			clone.removeElementAt(3);
			assertThat(clone, is(expected));
			clone.removeElement("c");
			assertThat(clone, hasSize(2));
			assertThat(clone.size(), is(2));
			clone.setSize(3);
			assertThat(clone, hasSize(3));
			assertThat(clone.size(), is(3));
			assertThat(clone.elementAt(2), nullValue());
			clone.insertElementAt("d", 3);
			assertThat(clone, hasSize(4));
			assertThat(clone.size(), is(4));
			assertThat(clone.elementAt(3), is("d"));
			clone.setElementAt("c", 2);
			final String[] values = new String[3];
			clone.copyInto(values);
			clone.ensureCapacity(5);
			clone.capacity(); // Incorrect so no test
			clone.trimToSize();
			clone.capacity(); // Incorrect so no test
			clone.clear();
			assertThat(clone, hasSize(0));
			assertThat(clone.size(), is(0));
			clone.addAll(Arrays.asList(values));
			assertThat(clone, hasSize(3));
			assertThat(clone.size(), is(3));
			clone.removeAllElements();
			assertThat(clone, hasSize(0));
			assertThat(clone.size(), is(0));
			assertThat(clone, not(contains("a")));
			assertThat(clone, IsEmptyCollection.empty());
			assertThat(actual, is(expected));
			assertThat(actual, hasItems("b"));
			assertThat(actual, hasSize(3));
			assertThat(actual.size(), is(3));
			assertThat(actual, contains("a", "b", "c"));
			assertThat(actual, containsInAnyOrder("c", "b", "a"));
			assertThat(actual, not(IsEmptyCollection.empty()));
		}

		/**
		 * Extra test specifically for
		 * {@link org.leplus.util.ArrayListVector.removeRange(int, int)}
		 */
		@Test
		public void testRemoveRange() {
			final ArrayListVector<String> v = new ArrayListVector<String>(Arrays.asList("a", "b", "c", "d")) {

				private static final long serialVersionUID = 1L;

				/*
				 * (non-Javadoc)
				 * 
				 * @see org.leplus.util.VectorAdapter#removeRange(int, int)
				 */
				@Override
				public void removeRange(final int fromIndex, final int toIndex) {
					super.removeRange(fromIndex, toIndex);
				}

			};
			v.removeRange(1, 3);
			assertThat(v.size(), is(2));
			assertThat(v, contains("a", "d"));
		}

		/**
		 * Extra equality tests.
		 */
		@Test
		public void testEquals() {
			new EqualsTester().addEqualityGroup(new ArrayListVector<String>(0), new ArrayListVector<String>())
					.testEquals();
			new EqualsTester().addEqualityGroup(new ArrayListVector<>(Arrays.asList("")),
					new ArrayListVector<>(Arrays.asList(""))).testEquals();
			new EqualsTester().addEqualityGroup(new ArrayListVector<>(Arrays.asList("x", "y", "z")),
					new ArrayListVector<>(Arrays.asList("x", "y", "z"))).testEquals();
		}

		/**
		 * Extra serialization tests.
		 */
		@Test
		public void testSerialize() {
			SerializableTester.reserializeAndAssert(new ArrayListVector<String>());
			SerializableTester.reserializeAndAssert(new ArrayListVector<>(Arrays.asList("")));
			SerializableTester.reserializeAndAssert(new ArrayListVector<>(Arrays.asList("x", "y", "z")));
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
		 * @return a test suite.
		 */
		public static TestSuite suite() {
			return ListTestSuiteBuilder.using(new TestStringListGenerator() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * com.google.common.collect.testing.TestStringListGenerator#create(java.lang.
				 * String[])
				 */
				@Override
				protected List<String> create(final String[] elements) {
					return new ArrayListVector<>(Arrays.asList(elements));
				}

			}).named("ArrayListVector tests").withFeatures(ListFeature.GENERAL_PURPOSE, ListFeature.REMOVE_OPERATIONS,
					ListFeature.SUPPORTS_ADD_WITH_INDEX, ListFeature.SUPPORTS_REMOVE_WITH_INDEX,
					ListFeature.SUPPORTS_SET, CollectionFeature.ALLOWS_NULL_QUERIES,
					CollectionFeature.ALLOWS_NULL_VALUES, CollectionFeature.DESCENDING_VIEW,
					CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION, CollectionFeature.GENERAL_PURPOSE,
					CollectionFeature.KNOWN_ORDER, CollectionFeature.REJECTS_DUPLICATES_AT_CREATION,
					CollectionFeature.REMOVE_OPERATIONS, CollectionFeature.RESTRICTS_ELEMENTS,
					CollectionFeature.SERIALIZABLE, CollectionFeature.SUBSET_VIEW, CollectionFeature.SUPPORTS_ADD,
					CollectionFeature.SUPPORTS_ITERATOR_REMOVE, CollectionFeature.SUPPORTS_REMOVE, CollectionSize.ANY,
					CollectionSize.ONE, CollectionSize.SEVERAL, CollectionSize.ZERO).createTestSuite();
		}

	}

	/**
	 * Tests for compatibility with Vector.
	 *
	 * @author Thomas Leplus
	 * @since 1.0.0
	 */
	public static class VectorCompatibilityTests {

		/**
		 * Test equality between a Vector and an ArrayListVector.
		 */
		@Test
		public void testEquals() {
			final Vector<String> v = new Vector<>(Arrays.asList("a", "b", "c"));
			final ArrayListVector<String> a = new ArrayListVector<>(Arrays.asList("a", "b", "c"));
			assertEquals(v, a);
			assertEquals(a, v);
			new EqualsTester().addEqualityGroup(v, a).testEquals();
		}

		/**
		 * Test equality between empty Vector and ArrayListVector.
		 */
		@Test
		public void testEqualsEmpty() {
			final Vector<String> v = new Vector<>();
			final ArrayListVector<String> a = new ArrayListVector<>();
			assertEquals(v, a);
			assertEquals(a, v);
			new EqualsTester().addEqualityGroup(v, a).testEquals();
		}

		/**
		 * Test hashcode equality between a Vector and an ArrayListVector.
		 */
		@Test
		public void testHashCode() {
			final Vector<String> v = new Vector<>(Arrays.asList("a", "b", "c"));
			final ArrayListVector<String> a = new ArrayListVector<>(Arrays.asList("a", "b", "c"));
			assertThat(v.hashCode(), is(a.hashCode()));
		}

		/**
		 * Test hashcode equality between empty Vector and ArrayListVector.
		 */
		@Test
		public void testHashCodeEmpty() {
			final Vector<String> v = new Vector<>();
			final ArrayListVector<String> a = new ArrayListVector<>();
			assertEquals(v, a);
			assertThat(v.hashCode(), is(a.hashCode()));
		}

	}

}
