#!/bin/sh

. ./setEnv.sh

$JAVAC -classpath build -d build src/main/java/com/ahamlat/javaperformancecourse/*.java
