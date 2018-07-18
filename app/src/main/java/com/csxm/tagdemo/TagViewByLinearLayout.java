package com.csxm.tagdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @author: zhouyunfei
 * @date: 2018/7/16
 * @desc:
 */
public class TagViewByLinearLayout extends LinearLayout {

    private int mContentWidth = 0;//内部可用空间
    private static final int LINE_SPACE = 30;//行间距，仅为演示直接写成像素值
    private static final int ROW_SPACE = 20;//列间距

    public TagViewByLinearLayout(Context context) {
        super(context);
    }

    public TagViewByLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentWidth = w - getPaddingLeft() - getPaddingRight();
    }


    public void setViewData(List<String> tagStrList) {
        removeAllViews();//清除所有view
        if (null == tagStrList || tagStrList.size() < 1) {
            return;
        }
        setOrientation(VERTICAL);
        LayoutInflater m_Inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int surplus;
        int i = 0;
        while (i < tagStrList.size()) {
            LinearLayout wrapLayout = new LinearLayout(getContext());
            wrapLayout.setOrientation(HORIZONTAL);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.topMargin = LINE_SPACE;
            }
            surplus = mContentWidth;
            wrapLayout.setLayoutParams(params);
            addView(wrapLayout);
            for (int j = i; j < tagStrList.size(); j++) {
                TextView aTextView = (TextView) m_Inflater.inflate(R.layout.item_tag, null);
                aTextView.setText(tagStrList.get(i));
                aTextView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                int width = aTextView.getMeasuredWidth();
                if (surplus - width >=0) {
                    if (surplus!=mContentWidth) {
                        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        textParams.leftMargin = ROW_SPACE;
                        aTextView.setLayoutParams(textParams);
                    }
                    surplus = surplus - width - ROW_SPACE;
                    wrapLayout.addView(aTextView);
                    i++;
                } else {
                    break;
                }

            }
        }


    }

}
