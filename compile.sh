#! /usr/bin/bash

if [ -d ./build ]; then rm -rf ./build; fi
if [ -f ./sources.txt ]; then rm -rf ./sources.txt; fi
if [ -f ./binaries.txt ]; then rm -rf ./binaries.txt; fi
touch sources.txt binaries.txt
find . -name "*.java" -print > sources.txt
javac -d build @sources.txt
cd ./build
find . -name "*.class" -print > ../binaries.txt
echo "Manifest-Version: 1.0" > Manifest.txt
echo "Main-Class: $1" >> Manifest.txt
jar -cfm ../App.jar Manifest.txt @../binaries.txt
cd ./..
