package com.upn.movil3431

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.upn.movil3431.composables.ListaPokemons
import com.upn.movil3431.ui.theme.Movil3431Theme

class PokemonJetpackActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Movil3431Theme {


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    ) {
                        ListaPokemons()
                    }

                }
            }
        }
    }
}

