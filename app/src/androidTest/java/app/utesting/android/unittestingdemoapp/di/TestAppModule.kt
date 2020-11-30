package app.utesting.android.unittestingdemoapp.di

import android.content.Context
import androidx.room.Room
import app.utesting.android.unittestingdemoapp.data.local.ShoppingItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

/**
 * Created by murodjon on 2020/11/30
 */
@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room
            .inMemoryDatabaseBuilder(context, ShoppingItemDatabase::class.java)
            .allowMainThreadQueries()// in Unit tests try to work on thee same thread, since multithreading manipulates each other
            .build()
}