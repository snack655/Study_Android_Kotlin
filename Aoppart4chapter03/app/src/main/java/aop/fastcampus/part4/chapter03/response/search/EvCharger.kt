package aop.fastcampus.part4.chapter03.response.search

data class EvCharger(
    val chargerId: String,
    val chargingDateTime: String,
    val isAvailable: String,
    val isFast: String,
    val operatorId: String,
    val operatorName: String,
    val powerType: String,
    val stationId: String,
    val status: String,
    val type: String,
    val updateDateTime: String
)