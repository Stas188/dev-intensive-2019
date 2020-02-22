package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extension.humanizeDiff
import java.util.*

class TextMessage (
    id: String,
    from: User?,
    chat: Chat,
    date: Date,
    var text:String?,
    isIncoming: Boolean):
    BaseMessage(id, from, chat,date=date,isIncoming = false) {

    override fun formatMessage(): String = "id$id ${from?.firstName} ${if(isIncoming)"получил" else 
        "отправил"} сообщеник \"$text\" ${date.humanizeDiff()}"

}