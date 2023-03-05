package pl.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.service.RecipesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRecipesApi(retrofit: Retrofit): RecipesApi =
        retrofit.create(pl.data.service.RecipesApi::class.java)
}
