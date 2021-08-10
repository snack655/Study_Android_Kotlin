package StudyClass

abstract class Animal {
    fun walk() {
        println("걷습니다.")
    }
    abstract fun move()
}

class Bird: Animal() {
    override fun move() {
        println("날아서 이동한다.")
    }
}

interface InterfaceKotlin {
    var variable: String
    fun get()
    fun set()
}

class KotlinImpl: InterfaceKotlin {
    override var variable: String = "init value"
    override fun get() {
        // 코드 구현
    }
    override fun set() {
        // 코드 구현
    }
}

fun main() {
    var kotlinImpl = object: InterfaceKotlin {
        override var variable: String = "init"
        override fun get() {
            // 코드
        }
        override fun set() {
            // 코드
        }
    }

}

