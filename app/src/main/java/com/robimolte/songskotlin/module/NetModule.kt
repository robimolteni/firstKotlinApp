package com.robimolte.songskotlin.module

import com.robimolte.songskotlin.Utils
import com.robimolte.songskotlin.repository.MusixMatchAPI
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule() {

    @Provides
    fun provideClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }.build()
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): MusixMatchAPI {
        return retrofit.create(MusixMatchAPI::class.java)
    }

}