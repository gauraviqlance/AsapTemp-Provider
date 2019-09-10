package com.iqlance.asaptemp.provider.tour.adapter;


import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.ListenerUtil;
import androidx.viewpager.widget.ViewPager;

import com.iqlance.asaptemp.provider.R;
import com.rd.PageIndicatorView;

@BindingMethods({
        @BindingMethod(type = ViewPager.class, attribute = "android:offscreenPageLimit", method = "setOffscreenPageLimit"),
        @BindingMethod(type = ViewPager.class, attribute = "android:adapter", method = "setAdapter"),
        @BindingMethod(type = ViewPager.class, attribute = "android:currentPage", method = "setCurrentItem"),
})
public final class ViewPagerBindingAdapter {

    private PageIndicatorView pageIndicatorView;

    @InverseBindingAdapter(attribute = "android:currentPage", event = "android:currentPageAttrChanged")
    public static int getCurrentPage(@NonNull final ViewPager pager) {
        return pager.getCurrentItem();
    }

    @BindingAdapter(value = {"android:onPageScrolled", "android:onPageSelected", "android:onPageScrollStateChanged",
            "android:currentPageAttrChanged"}, requireAll = false)
    public void onSetAdapter(@NonNull final ViewPager pager, final OnPageScrolled scrolled, final OnPageSelected selected,
                             final OnPageScrollStateChanged scrollStateChanged, final InverseBindingListener currentPageAttrChanged) {

        final ViewPager.OnPageChangeListener newValue;
        if (scrolled == null && selected == null && scrollStateChanged == null && currentPageAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                    if (scrolled != null) {
                        scrolled.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }

                @Override
                public void onPageSelected(final int position) {
                    if (selected != null) {
                        selected.onPageSelected(position);
                        pageIndicatorView.setSelection(position);
                    }
                    if (currentPageAttrChanged != null) {
                        currentPageAttrChanged.onChange();
                    }
                }

                @Override
                public void onPageScrollStateChanged(final int state) {
                    if (scrollStateChanged != null) {
                        scrollStateChanged.onPageScrollStateChanged(state);
                    }
                }
            };
        }
        final ViewPager.OnPageChangeListener oldValue = ListenerUtil.trackListener(pager, newValue, R.id.page_change_listener);
        if (oldValue != null) {
            pager.removeOnPageChangeListener(oldValue);
        }
        if (newValue != null) {
            pager.addOnPageChangeListener(newValue);
        }
    }

    public interface OnPageScrolled {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    public interface OnPageSelected {
        void onPageSelected(int position);
    }

    public interface OnPageScrollStateChanged {
        void onPageScrollStateChanged(int state);
    }

    private ViewPagerBindingAdapter() {
        throw new UnsupportedOperationException();
    }
}