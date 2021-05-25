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
import com.alibaba.android.arouter.launcher.ARouter
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.xt.garbage.R
import com.xt.garbage.base.BaseActivity
import com.xt.garbage.base.BaseConstant
import com.xt.garbage.bean.constant.SpConstant
import com.xt.garbage.bean.login.LoginBean
import com.xt.garbage.bean.motormain.ReceiveOrderBean
import com.xt.garbage.bean.user.UploadBean
import com.xt.garbage.constant.RoutePathConstant
import com.xt.garbage.glide.GlideEngine
import com.xt.garbage.netSubscribe.garbage.DriverSubscribe
import com.xt.garbage.netSubscribe.garbage.GarbageSubscribe
import com.xt.garbage.netapi.OnSuccessAndFaultListener
import com.xt.garbage.netapi.OnSuccessAndFaultSub
import com.xt.garbage.utils.*
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

            R.id.nickname_layout -> {
                ARouter.getInstance().build(RoutePathConstant.APP_SETTING_NICK)
                        .navigation()
            }
            R.id.site_layout -> {
                var addResDialogFragment:
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
        var oldFile: File? = CompressHelper(applicationContext).compressToFile(file)
        oldFile?.let { postPhoto(oldFile) }

    }

    private fun postPhoto(pathUrl: File) {
        DriverSubscribe.postPhoto(pathUrl,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var uploadBean:UploadBean = GsonUtils.fromJson(result,UploadBean::class.java)
                    if(uploadBean.errorCode == 0) {
                        userInfoUp(uploadBean.result)
                    }
                }

            }

            override fun onFailed(errorMsg: String?) {
                TODO("Not yet implemented")
            }
        }))

    }

    private fun userInfoUp(imageUrl: String?) {
        GarbageSubscribe.userInfoUp(null,imageUrl,null,OnSuccessAndFaultSub(object : OnSuccessAndFaultListener{
            override fun onSuccess(result: String?) {
                result?.let {
                    var deliverySuccessBean:ReceiveOrderBean = GsonUtils.fromJson(result,ReceiveOrderBean::class.java)
                    if(deliverySuccessBean.errorCode == 0) {
                        ImageLoaderUtil.loadCircleImage(context,BaseConstant.URLPREFIX+imageUrl,head_image)
                        var loginBean:LoginBean = login
                        loginBean.result.userInfoRespDTO.head = imageUrl
                        var loginString:String = GsonUtils.toJson(loginBean)
                        SPUtils.put(context,SpConstant.APP_LOGINBEAN,loginString)
                        showToast("头像更改成功")

                    }
                }
            }

            override fun onFailed(errorMsg: String?) {
                showToast("请求失败: $errorMsg")
            }
        },this))


    }

    private fun checkCameraPermission(f : (Boolean) -> Unit) {
        for (permission in permissions) {
            if(ActivityCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED) {
                f.invoke(false)
            }
        }
        f.invoke(true)
    }

    override fun onRestart() {
        super.onRestart()
        initUserInfo()
    }
}
