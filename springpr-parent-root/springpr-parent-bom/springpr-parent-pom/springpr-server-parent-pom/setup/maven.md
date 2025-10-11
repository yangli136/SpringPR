> Maven Instruction

---
#### Set Local Maven Repository

##### download from [Maven download page](https://maven.apache.org/download.cgi)

under the maven_install_location/config

Open

```
settings.xml
```

then modify _localRepository_

```
  <localRepository>/Users/you/Documents/apps/maven/repositories/392</localRepository>
```

#### Set Maven OS Environment

modify or if not exist, create a .zprofile

```
  open .zprofile
```

#### Set up Maven Variables

```
export MAVEN_HOME=~/Documents/apps/maven/apache-maven-3.9.2
export PATH=$PATH:$MAVEN_HOME/bin
```

##### set up JDK Variables

```
export JAVA_8_HOME=$(/usr/libexec/java_home -v1.8)
export JAVA_17_HOME=$(/usr/libexec/java_home -v17)

alias java8='export JAVA_HOME=$JAVA_8_HOME'
alias java17='export JAVA_HOME=$JAVA_17_HOME'

#default java17
export JAVA_HOME=$JAVA_17_HOME
```

#### Eclipse Instruction

- Find Maven installation and point it to the Maven installed above
- Find Maven User Setting and point it to the settings.xml file above
