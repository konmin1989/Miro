package com.konmin.miro.internal.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;

import com.konmin.miro.R;
import com.konmin.miro.entity.Album;
import com.konmin.miro.internal.ui.adapter.AlbumListAdapter;

import java.util.List;

/**
 * to display the albumList for user
 *
 * @author Konmin
 * @version create time:2017/11/23
 */

public class AlbumListFragment extends Fragment implements View.OnClickListener {


    private final static int ALPHA_DURATION = 300;
    private final static int TRANSLATE_DURATION = 250;
    private RecyclerView mRecyclerView;
    private View mVBg;

    private AlbumListAdapter mAlbumListAdapter;
    private boolean mDismissed = true;

    private List<Album> mAlbums;

    public AlbumListFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_album_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive()) {
            View focusView = getActivity().getCurrentFocus();
            if (focusView != null) {
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
            }
        }

        mRecyclerView = view.findViewById(R.id.rv_album);
        mVBg = view.findViewById(R.id.fl_bg);
        mAlbumListAdapter = new AlbumListAdapter() {
            @Override
            public void onAlbumSelected(Album album) {
                //
            }
        };
        mAlbumListAdapter.setAlbums(mAlbums);
        mRecyclerView.setAdapter(mAlbumListAdapter);
        mRecyclerView.startAnimation(createTranslationInAnimation());
        mVBg.startAnimation(createAlphaInAnimation());
        mVBg.setOnClickListener(this);
        super.onViewCreated(view, savedInstanceState);
    }


    private Animation createAlphaInAnimation() {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(ALPHA_DURATION);
        return an;
    }

    private Animation createTranslationInAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 1, type, 0);
        an.setDuration(TRANSLATE_DURATION);
        return an;
    }


    public void show(FragmentManager manager, List<Album> albums) {
        if (!mDismissed || manager.isDestroyed()) {
            return;
        }
        mDismissed = false;
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fl_container, this, "tag");
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        mAlbums = albums;
    }


    public void dismiss() {
        if (mDismissed) {
            return;
        }
        mDismissed = true;
        new Handler().post(new Runnable() {
            public void run() {
                mRecyclerView.startAnimation(createTranslationOutAnimation());
                mVBg.startAnimation(createAlphaOutAnimation());
                mVBg.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getFragmentManager().popBackStack();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.remove(AlbumListFragment.this);
                        ft.commitAllowingStateLoss();
                    }
                }, ALPHA_DURATION);
            }
        });
    }


    private Animation createAlphaOutAnimation() {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(ALPHA_DURATION);
        an.setFillAfter(true);
        return an;
    }

    private Animation createTranslationOutAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 0, type, 1);
        an.setDuration(TRANSLATE_DURATION);
        an.setFillAfter(true);
        return an;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onClick(View v) {
        if (v == mVBg) {
            dismiss();
        }
    }
}
