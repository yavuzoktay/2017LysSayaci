package com.ilk.yavuz.gerisayim;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    private ArrayList<Date> lysDates = new ArrayList<>();
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        random = new Random();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        for (int i = 0; i < 5; i++) {
            int year = getRandomInt(3) + 2016;
            int month = getRandomInt(12) + 8;
            int day = getRandomInt(30) + 1;

            calendar.set(year, month, day);
            Date date = calendar.getTime();
            lysDates.add(date);
        }

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < 5; i++) {
            String title = String.format("LYS-%d", (i + 1));
            viewPagerAdapter.addFragments(LYS.newInstance(title, getDateDiff(now, lysDates.get(i))), title);
        }

//        viewPagerAdapter.addFragments(new LYS1(), "LYS-1");
//        viewPagerAdapter.addFragments(new LYS2(), "LYS-2");
//        viewPagerAdapter.addFragments(new LYS3(), "LYS-3");
//        viewPagerAdapter.addFragments(new LYS4(), "LYS-4");
//        viewPagerAdapter.addFragments(new LYS5(), "LYS-5");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private int getRandomInt(int max) {
        return random.nextInt(max);
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
}

