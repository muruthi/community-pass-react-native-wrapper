# Contributing

Contributions are always welcome, no matter how large or small!

We want this community to be friendly and respectful to each other. Please follow it in all your interactions with the project. Before contributing, please read the [code of conduct](./CODE_OF_CONDUCT.md).

To get started please complete the following steps:

## 1 Fork the library

1. Fork the community-pass-react-native-wrapper repository
2. Create your feature branch (git checkout -b my-new-feature)

## 2 Install dependencies

> While it's possible to use [`npm`](https://github.com/npm/cli), the tooling is built around [`yarn`](https://classic.yarnpkg.com/), so you'll have an easier time if you use `yarn` for development for the following reasons:
>
> - `yarn` is faster because it installs dependency packages in parallel while `npm` installs them sequentially.
> - `yarn` automatically runs security checks in the background and uses the built-in license checker to avoid downloading dangerous scripts.
> - `yarn` prevents dependency issues (incompatibility).

1. Open your command line and navigate to the root folder of your project. See example below:

```sh
cd /path/community-pass-react-native-wrapper
```

2. Run the following command on your command line to install the [library dependencies](/package.json):

```sh
yarn
```

or

```sh
npm install

```

3. After you have completed step 3, please navigate to the root folder of the [example app](/example/). See example below:

```sh
cd /path/community-pass-react-native-wrapper/example
```

4. Run the following command on your command line to install the [example app dependencies](/example/package.json):

```sh
yarn
```

or

```sh
npm install

```

## 3 Add the Community Pass Kernel Library file to your Android Project

To help you connect to the Community Pass Kernel, our team created the Community Pass Kernel Library (.AAR file) that bridges the gap between your application and the Community Pass Kernel. This library will enable you to use the CPK service’s APIs while working on this library.

### 3.1 Pre-Requisites

