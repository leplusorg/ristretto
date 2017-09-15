package org.leplus.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;

/**
 * Tests for the {@link org.leplus.util.IdentityObject} class.
 * 
 * @author Thomas Leplus
 * @since 1.0.0
 */
public class TestIdentityObject {

	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} is equal (==) to a shallow clone of itself.
	 */
	@Test
	public void testEqualClone() {
		assertTrue(IdentityObject.IT == IdentityObject.IT.clone());
	}

	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} is equal (==) to a deep clone of itself.
	 */
	@Test
	public void testEqualDeepClone() {
		assertTrue(IdentityObject.IT == SerializationUtils.clone(IdentityObject.IT));
	}

	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} equals() itself.
	 */
	@Test
	public void testEquals() {
		assertTrue(IdentityObject.IT.equals(IdentityObject.IT));
		assertFalse(IdentityObject.IT.equals(new DuplicityObject()));
	}

	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} passes guava's equality tests.
	 */
	@Test
	public void testEqualsGuava() {
		new EqualsTester().addEqualityGroup(IdentityObject.IT, IdentityObject.IT).testEquals();
	}
	
	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} equals() a shallow clone of itself.
	 */
	@Test
	public void testEqualsClone() {
		assertTrue(IdentityObject.IT.equals(IdentityObject.IT.clone()));
		assertTrue(IdentityObject.IT.clone().equals(IdentityObject.IT));
	}

	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} equals() a deep clone of itself.
	 */
	@Test
	public void testEqualsDeepClone() {
		assertTrue(IdentityObject.IT.equals(SerializationUtils.clone(IdentityObject.IT)));
		assertTrue(SerializationUtils.clone(IdentityObject.IT).equals(IdentityObject.IT));
	}

	/**
	 * Checks {@link org.leplus.util.IdentityObject.IT}'s hashcode remains constant.
	 */
	@Test
	public void testHashCode() {
		assertTrue(IdentityObject.IT.hashCode() == IdentityObject.IT.hashCode());
		assertTrue(IdentityObject.IT.hashCode() == IdentityObject.IT.clone().hashCode());
		assertTrue(IdentityObject.IT.hashCode() == SerializationUtils.clone(IdentityObject.IT).hashCode());
	}
	
	/**
	 * Checks that {@link org.leplus.util.IdentityObject.IT} passes guava's serialization tests.
	 */
	@Test
	public void testSerializeGuava() {
		SerializableTester.reserializeAndAssert(IdentityObject.IT);
	}
	
}