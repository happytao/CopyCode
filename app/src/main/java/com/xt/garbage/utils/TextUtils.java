package com.xt.garbage.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.xt.garbage.R;

/**
 * @author:DIY
 * @date: 2021/3/23
 */
public class TextUtils {
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private SpannableStringBuilder stringBuilder;
        public static int LENGTH = 0;

        public Builder() {
            stringBuilder = new SpannableStringBuilder();
        }
        public Builder append(CharSequence text) {
            stringBuilder.append(text);
            LENGTH = stringBuilder.length();
            return this;
        }
        public Builder append(CharSequence text,int color) {
            int start = stringBuilder.length();
            stringBuilder.append(text);
            int end = stringBuilder.length();
            stringBuilder.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            LENGTH = stringBuilder.length();
            return this;
        }

        public Builder replace(CharSequence text,int color,String... replaces) {
            stringBuilder.append(text);
            for(int i = 0; i < replaces.length; i++) {
                String replace = replaces[i];
                int start = text.toString().indexOf(replace);
                if(start >= 0) {
                    int end = start + replace.length();
                    stringBuilder.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            LENGTH = stringBuilder.length();
            return this;
        }

        public Builder click(CharSequence text, final int color, final OnClickListener onClickListener, String... clickTexts) {
            stringBuilder.append(text);

            for(int i = 0; i < clickTexts.length; i++) {
                String clickText = clickTexts[i];
                final int position = i;
                int start = text.toString().indexOf(clickText);
                if(start >= 0) {
                    int end = start + clickText.length();
                    stringBuilder.setSpan(new ClickableSpan() {
                        @Override
                        public void onClick(@NonNull View widget) {
                            if(onClickListener != null) {
                                onClickListener.onClick(position);
                            }
                        }

                        @Override
                        public void updateDrawState(@NonNull TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setColor(color);
                            ds.setUnderlineText(false);
                        }
                    },start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            return this;
        }

        private boolean isChecked = false;

        public Builder checkBox(final Context context, final TextView tv, final OnImageClickListener listener) {
            setImageSpan(context,stringBuilder, R.drawable.checkbox_normal);
            stringBuilder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    isChecked = !isChecked;
                    if(isChecked) {
                        setImageSpan(context,stringBuilder,R.drawable.checkbox_pressed);
                        tv.setText(stringBuilder);
                        listener.onChecked();
                    }
                    else {
                        setImageSpan(context,stringBuilder,R.drawable.checkbox_normal);
                        tv.setText(stringBuilder);
                        listener.onUnChecked();
                    }
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.WHITE);
                    ds.setUnderlineText(false);
                }
            },0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        public Builder clickInto(TextView tv) {
            tv.setMovementMethod(LinkMovementMethod.getInstance());
            tv.setHighlightColor(Color.TRANSPARENT);
            tv.setText(stringBuilder);
            return this;
        }

        public Builder into(TextView tv) {
            tv.setText(stringBuilder);
            return this;
        }

        public Builder setSize(CharSequence text, int size, int start, int end) {
            stringBuilder.append(text);
            stringBuilder.setSpan(new AbsoluteSizeSpan(size,true),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            LENGTH = stringBuilder.length();
            return this;
        }

        public Builder setSize(int size, int start, int end) {
            stringBuilder.setSpan(new AbsoluteSizeSpan(size,true),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }

        public Builder setColor(int color,int start,int end) {
            stringBuilder.setSpan(new ForegroundColorSpan(color),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return this;
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    public interface OnImageClickListener {
        void onChecked();
        void onUnChecked();
    }

    private static void setImageSpan(Context context,SpannableStringBuilder builder, int resourceId) {
        MyImageSpan imageSpan = new MyImageSpan(context,resourceId,2);
        builder.setSpan(imageSpan,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    public static class MyImageSpan extends ImageSpan {


        public MyImageSpan(@NonNull Context context, int resourceId, int verticalAlignment) {
            super(context, resourceId, verticalAlignment);
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            int transY = bottom - drawable.getBounds().bottom;
            if(mVerticalAlignment == ALIGN_BASELINE) {
                transY -= fm.descent;
            }
            else if(mVerticalAlignment == ALIGN_CENTER) {
                transY = ((y + fm.descent) + (y + fm.ascent)) / 2 - drawable.getBounds().bottom / 2;
            }
            canvas.translate(x,transY);
            drawable.draw(canvas);
            canvas.restore();
        }
    }
}
