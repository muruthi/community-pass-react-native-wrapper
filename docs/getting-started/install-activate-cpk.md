# Section 6: Community Pass Kernel Installation & Activation

## 6.1 Objectives

The CPK Services APK distribution is closed and only available to registered members on the Community Pass platform.

Installing the Community Pass Kernel (CPK) assumes that you have submitted your program’s Reliant Application details through Section 4.4 above and have the following:

1. You received an email containing a secure link to download the CPK and an activation code that will be used thereafter. (This activation code is valid for only 12 hours.)
2. You have an approved POI Android device (details on approved devices are provided in [Section 1: Pre-requisites](pre-requisites.md))
3. You have enabled USB Debugging on your POI Android Device (Steps are in Section 2.4)

At the end of this section, you should have successfully installed the Community Pass Kernel (CPK) and activated it.

```
If your activation code has not been activated within 12 hours, please send an email to cp.patnerprogram[at]mastercard.com to get a new activation link.
```

## 6.2 Download, install, and activate the CPK

### 6.2.1 Instructions for MTF and Production Users using Samsung POI Devices

<details closed><summary>For MTF and Production Users</summary>

**Secure Element Personalization**

The Secure Element (SE) is a chip that is, by design, protected from unauthorized access and utilized to run a limited set of applications, as well as store confidential and cryptographic data. Smartphones, tablets, hardware cryptowallets, and other devices use Secure Element. The chip can store and process information such as PIN codes, passwords, fingerprints, payment information, and much more.

In addition to the SE, a software module called the Secure Access Module (SAM) enables the CPK APK to securely access the keys stored in the SE. The keys are loaded into the SE via a process called Key Provisioning.

**Personalization**

Interaction with a Community Pass card requires the CPK Secure Access Module (SAM) to be installed and the keys to be provisioned on the SE. SE personalization is the process of creating a secure domain on the SE to host the CP Kernel SAM. The security domain must be created and configured so that CPK can access the Secure Domain via the Fidesmo application.

The steps to personalize the Secure Element are as follows:

1. Install Fidesmo provided Android app on the POI device
    - Open any Web Browser on the POI device
    - Go to URL location: https://play.google.com/store/apps/details?id=com.fidesmo.sec.android
    - This will take you to the Google Play application/web page
    - Click on ‘Install’ to install Fidesmo
2. Once Installed, Open the App
3. Click on ‘Connect’
4. Select ‘This phone’ to initiate the personalization process. This may take a few minutes to complete. Do not close the application while the process is running.
5. Once completed, the Security Domain will have been created and the SE is personalized
6. After the successful creation of the Secure Domain, Fidesmo creates a unique ID for your device
7. You have now completed the Secure Element personalization.

Once all of the above steps are successfully completed, your POI device is ready to activate the CPK Services APK. Please proceed to the next section.
</details>

### 6.2.2 Download and Install the CPK (APK)

Open the email sent to you from Community Pass and click on the secure link shared to download the APK onto your POI device.

1. After Successful download, navigate to the downloads folder (located in my files > downloads) and click on the downloaded APK to install on the POI device
2. If ‘Install APK from unknown source’ is not enabled then enable it by clicking on settings. If this is already enabled, go to step 3
    - To enable ‘Install APK from unknown source’ click on downloaded APK and follow below steps
3. Install the APK.

![](/docs/assets/cpk-installation-1.png)

![](/docs/assets/cpk-installation-2.png)

**Figure 18** Installing the Community Pass Kernel APK

### 6.2.3 Activate CPK

The activation credentials are part of the invitation email sent to you that includes the download link for the CPK Services APK. Once you have successfully installed the CPK Services APK on an approved device, you should be able to activate it via the credentials received.

To activate the CPK Services APK and add a Community Pass Account on your POI device, please execute the following steps:

1. Open the email and retrieve the 16-digit activation code.
2. Go to “Android Device Settings -> Accounts”
3. Tap on “Add Account”
4. Select Community Pass Service from the list of available account types. A dialog will open asking you to enter the email address used to submit your reliant app and the activation code
5. Enter Email and the 16-digit activation code received by e-mail in the device activation screen
6. Click on “Activate”
7. Your CPK should now be activated

```
NOTE:The activation of the CPK might take a few minutes to complete. Kindly ensure that this process is complete before moving to the next step.
```

![](/docs/assets/cpk-activation.png)

**Figure 19** Activating the CPK using the credentials sent via email

### 6.2.4 Common Errors during activation process

As you go through the installation process, we have identified a number of common scenarios where you might encounter an error. The table below details the scenarios and associated error messages. We have also provided some ways to resolve these issues. Should you encounter any further challenges, please reach out to `cp.patnerprogram@mastercard.com` for assistance.


