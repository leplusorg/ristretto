package org.leplus.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class TestIdentityHashSet {

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
	public void testEmpty() {
		final Set<Dumb> set = new IdentityHashSet<Dumb>();
		assertTrue(set.isEmpty());
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
	}

	@Test
	public void testTwoObjects() {
		final Set<Dumb> set = new IdentityHashSet<Dumb>();
		set.add(new Dumb());
		set.add(new Dumb());
		assertFalse(set.isEmpty());
		assertEquals(2, set.size());
	}

}