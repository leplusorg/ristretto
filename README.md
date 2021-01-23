# Ristretto
A small library of (hopefully) useful java classes.

[![Maven](https://github.com/thomasleplus/ristretto/workflows/Maven/badge.svg)](https://github.com/thomasleplus/ristretto/actions?query=workflow:"Maven")
[![CodeQL](https://github.com/thomasleplus/ristretto/workflows/CodeQL/badge.svg)](https://github.com/thomasleplus/ristretto/actions?query=workflow:"CodeQL")
[![Publish](https://github.com/thomasleplus/ristretto/workflows/Publish/badge.svg)](https://github.com/thomasleplus/ristretto/actions?query=workflow:"Publish")
[![Maven Central](https://img.shields.io/maven-central/v/org.leplus/ristretto)](https://search.maven.org/artifact/org.leplus/ristretto)
[![Javadoc](https://javadoc.io/badge2/org.leplus/ristretto/javadoc.svg)](https://javadoc.io/doc/org.leplus/ristretto) 

## Digital Signature

Releases of Ristretto are digitally signed. You can verify the signature using the following [public key 0x6b1b9be54c155617](https://pgp.mit.edu/pks/lookup?op=get&search=0x6B1B9BE54C155617). I recommend that you verify the signature of all your dependencies:
- Maven: https://www.simplify4u.org/pgpverify-maven-plugin/
- Gradle: https://docs.gradle.org/current/userguide/dependency_verification.html

To veriry only Ristretto, you can run the following command (replacing `x.y.z` with the version that you want to use) and check that the displayed keyId matches the public key mentioned above:

`mvn org.simplify4u.plugins:pgpverify-maven-plugin:show -Dartifact=org.leplus:ristretto:x.y.z`
