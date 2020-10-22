package auto.upstream.requestblocker.service

import auto.upstream.requestblocker.model.IpRangeInfo

class RequestBlockerService(blockedcCidrs: Collection<String>) {

    private var cidars = mutableListOf<IpRangeInfo>()

    init {
        for (blockedCidr in blockedcCidrs) {
            this.cidars.add(IpRangeInfo(blockedCidr))
        }
    }

    fun isAllowed(incomingIp: String): Boolean {
        return !cidars.stream().filter { it.isInRange(IpRangeInfo.Companion.ipStringToInt(incomingIp)) }.findAny().isPresent
    }
}