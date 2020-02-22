package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.extension.humanizeDiff
import java.util.*

class ImageMessage (
    id: String,
    from: User?,
    chat: Chat,
    date: Date,
    var image:String?):
    BaseMessage(id, from, chat,  date = date) {

    override fun formatMessage(): String = "id$id ${from?.firstName} ${if(isIncoming)"получил" else
        "отправил"} изображение \"$image\" ${date.humanizeDiff()}"


}