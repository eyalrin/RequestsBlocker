package auto.upstream.requestblocker.block

import org.apache.commons.net.util.SubnetUtils

class RequestBlocker(blockedcCidrs: Collection<String>) {

    private var cidars = mutableListOf<SubnetUtils>()

    init {
        for (blockedCidr in blockedcCidrs) {
            this.cidars.add(SubnetUtils(blockedCidr))
        }
    }

    fun isAllowed(incomingIp: String): Boolean {
        return !cidars.stream().filter { it.info.isInRange(incomingIp) }.findAny().isPresent
    }
}