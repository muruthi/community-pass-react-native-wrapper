# react-native-cpk-library
A react native wrappper for the CPK, implementing all Community Pass Actions

## Requirements
- Community Pass Approved Android Device with the CPK Installed and Activated
- Reliant App GUID
- Program GUID
- Test Cards

## Installation

```sh
npm install react-native-cpk-library
```

## Usage

```js
import { connect } from "react-native-cpk-library";

const result = await connect(RELIANT_APP_GUID);
```

## API

| Method                                                                                            | Return Type         |
| --------------------------------------------------------------------------------------------------| ------------------- |
| [connect(RELIANT_APP_GUID: string)](#connect())                                                     | `Promise<Object>`   |
| [authenticatePasscode(RELIANT_APP_GUID: String, PROGRAM_GUID: String, PASSCODE, String)](#aupass) | `Promise<Object>`   |
| [authenticateBio(RELIANT_APP_GUID: string)](#aubio)                                               | `Promise<Object>`   |


### connect()

Connects to the CPK

#### Examples

```js
connect("RELIANT_APP_GUID").then((res) => {
    //res is a JSON Object
    // success response here {"data": null, "message": "Not connected", "status": false}
    // fail response here {"data": null, "message": "connected", "status": true}
});
```

### authenticatePasscode()

Connects to the CPK

#### Examples

```js
connect(RELIANT_APP_GUID: String, PROGRAM_GUID: String, PASSCODE, String).then((res) => {
    //res is a JSON Object
    // {"data": null, "message": "Authenticated", "status": true}
});
```

## License

MIT
