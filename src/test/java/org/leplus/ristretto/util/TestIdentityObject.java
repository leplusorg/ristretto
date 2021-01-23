package org.leplus.ristretto.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;

/**
 * Tests for the {@link org.leplus.ristretto.util.IdentityObject} class.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public class TestIdentityObject {

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} is equal (==) to a
	 * shallow clone of itself.
	 */
	@Test
	public void testEqualClone() {
		assertSame(IdentityObject.IT, IdentityObject.IT.clone());
	}

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} is equal (==) to a deep
	 * clone of itself.
	 */
	@Test
	public void testEqualDeepClone() {
		assertSame(IdentityObject.IT, SerializationUtils.clone(IdentityObject.IT));
	}

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} equals() itself.
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEquals() {
		assertEquals(IdentityObject.IT, IdentityObject.IT);
		assertNotEquals(IdentityObject.IT, new DuplicityObject());
	}

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} passes guava's equality
	 * tests.
	 */
	@Test
	public void testEqualsGuava() {
		new EqualsTester().addEqualityGroup(IdentityObject.IT, IdentityObject.IT).testEquals();
	}

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} equals() a shallow
	 * clone of itself.
	 */
	@Test
	public void testEqualsClone() {
		assertEquals(IdentityObject.IT, IdentityObject.IT.clone());
		assertEquals(IdentityObject.IT.clone(), IdentityObject.IT);
	}

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} equals() a deep clone
	 * of itself.
	 */
	@Test
	public void testEqualsDeepClone() {
		assertEquals(IdentityObject.IT, SerializationUtils.clone(IdentityObject.IT));
		assertEquals(SerializationUtils.clone(IdentityObject.IT), IdentityObject.IT);
	}

	/**
	 * Checks {@link org.leplus.ristretto.util.IdentityObject.IT}'s hashcode remains constant.
	 */
	@Test
	public void testHashCode() {
		assertEquals(IdentityObject.IT.hashCode(), IdentityObject.IT.hashCode());
		assertEquals(IdentityObject.IT.hashCode(), IdentityObject.IT.clone().hashCode());
		assertEquals(IdentityObject.IT.hashCode(), SerializationUtils.clone(IdentityObject.IT).hashCode());
	}

	/**
	 * Checks that {@link org.leplus.ristretto.util.IdentityObject.IT} passes guava's
	 * serialization tests.
	 */
	@Test
	public void testSerializeGuava() {
		SerializableTester.reserializeAndAssert(IdentityObject.IT);
	}

}
