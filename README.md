# react-native-chrome-intent

##### [Android Intents with Chrome](https://developer.chrome.com/multidevice/android/intents)

A little known feature in Android lets you launch apps directly from a web page via an Android Intent.

### Install

```shell
npm install --save react-native-chrome-intent
```

or

```shell
yarn add react-native-chrome-intent
```

## Usage

```javascript
import AndroidIntent from "react-native-chrome-intent";

AndroidIntent.openIntent(
  "intent://[HOST/URI-path]#Intent;package=[string];action=[string];category=[string];component=[string];scheme=[string];end;"
).then((wasOpened) => {});
```
