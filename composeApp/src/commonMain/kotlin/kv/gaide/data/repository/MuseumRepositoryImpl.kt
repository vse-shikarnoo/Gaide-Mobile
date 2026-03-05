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
            imageUrl = "https://velvitour.ru/assets/uploads/2020/03/лувр.png",
            description = "Самый посещаемый музей мира, известный картиной «Мона Лиза».",
            tags = listOf("art", "history", "world heritage")
        ),
        Museum(
            id = 4,
            name = "Британский музей",
            city = "Лондон",
            imageUrl = "https://allmyworld.ru/wp-content/uploads/2022/02/dostoprimechatelnosti-londona-The-British-Museum-2048x2048.jpg",
            description = "Один из крупнейших музеев истории и культуры человечества.",
            tags = listOf("history", "culture", "archaeology")
        ),
        Museum(
            id = 5,
            name = "Метрополитен-музей",
            city = "Нью-Йорк",
            imageUrl = "https://i.pinimg.com/474x/2b/54/a1/2b54a19f5278d8684ea18676822beb82.jpg?nii=t",
            description = "Крупнейший художественный музей США.",
            tags = listOf("art", "painting", "sculpture")
        ),
        Museum(
            id = 6,
            name = "Музей Прадо",
            city = "Мадрид",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Museo_del_Prado_2016_%2825185969599%29.jpg/960px-Museo_del_Prado_2016_%2825185969599%29.jpg",
            description = "Главный национальный музей Испании с коллекцией европейского искусства.",
            tags = listOf("art", "spanish art", "painting")
        ),
        Museum(
            id = 7,
            name = "Музеи Ватикана",
            city = "Ватикан",
            imageUrl = "https://q-xx.bstatic.com/xdata/images/xphoto/max1200/269103480.jpg?k=10e56bc3e0a5afa0c22765725176cfd6c6275fbdf1f99d8cfbe4a8b6842472c1&o=",
            description = "Комплекс музеев с огромной коллекцией произведений искусства и Сикстинской капеллой.",
            tags = listOf("art", "religion", "renaissance")
        ),
        Museum(
            id = 8,
            name = "Национальный музей Китая",
            city = "Пекин",
            imageUrl = "https://media.istockphoto.com/id/1489897655/ru/фото/национальный-музей-китая-пекин.jpg?s=612x612&w=0&k=20&c=oTi8TE23NW37Ugom7QR2YzvlnjQ-IFXBkowgAEIBZng=",
            description = "Один из крупнейших музеев мира, посвящённый истории и культуре Китая.",
            tags = listOf("history", "china", "culture")
        ),
        Museum(
            id = 9,
            name = "Музей современного искусства (MoMA)",
            city = "Нью-Йорк",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/10/Museum_of_Modern_Art_%28MoMA%29_%2851395759113%29.jpg/500px-Museum_of_Modern_Art_%28MoMA%29_%2851395759113%29.jpg",
            description = "Один из самых влиятельных музеев современного искусства в мире.",
            tags = listOf("modern art", "design", "contemporary")
        ),
        Museum(
            id = 10,
            name = "Музей Орсе",
            city = "Париж",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/03/Paris_Musée_d%27Orsay_Grande_nef_centrale_02a_Allée_des_sculptures.jpg/1280px-Paris_Musée_d%27Orsay_Grande_nef_centrale_02a_Allée_des_sculptures.jpg",
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