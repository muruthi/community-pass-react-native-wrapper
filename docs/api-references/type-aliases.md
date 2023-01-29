# Type Aliases

## User Registration Request

### SaveBiometricConsentParams<a name="save-biometric-consent-params"></a>

```ts
interface SaveBiometricConsentParams {
  reliantAppGUID: string;
  programGUID: string;
  consumerConsentValue: boolean;
}
```

### GetWritePasscodeParams<a name="get-write-passcode-params"></a>

```ts
interface GetWritePasscodeParams {
  reliantAppGUID: string;
  programGUID: string;
  rID: string;
  passcode: string;
}
```

### GetWriteProfileParams<a name="get-write-profile-params"></a>

```ts
interface GetWriteProfileParams {
  reliantAppGUID: string;
  programGUID: string;
  rID: string;
  overwriteCard: boolean;
}
```

### GetRegisterBasicUserParams<a name="get-register-basic-user-params"></a>

```ts
interface GetRegisterBasicUserParams {
  reliantAppGUID: string;
  programGUID: string;
}
```

### GetRegisterUserWithBiometricsParams<a name="get-register-user-with-biometrics-params"></a>

```ts
interface GetRegisterUserWithBiometricsParams {
  reliantAppGUID: string;
  programGUID: string;
  consentId: string;
}
```

## User Registration Response

### SaveBiometricConsentResult<a name="save-biometric-consent-result"></a>

```ts
interface SaveBiometricConsentResult {
  consentId: string;
  responseStatus: string;
}
```

### GetWritePasscodeResult<a name="get-write-passcode-result"></a>

```ts
interface GetWritePasscodeResult {
  responseStatus: string;
}
```

### GetWriteProfileResult<a name="get-write-profile-result"></a>

```ts
interface GetWriteProfileResult {
  consumerDeviceNumber: string;
}
```

### GetRegisterBasicUserResult<a name="get-register-basic-user-result"></a>

```ts
interface GetRegisterBasicUserResult {
  rId: string;
}
```

### GetRegisterUserWithBiometricsResult<a name="get-register-user-with-biometrics-result"></a>

```ts
interface GetRegisterUserWithBiometricsResult {
  rId: string;
  enrolmentStatus: string;
  bioToken: string;
  programGUID: string;
}
```
