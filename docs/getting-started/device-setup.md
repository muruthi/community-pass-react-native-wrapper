# Section 2: Setting Up Your Android Device

## 2.1 Objectives
By the end of this section you will have completed the following:

- Conducted initial manufacturer set up of device
- Enabled USB debugging to prepare you for development work

## 2.2 Prepping your device
Unbox your device and ensure you have the following provided inside the box:
- Community Pass Approved Android Device
- Battery
- Device Charger
- Cover (optional)

```
TIP:You may require a small coin or tool to pop the back cover off.
```

2.3 Setting up the POI
The following steps provide a guide into how to initiate and update the device that will then be used to build and test the integration of your Reliant App to the Community Pass Kernel:

1. After unboxing your device, remove it from the case.
2. If the battery is not pre-installed, follow manufacturer instructions to install it into your device

```
TIP:Samsung users may find the following visuals helpful: Install cards and the battery
```

3. Depending on the type of device you have, you may also need to install a SIM card
4. Charge the device before powering it on
5. Upon starting the device, go through the setup questions and connect the device to Wi-Fi and/or a Mobile Network connection (For SIM supported device)
6. Ensure the device is updated. You may get notifications to update the Android version (see Figure 2.1)
7. After any device updates, your device should now be ready to proceed to the next section

**Expand for step-by-step screenshots**

<details closed><summary>Updating your Android Device</summary>

</details>

```
NOTE:Your device may restart multiple times to complete software updates
```

## 2.4 Enabling USB Debugging on your POI device
Debugging options enable required development capabilities, such as on-device log data and debugging, as well as communication between the device and your development computer.

Depending on your device type, please follow the steps below by expanding the appropriate section to enable USB debugging:

<details closed><summary>Samsung Devices</summary>

1. Open the Settings app.
2. Select “About Tablet”
3. Select “Software Information”
4. Find “Build number”
5. Tap Build number seven times - Tablet will then give you Developer Options
6. Return to the “Settings” screen, scroll to the bottom to find “Developer Options.”
7. In “Developer options”, find and enable “USB debugging.”

![](/docs/assets/samsung-settings-page.png)

**Figure 2.2** Settings Page

![](/docs/assets/samsung-enable-debug-mode.png)

**Figure 2.3** Debugging options enabled

</details>

<details closed><summary>Famoco Devices</summary>

1. Open the Settings app
2. Select “System”
3. Select “About phone”
4. Find “Build number”
5. Tap Build number seven times - Tablet will then give you Developer Options
6. Return to the “System” screen, scroll to find “Developer Options”
7. In “Developer options”, find and enable “USB debugging.”

![](/docs/assets/famoco-settings-page.png)

**Figure 2.4** Settings Page

![](/docs/assets/famoco-systems-screen.png)

**Figure 2.5** System Page

![](/docs/assets/famoco-enable-debug.png)

**Figure 2.6** Debugging options enabled
</details>


<br/>

[Next Section](dev-environment-setup.md)