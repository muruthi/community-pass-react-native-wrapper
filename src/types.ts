export interface SaveBiometricsParamTypes {
  reliantAppGUID: string;
  programGUID: string;
  consumerConsentValue: boolean;
}

export interface GetWritePasscodeParamTypes {
  reliantAppGUID: string;
  programGUID: string;
  rID: string;
  passcode: string;
}

export interface GetWriteProfileParamTypes {
  reliantAppGUID: string;
  programGUID: string;
  rID: string;
  overwriteCard: boolean;
}

export interface GetRegisterBasicUser {
  reliantAppGUID: string;
  programGUID: string;
}

export interface GetRegisterUserWithBiometrics {
  reliantAppGUID: string;
  programGUID: string;
  consentID: string;
}
