import { NativeModules, Platform } from 'react-native';
import type {
  SaveBiometricsParamTypes,
  GetWritePasscodeParamTypes,
  GetWriteProfileParamTypes,
  GetRegisterBasicUser,
  GetRegisterUserWithBiometrics,
} from './types';

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

export function saveBiometricConsent({
  reliantAppGUID,
  programGUID,
  consumerConsentValue,
}: SaveBiometricsParamTypes) {
  return CpkLibrary.saveBiometricConsent({
    reliantAppGUID,
    programGUID,
    consumerConsentValue,
  });
}

export function getWritePasscode({
  reliantAppGUID,
  programGUID,
  rID,
  passcode,
}: GetWritePasscodeParamTypes) {
  return CpkLibrary.getWritePasscode({
    reliantAppGUID,
    programGUID,
    rID,
    passcode,
  });
}

export function getWriteProfile({
  reliantAppGUID,
  programGUID,
  rID,
  overwriteCard,
}: GetWriteProfileParamTypes) {
  return CpkLibrary.getWriteProfile({
    reliantAppGUID,
    programGUID,
    rID,
    overwriteCard,
  });
}

export function getRegisterBasicUser({
  reliantAppGUID,
  programGUID,
}: GetRegisterBasicUser) {
  return CpkLibrary.getRegisterBasicUser({
    reliantAppGUID,
    programGUID,
  });
}

export function getRegisterUserWithBiometrics({
  reliantAppGUID,
  programGUID,
  consentID,
}: GetRegisterUserWithBiometrics) {
  return CpkLibrary.getRegisterUserWithBiometrics({
    reliantAppGUID,
    programGUID,
    consentID,
  });
}
