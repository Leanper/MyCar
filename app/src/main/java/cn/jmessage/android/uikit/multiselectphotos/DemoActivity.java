package cn.jmessage.android.uikit.multiselectphotos;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ygst.cenggeche.R;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends Activity implements PickPictureActivity.OnSelectedListener {

    public static final int RESULT_CODE_SELECT_PICTURE = 8;
    public static final int REQUEST_CODE_SELECT_ALBUM = 10;
    private static final String PICTURE_PATH = "picturePath";
    String[] perms = {"android.permission.READ_EXTERNAL_STORAGE"};
    String permission = "android.permission.READ_EXTERNAL_STORAGE";
    final int REQUEST_PERMISSIONS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jmui_activity_mult);

        Button startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(DemoActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DemoActivity.this, perms, REQUEST_PERMISSIONS);
                } else {
                    intentActivity();
                }
            }
        });
    }

    private void intentActivity() {
        Intent intent = new Intent();
        intent.setClass(DemoActivity.this, AlbumListActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SELECT_ALBUM);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限被用户同意，可以去放肆了。
                    intentActivity();
                } else {
                    // 权限被用户拒绝了，洗洗睡吧。
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //得到从选择图片返回的Intent
        if (resultCode == RESULT_CODE_SELECT_PICTURE) {
            //得到图片路径
            ArrayList<String> pathList = data.getStringArrayListExtra(PICTURE_PATH);
            for (String path : pathList) {
                Log.d("DemoActivity", "path : " + path);
            }
        }
    }

    @Override
    public void onSelectedPictures(List<String> list) {
        Log.d("DemoActivity", list.toString());
    }
}
