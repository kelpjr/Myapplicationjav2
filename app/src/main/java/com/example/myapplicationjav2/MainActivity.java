package com.example.myapplicationjav2;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplicationjav2.databinding.ActivityMainBinding;

import android.Manifest;


public class MainActivity extends AppCompatActivity {

    public static final int RequestPermissionCode = 123;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.addDataFrag,
                R.id.findPedFrag, R.id.findDocFrag, R.id.settingsFragment)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        RequestPermissions();

        if (!CheckPermissions()) {

        }
        ;
    }

    public boolean CheckPermissions() {
        // this method is used to check permission
        int rec = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            //Android is 11 (R) or above
            return Environment.isExternalStorageManager() && rec == PackageManager.PERMISSION_GRANTED;
        }else {
            //Below android 11
            int write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

            return read == PackageManager.PERMISSION_GRANTED && write == PackageManager.PERMISSION_GRANTED && rec == PackageManager.PERMISSION_GRANTED;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] Results) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (Results.length > 0) {
                    boolean StoragePermission = Results[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = Results[1] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (StoragePermission && RecordPermission) {
//                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void RequestPermissions() {
        // this method is used to request the
        // permission for audio recording and storage.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                // If you don't have access, launch a new activity to show the user the system's dialog
                // to allow access to the external storage
            } else {
                Intent intent = new Intent();
                intent.setAction(
                        Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{ WRITE_EXTERNAL_STORAGE,RECORD_AUDIO}, RequestPermissionCode);
    }
}