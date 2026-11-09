package app.arsenijjke.snack.di

import app.arsenijjke.data.remote.api.MealApiService
import app.arsenijjke.data.remote.repository.MealRepositoryImpl
import app.arsenijjke.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainProvideModule {

    @Provides
    @Singleton
    fun provideMealRepositoryImpl(api: MealApiService): MealRepositoryImpl =
        MealRepositoryImpl(
            api = api
        )

}

@Module
@InstallIn(SingletonComponent::class)
interface DomainBindModule {
    @Binds
    fun bindMealRepository(repo: MealRepositoryImpl): MealRepository
}
