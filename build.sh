#!/bin/bash

set -e

rm -rf build
mkdir -p build

javac -d build $(find src -name "*.java")

echo "Build completata!"