// @author Noy Hillel
package sh.noy.apis

import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api")
@ApiKey()
class IPController {

    private val readGeoIP: ReadGeoIP = ReadGeoIP()
    private val annotation = javaClass.getAnnotation(ApiKey::class.java)!!

    private fun validate(ipAddress: String): GeoIP {
        val fromCurrentRequest = ServletUriComponentsBuilder.fromCurrentRequest()
        fromCurrentRequest.build().queryParams.forEach {
            if (it.key == "api-key") {
                it.value.forEach { apiKey ->
                    return if (apiKey != annotation.value) {
                        readGeoIP.apiKeyError()
                    } else {
                        readGeoIP.getLocation(ipAddress)
                    }
                }
            }
        }
        return readGeoIP.apiKeyError()
    }

    @PostMapping("/full")
    @Throws(Exception::class)
    fun getFullLocation(@RequestParam(value = "ipAddress", required = true) ipAddress: String): GeoIP {
        return validate(ipAddress)
    }

    @PostMapping("/country")
    fun getCountryName(@RequestParam(value = "ipAddress", required = true) ipAddress: String): String {
        return validate(ipAddress).countryName
    }

    @PostMapping("/countryCode")
    fun getCountryCode(@RequestParam(value = "ipAddress", required = true) ipAddress: String): String {
        return validate(ipAddress).countryCode
    }

    @PostMapping("/continent")
    fun getContinent(@RequestParam(value = "ipAddress", required = true) ipAddress: String): String {
        return validate(ipAddress).continentName
    }

    @PostMapping("/city")
    fun getCity(@RequestParam(value = "ipAddress", required = true) ipAddress: String): String {
        return validate(ipAddress).cityName
    }

    @PostMapping("/postcode")
    fun getPostalCode(@RequestParam(value = "ipAddress", required = true) ipAddress: String): String {
        return validate(ipAddress).postalCode
    }

    @PostMapping("/longitude")
    fun getLongitude(@RequestParam(value = "ipAddress", required = true) ipAddress: String): Double {
        return validate(ipAddress).longitude
    }

    @PostMapping("/latitude")
    fun getLatitude(@RequestParam(value = "ipAddress", required = true) ipAddress: String): Double {
        return validate(ipAddress).latitude
    }

    @PostMapping("/isineu")
    fun isInEU(@RequestParam(value = "ipAddress", required = true) ipAddress: String): Boolean {
        return validate(ipAddress).isInEU
    }

    @PostMapping("/timezone")
    fun getTimeZone(@RequestParam(value = "ipAddress", required = true) ipAddress: String): String {
        return validate(ipAddress).timeZone
    }
}
