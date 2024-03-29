package pl.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.data.repository.IngredientDetailsRepositoryImpl
import pl.data.repository.OnboardingRepositoryImpl
import pl.data.repository.RecipeDetailsRepositoryImpl
import pl.data.repository.RecipesRepositoryImpl
import pl.domain.repository.IngredientDetailsRepository
import pl.domain.repository.OnboardingRepository
import pl.domain.repository.RecipeDetailsRepository
import pl.domain.repository.RecipesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

    @Singleton
    @Binds
    fun bindRecipesRepository(impl: RecipesRepositoryImpl): RecipesRepository

    @Singleton
    @Binds
    fun bindRecipeDetailsRepository(impl: RecipeDetailsRepositoryImpl): RecipeDetailsRepository

    @Singleton
    @Binds
    fun bindIngredientDetailsRepository(impl: IngredientDetailsRepositoryImpl): IngredientDetailsRepository
}
