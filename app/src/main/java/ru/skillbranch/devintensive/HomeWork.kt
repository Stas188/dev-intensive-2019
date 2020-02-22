package ru.skillbranch.devintensive

fun main() {

    val data = mapOf(
        "Январь" to listOf(100,100,100,100),
        "Февраль" to listOf(200,200,-190,200),
        "Март" to listOf(300,180,300,100),
        "Апрель" to listOf(250,-250,100,300),
        "Май" to listOf(200,100,400,300),
        "Июнь" to listOf(200,100,300,300)
        )

    val square:(Int) -> Int = {it*it}
    println(square(6))
    printInfo(data)
}

fun printInfo(data: Map<String, List<Int>>) {


    val verData = data.filter {it.value.all { it>=0 }}

    val aver = verData.flatMap { it.value }.average()

    val summa = verData.map{it.value.sum()}


    val errData = data.filter{it.value.any{ it < 0 }}


    println("Srednee v nedelu $aver")


    for (i in errData)
        println(i.key)

}
