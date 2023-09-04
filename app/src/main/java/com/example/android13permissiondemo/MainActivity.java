package com.example.android13permissiondemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAskNearByPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkWifiPermission(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "NearBy Device Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    requestWifiPermission(MainActivity.this, 2023);
                }
            }
        });

        findViewById(R.id.btnCheckNearByPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkWifiPermission(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "NearBy Device Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "NearBy Device Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnAskMediaPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkStoragePermission(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Storage Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    requestStoragePermission(MainActivity.this, 222);
                }
            }
        });

        findViewById(R.id.btnCheckMediaPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkStoragePermission(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Storage Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Storage Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
            }
        });


        findViewById(R.id.btnAskNotificationPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNotificationPermission(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Notification Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    requestNotificationPermission(MainActivity.this, 222);
                }
            }
        });

        findViewById(R.id.btnCheckNotificationPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNotificationPermission(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Notification Permission Already Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Notification Permission Not Granted", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        if (requestCode == 222) {
            Toast.makeText(this, "Storage Permission Granted Successfully", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 2023) {
            Toast.makeText(this, "NearBy Scan Permission Granted Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

    public static boolean checkStoragePermission(Activity activity) { //true if GRANTED
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(
                    activity,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    activity,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED;
        }

    }

    public static boolean requestStoragePermission(Activity activity, int requestId) {
        boolean isGranted = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            isGranted = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED;

        } else {
            isGranted = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }

        if (!isGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_AUDIO, Manifest.permission.READ_MEDIA_VIDEO,}, requestId);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestId);
            }

        }
        return isGranted;
    }


    public static boolean checkWifiPermission(Activity activity) { //true if GRANTED
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.NEARBY_WIFI_DEVICES) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public static boolean requestWifiPermission(Activity activity, int requestId) {
        boolean isGranted = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            isGranted = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.NEARBY_WIFI_DEVICES) == PackageManager.PERMISSION_GRANTED;
        }/* else {
            isGranted = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }*/

        if (!isGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.NEARBY_WIFI_DEVICES,}, requestId);
            } /*else {
                ActivityCompat.requestPermissions(activity, new String[]{
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestId);
            }*/

        }
        return isGranted;
    }

    public static boolean checkNotificationPermission(Activity activity) { //true if GRANTED
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public static boolean requestNotificationPermission(Activity activity, int requestId) {
        boolean isGranted = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            isGranted = ContextCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED;
        }

        if (!isGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS,}, requestId);
            }

        }
        return isGranted;
    }
}