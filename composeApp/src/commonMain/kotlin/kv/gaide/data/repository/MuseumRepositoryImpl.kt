package kv.gaide.data.repository

import kv.gaide.data.models.Museum

class MuseumRepositoryImpl() : MuseumRepository {

    private val mockMuseums = listOf(
        Museum(
            id = 1,
            name = "Государственный Эрмитаж",
            city = "Санкт-Петербург",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Winter_Palace_Panorama_4.jpg/1920px-Winter_Palace_Panorama_4.jpg",
            description = "Один из крупнейших художественных музеев мира. Основан в 1764 году Екатериной II.",
            tags = listOf("art", "history", "painting")
        ),
        Museum(
            id = 2,
            name = "Третьяковская галерея",
            city = "Москва",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Moscow_05-2012_TretyakovGallery.jpg/1920px-Moscow_05-2012_TretyakovGallery.jpg",
            description = "Главный музей русского изобразительного искусства.",
            tags = listOf("art", "russian art", "painting")
        ),
        Museum(
            id = 3,
            name = "Лувр",
            city = "Париж",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a2/Louvre_Courtyard%2C_Looking_West.jpg/640px-Louvre_Courtyard%2C_Looking_West.jpg",
            description = "Самый посещаемый музей мира, известный картиной «Мона Лиза».",
            tags = listOf("art", "history", "world heritage")
        ),
        Museum(
            id = 4,
            name = "Британский музей",
            city = "Лондон",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/British_Museum_from_NE_2_%28cropped%29.JPG/640px-British_Museum_from_NE_2_%28cropped%29.JPG",
            description = "Один из крупнейших музеев истории и культуры человечества.",
            tags = listOf("history", "culture", "archaeology")
        ),
        Museum(
            id = 5,
            name = "Метрополитен-музей",
            city = "Нью-Йорк",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Metropolitan_Museum_of_Art_%28The_Met%29_-_Central_Park%2C_NYC.jpg/640px-Metropolitan_Museum_of_Art_%28The_Met%29_-_Central_Park%2C_NYC.jpg",
            description = "Крупнейший художественный музей США.",
            tags = listOf("art", "painting", "sculpture")
        ),
        Museum(
            id = 6,
            name = "Музей Прадо",
            city = "Мадрид",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Museo_del_Prado_2016_%2825185969599%29.jpg/640px-Museo_del_Prado_2016_%2825185969599%29.jpg",
            description = "Главный национальный музей Испании с коллекцией европейского искусства.",
            tags = listOf("art", "spanish art", "painting")
        ),
        Museum(
            id = 7,
            name = "Музеи Ватикана",
            city = "Ватикан",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Museums_in_the_Vatican_City.jpg/640px-Museums_in_the_Vatican_City.jpg",
            description = "Комплекс музеев с огромной коллекцией произведений искусства и Сикстинской капеллой.",
            tags = listOf("art", "religion", "renaissance")
        ),
        Museum(
            id = 8,
            name = "Национальный музей Китая",
            city = "Пекин",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/National_Museum_of_China_building_2.jpg/640px-National_Museum_of_China_building_2.jpg",
            description = "Один из крупнейших музеев мира, посвящённый истории и культуре Китая.",
            tags = listOf("history", "china", "culture")
        ),
        Museum(
            id = 9,
            name = "Музей современного искусства (MoMA)",
            city = "Нью-Йорк",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Museum_of_Modern_Art_Saitama_2010.jpg/640px-Museum_of_Modern_Art_Saitama_2010.jpg",
            description = "Один из самых влиятельных музеев современного искусства в мире.",
            tags = listOf("modern art", "design", "contemporary")
        ),
        Museum(
            id = 10,
            name = "Музей Орсе",
            city = "Париж",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Gare_d%27Orsay_%2849570190081%29.jpg/640px-Gare_d%27Orsay_%2849570190081%29.jpg",
            description = "Музей французского искусства XIX–XX веков, расположенный в здании бывшего вокзала.",
            tags = listOf("impressionism", "art", "painting")
        )
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