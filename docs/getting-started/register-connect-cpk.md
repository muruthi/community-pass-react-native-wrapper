# Section 7: Register and connect your React Native application with the Community Pass Service

## 7.1 Objectives

Now that you have successfully set up your device and installed the Community Pass React Native Wrapper on your project, you should be ready to start using the Community Pass Kernel Services. At the end of this segment, you will have completed the following:

- Connect your Reliant App and test your connection to the Community Pass Kernel

## 7.2 Prerequisites

Before you get started on this section, ensure you have completed the following:

- You will need to have completed sections 1, 2, 3, 4, 5 and 6 in the [Getting Started](README.md) guide.
- You have received a Reliant App and Program GUID: This is your reliant application identifier shared with you on email as part of our onboarding process.

## 7.3 Use the Community Pass APIs in your React Native Reliant Application

1. Open a terminal/command prompt and navigate to the root folder of your reliant application project

terminal/comand prompt 1

```sh
cd /path/TestApp
```

2. Start your reliant application by running the following command:

```sh
npx react-native start
```

or

```sh
yarn start
```

3. Ensure that your POI device has debug mode enabled and connect it to your computer.
4. Open another terminal and navigate to the root folder of your reliant application project.

terminal/comand prompt 2

```sh
cd /path/TestApp
```

5. Run the following command to install a debug variant apk of your reliant application to your POI device.

```sh
npx react-native run-android
```

or

```sh
yarn android
```

## 7.4 Usage Examples

> By default, your reliant application is connected to the community pass kernel if you can get a response from one of these APIs, as shown below.

Add the saveBiometricConsent() Commnity Pass API to your application

```typescript
saveBiometricConsent({
  reliantAppGUID: process.env.RELIANT_APP_GUID, //string
  programGUID: process.env.PROGRAM_GUID, //string
  consumerConsentValue: consentValue, //boolean
})
  .then((response: any) => {
    console.log(response);
  })
  .catch((e: any) => {
    console.log(e);
  });
```

Add the getRegisterUserWithBiometrics() compass API to your application

```typescript
getRegisterUserWithBiometrics({
  reliantAppGUID: process.env.RELIANT_APP_GUID, //string
  programGUID: process.env.PROGRAM_GUID, //string
  consentID: consentId, //string
})
  .then((response: any) => {
    console.log(response);
  })
  .catch((e: any) => {
    console.log(e);
  });
```

Add the getRegisterBasicUser() Commnity Pass API to your application

```typescript
getRegisterBasicUser({
  reliantAppGUID: process.env.RELIANT_APP_GUID, //string
  programGUID: process.env.PROGRAM_GUID, //string
})
  .then((response: any) => {
    console.log(response);
  })
  .catch((e: any) => {
    console.log(e);
  });
```

Add the getWritePasscode() Commnity Pass API to your application

```typescript
getWritePasscode({
  reliantAppGUID: process.env.RELIANT_APP_GUID, //string
  programGUID: process.env.PROGRAM_GUID, //string
  rID: rId, //tring
  passcode: passcode, //string
})
  .then((response: any) => {
    console.log(response);
  })
  .catch((e: any) => {
    console.log(e);
  });
```

Add the getWriteProfile() Commnity Pass API to your application

```typescript
getWriteProfile({
  reliantAppGUID: process.env.RELIANT_APP_GUID, //string
  programGUID: process.env.PROGRAM_GUID, //string
  rID: rId, //string
  overwriteCard: overwriteCard, //boolean
})
  .then((response: any) => {
    console.log(response);
    // {"consumerDeviceNumber": "1234564665"}
  })
  .catch((e: any) => {
    console.log(e);
    // {"code": "errorCode", message: "errorMessage"}
  });
```

For updates regarding the Community Pass APIs that have been added to the library, refer to the [TODO file](/TODO.md)

You are now ready to start building your React Native Reliant Application using the Community Pass Kernel assets.

[Return to Getting Started](README.md)
