# Section 5: Add the Community Pass React Native Wrapper

## 5.1 Objectives

To help you connect to the Community Pass Kernel, our team created the Community Pass React Native Wrapper that bridges the gap between your application and the Community Pass Kernel. This library will enable you to develop an application using the CPK service’s APIs.

At the end of this step you will have added the Community Pass React Native Wrapper to your Reliant application.

## 5.2 Pre-requisites

- You will need to use the Android Studio project that you setup in Section 3.3
- The React Native library which can be accessed through [CP Assets](https://developer.mastercard.com/cp-kernel-integration-api/documentation/cp-assets/cp-assets-request/) Request. We will show you how to add the library to your project.

```
NOTE: Please note that you will need to request access to the AAR through CP Assets Request.
The approval may take 1-2 business days. Once you have access, proceed to download the AAR library for your
development environment.
```

## 5.3 Adding the Community Pass React Native Wrapper

The following are the steps required to set up your project with the Community Pass Client SDK:

### 5.3.1 Installation

1. Locate the folder where you downloaded the library to. The library will have a name similar to the following example: `react-native-cpk-library.tgz`
2. Open a terminal/command prompt and navigate to the root folder of your reliant application project and run the following command:

```
yarn add /path/react-native-cpk-library
```

or

```
npm install /path/react-native-cpk-library
```

### 5.3.2 Confirm installation

1. Locate and open the `package.json` file from the root folder of your reliant application
2. Under dependences you should see the library added to your list. See bthe example below:

```json
"dependencies": {
    """"

    "react-native-cpk-library": "^x.y.z",

    """"
}

```

### 5.3.2 Use the Community Pass APIs in your React Native Reliant Application

1. Open a terminal/command prompt and navigate to the root folder of your reliant application project
2. Start your reliant application by running the following command:

```
yarn start
```

or

```
npm start
```

3. Ensure that your POI device has debug mode enabled (See [Section: 2](device-setup.md)) and connect it to your computer
4. Install the debug apk of your reliant application to your POI device using the following commands:

```
yarn android
```

or

```
npm android
```

5. Add the saveBiometricConsent compass API to your application

```typescript
saveBiometricConsent(RELIANT_APP_GUID, PROGRAM_GUID, CONSUMER_CONSENT_VALUE)
  .then((response: any) => {
    console.log(response);
  })
  .catch((e: any) => {
    console.log(e);
  });
```

For updates regarding the Community Pass APIs that have been added to the library, refer to the [TODO file](/TODO.md)

You are now ready to install the CPK onto your POI device and connect your Reliant Application to the Community Pass Kernel services.
