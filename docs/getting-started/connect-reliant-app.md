# Section 7: Register and connect your React Native Reliant App with the Community Pass React Native Wrapper

## 5.3.3 Use the Community Pass APIs in your React Native Reliant Application

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
