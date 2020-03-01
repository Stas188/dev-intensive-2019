package ru.skillbranch.devintensive.models

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion():String = when(question){
        Question.NAME -> Question.NAME.question
        Question.PROFFESION -> Question.PROFFESION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question

    }
    fun listenAnswer(answer:String) :Pair<String,Triple<Int,Int,Int>>{
        return if (question==Question.IDLE)
        {
            "${question.question}" to status.color
        }
        else
         if(question.answer.contains(answer)){
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        }else{
           if (status == Status.CRITICAL) {
               question = Question.NAME
               status = Status.NORMAL
               "Это неправильный ответ. Давай все по новой\n" +
                       "Как меня зовут?" to status.color
           } else {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }
    //fun hasNext()

    enum class Status(val color: Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus():Status{
            return if(this.ordinal <  values().lastIndex){
                values()[this.ordinal+1]
            } else{
                values()[0]
            }
        }
    }
    enum class Question(val question: String,val answer:List<String>){
        NAME("Как меня зовут?", listOf("bender","бендер")) {
            override fun nextQuestion(): Question = PROFFESION
        },
        PROFFESION("Назови мою профессию?", listOf("bender","сгибальщик")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал","дерево","wood","iron","metal")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("1","2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion():Question
    }
}