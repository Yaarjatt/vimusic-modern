# Spoof Update — Android Music Native Client

Changes made to bypass hidden-client block:
- `Innertube.kt`: Removed `BrowserUserAgent`, set native `ANDROID_MUSIC` User-Agent (`com.google.android.apps.youtube.music/7.04.52`), added `X-YouTube-Client-Name` / `X-YouTube-Client-Version`, switched API key to Android Music (`AIzaSyAOghZGza2MQSZkY_zfZ370N-PUdXEo8AI`).
- `Context.kt`: Updated `DefaultAndroid` to version `7.04.52` / SDK 34 / Android 14.
- All request bodies (`BrowseBody`, `NextBody`, `SearchBody`, `QueueBody`, `ContinuationBody`, `SearchSuggestionsBody`): Changed default from `DefaultWeb` to `DefaultAndroid`.

Note: This spoofs a native YouTube Music client. YouTube may still return `UNPLAYABLE` server-side (service restriction, not a code bug). No code change fully guarantees playback if YouTube enforces hidden-client blocking at the stream level.
