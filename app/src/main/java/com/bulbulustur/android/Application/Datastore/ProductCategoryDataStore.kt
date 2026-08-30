package com.bulbulustur.android.Application.Datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first

private const val ProductCategoryDataStoreName = "bulbulustur_product_categories"

private val Context.ProductCategoryDataStoreInstance: DataStore<Preferences> by preferencesDataStore(
    name = ProductCategoryDataStoreName
)

class ProductCategoryDataStore(
    context: Context
) {
    private val DataStore: DataStore<Preferences> =
        context.applicationContext.ProductCategoryDataStoreInstance

    private val Gson = Gson()

    suspend fun Get(languageId: Int): List<ProductCategoryDTO> {
        if (languageId <= 0) return emptyList()

        val preferences = DataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .first()

        val json = preferences[CategoryKey(languageId)].orEmpty()
        if (json.isBlank()) return emptyList()

        return runCatching {
            val type = object : TypeToken<List<ProductCategoryDTO>>() {}.type
            Gson.fromJson<List<ProductCategoryDTO>>(json, type).orEmpty()
        }.getOrElse {
            emptyList()
        }
    }

    suspend fun Set(
        languageId: Int,
        categories: List<ProductCategoryDTO>
    ) {
        if (languageId <= 0 || categories.isEmpty()) return

        val json = Gson.toJson(categories)

        DataStore.edit { preferences ->
            preferences[CategoryKey(languageId)] = json
        }
    }

    suspend fun Clear(languageId: Int) {
        if (languageId <= 0) return

        DataStore.edit { preferences ->
            preferences.remove(CategoryKey(languageId))
        }
    }

    private fun CategoryKey(languageId: Int): Preferences.Key<String> =
        stringPreferencesKey("product_categories_$languageId")
}
