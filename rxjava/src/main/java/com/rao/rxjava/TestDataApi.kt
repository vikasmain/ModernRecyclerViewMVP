package com.rao.rxjava

import io.reactivex.Observable


class TestDataApi{
    fun getData(): Observable<Desc> {
        return Observable.just(
               Desc(
                       name = "Vikas",
                       classname = "ten",
                       rollno = 1,
                       status = false
                  )
               )
    }
}

data class Desc(
        var name:String,
        var classname:String,
        var rollno:Int,
        var status:Boolean
)