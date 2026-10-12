#!/bin/bash

set -e

rm -rf build
mkdir -p build

find src -name "*.java" -print0 | xargs -0 javac -d build

echo "Build completata!"