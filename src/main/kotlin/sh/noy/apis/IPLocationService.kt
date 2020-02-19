// @author Noy Hillel
package sh.noy.apis

interface IPLocationService {
    fun getLocation(ip: String): GeoIP
    fun apiKeyError(): GeoIP
}
