/*
 * Copyright 2016-present Thomas Leplus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.leplus.ristretto.util;

/**
 * This class is meant to confuse the identity related tests. It is the evil twin sister of {@link
 * org.leplus.ristretto.util.IdentityObject}. Whereas identity can be seen as the strictest form of
 * equality (i.e. being the same object instance), instances of the DuplicityObject class have the
 * weakest form of equality (they are all equal to each other).
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class DuplicityObject {

  /** Creates a new instance. */
  public DuplicityObject() {
    super();
  }

  /**
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object o) {
    return o instanceof DuplicityObject;
  }

  /**
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Integer.MAX_VALUE;
  }
}
