package  org.leplus.util;

import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestIdentityHashSet {
	
	@Test
	public void testEmpty() {
		Set<Dumb> set = new IdentityHashSet<Dumb>();
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void testNotEmpty() {
		Set<Dumb> set = new IdentityHashSet<Dumb>();
		set.add(new Dumb());
		assertFalse(set.isEmpty());
		assertEquals(1, set.size());
	}
	
	@Test
	public void testTwoObjects() {
		Set<Dumb> set = new IdentityHashSet<Dumb>();
		set.add(new Dumb());
		set.add(new Dumb());
		assertFalse(set.isEmpty());
		assertEquals(2, set.size());
	}
	
	@Test
	public void testOneObject() {
		Set<Dumb> set = new IdentityHashSet<Dumb>();
		Dumb d = new Dumb();
		set.add(d);
		set.add(d);
		assertFalse(set.isEmpty());
		assertEquals(1, set.size());
	}
	
	@Test
	public void testEqualsTwoObjects() {
		Set<Dumb> set1 = new IdentityHashSet<Dumb>();
		set1.add(new Dumb());
		Set<Dumb> set2 = new IdentityHashSet<Dumb>();
		set2.add(new Dumb());
		assertFalse(set1.equals(set2));
	}
	
	@Test
	public void testEqualsOneObject() {
		Dumb d = new Dumb();
		Set<Dumb> set1 = new IdentityHashSet<Dumb>();
		set1.add(d);
		Set<Dumb> set2 = new IdentityHashSet<Dumb>();
		set2.add(d);
		assertEquals(set1, set2);
	}
	
	@Test
	public void testClone() {
		IdentityHashSet<Dumb> set1 = new IdentityHashSet<Dumb>();
		set1.add(new Dumb());
		@SuppressWarnings("unchecked")
		IdentityHashSet<Dumb> set2 = (IdentityHashSet<Dumb>) set1.clone();
		assertEquals(set1, set2);
		assertTrue(set1.iterator().next() == set2.iterator().next());
		set1.clear();
		assertEquals(0, set1.size());
		assertEquals(1, set2.size());
	}
	
}