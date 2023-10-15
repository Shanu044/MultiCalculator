package com.jetbrains.simplelogin.multicalculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform