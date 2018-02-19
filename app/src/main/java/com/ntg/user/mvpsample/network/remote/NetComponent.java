package com.ntg.user.mvpsample.network.remote;

import dagger.Component;

/**
 * Created by Sara Elmoghazy on 07/02/2018.
 */
@ForApplication
@Component(modules = {NetModule.class})
public interface NetComponent {

    RetrofitProvider getRetrofitProvider();

    class Initializer {

        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {
                component = DaggerNetComponent.builder()
                        .netModule(new NetModule("http://twilight-grass-3888.getsandbox.com/"))
                        .build();
            }

            return component;
        }
    }
}
