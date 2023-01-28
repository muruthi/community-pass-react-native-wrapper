# Section 4: Submit your Reliant App details to Community Pass

## 4.1 Objectives

In this step, you will be able to submit your Reliant application details to Community Pass. This will enable you to get access to the Community Pass Kernel that will get you ready to start testing out the capabilities provided through Community Pass.

As mentioned in the overview, the Community Pass SDK comprises the Community Pass Kernel which is the headless app, and the Client Library which is embedded in the Community Pass React Native library. The connection of the Client Library to the CPK is how your application will execute Community Pass related commands. Please refer to Figure 1 in the Overview for any additional context.

At the end of this section, you will be able to complete the following:

- Request the Community Pass Kernel (an .APK file for the Community Pass Kernel).
- Submit your Reliant / test application details to the Community Pass team
- Receive the following which you will need in subsequent steps:
- Reliant app identifier or Reliant app GUID
- Program Identifier or Program GUID
- Activation code(s) to be used to activate the Community Pass Kernel

## 4.2 Pre-requisites

In order to get started in this section, ensure you have completed the steps in Sections 1-3. At this point, you should have:

Android Studio set-up correctly based on the minimum requirements provided in [Section 3](dev-environment-setup.md)

- The POI device has successfully connected to your Android Development Environment
- You have created a new Reliant app (or are using an existing app) and you have seen Android Studio run your app on the POI

## 4.3 Registering Your Application with Community Pass Admin Portal

In order for a Reliant Application to securely authenticate, interact and consume the CP Kernel Service on the POI device, you must provide details about your Reliant application to the Community Pass onboarding team.

A program in this case is defined as a partner identity within the Community Pass Platform that will enable the Community Pass Team to identify your program. A program name could be your company name or a use case for which the Reliant application will be used (for example, “Merchant Onboarding”).

You should obtain and submit the following four details about your application:

- **Program Name**: the name of your program as you would like it to appear in Community Pass
- **Application Name**: the name of the application created in [Section 3](dev-environment-setup.md)
- **Application Identifier**: the unique application ID created during the creation of your Application Name and associated with your Reliant application
- **Certificate Fingerprint**: also called SHA1: this is a unique key that validates your Reliant application.

This process will enable you to receive the Community Pass Kernel (in the form of an Android APK) and the necessary permissions required to authorize a Reliant application’s access to read and write program specific data.

The following steps outline how to collect these pieces of information from your development application environment:

## 4.3.1 Application Name: your application’s name

1. Navigate to the Project and App section located on the left side of the Android Studio window
2. Locate “settings.gradle” under Gradle Scripts.
3. In the settings.gradle file, find the setting `rootProject.name`. This is your application name.

![](/docs/assets/locate-app-name.png)

**Figure 12** Locating your application name

## 4.3.2. Application Identifier: your application ID

1. Under Gradle Scripts, locate the module-level build.gradle, (not to be confused with the project-level build.gradle)
2. Find the applicationId (it should be something like `com.example.myapp`) as shown in Figure 14 below

![](/docs/assets/locate-app-id.png)

**Figure 13** Locating your application ID

## 4.3.3 Certificate Fingerprint: SHA-1 fingerprint of signing certificate

- To fetch the “SHA-1 fingerprint of signing certificate” you need to perform the signingReport gradle task outlined below. This is done via Android Studio on your developer computer.

### 4.3.3.1. Fetching SHA-1 fingerprint

1. Click the “Gradle” tab on the right toolbar
2. Click the root application name e.g. My App, and then click on Tasks > android > signingReport
3. This should produce a signing report as shown below. Copy the SHA1 value and share it with us.

```
TIP:If you cannot locate the Gradle Tasks, follow the below steps, and then proceed to perform the steps in 4.3.3.1 above:

- Window Users: Go to File -> Settings -> Experimental and uncheck Do not build Gradle task list during Gradle sync. Press OK and then sync the project located in File -> Sync Project with Gradle Files.
- Mac Users: Go to Android Studio -> Preferences -> Experimental and uncheck Do not build Gradle task list during Gradle sync. Press Apply and OK. You can now sync the project located in File -> Sync Project with Gradle Files.
```

```
Task :app:signingReport
Variant: debug
Config: debug
Store: ~/.android/debug.keystore
Alias: AndroidDebugKey
MD5: A5:88:41:04:8D:06:71:6D:FE:33:76:87:AC:AD:19:23
SHA1: A7:89:E5:05:C8:17:A1:22:EA:90:6E:A6:EA:A3:D4:8B:3A:30:AB:18
SHA-256: 05:A2:2C:35:EE:F2:51:23:72:4D:72:67:A5:6C:8C:58:22:2A:00:D6:DB:F6:45:D5:C1:82:D2:80:A4:69:A8:FE
Valid until: Wednesday, August 10, 2044
```

![](/docs/assets/locate-sha1-1.png)

![](/docs/assets/locate-sha1-2.png)

Figure 14 Locating your SHA1 certificate using Android Studio
