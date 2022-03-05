package bimerso.megabitefood.retopokebowl.models

data class Data(
    val coin: String,
    val id: Int,
    val item_image: String,
    val modifiers: List<Modifier>,
    val name: String,
    val offer: Offer,
    val price: Double,
    val slug: String,
    val summary: String,
    val title: String
)