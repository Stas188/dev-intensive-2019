package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?) : Pair<String?, String?> { // not null
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)  //?:"#Null first name#"
        if(firstName?.isEmpty()==true) firstName = null


        var lastName = parts?.getOrNull(1) //?:"#Null last name#"
        if(lastName?.isEmpty()==true) lastName = null

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
        var status = false
        for (i in 0 until payload.length) {


            for(j in 0 until englishLetter.size){
                if (payload[i].toString() == russianLetter[j])
                {
                    newString += englishLetter[j]
                    status = true
                }
            }
            if (payload[i].toString()==" ") {
                newString+=divider
                status = true
            }
            if (!status) newString += payload[i]
            status = false
        }
      return newString
    }

    fun toInitials(firstName: String? = null, lastName: String? = null): String? {

        val firstNameLatter = if(firstName.isNullOrEmpty() || firstName.isNullOrBlank()) null else firstName?.get(0)?.toUpperCase()
        val lastNameLatter = if(lastName.isNullOrEmpty() || lastName.isNullOrBlank()) null else lastName?.get(0)?.toUpperCase()


        val result = if (firstNameLatter==null && lastNameLatter==null) null else "$firstNameLatter${lastNameLatter?:""}"
        return result
    }
}