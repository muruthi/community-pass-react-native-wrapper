# Contributing

Contributions are always welcome, no matter how large or small!

We want this community to be friendly and respectful to each other. Please follow it in all your interactions with the project. Before contributing, please read the [code of conduct](./CODE_OF_CONDUCT.md).

## Development workflow

To get started with the project, run `yarn` in the root directory of the plugin and the root folder of the [example app](/example/) to install the required dependencies for each package:

```sh
yarn
```

or

```sh
npm install

```

> While it's possible to use [`npm`](https://github.com/npm/cli), the tooling is built around [`yarn`](https://classic.yarnpkg.com/), so you'll have an easier time if you use `yarn` for development.

While developing, you can run the [example app](/example/) to test your changes. Any changes you make in your library's JavaScript code will be reflected in the example app without a rebuild. If you change any native code, then you'll need to rebuild the example app.

To start the packager:

```sh
yarn example start
```

or

```
cd example
npx react-native start
```

To run the example app on Android:

```sh
yarn example android
```

or

```sh
cd example
npx react-native run-android
```

Make sure your code passes TypeScript and ESLint. Run the following to verify:

```sh
yarn typescript
yarn lint
```

or

```
npm run lint
```

To fix formatting errors, run the following:

```sh
yarn lint --fix
```

or

```
npm run lint --fix
```

Remember to add tests for your change if possible. Run the unit tests by:

```sh
yarn test
```

```sh
npm run test
```

To edit the Kotlin files, open `example/android` in Android studio and find the source files at `reactnativecpklibrary` under `Android`.

### Add the Community Pass Kernel Library File to your Android Project

To help you connect to the Community Pass Kernel, our team created the Community Pass Kernel Library (.AAR file) that bridges the gap between your application and the Community Pass Kernel. This library will enable you to develop an application using the CPK service’s APIs.

Please follow the section 5 instructions at Mastercard Developer Zone to complete this requirement:

- [Section 5](https://developer.mastercard.com/cp-kernel-integration-api/tutorial/getting-started-guide/step5/): Add the Community Pass Kernel Library to your Android project

At the end of this step you will have added the Community Pass Kernel Library to your local copy of tthis library.

> Watch out for possible duplicates before adding new dependencies to the build.gradle files in the android folder.

---

### Commit message convention

We follow the [conventional commits specification](https://www.conventionalcommits.org/en) for our commit messages:

- `fix`: bug fixes, e.g. fix crash due to deprecated method.
- `feat`: new features, e.g. add new method to the module.
- `refactor`: code refactor, e.g. migrate from class components to hooks.
- `docs`: changes into documentation, e.g. add usage example for the module..
- `test`: adding or updating tests, e.g. add integration tests using detox.
- `chore`: tooling changes, e.g. change CI config.

Our pre-commit hooks verify that your commit message matches this format when committing.

### Linting and tests

[ESLint](https://eslint.org/), [Prettier](https://prettier.io/), [TypeScript](https://www.typescriptlang.org/)

We use [TypeScript](https://www.typescriptlang.org/) for type checking, [ESLint](https://eslint.org/) with [Prettier](https://prettier.io/) for linting and formatting the code, and [Jest](https://jestjs.io/) for testing.

Our pre-commit hooks verify that the linter and tests pass when committing.

### Publishing

> Please note that the production version of the library is hosted at [CP Assets](https://developer.mastercard.com/cp-kernel-integration-api/documentation/cp-assets/cp-assets-request/) for easier versioning.

Please do not use these `yarn release` and `npm run release` commands with the library.

### Scripts

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

### Sending a pull request

> **Working on your first pull request?** You can learn how from this _free_ series: [How to Contribute to an Open Source Project on GitHub](https://app.egghead.io/playlists/how-to-contribute-to-an-open-source-project-on-github).

When you're sending a pull request:

- Prefer small pull requests focused on one change.
- Verify that linters and tests are passing.
- Review the documentation to make sure it looks good.
- Follow the pull request template when opening a pull request.
- For pull requests that change the API or implementation, discuss with maintainers first by opening an issue.
