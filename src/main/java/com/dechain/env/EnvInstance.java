package com.dechain.env;

public class EnvInstance {

    static EnvBase env = new EnvBase();

    static public void setEnv(String type) {
    }

    static public void setEnv(EnvBase env) {
        EnvInstance.env = env;
    }

    static public EnvBase getEnv() {
        return env;
    }

}
