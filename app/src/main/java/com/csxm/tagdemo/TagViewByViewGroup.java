package com.csxm.tagdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhouyunfei
 * @date: 2018/7/16
 * @desc:
 */
public class TagViewByViewGroup extends ViewGroup {

    private static final int LINE_SPACE = 30;//行间距，仅为演示直接写成像素值
    private static final int ROW_SPACE = 20;//列间距
    private int mTotalWidth;//tagView的总宽度
    private int mLeftPadding;//左侧padding
    private int mTopPadding;//右侧padding
    private int mRightPadding;//上部padding
    private int mBottomPadding;//下部padding

    public TagViewByViewGroup(Context context) {
        super(context);
    }

    public TagViewByViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /***
     * 设置标签数据
     * @param tagStrList
     */
    public void setViewData(List<String> tagStrList) {
        removeAllViews();//清除所有view
        if (null == tagStrList || tagStrList.size() < 1) {
            return;
        }
        LayoutInflater m_Inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < tagStrList.size(); i++) {
            TextView aTextView = (TextView) m_Inflater.inflate(
                    R.layout.item_tag, null);
            if (aTextView != null) {
                aTextView.setText(tagStrList.get(i));
                addView(aTextView);
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mTotalWidth = MeasureSpec.getSize(widthMeasureSpec);//获得总宽度
        mLeftPadding = getPaddingLeft();//左侧padding
        mTopPadding = getPaddingTop();//右侧padding
        mRightPadding = getPaddingRight();//上部padding
        mBottomPadding = getPaddingRight();//上部padding
        int x = mLeftPadding;
        int lineNum = 0;//行数
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();

            if (0 == i || (x + width > mTotalWidth - mRightPadding)) {
                lineNum++;//新一行
                x = mLeftPadding;
            }
            x = x + width + ROW_SPACE;
        }
        setMeasuredDimension(mTotalWidth,
                mTopPadding + (lineNum - 1) * (height + LINE_SPACE) + height + mBottomPadding);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int x = mLeftPadding;
        int lineNum = 0;//行数
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            if (0 == i || (x + width > mTotalWidth - mRightPadding)) {
                lineNum++;//新一行
                x = mLeftPadding;
            }
            view.layout(x, mTopPadding + (lineNum - 1) * (height + LINE_SPACE),
                    x + width, mTopPadding + (lineNum - 1) * (height + LINE_SPACE) + height);
            x = x + width + ROW_SPACE;
        }
    }

}
