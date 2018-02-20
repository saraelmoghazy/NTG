package com.ntg.user.mvpsample.network.remote;

import dagger.Component;

/**
 * @author Sara Elmoghazy
 */
@ForApplication
@Component(modules = {NetModule.class})
public interface NetComponent {
    public static final String BaseUrl = "http://endpoints.getsandbox.com/";

    RetrofitProvider getRetrofitProvider();

    class Initializer {

        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {
                component = DaggerNetComponent.builder()
                        .netModule(new NetModule(BaseUrl))
                        .build();
            }

            return component;
        }
    }
}
