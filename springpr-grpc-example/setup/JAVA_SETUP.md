> JAVA SETUP
---

## Java Installation

[Java Install]

Install Java 21 or 25

```
brew install --cask zulu@21
```

```
brew install --cask zulu@23
```

then add to ~/.zprofile

```
export JAVA_23_HOME=$(/usr/libexec/java_home -v23)
export JAVA_21_HOME=$(/usr/libexec/java_home -v21)

export JAVA_HOME=$JAVA_23_HOME
```

If you need multiple versions of Java, please consider to use jenv to manage them.

[jenv](https://github.com/jenv/jenv)

```
brew install jenv
```

then

```
export PATH="$HOME/.jenv/shims:$PATH"

#
# default java23
#
Jenv global 23
Jenv local 23

alias java23='export JAVA_HOME=$JAVA_23_HOME;jenv local 23'
alias java21='export JAVA_HOME=$JAVA_21_HOME;jenv local 21'
```

## Maven Installation

[Maven installation](https://maven.apache.org/install.html)

### macOS Homebrew installation

```
brew install maven

```

add following entries to ~/.zprofile

```
export MAVEN_HOME=/opt/homebrew/Cellar/maven/3.9.9
export PATH=${PATH}:${MAVEN_HOME}/bin

```

### Using binary distribution

Install latest stable version (3.9.9)

**You need a Java Development Kit (JDK) installed. Either set the JAVA_HOME environment variable to the path of your JDK installation or have the java executable on your PATH.**

**Add the bin directory of the created directory apache-maven-3.9.9 to the PATH environment variable.**

### Setup Maven Repository location - optional 

default repository location:

```
~/.m2/repository
```

**under Maven installation directory**, find file /conf/settings.xml
for homebrew Maven installation, the directory is: 

```
/opt/homebrew/Cellar/maven/3.9.9/libexec/conf

```

or set localRepository to another directory. e.g.

```
<localRepository>/Users/#adsId#/Documents/Apps/maven/repositories/399</localRepository>

```

/Users/#adsId# is the user home directory

### .zprofile example

```
#
# set up jenv
#
export PATH="$HOME/.jenv/shims:$PATH"
#
# set up Java
#
export JAVA_23_HOME=$(/usr/libexec/java_home -v23)
export JAVA_21_HOME=$(/usr/libexec/java_home -v21)
export JAVA_17_HOME=$(/usr/libexec/java_home -v17)
export JAVA_11_HOME=$(/usr/libexec/java_home -v11)
export JAVA_8_HOME=$(/usr/libexec/java_home -v1.8)
#
alias java23='export JAVA_HOME=$JAVA_23_HOME;jenv local 23'
alias java21='export JAVA_HOME=$JAVA_21_HOME;jenv local 21'
alias java17='export JAVA_HOME=$JAVA_17_HOME;jenv local 17'
alias java11='export JAVA_HOME=$JAVA_11_HOME;jenv local 11'
alias java8='export JAVA_HOME=$JAVA_8_HOME;jenv local 1.8'
#
# default java23
#
export JAVA_HOME=$JAVA_23_HOME
Jenv global 23
Jenv local 23
#
# set up Maven
#
export MAVEN_HOME=$HOME/Documents/Apps/maven/apache-maven-3.9.9
export PATH="$MAVEN_HOME/bin:$PATH"
#
# Set Java Trust Store location
#
export JAVA_CERTS=/Users/yli25/.java_certs/cacerts_CertaaSOnDemandNonProdIssuingCAII
```