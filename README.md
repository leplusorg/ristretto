# Ristretto
A small library of (hopefully) useful Java classes.

[![Maven](https://github.com/thomasleplus/ristretto/workflows/Maven/badge.svg)](https://github.com/thomasleplus/ristretto/actions?query=workflow:"Maven")
[![CodeQL](https://github.com/thomasleplus/ristretto/workflows/CodeQL/badge.svg)](https://github.com/thomasleplus/ristretto/actions?query=workflow:"CodeQL")
[![Publish](https://github.com/thomasleplus/ristretto/workflows/Publish/badge.svg)](https://github.com/thomasleplus/ristretto/actions?query=workflow:"Publish")
[![Maven Central](https://img.shields.io/maven-central/v/org.leplus/ristretto)](https://search.maven.org/artifact/org.leplus/ristretto)
[![Javadoc](https://javadoc.io/badge2/org.leplus/ristretto/javadoc.svg)](https://javadoc.io/doc/org.leplus/ristretto)

## [org.leplus.ristretto.util.UUIDConvertor](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/UUIDConvertor.html)

This class provides utility method to convert different primitives from/to UUID.
All the methods in this class are reversible.
While this is not a recommended way to generate UUIDs in general, it can be useful
in situations where you need to temporarily assign a UUID to an object based on
another unique ID.

For example if you need to convert a legacy object that has a integer or long ID
into a new object that uses UUIDs, you could just generate a new UUID
for the new object using [`java.util.UUID.randomUUID()`](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html#randomUUID--) but if you need to later link back
the legacy object from the new one, you might not have a place to store the legacy ID
on the new object. If you use this class's `toUUID()` methods to convert the legacy
object's ID into the new object's UUID, you will be able to later convert back
the UUID into the legacy ID. Provided the legacy IDs are unique (at least for the legacy
object type), the new UUIDs produced will be as unique.
 
Another use case could be to temporarily convert a legacy object into a new object
temporarily (for example for processing via a new method) and then need to convert
the result back into a legacy object. Then you can similarly use the methods in
this class to go back and forth between legacy IDs and UUIDs.

## [org.leplus.ristretto.util.ReproducibleUUIDs](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/ReproducibleUUIDs.html)

This class contains utility methods that generate deterministic UUIDs.
Meaning that given the same input, these methods will always return the
same UUID, as opposed to [`java.util.UUID.randomUUID()`](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html#randomUUID--). The produced
UUIDs may not be universally unique (since other code using the same method
on the same input would have produce the same UUIDs) but it can still be
useful in situations where you need exactly that: be able to generate a UUID
and now that other parts of a system will be able to generate a matching UUID
for the same input.

For example if two parts of a system receive a file and need to independently
produce pieces of data that will later have to be reconciled. Then each piece
of data could have it's own unique UUID plus a reference UUID generated from
the file using one of the methods below. Then both part of the system will have
generated the same reference UUID allowing for easier reconciliation.

## [org.leplus.ristretto.util.IdentityObject](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/IdentityObject.html)

This class is a singleton. It supports serialization and cloning. In both
case the unique instance of IdentityObject remains the same object.

## [org.leplus.ristretto.util.IdentityHashSet<E>](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/IdentityHashSet.html)

This [`java.util.Set`](https://docs.oracle.com/javase/8/docs/api/java/util/Set.html) relies on identity (`==`) to compare the objects it
contains. It does not matter what the objects' [`equals`](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-) methods say.

## [org.leplus.ristretto.util.IdentityEnum](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/IdentityEnum.html)

This enum is a very efficient singleton. It supports serialization but not
cloning.

## [org.leplus.ristretto.util.VectorAdapter<E>](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/VectorAdapter.html)

This adapter class extends [`java.util.Vector`](https://docs.oracle.com/javase/8/docs/api/java/util/Vector.html) to make it easier to replace [`java.util.Vector`](https://docs.oracle.com/javase/8/docs/api/java/util/Vector.html) uses by
another [`java.util.List`](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) implementation.

## [org.leplus.ristretto.util.ArrayListVector<E>](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/ArrayListVector.html)

An [`java.util.ArrayList<E>`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)-backed implementation of
[org.leplus.ristretto.util.VectorAdapter<E>](https://javadoc.io/doc/org.leplus/ristretto/latest/org/leplus/ristretto/util/VectorAdapter.html).

Using this class introduces a small memory overhead compared to using an
[`java.util.ArrayList<E>`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) directly. Typically that overhead is the size of an empty [`java.util.Vector`](https://docs.oracle.com/javase/8/docs/api/java/util/Vector.html),
e.g. 48 bytes on Oracle Java HotSpot 1.8.0 for Windows (64-Bit).

## Digital Signature

Releases of Ristretto are digitally signed. You can verify the signature using the following [public key 3F147B345EADE8C92DA0C0006B1B9BE54C155617](https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x6b1b9be54c155617). I recommend that you verify the signature of all your dependencies:
- Maven: https://www.simplify4u.org/pgpverify-maven-plugin/
- Gradle: https://docs.gradle.org/current/userguide/dependency_verification.html

To verify only Ristretto, you can run the following command (replacing `x.y.z` with the version that you want to use) and check that the displayed keyId matches the public key mentioned above:

`mvn org.simplify4u.plugins:pgpverify-maven-plugin:show -Dartifact=org.leplus:ristretto:x.y.z`

You can also use my convenient docker image (shameless plug):

`docker run --rm thomasleplus/pgp-verify-jar org.leplus:ristretto:x.y.z`

See https://github.com/thomasleplus/docker-pgp-verify-jar for details.

## License

Copyright 2016-present Thomas Leplus

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
