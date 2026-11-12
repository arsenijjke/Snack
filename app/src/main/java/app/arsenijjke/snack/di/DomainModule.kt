package app.arsenijjke.snack.di

import app.arsenijjke.data.remote.api.MealApiService
import app.arsenijjke.data.remote.repository.MealRepositoryImpl
import app.arsenijjke.data.util.IoDispatcher
import app.arsenijjke.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainProvideModule {

    @Provides
    @Singleton
    fun provideMealRepositoryImpl(
        api: MealApiService,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MealRepositoryImpl =
        MealRepositoryImpl(
            api = api,
            ioDispatcher = ioDispatcher
        )

}

@Module
@InstallIn(SingletonComponent::class)
interface DomainBindModule {
    @Binds
    fun bindMealRepository(repo: MealRepositoryImpl): MealRepository
}
