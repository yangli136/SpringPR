> JDK 17 on a MacBook

---
#### JDK 17 on a MacBook

[JDK download](https://www.oracle.com/java/technologies/downloads/)

#### Set Maven OS Environment

modify or if not exist, create a .zprofile

```
  open .zprofile
```

#### Set up JAVA_HOME Variables

```
export JAVA_8_HOME=$(/usr/libexec/java_home -v1.8)
export JAVA_17_HOME=$(/usr/libexec/java_home -v17)

alias java8='export JAVA_HOME=$JAVA_8_HOME'
alias java17='export JAVA_HOME=$JAVA_17_HOME'

#default java17
export JAVA_HOME=$JAVA_17_HOME
```

#### Eclipse Instruction

- Find Java installation and point it to the JDK home above

<br><br>


### [jenv - Manage Multiple Java versions on Mac](https://stackoverflow.com/questions/26252591/mac-os-x-and-multiple-java-versions)

This guide was cobbled together from various sources (replies above as well as other posts), and works perfect.

- If you haven't already, install homebrew.
See [https://brew.sh/](https://brew.sh/)

- Install jenv

```
brew install jenv
```

- Add jenv to the bash profile

```
if which jenv > /dev/null; then eval "$(jenv init -)"; fi
```

- Add jenv to your path

```
export PATH="$HOME/.jenv/shims:$PATH"
```

- Tap "homebrew/cask-versions"
FYI: "Tap" extends brew's list of available repos it can install, above and beyond brew's default list of available repos.

```
brew tap homebrew/cask-versions
```

- Install the latest version of java

```
brew install --cask temurin
```

- Install java 11 (or 17)

```
brew install --cask temurin11
```

? Maybe close and restart Terminal so it sees any new ENV vars that got setup.

- Review Installations
All Java version get installed here: /Library/Java/JavaVirtualMachines lets take a look.

```
ls -la /Library/Java/JavaVirtualMachines
```

- Add each path to jenv one-at-a-time.
We need to add "/Contents/Home" to the version folder. WARNING: Use the actual paths on your machine... these are just EXAMPLE's

```
jenv add /Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home
jenv add /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
jenv add /Library/Java/JavaVirtualMachines/temurin-20.jdk/Contents/Home
```

- Check if jenv registered OK

```
jenv versions
```

- Set java version to use (globably)
Where XX matches one of the items in the versions list above.

```
jenv global XX
```

- Check java version

```
java -version
```

- Check jenv versions
Should also indicate the current version being used with an asterisk.

```
jenv versions
```

### DONE

<br><br><br><br>
sudo rm -rf /Library/Java/JavaVirtualMachines/adoptopenjdk-16.jdk
