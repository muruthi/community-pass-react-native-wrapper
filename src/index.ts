import { NativeModules, Platform } from 'react-native';
import type {
  SaveBiometricsParamTypes,
  GetWritePasscodeParamTypes,
  GetWriteProfileParamTypes,
  GetRegisterBasicUser,
  GetRegisterUserWithBiometrics,
} from './types';

const LINKING_ERROR =
  `The package 'community-pass-react-native-wrapper' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const CompassLibraryReactNativeWrapper =
  NativeModules.CompassLibraryReactNativeWrapper
    ? NativeModules.CompassLibraryReactNativeWrapper
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
  return CompassLibraryReactNativeWrapper.saveBiometricConsent({
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
  return CompassLibraryReactNativeWrapper.getWritePasscode({
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
  return CompassLibraryReactNativeWrapper.getWriteProfile({
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
  return CompassLibraryReactNativeWrapper.getRegisterBasicUser({
    reliantAppGUID,
    programGUID,
  });
}

export function getRegisterUserWithBiometrics({
  reliantAppGUID,
  programGUID,
  consentID,
}: GetRegisterUserWithBiometrics) {
  return CompassLibraryReactNativeWrapper.getRegisterUserWithBiometrics({
    reliantAppGUID,
    programGUID,
    consentID,
  });
}
