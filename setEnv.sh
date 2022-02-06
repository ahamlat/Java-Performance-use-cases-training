 #!/bin/sh

# Initialiser la JAVA HOME
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home


# Exporter les exécutables JAVA
export JAVA=$JAVA_HOME/bin/java
export JAVAC=$JAVA_HOME/bin/javac
export JAVA_SRC=$JAVA_HOME/src.jar