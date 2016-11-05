/*
name: Denis Stepanov
student ID: n01077691
assignment #: 2
*/


package denis.stepanov;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;

public class DenisActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton, AddPatientButton, AddTestButton;
    Animation fabopen, fabclose, fabclock, fabanti;
    TextView addPatient, addTest;
    View bckgroundDimmer;
    boolean FABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info);

        this.bckgroundDimmer = findViewById(R.id.background_dimmer);
        fabopen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabclose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fabclock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clock);
        fabanti = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anti);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager(), DenisActivity.this));

        TabLayout tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //Add Patient
        addPatient = (TextView)findViewById(R.id.AddPatientText);
        addTest = (TextView)findViewById(R.id.AddTestText);

        AddPatientButton = (FloatingActionButton)findViewById(R.id.AddPatientFAB);
        AddTestButton = (FloatingActionButton)findViewById(R.id.AddTestFAB);

        //Floating Action Button
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(FABOpen)
                {
                    AddPatientButton.startAnimation(fabclose);
                    AddTestButton.startAnimation(fabclose);
                    floatingActionButton.startAnimation(fabanti);
                    addPatient.setVisibility(View.INVISIBLE);
                    AddPatientButton.setVisibility(View.INVISIBLE);
                    addTest.setVisibility(View.INVISIBLE);
                    AddTestButton.setVisibility(View.INVISIBLE);
                    FABOpen = false;
                    onMenuCollapsed();

                }
                else
                {
                    AddPatientButton.startAnimation(fabopen);
                    AddTestButton.startAnimation(fabopen);
                    floatingActionButton.startAnimation(fabclock);
                    addPatient.setVisibility(View.VISIBLE);
                    AddPatientButton.setVisibility(View.VISIBLE);
                    addTest.setVisibility(View.VISIBLE);
                    AddTestButton.setVisibility(View.VISIBLE);
                    FABOpen = true;
                    onMenuExpanded();
                }

            }
        });
        AddPatientButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DenisActivity.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });

        AddTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DenisActivity.this, AddTestActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onMenuCollapsed() {
        bckgroundDimmer.setVisibility(View.GONE);
    }

    public void onMenuExpanded() {
        bckgroundDimmer.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = null;

        switch(item.getItemId())
        {
            case R.id.search:
                intent = new Intent(DenisActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
