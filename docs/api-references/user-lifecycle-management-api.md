# User Lifecycle Management API

## User Registration

### saveBiometricConsent

This API is used to save the user consent prior to collecting the biometric(s) and generating a unique digital identity. This is a blocking call, therefore it is advised to perform on a non-UI thread.

```
NOTE: It is the responsibility of the Reliant Application to show and capture the user’s consent.
Then, the Reliant Application must store it with CPK.
```

**Compatibility**
| **Available as of CPK version #** | **Deprecated as of CPK version #** |
|-----------------------------------|------------------------------------|
| + CPK 2.0.1 | + n/a |

**Input Parameters**
| **Parameter** | **Type** | **Description** |
|---------------|----------|------------------------------------------------------|
| consent | Consent | Business object encapsulating the details of consent |

**Response Parameters**
| **Parameter** | **Type** | **Description** |
|-----------------|-----------------|----------------------------------------------------------|
| consentResponse | ConsentResponse | Business object representing the result of the operation |

**Error codes**
In addition to the [general error codes](https://developer.mastercard.com/cp-kernel-integration-api/documentation/reference-pages/code-and-formats/), below are the error codes that CPK can send as part of the response:

| **Error Code**                                | **Description**                                         |
| --------------------------------------------- | ------------------------------------------------------- |
| ERROR_CODE_PROGRAM_NOT_SUPPORTED              | Specified Program ID is not supported by CPK            |
| ERROR_CODE_PROGRAM_DOES_NOT_SUPPORT_BIOMETRIC | Specified Program ID does not support biometric capture |
