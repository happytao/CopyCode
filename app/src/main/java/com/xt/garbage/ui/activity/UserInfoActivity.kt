package com.xt.garbage.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.glide.GlideEngine
import com.xt.garbage.utils.FileUtil
import com.xt.garbage.utils.ImageLoaderUtil
import com.xt.garbage.wigdt.Toolbar
import kotlinx.android.synthetic.main.activity_user_info.*
import java.io.File


class UserInfoActivity : BaseActivity(), View.OnClickListener {
    private var permissions: Array<String> = arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    var requestCode:Int = 1000
    override fun initLayout(): Int {
        return R.layout.activity_user_info
    }

    override fun initView() {
        toolbar.setOnToolbarOnClickListener(object : Toolbar.ToolbarClickListener {
            override fun leftClick() {
                finish()
            }

            override fun rightClick() {
                TODO("Not yet implemented")
            }
        })

        site_layout.setOnClickListener(this)
        nickname_layout.setOnClickListener(this)
        head_layout.setOnClickListener(this)
    }

    override fun initData() {
        initUserInfo()
    }

    private fun initUserInfo() {
        ImageLoaderUtil.loadCircleImage(this, BaseConstant.URLPREFIX + login.result.userInfoRespDTO.head, head_image)
        nickname.text = login.result.userInfoRespDTO.nickName
        when (login.result.userInfoRespDTO.userType) {
            1 -> {
                site_layout.visibility = View.VISIBLE
                type.text = "普通用户"
            }

            2 -> {
                site_layout.visibility = View.VISIBLE
                type.text = "住户"
            }

            3 -> {
                site_layout.visibility = View.VISIBLE
                type.text = "公共机构"
            }

            4 -> {
                site_layout.visibility = View.GONE
                type.text = "驿站"
            }

            5 -> {
                site_layout.visibility = View.GONE
                type.text = "司机"
            }

            else -> {
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.head_layout -> {
                selectPhoto()
            }
        }
    }

    private fun selectPhoto() {
        requestMyPermissions()

    }

    private fun requestMyPermissions() {
        checkCameraPermission {
            if(it) ActivityCompat.requestPermissions(this,permissions,requestCode)
        }
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .minSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(PictureConfig.CHOOSE_REQUEST)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            when(requestCode) {
                PictureConfig.CHOOSE_REQUEST -> {
                    var images:MutableList<LocalMedia> = PictureSelector.obtainMultipleResult(data)
                    var path:String = images[0].path
                    if(path.contains("content://")) {
                        var uri:Uri = Uri.parse(path)
                        path = FileUtil.getFilePathByUri(uri,this)
                    }
                    Log.v("图片返回地址: ",path)
                    var file: File = File(path)
                    if(file.exists()) file.mkdir()
                    compressWithLs(file)
                }
            }
        }
    }

    private fun compressWithLs(file: File) {
        var oldFile:File = CompressHelper

    }

    private fun checkCameraPermission(f : (Boolean) -> Unit) {
        for (permission in permissions) {
            if(ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                f.invoke(false)
            }
        }
        f.invoke(true)
    }

}
