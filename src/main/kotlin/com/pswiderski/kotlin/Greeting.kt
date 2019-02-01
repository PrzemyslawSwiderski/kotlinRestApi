package com.pswiderski.kotlin

import io.swagger.annotations.ApiModel

@ApiModel(description = "Sample description of greeting model")
data class Greeting(val id: Long, val content: String)
