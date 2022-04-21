package differentCommission

fun main() {
//    println(
//        "Кейс 1. Перевод с маэтро 200 руб. Получили комиссию: " +
//                "${calcCommission("Maestro", 200)} коп," +
//                " ожидаем: 0 коп."
//    )
//    try {
//        println(
//            "Кейс 9. Перевод с Мир 151_000 руб, мы уже перевели 601_000 за месяц. Получили комиссию: " +
//                    "${calcCommission("Maestro", 151_000, 601_000)} коп," +
//                    " ожидаем: ожидаем исключение"
//        )
//
//    } catch (e: Exception) {
//        println("Получили исключение. Привышены лимиты.")
//    }
}

fun commissionMasterCardMaestro(payment: Int, monthPayments: Int): Int {
    val commissio = ((payment.toDouble()  * 100 * 0.006)).toInt()+ 20_00
    val transferAmount = payment + monthPayments
    if (transferAmount >= 300 && transferAmount <= 75_000) {
        return 0
    }
    return commissio
}

fun commissioMirVisa(payment: Int): Int {
    val commissio = ((payment.toDouble() * 0.0075) * 100).toInt()
    if (commissio > 3500) {
        return commissio
    }
    return 3500
}

fun calcCommission(nameCard: String = "VK Pay", payment: Int, monthPayments: Int = 0): Int {

    return when {
        payment < 0 -> throw Exception("Отрицательный перевод")

        (nameCard == "Visa" || nameCard == "Мир") && (payment <= 150_000 && monthPayments <= 600_000) ->
            commissioMirVisa(payment)
        (nameCard == "MasterCard" || nameCard == "Maestro") && (payment <= 150_000 && monthPayments <= 600_000) ->
            commissionMasterCardMaestro(payment, monthPayments)
        nameCard == "VK Pay" && payment <= 15_000 && monthPayments <= 40_000 -> 0

        else -> throw Exception("Что-то пошло не так")
    }
}
