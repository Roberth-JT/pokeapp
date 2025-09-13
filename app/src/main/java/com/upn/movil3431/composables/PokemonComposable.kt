package com.upn.movil3431.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.upn.movil3431.entities.Pokemon
import com.upn.movil3431.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ListaPokemons() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/") //
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    var isLoading by remember { mutableStateOf(true) }
    var hasNetworkError by remember { mutableStateOf(false) }
    var pokemons by remember { mutableStateOf(listOf<Pokemon>()) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            hasNetworkError = false
            val response = apiService.getPokemon()
            pokemons = response.results
        } catch (e: Exception) {
            hasNetworkError = true
            Log.e("POKEMON_APP", "Error: ${e.message}")
        } finally {
            isLoading = false
        }
    }

    when {
        hasNetworkError -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(" Error al obtener pokemons")
            }
        }
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                items(pokemons) { pokemon ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Nombre: ${pokemon.name}")
                        Text(text = "URL: ${pokemon.url}")
                        HorizontalDivider(
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                    }
                }
            }
        }
    }
}
