#!/bin/sh
set -e
./gradlew koverHtmlReportCustomDebug
./gradlew validateDebugScreenshotTest
