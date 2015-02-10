package micc.beaconav;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;


import micc.beaconav.gui.animationHelper.BackgroundColorChangerHSV;
import micc.beaconav.gui.animationHelper.DpHelper;
import micc.beaconav.gui.animationHelper.LayoutDimensionChanger;
import micc.beaconav.gui.animationHelper.ScrollViewResizer;
import micc.beaconav.gui.backPressedListeners.OnBackPressedListener;
import micc.beaconav.gui.backPressedListeners.VoidOnBackPressedListener;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

public class MainActivity extends ActionBarActivity
{

    private static final String TAG = "MainActivity";
    private DpHelper dpHelper;
    private Context context;


// * * * * * * * * * * * * COSTANTI (evitiamo i numeri magici) * * * * * * * * * * * * * * * * * *
    final float ANCHOR_POINT = 0.5f;
    final int SLIDING_BAR_EXPANDED_HEIGHT_DP = 120;
    final int SLIDING_BAR_HEIGHT_DP = 48;
    final int SEARCH_BAR_PADDING_DP = 8;
    final  int SEARCH_BAR_HIDED_PADDING_DP = -60;

//* * * * * * * * * * * *  FLAGS  * * * * * * * * * * * * * * *
    private boolean fadeOutAnimationStarted = false;
    private boolean colorAnimationStarted = false;
    public boolean panelAnchored = false;
    private boolean heightAnimationStarted = false;

// * * * * * * * * * * * *  DEFINIZIONE E INIZIALIZZAZIONE LAYOUT * * * * * * * * * * * * * * * * *
    private RelativeLayout fragmentHeaderContainer;
    private LinearLayout mSlidingBar;
    private RelativeLayout fragmentListContainer;
    private SlidingUpPanelLayout mSlidingUpPanelLayout;
    private TextView t;
    private ScrollView scrollView;
    private SearchView mSearch;
    private FloatingActionButton floatingActionButton;
    private LinearLayout dragView;





    private void initFragments(){
        FragmentHelper.setMainActivity(this);
        FragmentHelper.instance().showOutdoorFragment();
    }

    private void initActivityAndXML() {
    // FIND VIEW BY ID * * * * * * * * * * * * * * * * * * * * * * * *
        mSearch = (SearchView) findViewById(R.id.search_view);
        mSlidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        //mSlidingUpPanelLayout.setEnableDragViewTouchEvents(true);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        fragmentHeaderContainer = (RelativeLayout) findViewById(R.id.fragment_sliding_header_container);
        mSlidingBar = (LinearLayout) findViewById(R.id.slidingBar);
        fragmentListContainer = (RelativeLayout) findViewById(R.id.fragment_list_container);


    // INIT * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
        mSlidingUpPanelLayout.setAnchorPoint(ANCHOR_POINT);
        mSlidingUpPanelLayout.setDragView(fragmentHeaderContainer);
        // sliding avviene solo se si scrolla sulla slidingBar e non se si scrolla il contenuto
    }


