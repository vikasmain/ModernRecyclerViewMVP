package com.cogoport.utils

import android.support.v7.app.AppCompatActivity

class KotlinLambdasHigherOrder {
    //kotlin lambda
//    var ans: Type={parameters list->
//          codeBody
//        return last statement
//    }
    var ans = { param1: Int, param2: Int ->
        1
    }

// use of it= we use it when we have a one parameter in functions.

    //HigherOrder function kotlin
//    syntax:=
//    fun higher(functionname:(Argument DataType)->return Datatype)
    fun higher(lambda: (Double) -> Boolean): Boolean {
        return lambda(4.32)
    }

    fun higherSec(name: String, lambda: (String) -> Unit) {
        lambda(name)
    }

    fun higherThird(name: String, address: String, lambda: (String, String) -> Unit) {
        lambda(name, address)
    }

    //how to call higher order functions arguments
    //1.Instead of assigning the lambda to a variable, we can pass the literal directly into the method call:
    val higherOrderans = higher { it ->
        it == 4.32
    }

    //2.using lambda object variable
    val lambdavar = { arg: Double ->
        arg == 4.32
    }

    val higherOrderAnsSec = higher(lambdavar)

    //3.Lambda literals outside the brackets
    val higherOrderAnsThird = higher { arg -> arg.isNaN() }

    val higherOrderAnsFour = higher { arg ->
        print(arg)
        true//last statement is always return statement kotlin
    }
    //Most famous use of lambdas arguments
    val higherOrderAnsFifth = higherSec("Thor", { print("hi this is thor name $it") })
    //now we can use that function like a variable in kotlin

    //if lambda is the last argument we can also remove it from the brackets
    val higherOrderAnsSixth = higherSec("Thor") { print("hi this is thor name $it") }

    //lets take an example of list
    val items = listOf(1, 2, 3, 4, 5)

    fun itemsHigherOrderFunction() {
        items.fold(0) { acc: Int, i: Int ->
            print(acc)
            val result = acc + i
            result
        }
    }

    val joinedToString = items.fold("Elements", { acc, i -> acc + " " + i })

    val justCheckLambda = higherThird("Thor", "USA",
            { str1: String, str2: String ->
                print(str1 + str2)
            })

    fun callthis() {
        println(ans)
    }

    //kotlin closure we can modify the outside variable value in a function but in java we can't do
    // this because java also supports closure but it didn't support closures
    var result = 0

}
