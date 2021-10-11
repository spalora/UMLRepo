package com.bu.simplehealth;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author Seema Palora
 * BaseActivity , which is extended by all activity in this app,
 * common logics are placed here for code reusability
 */
public class BaseActivity extends AppCompatActivity {
    private AppCompatTextView title,save;
    private AppCompatImageView  profile;
    private AppCompatImageButton back,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Custom action bar
     */
    public void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable backgroundColour
                = new ColorDrawable(Color.parseColor("#000080"));
        actionBar.setBackgroundDrawable(backgroundColour);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        title = findViewById(R.id.title);
        logout = findViewById(R.id.logout);
        profile = findViewById(R.id.profile);
        back=findViewById(R.id.backbutton);
        save=findViewById(R.id.savebutton);

    }

    /**
     * This will set the screen title
     * @param text
     */
    public void setTitle(String text){
        title.setText(text);
        title.setVisibility(View.VISIBLE);
    }

    /**
     * Sets the title visibility
     * @param visibility
     */
    public void setTitleVisibility(int visibility) {
        title.setVisibility(visibility);
    }
    /**
     * Sets the profile icon visibility
     * @param visibility
     */
    public void setProfileIconVisibility(int visibility) {
        profile.setVisibility(visibility);
    }

    /**
     * Sets the logout icon visibility
     * @param visibility
     */
    public void setLogoutIconVisibility(int visibility) {
        logout.setVisibility(visibility);
    }
    /**
     * Sets the back icon visibility
     * @param visibility
     */
    public void setBackButtonVisibility(int visibility) {
        back.setVisibility(visibility);
    }

    /**
     * Sets the save icon visibility
     * @param visibility
     */
    public void setSaveIconVisibility(int visibility) {
        save.setVisibility(visibility);
    }

    /**
     * Method to start given activity
     * @param context
     * @param targetActivity
     * @param isFinish
     */
    public void startActivity(final Context context, final Class<?> targetActivity,final boolean isFinish) {
         new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                /* Launch the login activity*/
                Intent myIntent = new Intent(context, targetActivity);
                startActivity(myIntent);
                if(isFinish) {
                    finish();
                }
            }
        }, 1000);
    }

    /**
     * Profile icon click listener
     * @param listener
     */
    public void setProfileClickListener(View.OnClickListener listener){
        profile.setOnClickListener(listener);
    }

    /**
     * back button click listener
     * @param listener
     */
    public void setBackClickListener(View.OnClickListener listener){
        back.setOnClickListener(listener);
    }

    /**
     * Logout icon click listener
     * @param listener
     */
    public void setLogoutClickListener(View.OnClickListener listener){
        logout.setOnClickListener(listener);
    }

    /**
     * save  click listener
     * @param listener
     */
    public void setSaveClickListener(View.OnClickListener listener){
        save.setOnClickListener(listener);
    }

}
