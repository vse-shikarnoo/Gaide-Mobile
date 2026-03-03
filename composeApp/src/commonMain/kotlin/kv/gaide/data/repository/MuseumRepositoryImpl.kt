package kv.gaide.data.repository

import kv.gaide.data.models.Museum

class MuseumRepositoryImpl() : MuseumRepository {

    private val mockMuseums = listOf(
        Museum(
            1,
            "Государственный Эрмитаж",
            "Санкт-Петербург",
            imageUrl = "https://stories.oras.com/hubfs/Imported_Blog_Media/Hermitage_facade-860x480-5.jpg"
        ),
        Museum(2, "Третьяковская галерея", "Москва"),
        Museum(3, "Лувр", "Париж"),
        Museum(4, "Британский музей", "Лондон"),
        Museum(5, "Метрополитен-музей", "Нью-Йорк"),
        Museum(6, "Музей Прадо", "Мадрид"),
        Museum(7, "Музей Ватикана", "Ватикан"),
        Museum(8, "Национальный музей Китая", "Пекин"),
        Museum(
            9,
            "Музей современного искусства",
            "Нью-Йорк",
            description = "Музей современного искусства на Манхеттене в Нью-Йорке (англ. Museum of Modern Art, сокр. MoMA) — один из первых и наиболее представительных музеев современного искусства в мире[6], послуживший эталоном для многих других собраний подобного рода.\n" +
                    "\n" +
                    "Третий по посещаемости музей в Соединённых Штатах, входит в первую двадцатку самых посещаемых художественных музеев мира[7] — 3 066 337 посетителей в 2013 году[8]. Одна из главных достопримечательностей Нью-Йорка."
        ),
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