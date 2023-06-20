package com.example.deliverytest.di

import androidx.room.Room
import com.example.deliverytest.ui.bag.BagViewModel
import com.example.deliverytest.ui.dishes.DishesViewModel
import com.example.deliverytest.ui.main.MainViewModel
import com.github.terrakok.cicerone.Cicerone
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DiKoin {
    private fun provideGson(): Gson =
        GsonBuilder()
            .setPrettyPrinting()
            .create()

    private fun provideGetDataApi(gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(com.example.repository.api.ApiInterceptor)
                    .addNetworkInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    val koinModule = module {
        //Cicerone
        single { Cicerone.create() }

        //Room
        single { Room.databaseBuilder(get(), com.example.repository.data.room.BagDatabase::class.java, "bagDatabase.db").build() }
        single { get<com.example.repository.data.room.BagDatabase>().bagDishesDao() }

        //Categories
        single<com.example.repository.data.categories.CategoriesRepo> {
            com.example.repository.data.categories.CategoriesRepoImpl(
                provideGetDataApi(provideGson()).create(
                    com.example.repository.api.CategoriesApi::class.java
                )
            )
        }
        viewModel { MainViewModel(categoriesRepo = get()) }

        //Dishes
        single<com.example.repository.data.dishes.DishesRepo> {
            com.example.repository.data.dishes.DishesRepoImp(
                provideGetDataApi(provideGson()).create(com.example.repository.api.DishesApi::class.java)
            )
        }
        viewModel { DishesViewModel(dishesRepo = get()) }

        //Bag
        viewModel { BagViewModel(bagDishesDao = get()) }
    }
}