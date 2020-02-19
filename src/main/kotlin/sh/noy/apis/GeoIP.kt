package sh.noy.apis

// Could add more, but not needed for now.
data class GeoIP(
        val ipAddress: String,
        val countryName: String,
        val countryCode: String,
        val cityName: String,
        val postalCode: String,
        val isInEU: Boolean,
        val longitude: Double,
        val latitude: Double,
        val timeZone: String,
        val continentName: String,
        val continentCode: String
)
