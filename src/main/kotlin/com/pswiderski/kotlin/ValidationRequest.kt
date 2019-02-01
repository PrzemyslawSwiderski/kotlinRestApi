package com.pswiderski.kotlin

import io.swagger.annotations.ApiModelProperty

data class ValidationRequest(val message: String,@ApiModelProperty val person: JPerson)