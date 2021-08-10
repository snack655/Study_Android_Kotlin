package StudyClass

open class Parent {
    var hello: String = "안녕하세요."
    fun sayHello() {
        println(hello);
    }
}

class Child: Parent() {
    fun myHello() {
        hello = "Hello!"
        sayHello()
    }
}

open class BaseClass {
    open fun opened() {

    }
    fun notOpened() {
    }
}

class ChildClass: BaseClass() {
    override fun opened() {

    }
    /*override fun notOpened() {

    }*/
}

open class BaseClass2 {
    open var opened: String = "I am"
}

class ChildClass2: BaseClass2() {
    override var opened: String = "You are"
}


fun main() {
    val child1 = Child()
    child1.myHello()
}