package com.ntg.user.mvpsample.data.source.remote;

import com.ntg.user.mvpsample.ApplicationComponent;
import com.ntg.user.mvpsample.ApplicationModule;
import com.ntg.user.mvpsample.NTGApplication;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by mohamed yassin on 2/7/2018.
 */
@Singleton
@Component(dependencies = {ApplicationComponent.class}, modules = {NetModule.class})
public interface NetComponent {
    void inject(TaskRemoteDataSource remoteTaskRepo);

    class Initializer {

        private static NetComponent component;

        public static NetComponent buildComponent() {
            if (component == null) {
                component = DaggerNetComponent.builder()
                        .applicationComponent(NTGApplication.getApplicationComponent())
                        .netModule(new NetModule("http://yassin.getsandbox.com/"))
                        .build();
            }

            return component;
        }
    }
}
