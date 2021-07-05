package com.example.test_library;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;


import com.android.reportx.util.RP;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                RP.debug = true;
                RP.launch(MainActivity.this);
            }
        });

        //chech pemission
        int checkPermissionResult = CheckPermission.verifyStoragePermissions(MainActivity.this);
        if(checkPermissionResult == PackageManager.PERMISSION_GRANTED){
            //write2SDCard("a.txt","hello");
        }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //权限已成功申请
                    //Toast.makeText(this,grantResults[0]+"",Toast.LENGTH_SHORT);
                    //Log.e(TAG, String.valueOf(grantResults[0]));
                    //write2SDCard("a.txt","hello");
                } else {
                    //用户拒绝授权
                    Toast.makeText(this, "无法获取SD卡读写权限", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //
    private void write2SDCard(String fileName,String content){
        //1、判断sd卡是否可用
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //sd卡可用
            //2、获取sd卡路径
            try{
                File sdFile = Environment.getExternalStorageDirectory();
                //File sdFile = getExternalFilesDir(null);
                File packageSDDir = new File(sdFile,"/com.example.test_library/");
                if(!packageSDDir.exists() || !packageSDDir.isDirectory()){
                    boolean res = packageSDDir.mkdirs();//
                    //Log.e(TAG,String.valueOf(res));
                }
                File filePath = new File(packageSDDir,fileName);//sd卡下面的a.txt文件 参数 前面 是目录 后面是文件
                if(!filePath.exists() || !filePath.isFile()){
                    filePath.createNewFile();
                }

                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                fileOutputStream.write(content.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}