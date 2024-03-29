package ru.skillbranch.devintensive.models

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
           Question.NAME->Question.NAME.question
           Question.PROFESSION->Question.PROFESSION.question
           Question.MATERIAL->Question.MATERIAL.question
           Question.BDAY->Question.BDAY.question
           Question.SERIAL->Question.SERIAL.question
           Question.IDLE->Question.IDLE.question

    }

    fun listenAnswer(answer:String) : Pair<String, Triple<Int, Int, Int>>{

        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично - это правильный ответ\n${question.question}" to status.color
        }else{
            status = status.nextStatus()
            "Это не правильный ответ\n${question.question}" to status.color
        }

    }
    enum class Status(val color : Triple<Int, Int, Int>){
        NORMAL(Triple(225,225,225)),
        WARNING(Triple(225,120,0)),
        DANGER(Triple(225,60,60)),
        CRITICAL(Triple(225,225,0));

        fun nextStatus():Status{
            return if (this.ordinal<values().lastIndex) {
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }
    enum class Question(val question: String, val answers:List<String>){
        NAME("Как меня зовут?", listOf("бендер","bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою провессию?", listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл","дерево","metal","iron","wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом всё,вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = PROFESSION
        };

        abstract fun nextQuestion():Question
    }
}