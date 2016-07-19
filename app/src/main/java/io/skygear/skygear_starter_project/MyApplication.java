package io.skygear.skygear_starter_project;

import io.skygear.skygear.SkygearApplication;

public class MyApplication extends SkygearApplication {
    @Override
    public String getSkygearEndpoint() {
        return "http://skygear.dev/";
    }

    @Override
    public String getApiKey() {
        return "changeme";
    }
}
