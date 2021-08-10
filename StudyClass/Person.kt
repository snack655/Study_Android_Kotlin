package StudyClass

import java.sql.DriverManager.println

fun main() {
    println("생성자");
    Person(243, "민재")
}

class Person {
    constructor(value: String) {
        println("생성자로부터 전달받은 값은 ${value}입니다.")
    }
    constructor(value: Int) {
        println("생성자로부터 전달받은 값은 ${value}입니다.")
    }
    constructor(value1: Int, value2: String) {
        println("생성자로부터 전달받은 값은 ${value1}, ${value2}입니다.")
    }
}