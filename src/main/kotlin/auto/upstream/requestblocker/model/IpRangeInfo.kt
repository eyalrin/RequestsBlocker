package auto.upstream.requestblocker.model

class IpRangeInfo
(cidrIp: String) {

    private var addr: Int = 0
    private var mask: Int = 0
    private var lowest: Int = 0
    private var highest: Int = 0

    fun isInRange(ip: Int): Boolean {
        return ip in lowest..highest
    }

    companion object {
        fun ipStringToInt(ip: String): Int {
            val ipSplitted = ip.split(".")
            val octact1 = ipSplitted[0].toInt()
            val octact2 = ipSplitted[1].toInt()
            val octact3 = ipSplitted[2].toInt()
            val octact4 = ipSplitted[3].toInt()

            return (octact1 shl 24 and -0x1000000
                    or (octact2 shl 16 and 0xFF0000)
                    or (octact3 shl 8 and 0xFF00)
                    or (octact4 and 0xFF))
        }
    }

    init {
        val split = cidrIp.split("/")
        val ip = split[0]
        val cidrMask = split[1].toInt()
        // Step 1. Convert IPs into ints (32 bits).
        this.addr = ipStringToInt(ip)
        // Step 2. Get CIDR mask
        this.mask = -1 shl 32 - cidrMask
        // Step 3. Find lowest IP address
        this.lowest = addr and mask
        // Step 4. Find highest IP address
        this.highest = lowest + mask.inv()
    }
}