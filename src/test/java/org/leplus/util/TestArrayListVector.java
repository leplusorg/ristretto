package org.leplus.util;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

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

import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestArrayListVector.GuavaTests.class, TestArrayListVector.AdditionalTests.class, })
public class TestArrayListVector {

	public static class AdditionalTests {

		@Test
		public void test() {
			final Vector<String> actual = new ArrayListVector<String>(Arrays.asList("a", "b", "c"));
			final Vector<String> expected = new ArrayListVector<String>(Arrays.asList("a", "b", "c"));
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
			clone.clear();
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

	}

	public static class GuavaTests {

		public static TestSuite suite() {
			return ListTestSuiteBuilder.using(new TestStringListGenerator() {

				@Override
				protected List<String> create(String[] elements) {
					return new ArrayListVector<String>(Arrays.asList(elements));
				}

			}).named("ArrayListVector tests")
					.withFeatures(
							ListFeature.GENERAL_PURPOSE,
							ListFeature.REMOVE_OPERATIONS,
							ListFeature.SUPPORTS_ADD_WITH_INDEX,
							ListFeature.SUPPORTS_REMOVE_WITH_INDEX,
							ListFeature.SUPPORTS_SET,
							CollectionFeature.ALLOWS_NULL_QUERIES,
							CollectionFeature.ALLOWS_NULL_VALUES,
							CollectionFeature.DESCENDING_VIEW,
							CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION,
							CollectionFeature.GENERAL_PURPOSE,
							CollectionFeature.KNOWN_ORDER,
							CollectionFeature.REJECTS_DUPLICATES_AT_CREATION,
							CollectionFeature.REMOVE_OPERATIONS,
							CollectionFeature.RESTRICTS_ELEMENTS,
							CollectionFeature.SERIALIZABLE,
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
