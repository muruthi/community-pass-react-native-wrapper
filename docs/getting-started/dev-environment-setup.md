# Section 3: Setting Up Your React Native Development Environment

## 3.1 Objectives

Once your device has been set-up, you will now need to prepare your development environment. This will allow you to create your React Native and connect your device to your Reliant Application that will be used in later sections.

At the end of this section you will have done the following:

- Install development dependencies
- Setup Android Studio
- Setup React Native CLI
- Create a new project
- Connect the React Native CLI to your device
- Run a simple “Hello world” application to test your setup

> To configure an existing project for Community Pass integration, please see the [Install the Community Pass React Native Wrapper](README.md) instructions.

## 3.2 Installing dependencies on macOS

You will need the following:

- Node
- Watchman
- React Native command line interface
- JDK
- Android Studio.

While you can use any editor of your choice to develop your app, you will need to install Android Studio in order to set up the necessary tooling to build your React Native app for Android.

### 3.2.1 Node & Watchman

We recommend installing Node and Watchman using [Homebrew](http://brew.sh/). Run the following commands in a Terminal after installing Homebrew:

```sh
brew install node
brew install watchman
```

If you have already installed Node on your system, make sure it is Node 14 or newer.

[Watchman](https://facebook.github.io/watchman) is a tool by Facebook for watching changes in the filesystem. It is highly recommended you install it for better performance.

### 3.2.2 Java Development Kit

We recommend installing the OpenJDK distribution called Azul **Zulu** using Homebrew. Run the following commands in a Terminal after installing Homebrew:

```sh
brew tap homebrew/cask-versions
brew install --cask zulu11
```

The Zulu OpenJDK distribution offers JDKs for both Intel and M1 Macs. This will make sure your builds are faster on M1 Macs compared to using an Intel-based JDK.

If you have already installed JDK on your system, we recommend JDK 11. You may encounter problems using higher JDK versions.

## 3.3 Installing dependencies on Windows

You will need the following:

- Node
- Watchman
- React Native command line interface
- JDK
- Android Studio.

While you can use any editor of your choice to develop your app, you will need to install Android Studio in order to set up the necessary tooling to build your React Native app for Android.

### 3.3.1 Node, JDK

We recommend installing Node via [Chocolatey](https://chocolatey.org/), a popular package manager for Windows.

It is recommended to use an LTS version of Node. If you want to be able to switch between different versions, you might want to install Node via [nvm-windows](https://github.com/coreybutler/nvm-windows), a Node version manager for Windows.

React Native also requires [Java SE Development Kit (JDK)](https://openjdk.java.net/projects/jdk/11/), which can be installed using Chocolatey as well.

Open an Administrator Command Prompt (right click Command Prompt and select "Run as Administrator"), then run the following command:

```sh
choco install -y nodejs-lts microsoft-openjdk11
```

If you have already installed Node on your system, make sure it is Node 14 or newer. If you already have a JDK on your system, we recommend JDK11. You may encounter problems using higher JDK versions.

> You can find additional installation options on [Node's Downloads page](https://nodejs.org/en/download/).

> If you're using the latest version of Java Development Kit, you'll need to change the Gradle version of your project so it can recognize the JDK. You can do that by going to {project root folder}\android\gradle\wrapper\gradle-wrapper.properties and changing the distributionUrl value to upgrade the Gradle version. You can check out [here the latest releases of Gradle](https://gradle.org/releases/).

## 3.4 Android development environment

### 3.4.1 Install Android Studio

[Download and install Android Studio](https://developer.android.com/studio/index.html). While on Android Studio installation wizard, make sure the boxes next to all of the following items are checked:

- Android SDK
- Android SDK Platform
- Android Virtual Device
- If you are not already using Hyper-V: `Performance (Intel ® HAXM)` [(See here for AMD or Hyper-V)](https://android-developers.googleblog.com/2018/07/android-emulator-amd-processor-hyper-v.html)

Then, click "Next" to install all of these components.

> If the checkboxes are grayed out, you will have a chance to install these components later on.

Once setup has finalized and you're presented with the Welcome screen, proceed to the next step.

### 3.4.2 Install the Android SDK

Android Studio installs the latest Android SDK by default. Building a React Native app with native code, however, requires the `Android 12 (S)` SDK in particular. Additional Android SDKs can be installed through the SDK Manager in Android Studio.

To do that, open Android Studio, click on "More Actions" button and select "SDK Manager".

> The SDK Manager can also be found within the Android Studio "Preferences" dialog, under **Appearance & Behavior → System Settings → Android SDK.**

Select the "SDK Platforms" tab from within the SDK Manager, then check the box next to "Show Package Details" in the bottom right corner. Look for and expand the `Android 12 (S)` entry, then make sure the following items are checked:

- `Android SDK Platform 31`
- `Intel x86 Atom_64 System Image` or `Google APIs Intel x86 Atom System Image`

Next, select the "SDK Tools" tab and check the box next to "Show Package Details" here as well. Look for and expand the `Android SDK Build-Tools` entry, then make sure that `31.0.0` is selected.

Finally, click "Apply" to download and install the Android SDK and related build tools.

### 3.4.3 Configure the ANDROID_HOME environment variable (macOS)

The React Native tools require some environment variables to be set up in order to build apps with native code.

Add the following lines to your `~/.zprofile` or `~/.zshrc` (if you are using bash, then `~/.bash_profile` or `~/.bashrc`) config file:

```bash
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/emulator
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

Run source `~/.zprofile` (or `source ~/.bash_profile` for `bash`) to load the config into your current shell. Verify that ANDROID_HOME has been set by running `echo $ANDROID_HOME` and the appropriate directories have been added to your path by running echo `$PATH`.

> Please make sure you use the correct Android SDK path. You can find the actual location of the SDK in the Android Studio "Preferences" dialog, under **Appearance & Behavior → System Settings → Android SDK**.

### 3.4.4 Configure the ANDROID_HOME environment variable (Windows)

The React Native tools require some environment variables to be set up in order to build apps with native code.

- Open the **Windows Control Panel**.
- Click on **User Accounts**, then click **User Accounts** again
- Click on **Change my environment variables**
- Click on **New...** to create a new `ANDROID_HOME` user variable that points to the path to your Android SDK:

The SDK is installed, by default, at the following location:

```sh
%LOCALAPPDATA%\Android\Sdk
```

You can find the actual location of the SDK in the Android Studio "Settings" dialog, under **Appearance & Behavior → System Settings → Android SDK**.

Open a new Command Prompt window to ensure the new environment variable is loaded before proceeding to the next step.

- Open powershell
- Copy and paste `Get-ChildItem -Path Env:\` into powershell
- Verify `ANDROID_HOME` has been added

### 3.4.5 Add platform-tools to Path (Windows)

   <br/>

- Open the **Windows Control Panel**.
- Click on **User Accounts**, then click **User Accounts** again
- Click on **Change my environment variables**
- Select the **Path** variable.
- Click **Edit**.
- Click **New** and add the path to platform-tools to the list.

The default location for this folder is:

```sh
%LOCALAPPDATA%\Android\Sdk\platform-tools
```

## 3.5 React Native Command Line Interface

React Native has a built-in command line interface. Rather than install and manage a specific version of the CLI globally, we recommend you access the current version at runtime using `npx`, which ships with Node.js. With `npx react-native <command>`, the current stable version of the CLI will be downloaded and executed at the time the command is run.

### 3.5.1 Creating a new application

> If you previously installed a global react-native-cli package, please remove it as it may cause unexpected issues:
>
> ```properties
> npm uninstall -g react-native-cli @react-native-community/cli
> ```
>
> <br/>

<br/>

React Native has a built-in command line interface, which you can use to generate a new project. You can access it without installing anything globally using `npx`, which ships with Node.js. Let's create a new React Native project called "AwesomeProject":

```sh
npx react-native init AwesomeProject
```

This is not necessary if you are integrating React Native into an existing application, if you "ejected" from Expo, or if you're adding Android support to an existing React Native project (see [Integration with Existing Apps](https://reactnative.dev/docs/integration-with-existing-apps)). You can also use a third-party CLI to init your React Native app, such as [Ignite CLI](https://github.com/infinitered/ignite).

### 3.5.2 Connect your device via USB cable to your development computer

Connect your POI device to your development computer’s USB port With USB debugging turned on, the device should ask for approval (at least the first time).

### 3.5.3 Running your React Native application

**Step 1: Start Metro**

First, you will need to start Metro, the JavaScript bundler that ships with React Native. Metro "takes in an entry file and various options, and returns a single JavaScript file that includes all your code and its dependencies."—[Metro Docs](https://facebook.github.io/metro/docs/concepts)

To start Metro, run npx react-native start inside your React Native project folder:

```sh
npx react-native start
```

`react-native start` starts Metro Bundler.

> If you use the `Yarn` package manager, you can use `yarn` instead of `npx` when running React Native commands inside an existing project.

**Step 2: Start your application**

Let Metro Bundler run in its own terminal. Open a new terminal inside your React Native project folder. Run the following:

```sh
npx react-native run-android
```

If everything is set up correctly, you should see your new app running in your POI device shortly.

`npx react-native run-android` is one way to run your app - you can also run it directly from within Android Studio.

[Return to Getting Started](README.md)
