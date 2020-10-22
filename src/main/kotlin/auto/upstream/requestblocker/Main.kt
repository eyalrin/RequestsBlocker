package auto.upstream.requestblocker

import auto.upstream.requestblocker.block.RequestBlocker

fun main(args: Array<String>) {

//    // For testing
//    val requestBlocker1 = RequestBlocker(listOf("192.170.1.0/28", "192.170.0.0/10"))
//    val ip1 = "192.170.1.1" // Not in range
//    val ip2 = "192.170.5.5" // Not in range
//
//    prettyPrint(ip1, requestBlocker1)
//    prettyPrint(ip2, requestBlocker1)

    // For testing
    val requestBlocker2 = RequestBlocker(listOf("192.170.1.0/28"))
    val ip1 = "192.170.1.1" // In range
    val ip2 = "192.170.5.5" // Not in range

    prettyPrint(ip1, requestBlocker2)
    prettyPrint(ip2, requestBlocker2)
}

private fun prettyPrint(ip2: String, requestBlocker: RequestBlocker) {
    println("IP: " + ip2 + " is " + (if (requestBlocker.isAllowed(ip2)) "" else "not ") + "allowed")
}