| **Error message**                                                                                                       | **Scenario**                                                                                    | **Resolution**                                                                                                                                                                            |
|-------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Invalid Email                                                                                                           | User entered an invalid email                                                                   | Ensure you use the email address used to send your Reliant Application Details and try again.                                                                                             |
| Incorrect Activation code                                                                                               | User entered an activation code of invalid length                                               | Check your Activation Code and re-enter the code again if within the 12 hour window. Alternatively please reach out to CP Admin to get a new Activation Code.                             |
| Device activation code is expired, generate new activation code                                                         | Device activation code is expired                                                               | Please reach out to CP Admin to get a new Activation Code.                                                                                                                                |
| Error 110001: The device was partially set up and needs to be reactivated.                                              | Kernel account already exists on device                                                         | No action required. Reliant Application should be able to access APIs as expected. If the Reliant Application can not access certain APIs, uninstall and repeat the installation process. |
| Error 110002: Email ID or activation code could not be verified. Please check your connectivity and activation details  | Email address or Activation code is not correct                                                 | Please check that the Activation code is within the 12 hour window. Re-enter the email address and activation code afresh and try again.                                                  |
| Error 110003: The necessary files failed to download. Please check connectivity and try again.                          | Device configurations could not be downloaded                                                   | Check that your device is connected to an active internet connection and try again                                                                                                        |
| Error 110007: The necessary files failed to download. Please check connectivity and try again.                          | Device provisioning failed                                                                      | Check that your device is connected to an active internet connection and try again                                                                                                        |
| Error 110005: The device is not appear to be compatible. Please contact your Program Manager.                           | Device lacks a Hardware backed secure element or NFC technology                                 | Your device is not supported by Community Pass Kernel. Install the Community Pass Kernel on a Community Pass Approved Device as provided in POI Service Overview                          |
| Error. Check connectivity status and try again                                                                          | Device was not connected to an active internet connection while attempting activation           | Check that your device is connected to an active internet connection and try again                                                                                                        |
| Device details are not matching with rules                                                                              | The device in use is not supported by the Community Pass Kernel                                 | Your device is not supported by Community Pass Kernel. Install the Community Pass Kernel on a Community Pass Approved Device as provided in POI Service Overview                          |
| Error 110006: Community Pass has encountered an issue with your device and cannot be activated. CPK will be deactivated | The device in use is either rooted or emulated                                                  | Install the CP Kernel on non-compromised device.                                                                                                                                          |
| Enable NFC to use application                                                                                           | Occurs when a user starts the device activation process without turning on the NFC Setting      | Turn on the NFC toggle in the Android Settings and try again.                                                                                                                             |
| 851: Device initialization failed. Do you wish to continue?                                                             | Occurs if the device is not personalised on Samsung devices using MTF and Production CPK builds | Follow the process in step 6.2.1 to personalise the device and try again                                                                                                                  |



## 6.3 (Optional) Capturing Kernel Issues through Logging

```
NOTE:This functionality is available on Community Pass Kernel v2.1.6 and above. We recommend you always have file logging enabled
```

In the event that you’re having issues communicating with the Community Pass Kernel we offer two levels of logging via either the console and/or file.

- **Console logging** writes on the Logcat within Android Studio. This can be especially helpful if you need to see the logs side by side with your Reliant Application.

![](/docs/assets/cpk-console-channel-logs.png)

- **File logging** writes on a file stored within the Android POI device that you’re using. This mode is helpful if you need to share these logs with our engineering team for further debugging.

![](/docs/assets/cpk-file-channel-logs-2.png)

We also offer two levels of logging depending on the amount of information required for troubleshooting

- **Info Level** logs show only operations invoked
- **Debug Level** logs show operations invoked, parameter values passed and other rich metadata helpful for debugging issues.

### 6.3.1. Enabling Logs

You can enable the logs using two ways:

1. Via the Device Activation Screen: This is done in cases where the Kernel has unknown errors in the pre-activation process. Use this method during the CPK activation in step 6.2 above.
2. Via the Community Pass Account Information Screen: Used if there are unknown errors that occur post-activation

#### **6.3.1.1. Via the Device Activation Screen | Pre-Activation**

1. Go to “Android Device Settings -> Accounts”
2. Tap on “Add Account”
3. Select Community Pass Service from the list of available account types. A dialog will open asking you to enter the email address used to submit your reliant app and the activation code
4. Click on the menu button on the top-right of the screen and click on ‘CP Logging Configuration’

![](/docs/assets/cpk-logging-config-trigger-1.png)

5. Toggle the Enable logs option on

![](/docs/assets/cpk-enable-logs-1.png)

6. Click on ‘Log Channel’ to choose either File or Console logging.

![](/docs/assets/cpk-enable-logs-2.png)

7. Optionally use the ‘File Size’ slider to choose the size of the log files generated. Larger log files take up more space and bandwidth when sending online
8. Optionally use the ‘File Retention Period’ slider to choose the length of days it takes before logs are deleted from the device. The longer the amount of days, the more the storage used on the device.

You should now see logs appear on the channel of choice

#### **6.3.1.2. Via the Community Pass Account Information Screen | Post-Activation**

1. Go to “Android Device Settings -> Accounts”
2. Tap on “Manage Accounts”
3. Select Community Pass from the list of accounts added
4. Click on ‘CP Logging Configuration’ option.

![](/docs/assets/cpk-logging-config-trigger-2.png)

5. Toggle the Enable logs option on

![](/docs/assets/cpk-enable-logs-1.png)

6. Click on ‘Log Channel’ to choose either File or Console logging.

![](/docs/assets/cpk-enable-logs-2.png)

7. Optionally use the ‘File Size’ slider to choose the size of the log files generated. Larger log files take up more space and bandwidth when sending online
8. Optionally use the ‘File Retention Period’ slider to choose the length of days it takes before logs are deleted from the device. The longer the amount of days, the more the storage used on the device.


You can access more information about logging and how to send your logs to us from [Kernel Logging Configuration](https://developer.mastercard.com/cp-kernel-integration-api/documentation/reference-pages/cpk-logging-configuration)


[Next]()