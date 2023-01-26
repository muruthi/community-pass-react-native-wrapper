import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-cpk-library' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const CpkLibrary = NativeModules.CpkLibrary
  ? NativeModules.CpkLibrary
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function saveBiometricConsent(
  RELIANT_APP_GUID: string,
  PROGRAM_GUID: string,
  CONSUMER_CONSENT_VALUE: boolean
) {
  return CpkLibrary.saveBiometricConsent(
    RELIANT_APP_GUID,
    PROGRAM_GUID,
    CONSUMER_CONSENT_VALUE
  );
}

export function getWritePasscode(
  RELIANT_APP_GUID: string,
  PROGRAM_GUID: string,
  RID: string,
  PASSCODE: string
) {
  return CpkLibrary.getWritePasscode(
    PROGRAM_GUID,
    RELIANT_APP_GUID,
    RID,
    PASSCODE
  );
}

export function getWriteProfile(
  RELIANT_APP_GUID: string,
  PROGRAM_GUID: string,
  RID: string,
  OVERWRITE_CARD: boolean
) {
  return CpkLibrary.getWriteProfile(
    PROGRAM_GUID,
    RELIANT_APP_GUID,
    RID,
    OVERWRITE_CARD
  );
}

export function getRegisterBasicUser(
  RELIANT_APP_GUID: string,
  PROGRAM_GUID: string
) {
  return CpkLibrary.getRegisterBasicUser(PROGRAM_GUID, RELIANT_APP_GUID);
}

export function getRegisterUserWithBiometrics(
  RELIANT_APP_GUID: string,
  PROGRAM_GUID: string,
  CONSENT_ID: string
) {
  return CpkLibrary.getRegisterUserWithBiometrics(
    PROGRAM_GUID,
    RELIANT_APP_GUID,
    CONSENT_ID
  );
}
