#!/bin/bash
# Exit on failure
set -euo pipefail
IFS=$'\n\t'

# Only run Sonar with one JDK
if [ "$TRAVIS_JDK_VERSION" = "oraclejdk8" ]; then
    echo "TRAVIS_JDK_VERSION=$TRAVIS_JDK_VERSION"
else
    echo "Skipping analysis by SonarCloud."
    exit 0
fi

echo "Starting analysis by SonarCloud..."
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar
