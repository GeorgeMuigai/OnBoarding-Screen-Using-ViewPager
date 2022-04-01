package com.example.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSkip, btnNext, btnBack;
    ViewPager viewPager;
    LinearLayout dotLayout;
    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);
        viewPager = findViewById(R.id.slideViewPager);
        dotLayout = findViewById(R.id.indicatorLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(adapter);

        setUpIndicators(0);

        btnBack.setOnClickListener(View ->{
            if(viewPager.getCurrentItem() > 0)
            {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
        btnNext.setOnClickListener(View ->{
            if(viewPager.getCurrentItem() < 3)
            {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }else{
                startActivity(new Intent(MainActivity.this, MainWindow.class));
                finish();
            }
        });

        btnSkip.setOnClickListener(View ->{
            startActivity(new Intent(MainActivity.this, MainWindow.class));
            finish();
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setUpIndicators(position);

                if(position > 0)
                {
                    btnBack.setVisibility(View.VISIBLE);
                }else{
                    btnBack.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void setUpIndicators(int position)
    {
        dots = new TextView[4];
        dotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++)
        {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive));
            dotLayout.addView(dots[i]);
        }

        dots[position].setTextColor(getResources().getColor(R.color.active));
    }

}