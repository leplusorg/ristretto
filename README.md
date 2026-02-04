# Ristretto

A small library of (hopefully) useful Java classes.

[![Maven](https://github.com/leplusorg/ristretto/workflows/Maven/badge.svg)](https://github.com/leplusorg/ristretto/actions?query=workflow:"Maven")
[![Publish](https://github.com/leplusorg/ristretto/workflows/Publish/badge.svg)](https://github.com/leplusorg/ristretto/actions?query=workflow:"Publish")
[![Sigstore Signature](https://github.com/leplusorg/ristretto/workflows/PGP%20Signature%20Check/badge.svg)](https://github.com/leplusorg/ristretto/actions?query=workflow:"Sigstore%20Signature%20Check")
[![PGP Signature](https://github.com/leplusorg/ristretto/workflows/PGP%20Signature%20Check/badge.svg)](https://github.com/leplusorg/ristretto/actions?query=workflow:"PGP%20Signature%20Check")
[![Maven Central](https://img.shields.io/maven-central/v/org.leplus/ristretto)](https://search.maven.org/artifact/org.leplus/ristretto)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/10084/badge)](https://bestpractices.coreinfrastructure.org/projects/10084)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/leplusorg/ristretto/badge)](https://securityscorecards.dev/viewer/?uri=github.com/leplusorg/ristretto)
[![Maven Site](https://img.shields.io/badge/Maven-Site-blue)](https://leplusorg.github.io/ristretto)
[![Javadoc](https://img.shields.io/badge/Javadoc-Site-blue)](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/package-summary.html)

## Java version

| Ristretto | Mininum Java version |
| --------- | -------------------- |
| 1.x       | 1.8                  |
| 2.x       | 11                   |
| 3.x       | 17                   |

## Maven Coordinates

Below are instructions on how to add this library to your project
using various build tools.

### Apache Maven

```xml
<dependency>
  <groupId>org.leplus</groupId>
  <artifactId>ristretto</artifactId>
  <version>2.0.0</version>
</dependency>
```

### Apache Ivy

```xml
<dependency org="org.leplus" name="ristretto" rev="2.0.0">
  <artifact name="ristretto" type="jar" />
</dependency>
```

### Groovy Grape

```groovy
@Grapes(
@Grab(group='org.leplus', module='ristretto', version='2.0.0')
)
```

### Gradle/Grails

If you use ristretto in your implementation only:

```gradle
dependencies {
  implementation 'org.leplus:ristretto:2.0.0'
}
```

If you expose ristretto types in your public API:

```gradle
dependencies {
  api("org.leplus:ristretto:2.0.0")
}
```

### Scala SBT

```scala
libraryDependencies += "org.leplus" % "ristretto" % "2.0.0"
```

### Leiningen

```clojure
[org.leplus/ristretto "2.0.0"]
```

## Usage

### [org.leplus.ristretto.util.UUIDConvertor](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/UUIDConvertor.html)

This class provides utility method to convert different primitives from/to UUID.
All the methods in this class are reversible.
While this is not a recommended way to generate UUIDs in general, it can be useful
in situations where you need to temporarily assign a UUID to an object based on
another unique ID.

For example if you need to convert a legacy object that has a integer or long ID
into a new object that uses UUIDs, you could just generate a new UUID
for the new object using [`java.util.UUID.randomUUID()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/UUID.html#randomUUID%28%29) but if you need to later link back
the legacy object from the new one, you might not have a place to store the legacy ID
on the new object. If you use this class's `toUUID()` methods to convert the legacy
object's ID into the new object's UUID, you will be able to later convert back
the UUID into the legacy ID. Provided the legacy IDs are unique (at least for the legacy
object type), the new UUIDs produced will be as unique.

Another use case could be to temporarily convert a legacy object into a new object
temporarily (for example for processing via a new method) and then need to convert
the result back into a legacy object. Then you can similarly use the methods in
this class to go back and forth between legacy IDs and UUIDs.

### [org.leplus.ristretto.util.ReproducibleUUIDs](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/ReproducibleUUIDs.html)

This class contains utility methods that generate deterministic UUIDs.
Meaning that given the same input, these methods will always return the
same UUID, as opposed to [`java.util.UUID.randomUUID()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/UUID.html#randomUUID%28%29). The produced
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

### [org.leplus.ristretto.util.IdentityObject](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/IdentityObject.html)

This class is a singleton. It supports serialization and cloning. In both
case the unique instance of IdentityObject remains the same object.

### [org.leplus.ristretto.util.IdentityHashSet<E>](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/IdentityHashSet.html)

This [`java.util.Set`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html) relies on identity (`==`) to compare the objects it
contains. It does not matter what the objects' [`equals`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Object.html#equals%28java.lang.Object%29) methods say.

### [org.leplus.ristretto.util.IdentityEnum](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/IdentityEnum.html)

This enum is a very efficient singleton. It supports serialization but not
cloning.

### [org.leplus.ristretto.util.VectorAdapter<E>](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/VectorAdapter.html)

This adapter class extends [`java.util.Vector`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Vector.html) to make it easier to replace [`java.util.Vector`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Vector.html) uses by
another [`java.util.List`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html) implementation.

### [org.leplus.ristretto.util.ArrayListVector<E>](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/ArrayListVector.html)

An [`java.util.ArrayList<E>`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)-backed implementation of
[org.leplus.ristretto.util.VectorAdapter<E>](https://leplusorg.github.io/ristretto/apidocs/org/leplus/ristretto/util/VectorAdapter.html).

Using this class introduces a small memory overhead compared to using an
[`java.util.ArrayList<E>`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html) directly. Typically that overhead is the size of an empty [`java.util.Vector`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Vector.html),
e.g. 48 bytes on Oracle Java HotSpot 11 for Windows (64-Bit).

## Digital Signature

Releases of Ristretto are digitally signed in two different ways:
using Sigstore and using GnuPG.

### Sigstore

[Sigstore](https://docs.sigstore.dev) is trying to improve supply
chain security by allowing you to verify the origin of an
artifact. You can verify that the jar that you use was actually
produced by this repository. This means that if you verify the
signature of the ristretto jar, you can trust the integrity of the
whole supply chain from code source, to CI/CD build, to distribution
on Maven Central or wherever you got the jar from.

To verify the jar using its sigstore signature, you need to download
them both locally and then use the `cosign` tool to verify the
signature. The whole process can be done using the following 3
commands:

```bash
curl -fsSL 'https://repo1.maven.org/maven2/org/leplus/ristretto/2.0.0/ristretto-2.0.0.jar' -o ristretto-2.0.0.jar
curl -fsSL 'https://repo1.maven.org/maven2/org/leplus/ristretto/2.0.0/ristretto-2.0.0.jar.sigstore.json' -o ristretto-2.0.0.jar.sigstore.json
cosign verify-blob --bundle ristretto-2.0.0.jar.sigstore.json --certificate-identity 'https://github.com/leplusorg/ristretto/.github/workflows/publish.yml@refs/tags/v2.0.0' --certificate-oidc-issuer 'https://token.actions.githubusercontent.com' ristretto-2.0.0.jar
```

The only output that you should get is a message saying `Verified OK`.

For instructions on how to install `cosign`, please read this [documentation](https://docs.sigstore.dev/cosign/system_config/installation/).

### GnuPG

Having PGP signature is a requirement to publish artifacts to Maven Central. You can verify the PGP signature using the following [public key 3F147B345EADE8C92DA0C0006B1B9BE54C155617](https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x6b1b9be54c155617). I recommend that you verify the PGP signature of all your dependencies:

- [Maven](https://www.simplify4u.org/pgpverify-maven-plugin/)
- [Gradle](https://docs.gradle.org/current/userguide/dependency_verification.html)

To verify only Ristretto, you can run the following command and check
that the displayed keyId matches the public key mentioned above:

`mvn org.simplify4u.plugins:pgpverify-maven-plugin:show -Dartifact=org.leplus:ristretto:2.0.0`

You can also use my convenient Docker image (shameless plug):

`docker run --rm leplusorg/pgp-verify-jar --online-keys=6B1B9BE54C155617 org.leplus:ristretto:2.0.0`

See [here for details](https://github.com/leplusorg/docker-pgp-verify-jar).

## License

Copyright 2016-present Thomas Leplus

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
<https://www.apache.org/licenses/LICENSE-2.0>.
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## Security

Please read [SECURITY.md](SECURITY.md) for details on our security policy and how to report security vulnerabilities.

## Code of Conduct

Please read [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for details on our code of conduct.
