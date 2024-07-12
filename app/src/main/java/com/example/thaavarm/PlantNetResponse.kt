package com.example.thaavarm.api

data class PlantNetResponse(
    val results: List<Result>
)

data class Result(
    val score: Double,
    val species: Species
)

data class Species(
    val scientificNameWithoutAuthor: String,
    val commonNames: List<String>,
    val family: Family
)

data class Family(
    val scientificNameWithoutAuthor: String
)
