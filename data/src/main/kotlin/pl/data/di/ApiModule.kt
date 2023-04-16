package pl.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.service.IngredientsDetailsApi
import pl.data.service.RecipeDetailsApi
import pl.data.service.RecipesApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideRecipesApi(retrofit: Retrofit): RecipesApi =
        retrofit.create(RecipesApi::class.java)

    @Singleton
    @Provides
    fun provideRecipeDetailsApi(retrofit: Retrofit): RecipeDetailsApi =
        retrofit.create(RecipeDetailsApi::class.java)

    @Singleton
    @Provides
    fun provideIngredientDetailsApi(retrofit: Retrofit): IngredientsDetailsApi =
        retrofit.create(IngredientsDetailsApi::class.java)
}
