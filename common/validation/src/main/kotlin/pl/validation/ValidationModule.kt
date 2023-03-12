package pl.validation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ValidationModule {

    @Singleton
    @Binds
    fun bindValidator(impl: ValidatorImpl): Validator
}
