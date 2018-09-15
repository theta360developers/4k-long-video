# THETA Plug-in: CameraAPI Capture Plugin

Version: 1.0.0

This sample plug-in shows the way of capturing image with Camera API for RICOH THETA V.

## Contents

* [Terms of Service](#terms)
* [Development Environment](#requirements)
* [Specification of this plugin](#specification)
* [Getting Started](#started)
* [Trademark Information](#trademark)

<a name="terms"></a>
## Terms of Service

> You agree to comply with all applicable export and import laws and regulations applicable to the jurisdiction in which the Software was obtained and in which it is used. Without limiting the foregoing, in connection with use of the Software, you shall not export or re-export the Software  into any U.S. embargoed countries (currently including, but necessarily limited to, Crimea – Region of Ukraine, Cuba, Iran, North Korea, Sudan and Syria) or  to anyone on the U.S. Treasury Department’s list of Specially Designated Nationals or the U.S. Department of Commerce Denied Person’s List or Entity List.  By using the Software, you represent and warrant that you are not located in any such country or on any such list.  You also agree that you will not use the Software for any purposes prohibited by any applicable laws, including, without limitation, the development, design, manufacture or production of missiles, nuclear, chemical or biological weapons.

By using the RICOH THETA Plug-in SDK, you are agreeing to the above and the license terms, [LICENSE.txt](LICENSE.txt).

Copyright &copy; 2018 Ricoh Company, Ltd.

<a name="requirements"></a>
## Development Environment

This sample plug-in has been developed under the following conditions.

#### Camera

* RICOH THETA V
* Firmware ver.2.40.2 and above

    > Information on checking and updating the firmware is [here](https://theta360.com/en/support/manual/v/content/pc/pc_09.html).

#### RICOH THETA Plug-in SDK
* Version: 1.0.1

#### Development Software

* Android&trade; Studio 3.1+
* gradle 3.1.3
* Android&trade; SDK (API Level 25)
* compileSdkVersion 26
* buildToolsVersion "27.0.3"
* minSdkVersion 25
* targetSdkVersion 25

#### Operating System

* Windows 10 Version 1709
* macOS High Sierra ver.10.13

<a name="specification"></a>
## Specification of this plugin
* This plug-in capture still and video by using [Camera API](https://api.ricoh/docs/theta-plugin-reference/camera-api/), [AudioManager API](https://api.ricoh/docs/theta-plugin-reference/audio-manager-api/) and [PluginLibrary](https://github.com/ricohapi/theta-plugin-sdk/tree/master/pluginlibrary) for RICOH THETA V. This plug-in has been made as an example to show the way of using Camera API for RICOH THETA V.
* After capturing still or video, the JPEG or MP4+WAV file is stored in /sdcard/DCIM/ folder.
* The stored file name is "yyyyMMddHHmmss".jpg (.mp4 or .wav for video). "yyyyMMddHHmmss" is 20180123123456 when it is 12:34:56 Jan 23, 2018.
* WebAPI can not be used when Camera API is used.
* The .wav file includes 4ch spatial audio as a first-order ambisonics B-format.
* The .mp4 file includes 4K video and 1ch monaural audio.
* The metadata of the files (.mp4 and .jpg) which outputted by using CameraAPI will be missed than the case of using WebAPI. (We recommend to use WebAPI instead of CameraAPI.)

<a name="started"></a>
## Getting Started

0. Open Vysor chrome app to see desktop of the camera.
1. Initial setting for debugging

    At the first time to use this app, app need to be taken permissions to use camera and storage.
    Settings → Apps → "CameraAPI Capture Plugin" → Permissions →
      “Camera”, "Microphone" and “Storage” to be checked (turn ON).

2. Launch "CameraAPI Capture Plugin" app
    (Ignore button on GUI)
3. Press shutter button to take a photo/video
4. Pull JPEG/MP4/WAV by adb
    "adb pull /sdcard/DCIM/yyyyMMddHHmmss.jpg" ("yyyyMMddHHmmss" is 20180123123456 when it is 12:34:56 Jan 23, 2018.)

<a name="trademark"></a>
## Trademark Information

The names of products and services described in this document are trademarks or registered trademarks of each company.

* Android, Nexus, Google Chrome, Google Play, Google Play logo, Google Maps, Google+, Gmail, Google Drive, Google Cloud Print and YouTube are trademarks of Google Inc.
* Apple, Apple logo, Macintosh, Mac, Mac OS, OS X, AppleTalk, Apple TV, App Store, AirPrint, Bonjour, iPhone, iPad, iPad mini, iPad Air, iPod, iPod mini, iPod classic, iPod touch, iWork, Safari, the App Store logo, the AirPrint logo, Retina and iPad Pro are trademarks of Apple Inc., registered in the United States and other countries. The App Store is a service mark of Apple Inc.
* Bluetooth Low Energy and Bluetooth are trademarks or registered trademarks of US Bluetooth SIG, INC., in the United States and other countries.
* Microsoft, Windows, Windows Vista, Windows Live, Windows Media, Windows Server System, Windows Server, Excel, PowerPoint, Photosynth, SQL Server, Internet Explorer, Azure, Active Directory, OneDrive, Outlook, Wingdings, Hyper-V, Visual Basic, Visual C ++, Surface, SharePoint Server, Microsoft Edge, Active Directory, BitLocker, .NET Framework and Skype are registered trademarks or trademarks of Microsoft Corporation in the United States and other countries. The name of Skype, the trademarks and logos associated with it, and the "S" logo are trademarks of Skype or its affiliates.
* Wi-Fi™, Wi-Fi Certified Miracast, Wi-Fi Certified logo, Wi-Fi Direct, Wi-Fi Protected Setup, WPA, WPA 2 and Miracast are trademarks of the Wi-Fi Alliance.
* The official name of Windows is Microsoft Windows Operating System.
* All other trademarks belong to their respective owners.
