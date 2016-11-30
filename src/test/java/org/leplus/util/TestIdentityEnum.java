/*
 * Ristretto - A small library of (hopefully) useful java classes.
 * Copyright (C) 2016 Thomas Leplus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