    public void initEventListeners()  {

         mSlidingUpPanelLayout.setPanelSlideListener(new PanelSlideListener() {


            SlidingBarExtensionAnimationManager slidingBarHeightAnimMan = new SlidingBarExtensionAnimationManager(mSlidingBar, fragmentHeaderContainer, context);
            ObjectAnimator slidingBarHeightAnimation;
            BackgroundColorChangerHSV slidingBarColorChanger = new BackgroundColorChangerHSV(fragmentHeaderContainer, 255, 152, 0);
            ObjectAnimator slidingBarColorAnimation;
            ScrollViewResizer scrollViewResizer = new ScrollViewResizer(mSlidingUpPanelLayout, fragmentListContainer);

//            MarginChanger marginChanger = new MarginChanger((RelativeLayout.MarginLayoutParams) mSearch.getLayoutParams());
//            ObjectAnimator searchBarAnimation;



            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                scrollViewResizer.resizeScrollView(1-slideOffset);

                if (slideOffset >= 0.002)
                {
                    if (colorAnimationStarted == false )
                    {
                        colorAnimationStarted = true;

                        if (themeColor == ThemeColor.ORANGE)
                        {

                            slidingBarHeightAnimation = ObjectAnimator.ofFloat(slidingBarColorChanger, "saturation", 0, slidingBarColorChanger.getS());
                            slidingBarHeightAnimation.setDuration(500);
                            slidingBarHeightAnimation.start();
                        }
                    }
                }

                if (slideOffset <= 0.001)
                {
                    if (colorAnimationStarted == true )
                    {
                        colorAnimationStarted = false;

                        if (themeColor == ThemeColor.ORANGE)
                        {
                            //slidingBarHeightAnimation = ObjectAnimator.ofFloat(slidingBarColorChanger, "saturation", slidingBarColorChanger.getS(), 0);
                            slidingBarHeightAnimation = ObjectAnimator.ofFloat(slidingBarColorChanger, "saturation", slidingBarColorChanger.getS(), 0);
                            slidingBarHeightAnimation.setDuration(200);
                            slidingBarHeightAnimation.start();
                        }
                    }

                }


                if (slideOffset >= 0.4) {

                    if (fadeOutAnimationStarted == false)
                    {

                        Animation a = new Animation() {
                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mSearch.getLayoutParams();
                                params.topMargin = DpHelper.dpToPx(SEARCH_BAR_PADDING_DP, context) + (int) (dpHelper.dpToPx(SEARCH_BAR_HIDED_PADDING_DP) * interpolatedTime);
                                mSearch.setLayoutParams(params);
                            }
                        };
                        a.setDuration(200);
                        mSearch.startAnimation(a);

                        fadeOutAnimationStarted = true;

                         /*
                            SOSTITUITO COL CODICE SOPRA, NON SO PERCHÈ QUESTO NON FUNZIONA BENE:
                             animMargin = ObjectAnimator.ofInt(marginChanger, "topMargin", DpHelper.dpToPx(8, context), DpHelper.dpToPx(60, context));
                             animMargin.setDuration(1000);
                             animMargin.start();
                         */


                    }
                }
                else if (slideOffset <= 0.3)
                {
                    if (fadeOutAnimationStarted == true)
                    {
                        Animation a = new Animation() {
                            @Override
                            protected void applyTransformation(float interpolatedTime, Transformation t) {
                                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mSearch.getLayoutParams();
                                params.topMargin = dpHelper.dpToPx(SEARCH_BAR_PADDING_DP) + (int) (dpHelper.dpToPx(SEARCH_BAR_HIDED_PADDING_DP) * (1 - interpolatedTime));
                                mSearch.setLayoutParams(params);
                            }
                        };
                        a.setDuration(200);
                        mSearch.startAnimation(a);
                        fadeOutAnimationStarted = false;


                        /*
                           SOSTITUITO COL CODICE SOPRA, NON SO PERCHÈ QUEST NON FUNZIONA BENE:
                            marginChanger = new MarginChanger((RelativeLayout.MarginLayoutParams)mSearch.getLayoutParams() );
                            animMargin = ObjectAnimator.ofInt(marginChanger, "topMargin", DpHelper.dpToPx(60, context), DpHelper.dpToPx(8, context));
                            animMargin.setDuration(1000);
                            animMargin.start();
                        */
                    }
                }

                if(slideOffset >= 0.95)
                {
                    if(heightAnimationStarted != true)
                    {
                        slidingBarHeightAnimation = ObjectAnimator.ofInt(slidingBarHeightAnimMan, "dpHeight", SLIDING_BAR_HEIGHT_DP, SLIDING_BAR_EXPANDED_HEIGHT_DP);
                        slidingBarHeightAnimation.setDuration(200);
                        slidingBarHeightAnimation.start();
                        heightAnimationStarted = true;
                    }

                }

                if(slideOffset <= 0.92)
                {
                    if(heightAnimationStarted != false)
                    {
                        slidingBarHeightAnimation = ObjectAnimator.ofInt(slidingBarHeightAnimMan, "dpHeight", SLIDING_BAR_EXPANDED_HEIGHT_DP, SLIDING_BAR_HEIGHT_DP);
                        slidingBarHeightAnimation.setDuration(500);
                        slidingBarHeightAnimation.start();
                        heightAnimationStarted = false;
                    }
                }

            }


            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");
                
                scrollViewResizer.resizeScrollView(0.0f);
                panelAnchored = false; //è considerato true solo se fermo a metà

            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");

                scrollViewResizer.resizeScrollView(ANCHOR_POINT);

            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }
        });


//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (panelAnchored == false) {
//                    mSlidingUpPanelLayout.setPanelState(PanelState.ANCHORED);
//                    panelAnchored = true;
//                } else {
//                    mSlidingUpPanelLayout.setPanelState(PanelState.COLLAPSED);
//                    panelAnchored = false;
//                }
//
//            }
//        });

    }


