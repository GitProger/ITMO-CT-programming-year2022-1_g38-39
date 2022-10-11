#!/usr/bin/env bash

javac IntList.java
javac Wspp.java
javac WsppCountLastL.java
java -ea -jar WsppTest.jar CountLastL
