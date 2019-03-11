--- 
title: Run mobile apps 
keywords: Mobile, Phone, Cordova, tablet, Android, iOS, App, AppStore, play market 
sidebar: guide-mobile-app_sidebar 
toc: false 
permalink: en/gma_launch-mobile-app.html 
lang: en 
autotranslated: true 
hash: 5469717507d6990bd9b6fa5f5823a8e77860bca63baa41ce2a075a819c053508 
--- 

## Description 

This step describes the process of launching mobile applications Android and iOS on various devices to test the developed applications. 

## the Launch of a mobile application for Android 

Start creating a mobile app in Apache Cordova there are three ways: 

### On the device emulator 

To run the application on the device emulator at a command prompt, run the command `cordova emulate android`, with the execution of this command, the build and launch of a mobile app. 

![](/images/pages/guides/mobile-app/cordova-emulate-android.png) 
![](/images/pages/guides/mobile-app/cordova-emulate-android1.png) 

{% include note.html content="If, after starting the emulator, not run the application, then at the command prompt, run the command cordova run android." %} 

![](/images/pages/guides/mobile-app/cordova-run-mobile-app.png) 

### On the connected to the computer device 

To run mobile apps on your phone at the command prompt, run the command `cordova run android`. 

![](/images/pages/guides/mobile-app/cordova-run-mobile-app2.png) 

{% include note.html content="If Cordova does not detect the phone connected to the computer running the application will be made on the device emulator." %} 

### app Using Android Studio 

Opening and launch apps [Android Studio](https://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#opening-a-project-in-android-studio). 

## launch of a mobile app iOS 

To view mobile app for iOS, you must open the workspace file `platforms/ios/Flexberry Ember Demo.xcodeproj` in **Xcode** or in the command line to execute the command `$ open ./platforms/ios/Flexberry\ Ember\ Demo.xcodeproj/`. 

Make sure `Flexberry Ember Demo` project selected in the left pane (1). 

![](/images/pages/guides/mobile-app/open-a xcode.png) 

Start creating a mobile application in two ways: 

### On the device emulator 

1.Select the desired device from the menu **Scheme** the toolbar (example: `iPhone 6S Simulator`) as shown in the figure above under the numeral (2). 

2.Click to Run» «(3) on the same toolbar to the left of the **Scheme**. When the button is created, you deploy and run the app in the simulator. To display the application opens a separate simulator application. 

![](/images/pages/guides/mobile-app/open-mobeli-app.png) 

{% include note.html content="For once you can run only one simulation, so if you want to test the application in a different simulator, you must exit the simulator and start a different simulator in Xcode." %} 

### On the connected to the computer device 

Before deployment you must perform the following steps: 

1.Create a profile `Provisioning Profile` in the [iOS Provisioning Portal](https://developer.apple.com/ios/manage/overview/index.action). 

2.Make sure that the option `Signing Identity` in section `Code Signing` in build settings is set to your profile name. 

![](/images/pages/guides/mobile-app/signing-identity.png) 

To deploy on the device: 

1.Use `USB-кабель` to connect the device to the computer `Mac`. 

2.Select the project name from the drop-down list **Scheme** window `Xcode` . 

3.Select a device from the drop-down list `Device`. If the device is connected via USB but is not visible, click `Organizer` to fix any errors. 

4.Click `Run` to create, deploy and run the application on the device. 

![](/images/pages/guides/mobile-app/iphone-5s.png) 

In the result of the execution of this step has been described the process of launching mobile apps. Next will be articles on how to publish the created app in the AppStore or Google Play. 

## You can 

* [Go to next step ->](gma_publish-mobile-app.html) 
* [<- Back to previous step](gma_build-mobile-app.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}