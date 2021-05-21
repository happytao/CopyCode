package com.xt.garbage.wigdt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xt.garbage.R;

/**
 * @author:DIY
 * @date: 2021/3/25
 */
public class Toolbar extends RelativeLayout {
    //声明控件
    private TextView leftButton,rightButton;
    private TextView tvTitle;

    //声明控件属性
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;
    private float leftTextSize;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;
    private float rightTextSize;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    //布局属性
    private LayoutParams leftParams,rightParams,titleParams;

    //接口回调
    private ToolbarClickListener listener;

    //接口定义
    public interface ToolbarClickListener {
        void leftClick();
        void rightClick();
    }

    //设置接口方法
    public void setOnToolbarOnClickListener(ToolbarClickListener listener) {
        this.listener = listener;
    }



    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取styleable属性集
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Toolbar);
        //获取自定义属性及默认值
        leftText = ta.getString(R.styleable.Toolbar_leftText);
        leftTextColor = ta.getColor(R.styleable.Toolbar_leftTextColor,0);
        leftTextSize = ta.getFloat(R.styleable.Toolbar_leftTextSize,16);
        leftBackground = ta.getDrawable(R.styleable.Toolbar_leftBackground);

        rightText = ta.getString(R.styleable.Toolbar_rightText);
        rightTextColor = ta.getColor(R.styleable.Toolbar_rightTextColor,0);
        rightTextSize = ta.getFloat(R.styleable.Toolbar_rightTextSize,16);
        rightBackground = ta.getDrawable(R.styleable.Toolbar_rightBackground);

        title = ta.getString(R.styleable.Toolbar_title);
        titleTextColor = ta.getColor(R.styleable.Toolbar_titleTextColor,0);
        titleTextSize = ta.getFloat(R.styleable.Toolbar_titleTextSize,16);
        //回收TypedArray
        ta.recycle();

        //初始化控件
        leftButton = new TextView(context);
        rightButton = new TextView(context);
        tvTitle = new TextView(context);

        //设置控件的值
        if(leftButton != null) {
            leftButton.setTextColor(leftTextColor);
            leftButton.setText(leftText);
            leftButton.setTextSize(leftTextSize);
            leftButton.setBackground(leftBackground);
            leftButton.setMinimumHeight(10);
            leftButton.setMinimumWidth(10);


            //设置控件宽高
            leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            leftParams.setMargins(25,0,0,0);

            //设置对齐
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
            leftParams.addRule(RelativeLayout.CENTER_VERTICAL);

            //将leftButton加入viewGroup控件中
            addView(leftButton,leftParams);

            //增加点击事件
            leftButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.leftClick();
                    }
                }
            });
        }

        if(rightButton != null) {
            rightButton.setTextColor(rightTextColor);
            rightButton.setText(rightText);
            rightButton.setTextSize(rightTextSize);
            rightButton.setBackground(rightBackground);
            rightButton.setMinimumHeight(10);
            rightButton.setMinimumWidth(10);

            //设置控件宽高
            rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            rightParams.setMargins(0,0,40,0);

            //设置对齐
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightParams.addRule(RelativeLayout.CENTER_VERTICAL);

            //将leftButton加入viewGroup控件中
            addView(rightButton,rightParams);

            rightButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.rightClick();
                    }
                }
            });
        }

        if(tvTitle != null) {
            tvTitle.setText(title);
            tvTitle.setTextSize(titleTextSize);
            tvTitle.setTextColor(titleTextColor);
            tvTitle.setGravity(Gravity.CENTER);
            tvTitle.setLines(1);
            tvTitle.setEllipsize(TextUtils.TruncateAt.END);

            titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
            titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
            addView(tvTitle,titleParams);
        }


    }

    public void setLeftIsVisible(boolean flag) {
        if(leftButton != null) {
            if(flag) {
                leftButton.setVisibility(View.VISIBLE);
            }
            else {
                leftButton.setVisibility(View.GONE);
            }
        }
    }

    public void setRightIsVisible(boolean flag) {
        if(rightButton != null) {
            if(flag) {
                rightButton.setVisibility(View.VISIBLE);
            }
            else {
                rightButton.setVisibility(View.GONE);
            }
        }
    }

    //动态设置toolbar的标题

    public TextView getLeftButton() {
        return leftButton;
    }

    public void setLeftButton(TextView leftButton) {
        this.leftButton = leftButton;
    }

    public TextView getRightButton() {
        return rightButton;
    }

    public void setRightButton(TextView rightButton) {
        this.rightButton = rightButton;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
    }

    public Drawable getLeftBackground() {
        return leftBackground;
    }

    public void setLeftBackground(Drawable leftBackground) {
        this.leftBackground = leftBackground;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }

    public Drawable getRightBackground() {
        return rightBackground;
    }

    public void setRightBackground(Drawable rightBackground) {
        this.rightBackground = rightBackground;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public float getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
    }

    public float getTitleTextSize() {
        return titleTextSize;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
    }

    public int getTitleTextColor() {
        return titleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }
}
