package bimerso.megabitefood.retopokebowl.models

data class Option(
    val available: Boolean,
    val id: Int,
    val item_id: Int,
    val max_limit: Int,
    val price: Int,
    val slug: String,
    val summary: String,
    val title: String,
    val unit: Int
)