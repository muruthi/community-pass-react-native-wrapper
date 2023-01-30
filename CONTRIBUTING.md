# 1.0 Contributing

Contributions are always welcome, no matter how large or small!

We want this community to be friendly and respectful to each other. Please follow it in all your interactions with the project. Before contributing, please read the [code of conduct](./CODE_OF_CONDUCT.md).

To get started please complete the following steps:

## 1.1 Fork the library

1. Fork the community-pass-react-native-wrapper repository
2. Create your feature branch (git checkout -b my-new-feature)

## 1.2 Install dependencies

> While it's possible to use [`npm`](https://github.com/npm/cli), the tooling is built around [`yarn`](https://classic.yarnpkg.com/), so you'll have an easier time if you use `yarn` for development.

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

## 1.3 Add the Community Pass Kernel Library file to your Android Project

To help you connect to the Community Pass Kernel, our team created the Community Pass Kernel Library (.AAR file) that bridges the gap between your application and the Community Pass Kernel. This library will enable you to use the CPK service’s APIs while working on this library.

Please follow the instructions in section 5 on the Mastercard Developer Zone to complete this requirement:

- [Section 5](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step5/): Add the Community Pass Kernel Library to your Android project

At the end of this step you will have added the Community Pass Kernel Library to your local copy of tthis library.

> Watch out for possible duplicates before adding new dependencies to the build.gradle files in the android folder.

## 1.4 Start Metro Bundler and Run the Example Application

1. Open your command line and navigate to the root folder of your project. See example below:

```sh
cd /path/community-pass-react-native-wrapper
```

2. To start the packager run the following command on your command line:

```sh
yarn example start
```

or

```sh
cd example
npx react-native start
```

3. Open another command line and navigate to the root folder of your project. See example below:

```sh
cd /path/community-pass-react-native-wrapper
```

4. Run the following command to build, install and start the example application on your POI device.:

```sh
yarn example android
```

or

```sh
cd example
npx react-native run-android
```

> To edit the Kotlin files, open `example/android` in Android studio and find the source files at `reactnativecpklibrary` under `Android`.

> While developing, you can run the [example app](/example/) to test your changes. Any changes you make in your library's JavaScript code will be reflected in the example app without a rebuild. If you change any native code, then you'll need to rebuild the example app.

## 1.5 Quality Expectations

Please ensure any contributions include unit tests. The project maintains a high level of test coverage for its functionality. Submissions are expected to maintain a similar level of coverage.

### 1.5.1 Run Type-Safety Checks, Code Tests and Lint

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

### 1.5.2 Commit message convention

We follow the [conventional commits specification](https://www.conventionalcommits.org/en) for our commit messages:

- `fix`: bug fixes, e.g. fix crash due to deprecated method.
- `feat`: new features, e.g. add new method to the module.
- `refactor`: code refactor, e.g. migrate from class components to hooks.
- `docs`: changes into documentation, e.g. add usage example for the module..
- `test`: adding or updating tests, e.g. add integration tests using detox.
- `chore`: tooling changes, e.g. change CI config.

Our pre-commit hooks verify that your commit message matches this format when committing.

## 1.6 Publishing

Note that the production version of the library is hosted at [CP Assets](https://developer.mastercard.com/cp-kernel-integration-api/documentation/cp-assets/cp-assets-request/) for easier versioning. Please do not use `yarn release` and `npm run release` commands with the library.

## 1.7 Scripts

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

## 1.8 Sending a pull request

> **Working on your first pull request?** You can learn how from this _free_ series: [How to Contribute to an Open Source Project on GitHub](https://app.egghead.io/playlists/how-to-contribute-to-an-open-source-project-on-github).

When you're sending a pull request:

- Prefer small pull requests focused on one change.
- Verify that linters and tests are passing.
- Review the documentation to make sure it looks good.
- Follow the pull request template when opening a pull request.
- For pull requests that change the API or implementation, discuss with maintainers first by opening an issue.
