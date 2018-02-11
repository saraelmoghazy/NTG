package com.ntg.user.mvpsample.data.sourse.remote;

import dagger.Component;

/**
 * @author islam
 */
@ForApplication
@Component(modules = {NetModule.class})
public interface NetComponent {

    RetrofitProvider getRetrofitProvider();
        class Initializer {
            public static final String baseUrl ="http://twilight-grass-3888.getsandbox.com/";
        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {
               component = DaggerNetComponent.builder().netModule(new NetModule(baseUrl)).build();
            }
            return component;
        }
    }
}
