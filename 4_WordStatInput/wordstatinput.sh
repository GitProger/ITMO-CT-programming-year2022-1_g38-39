#!/usr/bin/env bash

javac WordStatInput.java
javac WordStatWordsShingles.java

java -ea -jar WordStatTest.jar Shingles
