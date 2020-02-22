package ru.skillbranch.devintensive.extension

fun String.truncate(number:Int=16):String{

    var tempString = this.substring(0 until number)
    if(tempString[number-1]==' ') tempString=tempString.trim()
    tempString+="..."

return tempString
}