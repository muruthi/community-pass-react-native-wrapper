# react-native-cpk-library
A react native wrappper for Mastercard’s Community Pass Kernel (CPK), implementing all Community Pass Actions

## Table of Contents

  - [Requirements](#requirements)
  - [Installation](#installation)
    - [Package manager](#package-manager)
  - [Example](#example)
  - [React Native CPK API](#api)

## Requirements
- Community Pass Approved Android Device with the CPK Installed and Activated
- Reliant App GUID
- Program GUID
- Test Cards
- React Native Library Zip File

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

## Example

Once the package is installed, you can import the library using import or require approach:

```js
import { connect } from "react-native-cpk-library";

const result = await connect(RELIANT_APP_GUID);
```

## API

| Method                                                                                            | Return Type         |
| --------------------------------------------------------------------------------------------------| ------------------- |
| [connect(RELIANT_APP_GUID: string)](#connect())                                                     | `Promise<Object>`   |
| [authenticateWithPasscode(RELIANT_APP_GUID: String, PROGRAM_GUID: String, PASSCODE, String)](#authenticatewithpasscode) | `Promise<Object>`   |
| [authenticateWithBio(PROGRAM_GUID : string, RELIANT_APP_GUID: string)](#authenticatewithbio)                                               | `Promise<Object>`   |
| [checkRegistrationStatus(PROGRAM_GUID : string, RELIANT_APP_GUID: string)](#checkregistrationstatus)                                               | `Promise<Object>`   |
| [resgisterWithBio(PROGRAM_GUID : string, RELIANT_APP_GUID: string, OVERWRITE: boolean)](#registerwithbio)                                               | `Promise<Object>`   |
| [resgisterWithPasscode(PROGRAM_GUID : string, RELIANT_APP_GUID: string, PASSCODE: string, OVERWRITE: boolean)](#registerwithpasscode)                                               | `Promise<Object>`   |


### connect()

Connects to the CPK

#### Examples

```js
connect("RELIANT_APP_GUID").then((res) => {
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


## License

MIT
