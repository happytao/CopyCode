package com.xt.garbage.ui.activity.work;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.zxing.qrcode.encoder.QRCode;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xt.garbage.R;
import com.xt.garbage.base.BaseActivity;
import com.xt.garbage.bean.workmain.CodeBean;
import com.xt.garbage.constant.RoutePathConstant;
import com.xt.garbage.utils.DesUtil;
import com.xt.garbage.utils.FileUtil;
import com.xt.garbage.utils.GsonUtils;
import com.xt.garbage.wigdt.Toolbar;
import com.xt.garbage.glide.GlideEngine;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
@Route(path = RoutePathConstant.WORK_SCAN_CODE)
public class ScanQRCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.zxing_view)
    ZXingView mZXingView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.flash_light)
    ImageView mFlash;
    private boolean isFlashOpened = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScanQRCodeActivityPermissionsDispatcher.onStartWithPermissionCheck(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_scan_q_r_code;
    }


    @Override
    protected void initView() {
        mZXingView.setDelegate(this);
        mToolbar.setOnToolbarOnClickListener(new Toolbar.ToolbarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                selectPhoto();

            }
        });
        mFlash.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                isFlashOpened = !isFlashOpened;
                if(isFlashOpened) {
                    mZXingView.openFlashlight();
                    view.setBackgroundColor(getResources().getColor(R.color.white));
                }
                else {
                    mZXingView.closeFlashlight();
                    view.setBackgroundColor(getResources().getColor(R.color.picture_color_transparent));
                }
            }
        });


    }
    @NeedsPermission({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    public void selectPhoto() {
        PictureSelector.create(ScanQRCodeActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .minSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> images = PictureSelector.obtainMultipleResult(data);
                    String path = images.get(0).getPath();
                    if(path.contains("content://")) {
                        Uri uri = Uri.parse(path);
                        path = FileUtil.getFilePathByUri(uri,this);
                        scanLocalImage(path);
                    }
                    break;
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    @NeedsPermission(Manifest.permission.CAMERA)
    protected void onStart() {
        super.onStart();
        /**
         * 打开后置摄像头开始预览，未开始识别二维码
         * mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);为打开前置摄像头
         */
        mZXingView.startCamera();
        //显示扫描框开始识别
        mZXingView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera();
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy();
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG,"result:" + result);
        //encrypt：v,加密
        try {
            String encryptResult = DesUtil.decode(result);
            CodeBean codeBean = GsonUtils.fromJson(encryptResult,CodeBean.class);
            if(codeBean.getCode() == 202) {
                goZxing(codeBean.getValue());
            }
            else if(codeBean.getCode() == 203) {
                goShopDetails(codeBean.getValue());
            }
        } catch (Exception e) {
            Log.e(TAG,Log.getStackTraceString(e));
        }
        vibrate();
        finish();

    }
    //当环境过暗时回调此方法
    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if(isDark) {
            if(!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);

            }
            else {
                if(tipText.contains(ambientBrightnessTip)) {
                    tipText = tipText.substring(0,tipText.indexOf(ambientBrightnessTip));
                    mZXingView.getScanBoxView().setTipText(tipText);
                }
            }

        }


    }

    @Override
    public void onScanQRCodeOpenCameraError() {


    }

    private void goZxing(String value) {

    }

    private void goShopDetails(String value) {

    }
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void scanLocalImage(String path) {
        mZXingView.decodeQRCode(path);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ScanQRCodeActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);

    }
}