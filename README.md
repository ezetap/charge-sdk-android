# charge-sdk-android

This section is meant for people who have existing android native and would like to integrate Ezetap PoS solution through a native android SDK implementation.

######This documentation contains the instructions to integrate-
Ezetap Native Android SDK

#1. Native Android Integration

If you have an Deploy-able Android Native Application this API will help you integrate Ezetap Services to your App. To integrate this API, you need to have a good grasp of Android app development, building APKs etc. The Ezetap integration part involves setting up a project, importing a library and then actual coding with just a few lines of code.

## Getting Started
1. Android development environment
2. Android phone that can connect to internet
3. This documentation
4. Ezetap app key or login credentials to Ezetap service
5. Ezetap device to test

##Sample App
There is a sample Android App inside the sample folder of the repository. You can use this project as a reference to integrate Ezetap SDK.

#####Follow the steps below to get the demo app working-
1. Import the project as an Android Project in Eclipse IDE.
2. Clean & Build the project.
3. Run the Sample application on your Smartphone.
4. EzeNativeSampleActivity.java will be your point of reference for Native Android SDK integration

>Note- The errors you may face while importing the project will most likely be for Android version mismatch which EclipseIDE would normally resolve itself. Changing the Android version or restarting the Eclipse can help u solve this problem

## Steps to follow-
* You can find ChargeAPI jar file in the releases folder of this repository, Add the jar file in <a href="https://github.com/ezetap/charge-sdk-android/tree/master/release">libs</a> folder of your Native Android Project.
* In the manifest file of your project add the permission WRITE_EXTERNAL_STORAGE, which looks like this-
```xml
	    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
* In the manifest file of your Android Project add a new Activity, which looks like this-
```xml
		<activity android:name="com.eze.api.ChargeAPIActivity"
	        android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale"
	        android:screenOrientation="portrait"
	        android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
```
* IMPORTANT- If your project's targetSdkVersion is higher or equal to 23(Android 6.0 Marshmallow) please add Android support library v4 to your Android project from <a href="http://developer.android.com/tools/support-library/setup.html">here.</a> The Android support libraries are not required if your project's targetSdkVersion is lesser than 23.
* Good to go, please refer <a href="http://developers.ezetap.com/api/"> Ezetap API Portal</a> for API usage

>Note- The EzeAPIActivity has to be configured with the same attributes as given above.