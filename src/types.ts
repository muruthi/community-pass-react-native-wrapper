export interface SaveBiometricsParamType {
  reliantAppGUID: string;
  programGUID: string;
  consumerConsentValue: boolean;
}

export interface GetWritePasscodeParamType {
  reliantAppGUID: string;
  programGUID: string;
  rID: string;
  passcode: string;
}

export interface GetWriteProfileParamType {
  reliantAppGUID: string;
  programGUID: string;
  rID: string;
  overwriteCard: boolean;
}

export interface GetRegisterBasicUserParamType {
  reliantAppGUID: string;
  programGUID: string;
}

export interface GetRegisterUserWithBiometricsParamType {
  reliantAppGUID: string;
  programGUID: string;
  consentID: string;
}

export interface GetSaveBiometricConsentResultType {
  consentId: string;
  responseStatus: string;
}

export interface GetRegisterBasicUserResultType {
  rId: string;
}

export interface GetRegisterUserWithBiometricsResultType {
  programGUID: string;
  rId: string;
  bioToken: string;
  enrolmentStatus: string;
  responseStatus: string;
}

export interface GetWriteProfileResultType {
  consumerDeviceNumber: string;
}

export interface GetWritePasscodeResultType {
  responseStatus: string;
}

export interface GetErrorResultType {
  code: string;
  message: string;
}
