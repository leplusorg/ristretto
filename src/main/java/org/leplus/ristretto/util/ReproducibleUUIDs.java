/*
 * Copyright 2016-present Thomas Leplus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.leplus.ristretto.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * This class contains utility methods that generate deterministic UUIDs. Meaning that given the
 * same input, these methods will always return the same UUID, as opposed to {@link
 * java.util.UUID#randomUUID()}. The produced UUIDs may not be universally unique (since other code
 * using the same method on the same input would have produce the same UUIDs) but it can still be
 * useful in situations where you need exactly that: be able to generate a UUID and now that other
 * parts of a system will be able to generate a matching UUID for the same input.
 *
 * <p>For example if two parts of a system receive a file and need to independently produce pieces
 * of data that will later have to be reconciled. Then each piece of data could have it's own unique
 * UUID plus a reference UUID generated from the file using one of the methods below. Then both
 * parts of the system will have generated the same reference UUID allowing for easier
 * reconciliation.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
@SuppressFBWarnings("WEAK_MESSAGE_DIGEST_MD5")
public final class ReproducibleUUIDs {

  /** Underlying hash algorithm. MD5 is OK since we do not need a one-way hash function. */
  private static final String MD5 = "MD5";

  /** Bytes per UUID. */
  private static final int UUID_BYTES = 16;

  /** Buffer size for digest. */
  private static final int BUFFER_SIZE = 8192;

  /** Private constructor to prevent instantiation. */
  private ReproducibleUUIDs() {
    super();
  }

  /**
   * Generates a UUID from the given String. The same String will always produce the same UUID.
   *
   * @param s the input string.
   * @return the resulting UUID.
   */
  public static UUID fromString(final String s) {
    if (s == null) {
      return null;
    }
    return fromBytes(s.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Generates a UUID from the given bytes. The same bytes will always produce the same UUID.
   *
   * @param bytes the input bytes.
   * @return the resulting UUID.
   */
  public static UUID fromBytes(final byte... bytes) {
    if (bytes == null) {
      return null;
    }
    final MessageDigest md = createDigest();
    md.update(bytes);
    return digest(md);
  }

  /**
   * Generates a UUID from the given byte buffer. The same bytes will always produce the same UUID.
   *
   * @param buffer the input byte buffer.
   * @return the resulting UUID.
   */
  public static UUID fromByteBuffer(final ByteBuffer buffer) {
    if (buffer == null) {
      return null;
    }
    final MessageDigest md = createDigest();
    md.update(buffer);
    return digest(md);
  }

  /**
   * Digests bytes in a manner compatible with UUID.nameUUIDFromBytes(byte[]).
   *
   * @param md the digest.
   * @return the resulting UUID.
   */
  @SuppressWarnings("checkstyle:magicnumber")
  private static UUID digest(final MessageDigest md) {
    final byte[] md5Bytes = md.digest();
    // for compatibility with UUID.nameUUIDFromBytes(byte[]).
    md5Bytes[6] &= (byte) 0x0f;
    md5Bytes[6] |= (byte) 0x30;
    md5Bytes[8] &= (byte) 0x3f;
    md5Bytes[8] |= (byte) 0x80;
    return UUIDConvertor.toUUID(md5Bytes);
  }

  /**
   * Private factory method for digests.
   *
   * @return the digest to use.
   */
  private static MessageDigest createDigest() {
    try {
      /*
       * MD5 is not secure but it's OK in this case since we just need good
       * entropy, not actual security. Also MD5 gives us exactly the 128 bits
       * we need to create a UUID.
       */
      return MessageDigest.getInstance(MD5);
    } catch (final NoSuchAlgorithmException e) {
      throw new IllegalStateException(MD5 + " not supported", e);
    }
  }

  /**
   * Generates a UUID from the given input stream. The same bytes will always produce the same UUID.
   *
   * @param input the input stream.
   * @return the resulting UUID.
   * @throws IOException if an I/O error occurs.
   */
  @SuppressWarnings({"PMD.EmptyWhileStmt", "PMD.EmptyControlStatement", "checkstyle:emptyblock"})
  public static UUID fromInputStream(final InputStream input) throws IOException {
    if (input == null) {
      return null;
    }
    final MessageDigest md = createDigest();
    try (DigestInputStream dis = new DigestInputStream(input, md)) {
      final byte[] buffer = new byte[BUFFER_SIZE];
      while (dis.read(buffer) >= 0) {}
    }
    return digest(md);
  }

  /**
   * Generates a UUID from the given UUIDs. The same UUIDs will always produce the same UUID.
   *
   * @param uuids the UUIDs.
   * @return the resulting UUID.
   */
  public static UUID fromUUIDs(final UUID... uuids) {
    if (uuids == null || uuids.length == 0) {
      return null;
    }
    if (uuids.length == 1) {
      return uuids[0];
    }
    final ByteBuffer bytes = ByteBuffer.allocate(uuids.length * UUID_BYTES);
    final LongBuffer longs = bytes.asLongBuffer();
    for (final UUID uuid : uuids) {
      longs.put(uuid.getMostSignificantBits());
      longs.put(uuid.getLeastSignificantBits());
    }
    return fromByteBuffer(bytes);
  }
}
