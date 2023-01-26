# react-native-cpk-library

A react native wrappper for Mastercard’s Community Pass Kernel (CPK), implementing all Community Pass Actions

## Table of Contents

- [Overview](#overview)
  - [Platform Support](#platform-support)
  - [References](#references)
- [Usage](#usage)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Installation](#installation)
  - [Usage Examples](#usage-examples)
  - [API](#api)
- [Support](#support)

## Overview <a name="overview"></a>

The Community Pass Platform provides technology that allows customers servicing digitally excluded and remote communities to do so in a more efficient and effective manner. The lives of consumers and the variety of services needed across any community are not limited to commerce and payments – rather they include a broad range of “life transactions” such as verifying who you are, health visits, crop exchanges, and agricultural deliveries, to name a few. In Community Pass, the functionality and capabilities are organized around these “life transactions” and include activities necessary to administer and maintain any technology-based program that supports them. Enabling these transactions requires different actions and attention before, during and after an event or service is provided – as well as various administrative or back-office functions. Please see here for more details on the API: [Mastercard Developers](https://developer.mastercard.com/product/community-pass-platform/documentation/).

## Platform Support

| Android | iOS | Web | Windows |
| ------- | --- | --- | ------- |
| ✔️      | ❌  | ❌  | ❌      |

## Prerequisites

- [React Native](https://reactnative.dev/docs/environment-setup) installed and working.
- Android Studio IDE or equivalent
- Community Pass Approved Android Device with the CPK Installed and Activated
- Reliant App GUID
- Program GUID
- Test Cards
- React Native Library Zip File
- Android 7.0 (Nougat) or later
- minSdkVersion 24 or later
- Android Gradle Plugin v3.4.0 or greater required
- Gradle 5.1.1 or greater required

## Configuration

To get started please visit our documentation at Mastercard Developer Zone and complete the following sections

- [Section 1:](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step1) Pre-requisites required to get you starting
- [Section 2:](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step2) Setting up your Community Pass Approved Device
- [Section 3:](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step3) Setting up your development environment
- [Section 4:](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step4) Submit your Reliant App details to Community Pass
- [Section 6:](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step6) Install and Activate the Community Pass Kernel

To report any issue about the Mastercard Developer Zone, please send an email to cp.patnerprogram[at]mastercard.com

For issues related with this plugin, please use github issues to report it.

## Installation

Download the react-native-cpk-library and store it in a folder or location that is accessible relative to your react native project. You will need to reference this path when installing the CPK using your package manager

### Package Manager

using npm

```sh
npm install /path/to/react-native-cpk-library
```

using yarn

```sh
yarn add /path/to/react-native-cpk-library
```

## API

| Method                                                          | Parameters                                                                                                                                    | Return Type       |
| --------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- | ----------------- |
| [saveBiometricConsent](#savebiometricconsent)                   | `reliantAppGuid`: String (required)<br/> `programGuid]`: String (required)                                                                    | `Promise<Object>` |
| [getRegisterUserWithBiometrics](#getregisteruserwithbiometrics) | `reliantAppGuid`: String (required)<br/> `programGuid`: String (required)<br/> `consentId`: String (required)                                 | `Promise<Object>` |
| [getRegisterBasicUser](#getregisterbasicuser)                   | `reliantAppGuid`: String (required)<br/> `programGuid`: String (required)                                                                     | `Promise<Object>` |
| [getWriteProfile](#getwriteprofile)                             | `reliantAppGuid`: String (required)<br/> `programGuid`: String (required)<br/> `rId`: String (required)<br/> `overwriteCard`: Bool (optional) | `Promise<Object>` |
| [getWritePasscode](#getwritepasscode)                           | `reliantAppGuid`: String (required)<br/> `programGuid`: String (required)<br/> `rId`: String (required)<br/> `passcode`: String (required)    | `Promise<Object>` |

## Example

Once the package is installed, you can import the library using import or require approach:

```js
import { connect } from 'react-native-cpk-library';

const result = await connect(RELIANT_APP_GUID);
```

### connect()

Connects to the CPK

#### Examples

```js
connect('RELIANT_APP_GUID').then((res) => {
  //res is a JSON Object
  // success response here {"data": null, "message": "Not connected", "status": false}
  // fail response here {"data": null, "message": "connected", "status": true}
  // if you connected to the CPK succesfully, conratulations!! You are ready to implement all CPK actions
});
```

### authenticateWithPasscode()

Authenticates a user using their passcode

#### Examples

```js
authenticateWithPassCode(RELIANT_APP_GUID: String, PROGRAM_GUID: String, PASSCODE, String).then((res) => {
    //res is a JSON Object
    // {"data": {"success":"true", "rId":"xxxxxxxxxxxx"}, "message": "Authenticated", "status": true}
    // {"data": {"success":"false", "rId":""}, "message": "Authentication failed. Passcode retry attempts remaining 2", "status": true}
});
```

### authenticateWithBio()

Authenticates a user using their Biometrics

#### Examples

```js
authenticateWithBio(RELIANT_APP_GUID: String, PROGRAM_GUID: String).then((res) => {
    //res is a JSON Object
    // {"data": {"success":"true", "rId":"xxxxxxxxxxxx"}, "message": "Authenticated", "status": true}
    // {"data": {"success":"false", "rId":""}, "message": "Authentication failed", "status": true}
});
```

### checkRegistrationStatus()

Checks the users registration method if availabe, returns a data object containing, BIO for biometric registration, PASSCODE for passcode registration and NONE for not available

#### Examples

```js
checRegistrationStatus(RELIANT_APP_GUID: String, PROGRAM_GUID: String).then((res) => {
    //res is a JSON Object
    // {"data": "BIO", "message": "Biometric Registration Detected. Proceed to authenticate with Biometrics", "status": true}
    // {"data": "NONE", "message": "User Not Enrolled", "status": true}
});
```

### checkRegistrationStatus()

Checks the users registration method if availabe, returns a data object containing, BIO for biometric registration, PASSCODE for passcode registration and NONE for not available

#### Examples

```js
checRegistrationStatus(RELIANT_APP_GUID: String, PROGRAM_GUID: String).then((res) => {
    //res is a JSON Object
    // {"data": "BIO", "message": "Biometric Registration Detected. Proceed to authenticate with Biometrics", "status": true}
    // {"data": "NONE", "message": "User Not Enrolled", "status": true}
});
```

### registerWithBio()

Registers a user onto the community pass platform using their biometrics

#### Examples

```js
resgisterWithBio(PROGRAM_GUID : string, RELIANT_APP_GUID: string, OVERWRITE: boolean).then((res) => {
    //res is a JSON Object
    // {"data": "{"rid":"xxx", "deviceId":"xxx"}", "message": "Succesfully registered using biometrics", "status": true}
    // {"data": "{"rid":null, "deviceId":null}",, "message": "User Not Enrolled", "status": true}
});
```

### registerWithPasscode()

Registers a user onto the community pass platform using their biometrics

#### Examples

```js
resgisterWithPasscode(PROGRAM_GUID : string, RELIANT_APP_GUID: string, PASSCODE: string, OVERWRITE: boolean){.then((res) => {
    //res is a JSON Object
    // {"data": "{"rid":"xxx", "deviceId":"xxx"}", "message": "Succesfully registered using passcode", "status": true}
    // {"data": "{"rid":null, "deviceId":null}",, "message": "User Not Enrolled", "status": true}
});
```

### Support

If you would like further information, please send an email to `cp.partnerprogram[at]mastercard.com`

- For updates regarding the community pass actions implementation, refer to the [TODO file](TODO.md)
- For information regarding licensing, refer to the [License file](LICENSE.md).
- For copyright information, refer to the [Copyright file](COPYRIGHT.md).
- For instructions on how to contribute to this project, refer to the [Contributing file](CONTRIBUTING.md).
- For changelog information, refer to the [Changelog file](CHANGELOG.md).