- You will need to use the Android Studio
- Download the Commmunity Pass Kernel AAR library which can be accessed through [CP Assets Request](https://developer.mastercard.com/cp-kernel-integration-api/documentation/cp-assets/cp-assets-request/). We will show you how to add the AAR to your project.

```

NOTE: Please note that you will need to request access to the AAR through CP Assets Request. The approval may take 1-2 business days. Once you have access, proceed to download the AAR library for your development environment.

```

### 3.2 Adding the Community Pass Kernel Library File

The following are the steps required to set up your project with the Community Pass Client SDK:

#### **3.2.1 Locate and Move the Community Pass Kernel Library File to your Android Studio Project**

1. Locate the folder where you downloaded the AAR library to. The library will have a name similar to the following example: `community-pass-library-2.4.0.aar`
2. Start your Android Studio and click on the open button at the top right corner.

3. Navigate to the location of the `community-pass-react-native-wrapper` folder and select the android folder at the root level of the project. Click open.

```
Please note that there are two android folders in the project.

- In the root level of the library files (Open this)
- Inside the example folder

```

![](/docs/assets/open-project-2.png)

4. The project will take some time to build. After the build process is completed, click on the Project Tab in the top left corner and then click on the Project dropdown to open `community-pass-react-native-wrapper`.

5. Navigate through `community-pass-react-native-wrapper` from the dropdown > app > libs
6. Copy your AAR file into the libs folder in your Android Studio as shown in the figure below:

![](/docs/assets/add-aar-android-studio.png)

**Figure 1** Move the Community Pass Kernel Library file to your Android Studio project

#### **3.2.2 Add the CP Kernel client dependency to your project**

1. Click on **File > Project Structure > Dependencies**
2. Select “All Modules” from the Modules Tab and click on the “+” sign under All Dependencies (highlighted in red in Figure 16 below)
3. Select JAR/AAR Dependency (Note: Some Android Studio Versions will only show JAR Dependency. Select this)
4. Enter the following path in step 1 of the navigation window:
   - `libs/community-pass-library-v2.4.0.aar`
5. Leave the selection of “implementation” on step 2 and click OK. The window will close. Click Apply.
6. The project will take some time to build. Then click OK to close the Project Structure window.

**Expand for step by step screenshots**

<details closed>
<summary>Expand</summary>

![](/docs/assets/add-aar-1.png)

![](/docs/assets/add-cpl-aar-as-dependency.png)

**Figure 2** Add the CP Kernel client dependency to your project

</details>

<br/>

#### **3.2.3 Confirm Community Pass Kernel Library file (AAR file) has been added to your project**

To confirm that the AAR file has been added, navigate to **Project > MyApp > app > build.gradle**. You should see the following under dependencies:

```gradle
implementation files('libs/community-pass-library-2.4.0.aar')
```

![](/docs/assets/check-aar-installation.png)

**Figure 3** Check Community Pass Kernel Library file (AAR file) has been added to Android Project

#### **3.2.4 Add JJWT required libraries to your Android Studio project**

In the same build.gradle file as above, add the following additional libraries under dependencies using the code provided below:

> Note: If you have an existing solution or Reliant Application, you can choose to modify the minor or patch versions of these dependencies depending on your use case, but the major versions should remain consistent. For example:
>
> - implementation 'com.google.code.gson:gson:2.9.0' is compatible with the CPK library
> - implementation 'com.google.code.gson:gson:1.7.2' is not compatible

```gradle
{
    implementation 'com.google.code.gson:gson:2.8.6' //Required for object serialization and deserialization while performing CPK communication
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2' //Required for JWT encoding and decoding in some of the biometric use cases
    implementation 'io.jsonwebtoken:jjwt-impl:0.11.2' //Required for JWT encoding and decoding in some of the biometric use cases
    implementation 'io.jsonwebtoken:jjwt-orgjson:0.11.2' //Required for JWT encoding and decoding in some of the biometric use cases
    implementation 'org.apache.commons:commons-lang3:3.1' //Helper utilities required by the CPK library
}
```

A pop up notification will appear as given in the below image informing you that the Gradle files have changed. Click on Sync Now to synchronize the project with the Gradle files.

![](/docs/assets/android-studio-popup.png)

You should now have completed the process of adding the Community Pass AAR Library into your Android Studio Project.

You are now ready to install the CPK onto your POI device and connect your Reliant Application to the Community Pass Kernel services.

## 4 Start Metro Bundler and Run the Example Application

1. Open your command line and navigate to the example app folder of your project. See example below:

```sh
cd /path/community-pass-react-native-wrapper/example
```

2. To start the packager run the following command on your command line:

```sh
yarn start
```

or

```sh
npx react-native start
```

3. Open another command line and navigate to the example app folder of your project. See example below:

```sh
cd /path/community-pass-react-native-wrapper/example
```

4. Run the following command to build, install and start the example application on your POI device.:

```sh
yarn android
```

or

```sh
npx react-native run-android
```

> To edit the Kotlin files, open `example/android` in Android studio and find the source files at `community-pass-react-native-wrapper` under `Android`.

> While developing, you can run the [example app](/example/) to test your changes. Any changes you make in your library's JavaScript code will be reflected in the example app without a rebuild. If you change any native code, then you'll need to rebuild the example app.

## 5 Quality Expectations

Please ensure any contributions include unit tests. The project maintains a high level of test coverage for its functionality. Submissions are expected to maintain a similar level of coverage.

### 5.1 Run Type-Safety Checks, Code Tests and Lint

We use [TypeScript](https://www.typescriptlang.org/) for type checking, [ESLint](https://eslint.org/) with [Prettier](https://prettier.io/) for linting and formatting the code, and [Jest](https://jestjs.io/) for testing.

Our pre-commit hooks verify that the linter and tests pass when committing.

Before making `git commit` and `git push` actions, please pay attention to the following tips:

1. Make sure your code passes TypeScript and ESLint. Run the following to verify:

```sh
yarn typescript
yarn lint
```

or

```sh
npm run lint
```

2. To fix formatting errors, run the following:

```sh
yarn lint --fix
```

or

```sh
npm run lint --fix
```

3. Remember to add tests for your change if possible. Run the unit tests by:

```sh
yarn test
```

or

```sh
npm run test
```

### 5.2 Commit message convention

We follow the [conventional commits specification](https://www.conventionalcommits.org/en) for our commit messages:

- `fix`: bug fixes, e.g. fix crash due to deprecated method.
- `feat`: new features, e.g. add new method to the module.
- `refactor`: code refactor, e.g. migrate from class components to hooks.
- `docs`: changes into documentation, e.g. add usage example for the module..
- `test`: adding or updating tests, e.g. add integration tests using detox.
- `chore`: tooling changes, e.g. change CI config.

Our pre-commit hooks verify that your commit message matches this format when committing.

## 6 Publishing

Note that the production version of the library is hosted at [CP Assets](https://developer.mastercard.com/cp-kernel-integration-api/documentation/cp-assets/cp-assets-request/) for easier versioning. Please do not use `yarn release` and `npm run release` commands with the library.

## 7 Scripts

The `package.json` file contains various scripts for common tasks:

- `yarn typescript`: type-check files with TypeScript.
- `yarn lint`: lint files with ESLint.
- `yarn test`: run unit tests with Jest.
- `yarn example start`: start the Metro server for the example app.
- `yarn example android`: run the example app on Android.

and

- `npm run typescript`: type-check files with TypeScript.
- `npm run lint`: lint files with ESLint.
- `npm run test`: run unit tests with Jest.
- `npm react-native start`: start the Metro server for the example app.
- `npm react-native android`: run the example app on Android.

## 8 Sending a pull request

> **Working on your first pull request?** You can learn how from this _free_ series: [How to Contribute to an Open Source Project on GitHub](https://app.egghead.io/playlists/how-to-contribute-to-an-open-source-project-on-github).

When you're sending a pull request:

- Prefer small pull requests focused on one change.
- Verify that linters and tests are passing.
- Review the documentation to make sure it looks good.
- Follow the pull request template when opening a pull request.
- For pull requests that change the API or implementation, discuss with maintainers first by opening an issue.
