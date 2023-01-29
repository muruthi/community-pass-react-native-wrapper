# Adding the Community Pass React Native Wrapper Guide

## Objectives

To help you connect to the Community Pass Kernel, our team created the Community Pass React Native Wrapper that bridges the gap between your application and the Community Pass Kernel. This library will enable you to develop an application using the CPK service’s APIs.

- At the end of this step you will have added the Community Pass React Native Wrapper to your Reliant application.

## Pre-requisites

1.  Complete the following sections of the technical integration guide tutorial at [Mastercard Developer Zone](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/)

    - [Section 1](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step1): Community Pass Pre-requisites required to get you starting
    - [Section 2](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step2): Setting up your Community Pass Approved Device
    - [Section 3](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step3): Setting up your development environment
    - [Section 4](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step4): Submit your Reliant App details to Community Pass
    - Section 5: Install the Community Pass React Native Wrapper

      > The section 5 information at [Mastercard Developer Zone](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step5/) is currently applicable to adding the Community Pass Kernel Library to Kotlin and Java native applications. To complete this section in the context of a React Native application, please follow the steps below.

    - [Section 6](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step6): Install and Activate the CPK
    - Section 7: Register and connect your React Native Reliant App with the Community Pass React Native Wrapper

      > The section 7 information at [Mastercard Developer Zone](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step7/) is currently applicable to registering and connecting Kotlin and Java reliant applications to the Community Pass Kernel. To complete this section in the context of a React Native application, please see the [Usage Examples](usage-examples.md) segment of this guide.

2.  The React Native library which can be accessed through [CP Assets](https://developer.mastercard.com/cp-kernel-integration-api/documentation/cp-assets/cp-assets-request/) Request. We will show you how to add the library to your project.

```
NOTE: Please note that you will need to create a developer account and request access to the library file
through CP Assets request. The approval may take 1-2 business days. Once you have access, proceed to download
the library for your development environment.
```

## Install the Community Pass React Native Wrapper

The following are the steps required to set up your project with the Community Pass Client SDK:

### Installation

1. Locate the folder where you downloaded the library to. The library will have a name similar to the following example: `react-native-cpk-library-x.y.z.tgz`
2. Open a terminal/command prompt and navigate to the root folder of your reliant application project. See example below:

```sh
cd /path/TestApp
```

3. From the root folder of your reliant applciation , run the following command to install the Community Pass React Native Wrapper

```sh
npm install /path/react-native-cpk-library-x.y.z.tgz
```

4. Open `android/app/build.gradle` file of your reliant application and add the following line to your dependencies

```gradle
dependencies {
    ...

    // add this to your list of dependencies
    implementation files("../../node_modules/react-native-cpk-library/android/libs/community-pass-library-v2.4.0.aar");

    ...
}
```

5. In the same file `android/app/build.gradle` add the configurations block after your dependencies. See below:

```gradle
dependencies {
    ...
}

// Copy this and paste it below your dependencies block
configurations {
    implementation.exclude group: 'org.slf4j', module: 'slf4j-api'
}
```

6.  A pop up notification will appear as given in the below image informing you that the Gradle files have changed. Click on Sync Now to synchronize the project with the Gradle files.

![](/docs/assets/android-studio-popup.png)

### Confirm installation

1. Locate and open the `package.json` file from the root folder of your reliant application
2. Under dependencies you should see the library added to your list. See the example below:

<br/>

```json
"dependencies": {
    ...

    "react-native-cpk-library": "file:react-native-cpk-library-x.y.z.tgz",

    ...
}
```

<br/>

---

<br/>
You should now have completed the process of adding the Community Pass React Native Wrapper into your React Native Android Project.

You are now ready to engage with the CPK on your POI device and connect your Reliant Application to the Community Pass Kernel services.

[Next Section: Usage Example](usage-examples.md)
