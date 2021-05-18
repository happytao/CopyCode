package com.xt.garbage.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.xt.garbage.wigdt.IEditTextChangeListener;

/**
 * @author:DIY
 * @date: 2021/3/23
 */
public class EditStatusCheck {

    static IEditTextChangeListener mChangeListener;
    public static void setChangeListener(IEditTextChangeListener changeListener) {
        mChangeListener = changeListener;
    }

    public static class TextChangeListener {
        private TextView button;
        private EditText[] editTexts;

        public TextChangeListener(TextView button) {
            this.button = button;
        }

        public TextChangeListener addAllEditText(EditText...editTexts) {
            this.editTexts = editTexts;
            initEditListener();
            return this;
        }

        private void initEditListener() {
            Log.i("TAG","调用了遍历edittext的方法");
            for (EditText editText : editTexts) {
                editText.addTextChangedListener(new TextChange());
            }
        }


        private class TextChange implements TextWatcher {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(checkAllEdit()) {
                    Log.i("TAG","所有edittext有值了");
                    mChangeListener.textChange(true);
                    button.setEnabled(true);
                }
                else {
                    button.setEnabled(false);
                    Log.i("TAG","有edittext没有值");
                    mChangeListener.textChange(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }



        }

        private boolean checkAllEdit() {
            for (EditText editText : editTexts) {
                if(!TextUtils.isEmpty(editText.getText() + "")) {
                    continue;
                }
                else {
                    return false;
                }
            }
            return true;
        }
    }
}
