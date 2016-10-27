package org.leplus.util;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.Test;

public class TestIdentityEnum {

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
		assertTrue(IdentityEnum.IT == IdentityEnum.IT);
	}

	@Test
	public void testEqualDeepClone() throws ClassNotFoundException, IOException {
		assertTrue(IdentityEnum.IT == deepClone(IdentityEnum.IT));
	}

	@Test
	public void testEquals() {
		assertTrue(IdentityEnum.IT.equals(IdentityEnum.IT));
	}

	@Test
	public void testEqualsDeepClone() throws ClassNotFoundException, IOException {
		assertTrue(IdentityEnum.IT.equals(deepClone(IdentityEnum.IT)));
		assertTrue(deepClone(IdentityEnum.IT).equals(IdentityEnum.IT));
	}

	@Test
	public void testHashCode() {
		assertTrue(IdentityEnum.IT.hashCode() == IdentityEnum.IT.hashCode());
	}

}