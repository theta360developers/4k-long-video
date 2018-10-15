# 4K Long Video Plug-in for RICOH THETA

This sample plug-in is based on the 
[CameraAPI Capture plug-in from Ricoh](https://github.com/ricohapi/theta-plugin-camera-api-sample).  

The plug-in uses the CameraAPI to record 4K video. It bypasses the 25 minute
video limitation of the standard camera.

The plug-in should be installed into the RICOH THETA V with adb.

## Additional Hacks

* default video encoder is H264. Per the
[Android Developer API documentation](https://developer.android.com/reference/android/media/MediaRecorder.VideoEncoder)
for VideoEncoder, this can be changed to HEVC. It works, but does get hot in my tests. If the camera overheats. set
the encoder back down to H264.
* you can fiddle around with `mMediaRecorder.setMaxDuration` to try and get a longer video

## Development Environment

* RICOH THETA V
* Firmware ver.2.40.2 and above

    > Information on checking and updating the firmware is [here](https://theta360.com/en/support/manual/v/content/pc/pc_09.html).

### RICOH THETA Plug-in SDK

* Version: 1.0.1

### Android Development Software

* Android&trade; Studio 3.1+
* gradle 3.1.3
* Android&trade; SDK (API Level 25)
* compileSdkVersion 26
* buildToolsVersion "28.0.2"
* minSdkVersion 25
* targetSdkVersion 25

### Operating System

* Windows 10 Version 1809
* macOS High Sierra ver.10.13

## Specification of this plugin

* This plug-in capture still and video by using [Camera API](https://api.ricoh/docs/theta-plugin-reference/camera-api/), [AudioManager API](https://api.ricoh/docs/theta-plugin-reference/audio-manager-api/) and [PluginLibrary](https://github.com/ricohapi/theta-plugin-sdk/tree/master/pluginlibrary) for RICOH THETA V. This plug-in has been made as an example to show the way of using Camera API for RICOH THETA V.
* After capturing still or video, the JPEG or MP4+WAV file is stored in /sdcard/DCIM/ folder.
* The stored file name is "yyyyMMddHHmmss".jpg (.mp4 or .wav for video). "yyyyMMddHHmmss" is 20180123123456 when it is 12:34:56 Jan 23, 2018.
* WebAPI can not be used when Camera API is used.
* The .wav file includes 4ch spatial audio as a first-order ambisonics B-format.
* The .mp4 file includes 4K video and 1ch monaural audio.
* The metadata of the files (.mp4 and .jpg) which outputted by using CameraAPI will be missed than the case of using WebAPI. (We recommend to use WebAPI instead of CameraAPI.)

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

![Analytics](https://ga-beacon.appspot.com/UA-73311422-5/4k-long-video-plugin)
