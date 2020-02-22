package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extension.humanizeDiff
import java.util.*

class ImageMessage (
    id: String,
    from: User?,
    chat: Chat,
    date: Date,
    var image:String?,
    isIncoming: Boolean):
    BaseMessage(id, from, chat,  date = date,isIncoming = isIncoming) {

    override fun formatMessage(): String = "id$id ${from?.firstName} ${if(isIncoming)"получил" else
        "отправил"} изображение \"$image\" ${date.humanizeDiff()}"


}