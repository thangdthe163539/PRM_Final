package com.example.prm_final;

import static com.example.prm_final.R.id.home;
import static com.example.prm_final.R.id.person;
import static com.example.prm_final.R.id.search;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prm_final.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    User informationUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case home:
                    replaceFragment(new HomeFragment());
                    break;
                case  search:
                    replaceFragment(new SearchFragment());
                    break;
                case person:
                    PersonFragment personFragment = new PersonFragment();
                    replaceFragment(personFragment);
                    Bundle bundle = getIntent().getExtras();
                    if (bundle == null) {
                        break;
                    } else {
                        informationUser = (User) bundle.get("user");
                        bundle.putSerializable("user", informationUser);
                        personFragment.setArguments(bundle);
                    }
                    break;
            }

            return true;
        });
    }

    private void replaceFragment (Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fs = fm.beginTransaction();
        fs.replace(R.id.fragment_layout, fragment);
        fs.commit();
    }
}