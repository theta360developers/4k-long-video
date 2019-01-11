/**
 * Copyright 2018 Ricoh Company, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package guide.theta360.long4kvideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.WindowManager;
import com.theta360.pluginlibrary.activity.PluginActivity;
import com.theta360.pluginlibrary.callback.KeyCallback;
import com.theta360.pluginlibrary.receiver.KeyReceiver;
import com.theta360.pluginlibrary.values.LedColor;
import com.theta360.pluginlibrary.values.LedTarget;

public class MainActivity extends PluginActivity implements CameraFragment.CFCallback {
    private boolean isVideo = false;
    private boolean isEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // should close plug-in when end processing completes
        setAutoClose(true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Set a callback when a button operation event is acquired.
        setKeyCallback(new KeyCallback() {
            @Override
            public void onKeyDown(int keyCode, KeyEvent event) {
                if (keyCode == KeyReceiver.KEYCODE_CAMERA) {
                    if (isVideo) {
                        if (!takeVideo()) {
                            // Cancel recording
                            notificationAudioWarning();
                        }
                    } else {
                        takePicture();
                    }
                }
            }

            @Override
            public void onKeyUp(int keyCode, KeyEvent event) {
                if (keyCode == KeyReceiver.KEYCODE_MEDIA_RECORD) {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
                    if (fragment != null && fragment instanceof CameraFragment) {
                        if (((CameraFragment) fragment).isMediaRecorderNull() && !(((CameraFragment) fragment).isCapturing())) {
                            // not recording video or capturing still
                            isVideo = !isVideo;
                            updateLED();
                        }
                    }
                }
            }

            @Override
            public void onKeyLongPress(int keyCode, KeyEvent event) {
                if (keyCode == KeyReceiver.KEYCODE_MEDIA_RECORD) {
                    endProcess();
                }
            }
        });

        notificationWlanOff();
        notificationCameraClose();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLED();
    }

    @Override
    protected void onPause() {
        // added close
//        close();
//        endProcess();

        super.onPause();
    }

    @Override
    public void onShutter() {
        notificationAudioShutter();
    }

    @Override
    public void onPictureTaken() {

    }

    private void takePicture() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment != null && fragment instanceof CameraFragment) {
            if (!(((CameraFragment) fragment).isCapturing())) {
                ((CameraFragment) fragment).takePicture();
            }
        }
    }

    private boolean takeVideo() {
        boolean result = true;
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (fragment != null && fragment instanceof CameraFragment) {

            if (((CameraFragment) fragment).isMediaRecorderNull()) {
                // start recording
                if (!(((CameraFragment) fragment).isCapturing())) {
                    notificationAudioMovStart();
                    notificationLedBlink(LedTarget.LED7, LedColor.RED, 2000);
                }
                result = ((CameraFragment) fragment).takeVideo();
            } else {
                // stop recording
                result = ((CameraFragment) fragment).takeVideo();
                if (result) {
                    notificationAudioMovStop();
                }
                notificationLedHide(LedTarget.LED7);
            }
        }
        return result;
    }

    private void updateLED() {
        if (isVideo) {
            notificationLedHide(LedTarget.LED4);
            notificationLedShow(LedTarget.LED5);
        } else {
            notificationLedHide(LedTarget.LED5);
            notificationLedShow(LedTarget.LED4);
        }
    }

    private void endProcess() {
        if (!isEnded) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
            if (fragment != null && fragment instanceof CameraFragment) {
                if (!((CameraFragment) fragment).isMediaRecorderNull()) {
                    takeVideo(); // stop recording
                }
            }
            close();
            isEnded = true;
        }
    }
}
