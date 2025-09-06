package com.upn.movil3431.services


import com.upn.movil3431.entities.Contact
import com.upn.movil3431.entities.Pokemon
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // GET https://68b5a32ae5dc090291afbd0d.mockapi.io/contacts
   @GET("/contacts")
   suspend fun getContacts(): List<Contact>

    // https://68b5a32ae5dc090291afbd0d.mockapi.io/contacts/4
   @GET("/contacts/{id}")
  fun findContactById(@Path("id") id: Int): Contact

    // GET https://pokeapi.co/api/v2/pokemon
    @GET("/pokemon")
    suspend fun getPokemon(): List<Pokemon>



}