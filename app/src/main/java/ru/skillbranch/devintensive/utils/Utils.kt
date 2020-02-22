package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?) : Pair<String, String> { // not null
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)?:"#Null first name#"
        if (firstName.isEmpty()) firstName = "=Empty Name="

        var lastName = parts?.getOrNull(1)?:"#Null last name#"
        if(lastName.isEmpty()) lastName = "=Empty Last Name="

        return firstName to lastName
    }

    fun transliteration(payload: String,divider:String = " "): String {
       val russianLetter = arrayOf("А","Б","В","Г","Д","Е","Ё", "Ж", "З","И","Й","К","Л","М","Н","О","П","Р","С",
           "Т","У","Ф","Х","Ч", "Ц","Ш", "Щ", "Э","Ю", "Я", "Ы","Ъ", "Ь", "а","б","в","г","д","е","ё",
           "ж", "з","и","й","к","л","м","н","о","п","р","с","т","у","ф","х","ч", "ц","ш", "щ", "э","ю",
           "я", "ы","ъ","ь")
        val englishLetter = arrayOf("A","B","V","G","D","E","E","Zh","Z","I","I","K","L","M","N","O","P","R","S",
            "T","U","F","H","C","Ch","Sh","Sh","E","Yu","Ya","I","", "'", "a","b","v","g","d","e",
            "e","zh","z","i","i","k","l","m","n","o","p","r","s","t","u","f","h","c","ch","sh","sh",
            "e","yu","ya","i","","")

        var newString:String = ""
        for (i in 0 until payload.length) {
            for(j in 0 until englishLetter.size){
                if (payload[i].toString() == russianLetter[j])
                    newString += englishLetter[j]
            }
            if (payload[i].toString()==divider) newString+=divider
        }
      return newString
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "${firstName?.get(0)}${lastName?.get(0)}"
    }
}