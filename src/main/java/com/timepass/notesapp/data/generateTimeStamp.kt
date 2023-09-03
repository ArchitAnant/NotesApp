package com.timepass.notesapp.data

import java.time.LocalDateTime

fun generateTimeStamp():String{
    val current = LocalDateTime.now()

    val month = current.month.toString()
    val date = current.dayOfMonth
    val year = current.year
    var time = ""

    val finalMonthString = month[0]+month.substring(1).lowercase()
    if (date in 4..19){
        time = "${date}th $finalMonthString $year"
    }
    else if(date.toString().reversed()[0]== '1'){
        time = "${date}st $finalMonthString $year"
    }
    else if(date.toString().reversed()[0]=='2'){
        time = "${date}nd $finalMonthString $year"
    }
    else if(date.toString().reversed()[0]=='3'){
        time = "${date}rd $finalMonthString $year"
    }
    else{
        time = "${date}th $finalMonthString $year"
    }
    return time
}