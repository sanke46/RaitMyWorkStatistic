package com.sanke.ilafedoseev.raitmyworkstatistic

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sanke.ilafedoseev.raitmyworkstatistic.FireBase.FireBaseRealTime



class MainActivity : AppCompatActivity() {

//    var mDemoCollectionPagerAdapter: DemoCollectionPagerAdapter? = null
    var mViewPager: ViewPager? = null

    lateinit var textPercent : TextView
    lateinit var starPercent5 : TextView
    lateinit var starPercent4 : TextView
    lateinit var starPercent3 : TextView
    lateinit var starPercent2 : TextView
    lateinit var starPercent1 : TextView
    lateinit var allReview : TextView

    lateinit var tabLikeNumber : TextView
    lateinit var tabDislikeNumber : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mDemoCollectionPagerAdapter =
//                 DemoCollectionPagerAdapter(
//                        getSupportFragmentManager());
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        var fireBase = FireBaseRealTime("service")
        var textPercent = findViewById(R.id.averageInterest) as TextView
        starPercent5 = findViewById(R.id.starPercent5) as TextView
        starPercent4 = findViewById(R.id.starPercent4) as TextView
        starPercent3 = findViewById(R.id.starPercent3) as TextView
        starPercent2 = findViewById(R.id.starPercent2) as TextView
        starPercent1 = findViewById(R.id.starPercent) as TextView
        allReview = findViewById(R.id.allReview) as TextView
        tabLikeNumber = findViewById(R.id.likesNumber) as TextView
        tabDislikeNumber = findViewById(R.id.dislikesNumber) as TextView

        fireBase.allAverageLike(textPercent,
                starPercent1,
                starPercent2,
                starPercent3,
                starPercent4,
                starPercent5,
                allReview)

        fireBase.allTabLikesAndDislike(tabLikeNumber,
                tabDislikeNumber)

//        fireBase.allStatictic()
    }
}
