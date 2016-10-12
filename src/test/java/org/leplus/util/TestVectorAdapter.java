package org.leplus.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

public class TestVectorAdapter {

	@Test
	public void test() {
		Vector<String> actual = new VectorAdapter<String>(Arrays.asList("a", "b", "c"));
		Vector<String> expected = new VectorAdapter<String>(Arrays.asList("a", "b", "c"));
		assertThat(actual, is(expected));
		assertThat(actual, hasItems("b"));
		assertThat(actual, hasSize(3));
		assertThat(actual.size(), is(3));
		assertThat(actual, contains("a", "b", "c"));
		assertThat(actual, containsInAnyOrder("c", "b", "a"));
		assertThat(actual, not(IsEmptyCollection.empty()));
		assertThat(new VectorAdapter<String>(new ArrayList<String>()), IsEmptyCollection.empty());
		@SuppressWarnings("unchecked")
		Vector<String> clone = (Vector<String>) actual.clone();
		assertThat(clone, is(expected));
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
