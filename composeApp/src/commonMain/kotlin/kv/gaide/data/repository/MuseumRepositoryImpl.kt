package kv.gaide.data.repository

import kv.gaide.data.models.Museum

class MuseumRepositoryImpl() : MuseumRepository {

    private val mockMuseums = listOf(
        Museum(1, "Государственный Эрмитаж", "Санкт-Петербург"),
        Museum(2, "Третьяковская галерея", "Москва"),
        Museum(3, "Лувр", "Париж"),
        Museum(4, "Британский музей", "Лондон"),
        Museum(5, "Метрополитен-музей", "Нью-Йорк"),
        Museum(6, "Музей Прадо", "Мадрид"),
        Museum(7, "Музей Ватикана", "Ватикан"),
        Museum(8, "Национальный музей Китая", "Пекин"),
        Museum(9, "Музей современного искусства", "Нью-Йорк"),
        Museum(10, "Музей Орсе", "Париж")
    )

    override suspend fun getMuseumsList(): Result<List<Museum>> = runCatching {
        mockMuseums
    }

    override suspend fun getMuseumsByCity(city: String): Result<List<Museum>> = runCatching {
        mockMuseums.filter { museum ->
            museum.city == city
        }
    }

    override suspend fun getMuseumByName(name: String): Result<List<Museum>> = runCatching {
        mockMuseums.filter { museum ->
            museum.name.contains(name)

        }
    }
}