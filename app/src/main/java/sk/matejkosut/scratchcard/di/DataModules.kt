package sk.matejkosut.scratchcard.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sk.matejkosut.scratchcard.data.DefaultScratchCardRepository
import sk.matejkosut.scratchcard.data.ScratchCardRepository
import sk.matejkosut.scratchcard.data.source.local.ScratchCardDao
import sk.matejkosut.scratchcard.data.source.local.ScratchCardDatabase
import sk.matejkosut.scratchcard.data.source.network.ActivationService
import sk.matejkosut.scratchcard.data.source.network.LiveNetworkMonitor
import sk.matejkosut.scratchcard.data.source.network.NetworkMonitor
import sk.matejkosut.scratchcard.data.source.network.NetworkMonitorInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindScratchCardRepository(repository: DefaultScratchCardRepository): ScratchCardRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesLiveNetworkMonitor(
        @ApplicationContext appContext: Context
    ): NetworkMonitor {
        return LiveNetworkMonitor(appContext)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        liveNetworkMonitor: LiveNetworkMonitor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(NetworkMonitorInterceptor(liveNetworkMonitor))
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.o2.sk/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideActivationService(retrofit: Retrofit): ActivationService
        = retrofit.create(ActivationService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ScratchCardDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ScratchCardDatabase::class.java,
            "ScratchCard.db"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: ScratchCardDatabase): ScratchCardDao = database.scratchCardDao()
}
