import { Platform, Linking, NativeModules } from "react-native";

const AndroidIntentModule = NativeModules.AndroidIntent;

const AndroidIntent = {
  openIntent(dataUri) {
    if (Platform.OS === "android") {
      return AndroidIntentModule.openIntent(dataUri);
    } else {
      Linking.openURL(dataUri).catch((er) => {
        console.warn("Failed to open Link: " + er.message);
      });
    }
  },
};

export default AndroidIntent;
