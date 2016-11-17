package org.leplus.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;

/**
 * Tests for the {@link org.leplus.util.IdentityEnum} enum.
 * 
 * @author Thomas Leplus
 * @since 1.0.0
 */
public class TestIdentityEnum {

	/**
	 * Checks that {@link org.leplus.util.IdentityEnum.IT} is equal (==) to a deep clone of itself.
	 */
	@Test
	public void testEqualDeepClone() {
		assertTrue(IdentityEnum.IT == SerializationUtils.clone(IdentityEnum.IT));
	}

	/**
	 * Checks that {@link org.leplus.util.IdentityEnum.IT} equals() itself.
	 */
	@Test
	public void testEquals() {
		assertTrue(IdentityEnum.IT.equals(IdentityEnum.IT));
		assertFalse(IdentityEnum.IT.equals(new DuplicityObject()));
	}

	/**
	 * Checks that {@link org.leplus.util.IdentityEnum.IT} equals() a deep clone of itself.
	 */
	@Test
	public void testEqualsDeepClone() {
		assertTrue(IdentityEnum.IT.equals(SerializationUtils.clone(IdentityEnum.IT)));
		assertTrue(SerializationUtils.clone(IdentityEnum.IT).equals(IdentityEnum.IT));
	}
	
	/**
	 * Checks that {@link org.leplus.util.IdentityEnum.IT} passes guava's equality tests.
	 */
	@Test
	public void testEqualsGuava() {
		new EqualsTester().addEqualityGroup(IdentityEnum.IT, IdentityEnum.IT).testEquals();
	}

	/**
	 * Checks {@link org.leplus.util.IdentityEnum.IT}'s hashcode remains constant.
	 */
	@Test
	public void testHashCode() {
		assertTrue(IdentityEnum.IT.hashCode() == IdentityEnum.IT.hashCode());
		assertTrue(IdentityEnum.IT.hashCode() == SerializationUtils.clone(IdentityEnum.IT).hashCode());
	}
	
	/**
	 * Checks that {@link org.leplus.util.IdentityEnum.IT} passes guava's serialization tests.
	 */
	@Test
	public void testSerializeGuava() {
		SerializableTester.reserializeAndAssert(IdentityEnum.IT);
	}

}