#!/bin/sh
set -e
./scripts/ktlintCheck.sh
./gradlew koverXmlReport
./gradlew koverHtmlReport
./gradlew validateDebugScreenshotTest
./scripts/testCoverageValidator.sh -m 0.0 -b 0.0
