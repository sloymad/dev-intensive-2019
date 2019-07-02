package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (
            val id:String,
            val from:User?,
            val chat:Chat,
            val isIncoming : Boolean = false,
            val date: Date = Date()
)
{
    abstract fun formatMessage():String
    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), payload: Any?, type: String, isIncoming: Boolean = false):BaseMessage{
            lastId++
            return when(type) {
                "image" -> ImageMessage("$lastId",from,chat,date=date, image = payload as String, isIncoming = isIncoming)
                "text" -> TextMessage("$lastId", from, chat, date = date, text = payload as String, isIncoming = isIncoming)
                else -> if (payload == "text" || payload == "image") makeMessage(from, chat, date, payload = type, type = payload as String, isIncoming = isIncoming)
                else TextMessage("$lastId", from, chat, date = date, text = payload as String, isIncoming = isIncoming)
            }
        }
    }
}