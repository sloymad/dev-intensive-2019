package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        if (fullName != null && !fullName.isEmpty()){
            val fullNameList: List<String> = fullName.trim().split(" ")

            val firstName: String? = fullNameList.getOrNull(0)
            val lastName: String? = fullNameList.getOrNull(1)

            return when{
                firstName == "" -> Pair(null, lastName)
                lastName == "" -> Pair(firstName, null)
                else -> Pair(firstName,lastName)
            }
        }
        return Pair(null,null)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO()
    }
}