#!/usr/bin/env bash

javac Main.java
javap -c Main.class
cat Main.java

# https://en.wikipedia.org/wiki/List_of_Java_bytecode_instructions
