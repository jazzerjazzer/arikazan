package com.po.kazan;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        
        cfg.useGL20 = true;
        cfg.hideStatusBar = true;
        ActionResolverAndroid actionResolver = new ActionResolverAndroid(this);
        
        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(initializeForView(new MainProgram(actionResolver), false));
        setContentView(layout);

        // starts libGDX render thread
        //initialize(new MainProgram(actionResolver), cfg);
    }
}