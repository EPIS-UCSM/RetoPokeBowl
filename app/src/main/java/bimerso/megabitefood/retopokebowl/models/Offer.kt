package bimerso.megabitefood.retopokebowl.models

data class Offer(
    val call_to_action: String,
    val date_active_from: String,
    val date_active_to: String,
    val description: String,
    val discount_percent: Int,
    val id: Int,
    val title: String
)