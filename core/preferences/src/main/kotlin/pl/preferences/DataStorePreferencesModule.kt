package pl.preferences

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStorePreferencesModule {

    @Singleton
    @Provides
    fun providePreferences(
        @ApplicationContext context: Context
    ): DataStorePreferences = DataStorePreferencesImpl(context)
}
