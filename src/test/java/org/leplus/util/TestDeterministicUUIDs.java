package org.leplus.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

import org.junit.Test;

public class TestDeterministicUUIDs {

	private static final int NUMBER_OF_BYTES = 1024;

	@Test
	public void testNulls() throws IOException {
		assertNull(DeterministicUUIDs.fromBytes(null));
		assertNull(DeterministicUUIDs.fromByteBuffer(null));
		assertNull(DeterministicUUIDs.fromString(null));
		assertNull(DeterministicUUIDs.fromInputStream(null));
	}

	@Test
	public void testBytes() throws IOException {
		final SecureRandom random = new SecureRandom();
		final byte[] bytes = new byte[NUMBER_OF_BYTES];
		random.nextBytes(bytes);
		assertEquals(DeterministicUUIDs.fromBytes(bytes),
				DeterministicUUIDs.fromBytes(bytes));
		// Test compatibility with java.util.UUID.nameUUIDFromBytes(byte[])
		assertEquals(DeterministicUUIDs.fromBytes(bytes),
				UUID.nameUUIDFromBytes(bytes));
	}

	@Test
	public void testByteBuffer() throws IOException {
		final SecureRandom random = new SecureRandom();
		final byte[] bytes = new byte[NUMBER_OF_BYTES];
		random.nextBytes(bytes);
		assertEquals(DeterministicUUIDs.fromByteBuffer(ByteBuffer.wrap(bytes)),
				DeterministicUUIDs.fromByteBuffer(ByteBuffer.wrap(bytes)));
	}

	@Test
	public void testString() throws IOException {
		final SecureRandom random = new SecureRandom();
		final byte[] bytes = new byte[NUMBER_OF_BYTES];
		random.nextBytes(bytes);
		assertEquals(DeterministicUUIDs.fromString(new String(bytes)),
				DeterministicUUIDs.fromString(new String(bytes)));
	}

	@Test
	public void testInputStream() throws IOException {
		final SecureRandom random = new SecureRandom();
		final byte[] bytes = new byte[NUMBER_OF_BYTES];
		random.nextBytes(bytes);
		assertEquals(DeterministicUUIDs.fromInputStream(new ByteArrayInputStream(bytes)),
				DeterministicUUIDs.fromInputStream(new ByteArrayInputStream(bytes)));
	}

}
