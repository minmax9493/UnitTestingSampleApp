package app.utesting.android.unittestingdemoapp.di

import android.content.Context
import androidx.room.Room
import app.utesting.android.unittestingdemoapp.data.local.ShoppingDao
import app.utesting.android.unittestingdemoapp.data.local.ShoppingItemDatabase
import app.utesting.android.unittestingdemoapp.data.remote.PixabayAPI
import app.utesting.android.unittestingdemoapp.other.Constants.BASE_URL
import app.utesting.android.unittestingdemoapp.other.Constants.DATABASE_NAME
import app.utesting.android.unittestingdemoapp.repositories.DefaultShoppingRepository
import app.utesting.android.unittestingdemoapp.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by murodjon on 2020/11/26
 */
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultShoppingRepo(
            dao: ShoppingDao,
            api: PixabayAPI) = DefaultShoppingRepository(dao, api) as ShoppingRepository
}