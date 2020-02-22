package ru.skillbranch.devintensive.extension

fun String.truncate(number:Int=16):String{

    if (this.length < number) return this.trimEnd()

    var tempString = this.substring(0 until number)
    if(tempString[number-1]==' ') tempString=tempString.trimEnd() //else
    tempString+="..."

return tempString
}