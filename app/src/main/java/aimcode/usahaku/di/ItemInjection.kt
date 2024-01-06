package aimcode.usahaku.di

import aimcode.usahaku.data.ItemRepository

object ItemInjection {
    fun provideRepository(): ItemRepository {
        return ItemRepository.getInstance()
    }
}