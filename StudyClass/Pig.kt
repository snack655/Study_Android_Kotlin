package StudyClass

class Pig {
    companion object {
        var name: String = "Pinky"
        fun printName() {
            println("Pig의 이름은 ${name}입니다.")
        }
    }
    fun walk() {
        println("$name 걸어갑니다.")
    }
}

data class Students(val name: String, var age: Int)

fun main() {
    // companion object 안의 코드 사용하기
    Pig.name = "SSnack"
    Pig.printName()

    // companion object 밖의 코드 사용하기
    val fatPig = Pig()
    fatPig.walk()

    var student1 = Students("ssnack", 17)
    //student1.name = "white_bear" val로 선언되어 변경 불가
    student1.age = 20 // var이니 가능

    println("Students는 ${student1.toString()}")
    var student2 = student1.copy()

}