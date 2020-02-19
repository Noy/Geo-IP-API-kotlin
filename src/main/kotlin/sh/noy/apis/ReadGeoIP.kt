// @author Noy Hillel
package sh.noy.apis

import com.maxmind.geoip2.DatabaseReader
import com.maxmind.geoip2.exception.GeoIp2Exception
import java.io.File
import java.io.IOException
import java.net.InetAddress

class ReadGeoIP: IPLocationService {

    private val databaseReader: DatabaseReader

    init {
        val database = File("geodb.mmdb")
        databaseReader = DatabaseReader.Builder(database).build()
    }

    @Throws(IOException::class, GeoIp2Exception::class)
    override fun getLocation(ip: String): GeoIP {
        val ipAddress = InetAddress.getByName(ip)
        val response = databaseReader.city(ipAddress)
        val cityName = response.city.name
        val postalCode = response.postal.code
        val inEuropeanUnion = response.country.isInEuropeanUnion
        val longitude = response.location.longitude
        val latitude = response.location.latitude
        val timeZone = response.location.timeZone
        val countryName = response.country.name
        val countryCode= response.country.isoCode
        val continentName = response.continent.name
        val continentCode = response.continent.code
        return GeoIP(ip, countryName, countryCode, cityName, postalCode, inEuropeanUnion, longitude, latitude, timeZone, continentName, continentCode)
    }

    @Throws(APIKeyException::class)
    override fun apiKeyError(): GeoIP {
        return GeoIP("Invalid API Key",
                "Invalid API Key",
                "Invalid API Key",
                "Invalid API Key",
                "Invalid API Key",
                false,
                0.0,
                0.0,
                "Invalid API Key",
                "Invalid API Key",
                "Invalid API Key")
    }
}

data class APIKeyException(val string: String): Throwable()
