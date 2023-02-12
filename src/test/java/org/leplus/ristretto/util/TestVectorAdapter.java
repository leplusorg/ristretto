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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Tests the {@link org.leplus.ristretto.util.VectorAdapter} class.
 *
 * @author Thomas Leplus
 * @since 1.0.0
 */
public final class TestVectorAdapter {

    public static class ClassComparator implements Comparator<Class<?>> {

        @Override
        public int compare(final Class<?> o1, final Class<?> o2) {
            return o1.toString().compareTo(o2.toString());
        }

    }

    private static class Signature implements Comparable<Signature> {

        private final String methodName;
        private final Class<?>[] methodParameterTypes;
        private final Class<?> methodReturnType;
        private final Class<?>[] methodExceptionTypes;

        public Signature(final Method method) {
            methodName = method.getName();
            methodParameterTypes = method.getParameterTypes();
            methodReturnType = method.getReturnType();
            methodExceptionTypes = method.getExceptionTypes();
        }

        @Override
        public int compareTo(final Signature other) {
            final ClassComparator comp = new ClassComparator();
            int c = methodName.compareTo(other.methodName);
            if (c != 0) {
                return c;
            }
            c = comp.compare(methodReturnType, other.methodReturnType);
            if (c != 0) {
                return c;
            }
            c = Arrays.compare(methodExceptionTypes, other.methodExceptionTypes,
                    comp);
            if (c != 0) {
                return c;
            }
            c = Arrays.compare(methodParameterTypes, other.methodParameterTypes,
                    comp);
            return c;
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj == null) || (getClass() != obj.getClass())) {
                return false;
            }
            final Signature other = (Signature) obj;
            return Arrays.equals(methodExceptionTypes,
                    other.methodExceptionTypes)
                    && Objects.equals(methodName, other.methodName)
                    && Arrays.equals(methodParameterTypes,
                            other.methodParameterTypes)
                    && Objects.equals(methodReturnType, other.methodReturnType);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(methodExceptionTypes);
            result = prime * result + Arrays.hashCode(methodParameterTypes);
            return prime * result + Objects.hash(methodName, methodReturnType);
        }

        @Override
        public String toString() {
            return methodReturnType.getName() + " " + methodName + "("
                    + Arrays.toString(methodParameterTypes) + ")"
                    + (methodExceptionTypes == null
                            || methodExceptionTypes.length == 0 ? ""
                                    : " throws " + Arrays
                                            .toString(methodExceptionTypes));
        }

    }

    public TestVectorAdapter() {
        super();
    }

    /**
     * Tests that VectorAdapter overrides all public methods from Vector.
     */
    @Test
    public void testOverride() {
        assertEquals(
                Arrays.asList(Vector.class.getDeclaredMethods()).stream()
                        .filter(m -> Modifier.isPublic(m.getModifiers())
                                && !Modifier.isStatic(m.getModifiers()))
                        .map(Signature::new)
                        .collect(Collectors.toCollection(TreeSet::new)),
                Arrays.asList(VectorAdapter.class.getDeclaredMethods()).stream()
                        .filter(m -> Modifier.isPublic(m.getModifiers())
                                && !Modifier.isStatic(m.getModifiers()))
                        .map(Signature::new)
                        .collect(Collectors.toCollection(TreeSet::new)));
    }

}
