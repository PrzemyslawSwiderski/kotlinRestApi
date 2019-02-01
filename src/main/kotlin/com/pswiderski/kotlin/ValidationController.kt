package com.pswiderski.kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class ValidationController {

    @GetMapping("/validate")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String,
                 @RequestParam(value = "age", defaultValue = 23.toString()) age: Int) =
            ValidationRequest("testMsg", JPerson(name, age))
}