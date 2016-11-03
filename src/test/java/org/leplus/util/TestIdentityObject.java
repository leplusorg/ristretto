package org.leplus.util;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;

public class TestIdentityObject {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepClone(final T object) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try {
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.flush();
			final ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bin);
			return (T) ois.readObject();
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (oos != null) {
				oos.close();
			}
		}
	}

	@Test
	public void testEqual() {
		assertTrue(IdentityObject.IT == IdentityObject.IT);
	}

	@Test
	public void testEqualClone() {
		assertTrue(IdentityObject.IT == IdentityObject.IT.clone());
	}

	@Test
	public void testEqualDeepClone() throws ClassNotFoundException, IOException {
		assertTrue(IdentityObject.IT == deepClone(IdentityObject.IT));
	}

	@Test
	public void testEquals() {
		assertTrue(IdentityObject.IT.equals(IdentityObject.IT));
	}

	@Test
	public void testEqualsGuava() {
		new EqualsTester().addEqualityGroup(IdentityObject.IT, IdentityObject.IT).testEquals();
	}
	
	@Test
	public void testEqualsClone() {
		assertTrue(IdentityObject.IT.equals(IdentityObject.IT.clone()));
		assertTrue(IdentityObject.IT.clone().equals(IdentityObject.IT));
	}

	@Test
	public void testEqualsDeepClone() throws ClassNotFoundException, IOException {
		assertTrue(IdentityObject.IT.equals(deepClone(IdentityObject.IT)));
		assertTrue(deepClone(IdentityObject.IT).equals(IdentityObject.IT));
	}

	@Test
	public void testHashCode() {
		assertTrue(IdentityObject.IT.hashCode() == IdentityObject.IT.hashCode());
	}
	
	@Test
	public void testSerializeGuava() {
		SerializableTester.reserializeAndAssert(IdentityObject.IT);
	}
	
}