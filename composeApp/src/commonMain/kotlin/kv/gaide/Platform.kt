package kv.gaide

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform