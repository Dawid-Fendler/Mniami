package pl.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.datasource.recipedetails.RecipeDetailsDataSource
import pl.data.datasource.recipedetails.RecipeDetailsRemoteDataSource
import pl.data.datasource.recipes.RecipesDataSource
import pl.data.datasource.recipes.RecipesRemoteDataSource
import pl.data.repository.AuthRepositoryImpl
import pl.data.service.RecipeDetailsApi
import pl.data.service.RecipesApi
import pl.domain.repository.AuthRepository
import pl.preferences.DataStorePreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideAuthRepository(auth: FirebaseAuth, dataStore: DataStorePreferences): AuthRepository =
        AuthRepositoryImpl(auth, dataStore)

    @Singleton
    @Provides
    fun provideRecipesDataSource(api: RecipesApi): RecipesDataSource =
        RecipesRemoteDataSource(api)

    @Singleton
    @Provides
    fun provideRecipeDetailsDataSource(api: RecipeDetailsApi): RecipeDetailsDataSource =
        RecipeDetailsRemoteDataSource(api)
}