// * * * * * * * * * * * * * * *  EVENT MANAGER DELLA ACTIVITY * * * * * * * * * * * * * * *

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.context = this;
        dpHelper = new DpHelper(this);


        initActivityAndXML();
        initEventListeners();
        initFragments();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_toggle);
        if (mSlidingUpPanelLayout != null) {
            if (mSlidingUpPanelLayout.getPanelState() == PanelState.HIDDEN) {
                item.setTitle(R.string.action_show);
            } else {
                item.setTitle(R.string.action_hide);
            }
        }
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_toggle: {
                if (mSlidingUpPanelLayout != null) {
                    if (mSlidingUpPanelLayout.getPanelState() != PanelState.HIDDEN) {
                        mSlidingUpPanelLayout.setPanelState(PanelState.HIDDEN);
                        item.setTitle(R.string.action_show);
                    } else {
                        mSlidingUpPanelLayout.setPanelState(PanelState.EXPANDED);
                        item.setTitle(R.string.action_hide);
                    }
                }
                return true;
            }
            case R.id.action_anchor: {
                if (mSlidingUpPanelLayout != null) {
                    if (mSlidingUpPanelLayout.getAnchorPoint() == 1.0f) {
                        mSlidingUpPanelLayout.setAnchorPoint(0.7f);
                        mSlidingUpPanelLayout.setPanelState(PanelState.ANCHORED);
                        item.setTitle(R.string.action_anchor_enable);
                    } else {
                        mSlidingUpPanelLayout.setAnchorPoint(1.0f);
                        mSlidingUpPanelLayout.setPanelState(PanelState.COLLAPSED);
                        item.setTitle(R.string.action_anchor_disable);
                    }
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }












//* * * * * * * * * * * * GETTERS * * * * * * * * * * * * * * * * * * *

    public SlidingUpPanelLayout getSlidingUpPanelLayout()
    {
        return this.mSlidingUpPanelLayout;
    }
    public RelativeLayout getFragmentHeaderContainer()
    {
        return this.fragmentHeaderContainer;
    }
    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }


    // * * * * * * * * * * * * * * *  ALTRI EVENT MANAGER CREATI BEACONAV * * * * * * * * * * * * * * *



    public enum ThemeColor {  ORANGE, PURPLE, RED; }
    private ThemeColor themeColor = ThemeColor.ORANGE;

    public void setThemeColor(ThemeColor newColor) {
        switch(newColor)
        {
            case ORANGE:
                if(mSlidingUpPanelLayout.getPanelState() == PanelState.COLLAPSED )
                    fragmentHeaderContainer.setBackgroundColor(getResources().getColor(R.color.white));
                else fragmentHeaderContainer.setBackgroundColor(getResources().getColor(R.color.orange));

                floatingActionButton.setColorNormal(getResources().getColor(R.color.orange));
                this.themeColor = ThemeColor.ORANGE;
                break;

            case PURPLE:
                fragmentHeaderContainer.setBackgroundColor(getResources().getColor(R.color.material_deep_purple));
                floatingActionButton.setColorNormal(getResources().getColor(R.color.material_deep_purple));
                this.themeColor = ThemeColor.PURPLE;
                break;

            case RED:
                fragmentHeaderContainer.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                floatingActionButton.setColorNormal(getResources().getColor(android.R.color.holo_red_light));
                this.themeColor = ThemeColor.RED;
                break;
        }
    }

    public void setFABListener(View.OnClickListener onClickListener)
    {
        floatingActionButton.setOnClickListener(onClickListener);
    }

    public void hideFAB() {
        // TODO
    }
    public void showFab() {
        // TODO
    }


    //Listener di default per il back, quando siamo nella lista di musei
    private OnBackPressedListener backPressedListener = new VoidOnBackPressedListener();

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        this.backPressedListener = listener;
    }
    @Override
    public void onBackPressed() {
        backPressedListener.doBack();
    }


}


// HELPER PER ANIMAZIONE
class SlidingBarExtensionAnimationManager {

    LayoutDimensionChanger c1;
    LayoutDimensionChanger c2;
    SlidingUpPanelLayout v3;
    Context context;

    SlidingBarExtensionAnimationManager(ViewGroup v1, ViewGroup v2, Context context)
    {
        this.c1 = new LayoutDimensionChanger(v1, context);
        this.c2 = new LayoutDimensionChanger(v2, context);
        this.context = context;
    }
    public void setDpHeight(int dpHeight)
    {
        c1.setDpHeight(dpHeight);
        c2.setDpHeight(dpHeight);
    }
}

