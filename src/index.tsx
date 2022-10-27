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

export function connect(RELIANT_APP_GUID: string){
  return CpkLibrary.connectToCpk(RELIANT_APP_GUID)
}

export function hello(){
  return CpkLibrary.hello()
}

export function checkRegistrationStatus(PROGRAM_GUID : string, RELIANT_APP_GUID: string){
  return CpkLibrary.checkRegistrationStatus(PROGRAM_GUID, RELIANT_APP_GUID)
}

export function startBioRegistration(PROGRAM_GUID : string, RELIANT_APP_GUID: string){
  return CpkLibrary.initBioRegistration(PROGRAM_GUID, RELIANT_APP_GUID)
}

