package bimerso.megabitefood.retopokebowl.models

data class Modifier(
    val description: String,
    val item_id: Int,
    val item_name: String,
    val max: Int,
    val min: Int,
    val options: List<Option>,
    val order: Int,
    val required: Boolean,
    val rule: String,
    val slug: String,
    val type: String
)