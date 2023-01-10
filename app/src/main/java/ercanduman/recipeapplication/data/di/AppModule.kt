package ercanduman.recipeapplication.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ercanduman.recipeapplication.data.api.RecipeService
import ercanduman.recipeapplication.data.repository.RecipeRepository
import ercanduman.recipeapplication.data.repository.RecipeRepositoryImpl
import ercanduman.recipeapplication.util.AppResourcesProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val API_BASE_URL = "https://food2fork.ca/api/recipe/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(
        retrofit: Retrofit
    ): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeService)
    }

    @Singleton
    @Provides
    fun provideAppResourcesProvider(
        @ApplicationContext context: Context
    ): AppResourcesProvider {
        return AppResourcesProvider(context)
    }
}
