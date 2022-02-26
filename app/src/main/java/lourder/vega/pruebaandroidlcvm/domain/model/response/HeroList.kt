package lourder.vega.pruebaandroidlcvm.domain.model.response

data class HeroList (
    val id: Int,
    val name: String,
    val images: Images
    )

data class ImagesList(
    val md: String
)