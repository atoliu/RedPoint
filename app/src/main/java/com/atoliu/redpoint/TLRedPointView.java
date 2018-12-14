

package com.atoliu.redpoint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ppdai.redpoint.R;

public class TLRedPointView extends AppCompatTextView {
    private static final int NUM_MAX = 100;
    private static final int TEXT_SIZE = 7;
    private static final int PADDING_OFFSET = 8;
    private static final int CIRCLE_PADDING_OFFSET = 5;
    private static final int PADDING_TEXT_OFFSET = 12;
    private static final int OFFSET = 5;
    private int mTextSize = TEXT_SIZE;
    private int mTextColor = Color.WHITE;
    private int mBGColor = Color.parseColor("#FF547A");
    private int mCount = 0;
    private GradientDrawable mBgDrawable;
    private boolean showZeroNumPoint = false;
    private int mZeroRadius = TEXT_SIZE / 2;
    private String mText = "";
    private boolean isPopular = false;

    private View mTargetView;

    public TLRedPointView(Context context) {
        super(context);
        init();
    }

    public TLRedPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public TLRedPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TLRedPointView);
        mTextSize = array.getInt(R.styleable.TLRedPointView_tlRedPointViewTextSize, TEXT_SIZE);
        mTextColor = array.getColor(R.styleable.TLRedPointView_tlRedPointViewTextColor, Color.WHITE);
        mBGColor = array.getColor(R.styleable.TLRedPointView_tlRedPointViewBGColor, mBGColor);
        mCount = array.getInt(R.styleable.TLRedPointView_tlRedPointViewCount, 0);
        showZeroNumPoint = array.getBoolean(R.styleable.TLRedPointView_tlRedpointViewZeroShowPoint, false);
        mZeroRadius = array.getInt(R.styleable.TLRedPointView_tlRedPointViewZeroRadius, 2);
        int style = array.getInt(R.styleable.TLRedPointView_tlRedPointStyle, 0);
        if (style == 1) {
            isPopular = true;
        }
        array.recycle();
    }

    private void init() {
        this.setGravity(Gravity.CENTER);
        setTextSize(mTextSize);
        setTextColor(mTextColor);
        try {
            Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.fangzhenglanting);
            setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBgDrawable = new GradientDrawable();
        mBgDrawable.setColor(mBGColor);
        mBgDrawable.setShape(GradientDrawable.RECTANGLE);
    }

    public void setmTextSize(int textSize) {
        this.mTextSize = textSize;
        setBackgroundDrawable(null);
        setTextSize(textSize);
    }

    public void setmTextColor(int textColor) {
        this.mTextColor = textColor;
        setTextColor(textColor);
    }

    public void setmBGColor(int bgColor) {
        this.mBGColor = bgColor;
        mBgDrawable.setColor(bgColor);
        setBackgroundDrawable(mBgDrawable);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isPopular) {
            setmText(getText().toString());
        } else {
            setmCount(mCount);
        }
    }

    public void setmCount(int count) {
        this.mCount = count;
        setBackgroundDrawable(null);

        Log.e("num", count + "");
        if (count >= NUM_MAX) {
            setPadding(PADDING_OFFSET, CIRCLE_PADDING_OFFSET, PADDING_OFFSET, CIRCLE_PADDING_OFFSET);
            setTextSize(TEXT_SIZE);
            setText("99+");
            try {
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.fangzhenglanting);
                setTypeface(typeface);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.fangzhenglanting);
                setTypeface(typeface);
            } catch (Exception e) {
                e.printStackTrace();
            }
            setPadding(CIRCLE_PADDING_OFFSET, CIRCLE_PADDING_OFFSET, CIRCLE_PADDING_OFFSET, CIRCLE_PADDING_OFFSET);
            setTextSize(TEXT_SIZE);
            setText(count + "");
        }

    }

    public void isShowZeroNumPoint(boolean showzeroNumPoint) {
        this.showZeroNumPoint = showzeroNumPoint;
    }

    public void setZeroRadius(int zeroRadius) {
        this.mZeroRadius = zeroRadius;
        setmCount(mCount);
    }

    public void isPopular(boolean popular) {
        isPopular = popular;
    }

    public void setmText(String text) {
        this.mText = text;
        try {
            if ("99+".equals(mText)) {

                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.fangzhenglanting);
                setTypeface(typeface);
            } else {
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.fangzhenglanting);
                setTypeface(typeface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setBackgroundDrawable(null);
        setPadding(PADDING_TEXT_OFFSET, CIRCLE_PADDING_OFFSET, PADDING_TEXT_OFFSET, CIRCLE_PADDING_OFFSET);
        setText(mText);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mCount == 0 && !isPopular) {
            if (showZeroNumPoint) {
                setText("");
                mBgDrawable.setCornerRadius(mZeroRadius);
                mBgDrawable.setSize(2 * mZeroRadius, 2 * mZeroRadius);
                setBackgroundDrawable(mBgDrawable);
                setMeasuredDimension(2 * mZeroRadius, 2 * mZeroRadius);
            } else {
                setText("");
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            Log.e("onMeasure", "width=" + width + "   height =" + height);
            if (isPopular) {
                mBgDrawable.setCornerRadii(new float[]{
                        height / 2 + OFFSET, height / 2 + OFFSET, height / 2 + OFFSET, height / 2 + OFFSET,
                        height / 2 + OFFSET, height / 2 + OFFSET, 0, 0
                });
                mBgDrawable.setSize(width, height);
                setBackgroundDrawable(mBgDrawable);
            } else {
                if (width <= height) {
                    mBgDrawable.setCornerRadius(height / 2 + OFFSET);
                    mBgDrawable.setSize(height, height);
                    setBackgroundDrawable(mBgDrawable);
                    setMeasuredDimension(height, height);
                    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                } else {
                    mBgDrawable.setCornerRadius(height / 2 + OFFSET);
                    mBgDrawable.setSize(width, height);
                    setBackgroundDrawable(mBgDrawable);
                }
            }

        }

    }

    public void setTargetView(View target) {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
        if (target == null) {
            return;
        }
        if (target.getParent() instanceof ViewGroup) {
            ViewGroup parentContainer = (ViewGroup) target.getParent();
            int index = parentContainer.indexOfChild(target);
            parentContainer.removeView(target);
            ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();
            if (target instanceof Button || target instanceof AppCompatButton) {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.addView(target, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                target = linearLayout;
            }
            mTargetView = target;
            FrameLayout badgeContainer = new FrameLayout(getContext());
            badgeContainer.setLayoutParams(parentLayoutParams);
            target.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            fl.gravity = Gravity.TOP | Gravity.RIGHT;


            badgeContainer.addView(this, fl);
            badgeContainer.addView(target, 0);
            parentContainer.addView(badgeContainer, index, parentLayoutParams);
        } else if (target.getParent() == null) {
            Log.e(getClass().getSimpleName(), "ParentView is needed");
        }
    }

    public void setPointViewMarginLef(int left, int top, int right, int bottom) {
        if (mTargetView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
            layoutParams.leftMargin = left;
            layoutParams.topMargin = top;
            layoutParams.rightMargin = right;
            layoutParams.bottomMargin = bottom;
            mTargetView.setLayoutParams(layoutParams);
            mTargetView.getParent().requestLayout();
        }
    }

    public void setPointViewMarginLef(int left) {
        if (mTargetView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
            layoutParams.leftMargin = left;
            mTargetView.setLayoutParams(layoutParams);
            mTargetView.getParent().requestLayout();
        }
    }

    public void setPointViewMarginTop(int top) {
        if (mTargetView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
            layoutParams.topMargin = top;
            mTargetView.setLayoutParams(layoutParams);
            mTargetView.getParent().requestLayout();
        }
    }

    public void setPointViewMarginRight(int right) {
        if (mTargetView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
            layoutParams.rightMargin = right;
            mTargetView.setLayoutParams(layoutParams);
            mTargetView.getParent().requestLayout();
        }
    }

    public void setPointViewMarginBottom(int bottom) {
        if (mTargetView != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mTargetView.getLayoutParams();
            layoutParams.bottomMargin = bottom;
            mTargetView.setLayoutParams(layoutParams);
            mTargetView.getParent().requestLayout();
        }
    }
}
