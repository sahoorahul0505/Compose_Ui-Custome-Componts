package com.kodebug.customecomponets.data

import kotlinx.coroutines.internal.OpDescriptor

data class Model(
    val id : String,
    val name : String,
    val descriptor: String,
)