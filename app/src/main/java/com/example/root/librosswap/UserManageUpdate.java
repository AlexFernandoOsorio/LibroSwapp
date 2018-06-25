package com.example.root.librosswap;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class UserManageUpdate extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage_update);
        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);


        viewPager = (ViewPager) findViewById(R.id.ini_viewpager3);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.ini_tabs3);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        UserManageUpdate.ViewPagerAdapter adapter = new UserManageUpdate.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragRecuperarUsuarios(), "Recuperar Contraseña");
        //adapter.addFragment(new FragRecuperaReg(), "Recuperación");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        };

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
    public void onBackPressed() {
        Intent nav =new Intent(UserManageUpdate.this,LoginLibros.class);
        startActivity(nav);
        finish();
        //super.onBackPressed();
    }
}
