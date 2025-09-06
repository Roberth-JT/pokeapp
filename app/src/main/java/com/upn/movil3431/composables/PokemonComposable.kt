package com.upn.movil3431.composables

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.upn.movil3431.entities.Pokemon
import com.upn.movil3431.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.plus


@Composable
fun ListaPokemons() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)


    var isLoading by remember { mutableStateOf(true) }
    var hasNetworkError by remember { mutableStateOf(false) }
    var pokemon by remember { mutableStateOf(listOf<Pokemon>()) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            hasNetworkError = false
            pokemon = apiService.getPokemon()
        } catch (e: Exception) {
            hasNetworkError = true
            Log.e("MAIN_APP", e.toString())
        } finally {
            isLoading = false
        }
    }
    if (hasNetworkError) {
        Text(text = "No se ha podido conectar con el servicio para obtener la lista de pokemons")
    } else {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column {
                Button(
                    onClick = {
                        pokemon = pokemon + Pokemon("Nuevo Contacto", "000000000")
                    }
                ) {
                    Text(text = "Add Random Contact")
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    items(pokemon.size) { index ->
                        val contact = pokemon[index]
                        Column {
                            Text(
                                text = contact.name,
                            )
                            Text(
                                text = contact.url
                            )
                        }
                        HorizontalDivider(
                            Modifier,
                            DividerDefaults.Thickness,
                            DividerDefaults.color
                        )
                    }
                }
            }
        }
    }
}