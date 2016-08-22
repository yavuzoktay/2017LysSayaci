package com.ilk.yavuz.gerisayim;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ImageButton floatButton ;
    InterstitialAd mInterstitialAd;

    private ArrayList<Date> lysDates = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ilk.yavuz.gerisayim.R.layout.activity_main);


        mInterstitialAd =new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3040537334820006/3194457372");
        reklamiYukle();

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();

                reklamiYukle();
                beginPlayingGame();
            }

            @Override
            public void onAdLoaded() {
                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();

                }
                else {
                    beginPlayingGame();
                }



            }

        });

        floatButton = (ImageButton) findViewById(com.ilk.yavuz.gerisayim.R.id.fab);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"2017 LYS sınav tarihleri şu anda belli" +
                        " olmadığından tahmini tarihler girilmiştir.",Toast.LENGTH_LONG).show();
            }
        });






        toolbar = (Toolbar) findViewById(com.ilk.yavuz.gerisayim.R.id.toolBar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(com.ilk.yavuz.gerisayim.R.id.tabLayout);
        viewPager = (ViewPager) findViewById(com.ilk.yavuz.gerisayim.R.id.viewPager);

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

            int year =2017;
            int month =  5;
            int day =  18;
            calendar.set(year, month, day);
            Date date = calendar.getTime();
            lysDates.add(date);

            int year1 =2017;
            int month1 =  5;
            int day1 =  24;
            calendar.set(year1, month1, day1);
            Date date1 = calendar.getTime();
            lysDates.add(date1);

            int year2 =2017;
            int month2 =  5;
            int day2 =  25;
            calendar.set(year2, month2, day2);
            Date date2= calendar.getTime();
            lysDates.add(date2);

            int year3 =2017;
            int month3 =  5;
            int day3 =  17;
            calendar.set(year3, month3, day3);
            Date date3= calendar.getTime();
            lysDates.add(date3);

            int year4 =2017;
            int month4 =  5;
            int day4 =  24;
            calendar.set(year4, month4, day4);
            Date date4= calendar.getTime();
            lysDates.add(date4);

            int year5 =2017;
            int month5 =  2;
            int day5 =  12;
            calendar.set(year5, month5, day5);
            Date date5= calendar.getTime();
            lysDates.add(date5);




        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
            String title5 = String.format("YGS");
            viewPagerAdapter.addFragments(YGS.newInstance(title5,  getDateDiff(now,lysDates.get(5))), title5);

            String title = String.format("LYS-1");
            viewPagerAdapter.addFragments(LYS.newInstance(title,  getDateDiff(now,lysDates.get(0))), title);
            String title1 = String.format("LYS-2");
            viewPagerAdapter.addFragments(LYS.newInstance(title1, getDateDiff(now, lysDates.get(1))), title1);
            String title2 = String.format("LYS-3");
            viewPagerAdapter.addFragments(LYS.newInstance(title2, getDateDiff(now, lysDates.get(2))), title2);
            String title3 = String.format("LYS-4");
            viewPagerAdapter.addFragments(LYS.newInstance(title3, getDateDiff(now, lysDates.get(3))), title3);
            String title4 = String.format("LYS-5");
            viewPagerAdapter.addFragments(LYS.newInstance(title4, getDateDiff(now, lysDates.get(4))), title4);







        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void beginPlayingGame() {
    }

    private void reklamiYukle() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


    public long getDateDiff(Date sourceDate, Date targetDate) {
        long sourceMilliseconds = sourceDate.getTime();
        long targetMilliseconds = targetDate.getTime();

        // 1 gundeki millisecond
        long oneDay = 1000 * 60 * 60 * 24;

        // aradaki gun sayisi
        long diffDay = (targetMilliseconds - sourceMilliseconds) / oneDay;
        return diffDay;
    }

    @Override
    public  void onBackPressed(){
        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(com.ilk.yavuz.gerisayim.R.string.quit_dig_confirmation);
        builder.setMessage(com.ilk.yavuz.gerisayim.R.string.quit_dig_confirmation_msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton(android.R.string.no,null);
        builder.show();

    }

}

