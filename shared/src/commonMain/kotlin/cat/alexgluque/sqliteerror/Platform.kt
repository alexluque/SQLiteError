package cat.alexgluque.sqliteerror

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform