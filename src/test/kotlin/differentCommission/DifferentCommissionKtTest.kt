package differentCommission

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

class DifferentCommissionKtTest {

    @Test
    fun calcComission_excess_limit_VkPay() {
        val exception = assertThrows(Exception::class.java) {
            calcCommission(nameCard = "VK Pay", payment = 15_100, monthPayments = 40_100)
        }
        assertEquals("Что-то пошло не так", exception.message)
    }

    @Test
    fun calcComission_excess_limit_Visa() {
        val exception = assertThrows(Exception::class.java) {
            calcCommission(nameCard = "Visa", payment = 151_000, monthPayments = 601_000)
        }
        assertEquals("Что-то пошло не так", exception.message)
    }

    @Test
    fun calcComission_excess_limit_MasterCard() {
        val exception = assertThrows(Exception::class.java) {
            calcCommission(nameCard = "MasterCard", payment = 151_000, monthPayments = 601_000)
        }
        assertEquals("Что-то пошло не так", exception.message)
    }


    @Test
    fun calcComissionIncorrectPaymentSystem() {
        val exception = assertThrows(Exception::class.java) {
            // Вызываем некорректную платежную систему
            calcCommission("blah", 100)
        }
        assertEquals("Что-то пошло не так", exception.message)
    }

    @Test
    fun calcComissionNegativePayment() {
        val exception = assertThrows(Exception::class.java) {
            // Пытаемся выполнить отрицательный перевод
            calcCommission("VK Pay", -100)
        }
        assertEquals("Отрицательный перевод", exception.message)
    }

    @Test
    fun commissionMasterCardMaestro() {
        // Пример тестов взят https://github.com/Kotlin/kotlinx-datetime/blob/v0.3.2/core/common/test/DateTimePeriodTest.kt#L53
        assertEquals(0, commissionMasterCardMaestro(payment = 300, monthPayments = 0))
        assertEquals(0, commissionMasterCardMaestro(payment = 300, monthPayments = 70_000))
        assertEquals(20_60, commissionMasterCardMaestro(payment = 100, monthPayments = 0))
        assertEquals(26_00, commissionMasterCardMaestro(payment = 1_000, monthPayments = 100_000))
    }

    @Test
    fun commissioMirVisa_min_transfer() {
        //arrang
        val pay = 10
        //act
        val result = commissioMirVisa(payment = pay)
        //assert
        assertEquals(35_00, result)
    }

    @Test
    fun commissioMirVisa_transfer_percent() {
        val pay = 10_000
        val result = commissioMirVisa(payment = pay)
        assertEquals(75_00, result)
    }

    @Test
    fun calcCommission_VK_Pay_transfer_max_monthPayments() {
        val actual = calcCommission(
            nameCard = "VK Pay",
            payment = 1_000,
            monthPayments = 4_000
        )
        assertEquals(0, actual)
    }
}