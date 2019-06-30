package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user2 = User("2", "John", "Cena")
    }

    @Test
    fun test_factory() {
     //   val user = User.Factory.makeUser("Jonh Cena")
    //    val user2 = User.Factory.makeUser("Jonh Wick")
        val user3 = User.Factory.makeUser("Jonh Silverhand")
        val user2=user3.copy(id="2", lastName = "Cena", lastVisit = Date())
        print("$user2 \n$user3")
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("John Wick")
        fun getUserInfo() = user

        val(id, firstName, lastName)= getUserInfo()

        println("$id, $firstName, $lastName")
        println("${user.component1()},${user.component2()},${user.component3()}")
    }

    @Test
    fun test_copy() {
        val user1 = User.makeUser("John Wick")
        val user2 = user1.copy(id="2", lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user3 = user1.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println("""
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
        """.trimIndent())
    }

    @Test
    fun test_dataq_mapping(){
        val user1 = User.makeUser("John Wick")
        print(user1)

        val userView = user1.toUserView()
        userView.prinMe()
    }
    @Test
    fun test_abstract_factory(){
        val user = User.makeUser("Алексей Бельский")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMessage = BaseMessage.makeMessage(user, Chat("0"),payload = "any image url", type = "image")

        println(txtMessage.formatMessage())
        println(txtMessage.formatMessage())
    }
}
