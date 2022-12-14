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

export function connectKernelService(RELIANT_APP_GUID: string){
  return CpkLibrary.connectKernelService(RELIANT_APP_GUID)
}

export function checkRegistrationStatus(PROGRAM_GUID : string, RELIANT_APP_GUID: string){
  return CpkLibrary.checkRegistrationStatus(PROGRAM_GUID, RELIANT_APP_GUID)
}

export function resgisterWithBio(PROGRAM_GUID : string, RELIANT_APP_GUID: string, OVERWRITE: boolean){
  return CpkLibrary.registerWithBio(PROGRAM_GUID, RELIANT_APP_GUID, OVERWRITE)
}

export function resgisterWithPasscode(PROGRAM_GUID : string, RELIANT_APP_GUID: string, PASSCODE: string, OVERWRITE: boolean){
  return CpkLibrary.registerWithPassCode(PROGRAM_GUID, RELIANT_APP_GUID, PASSCODE, OVERWRITE)
}

export function authenticateWithBio(PROGRAM_GUID : string, RELIANT_APP_GUID: string){
  return CpkLibrary.authenticateWithPasscode(PROGRAM_GUID, RELIANT_APP_GUID)
}

export function authenticateWithPasscode(PROGRAM_GUID : string, RELIANT_APP_GUID: string, PASSCODE: string){
  return CpkLibrary.authenticateWithPasscode(PROGRAM_GUID, RELIANT_APP_GUID, PASSCODE)
}

export function blackListCard(programGuid: string, reliantAppGuid: string, rId: string, consumerDeviceId: string){
  return CpkLibrary.blackListCard(programGuid, reliantAppGuid, rId, consumerDeviceId)
}

