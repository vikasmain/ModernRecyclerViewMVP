package com.cogoport.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cogoport.R
import com.cogoport.model.Test
import java.io.File

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val firstPerson = Person("vikas", 23)
        //let{}

        //let() is a scoping function: use it whenever you want to define a variable for a specific
        // scope of your code but not beyond. It’s extremely useful to keep your code nicely
        // self-contained so that you don’t have variables “leaking out”: being accessible past the
        // point where they should be.

        /** ex:-
         *  DbConnection.getConnection().let { connection ->}
        now connection is no longer visible here
         */

        //let() can also be used as an alternative to testing against null:
        val map = HashMap<String, Test>()
        val let = map["hello"]?.let {
            // This whole block will not be executed if "map["hello"]" is null.
            // Additionally, "it" has now been cast to a "Test" (no question mark)
            //we can return here anything in let
            //because let{} takes this as an argument and returns this
            //if we write 42 here it will be of return type int
        }
        //let{} example
        test()

        //apply{}

        val apply = map["hello"]?.apply {
            //as we can see here receiver is 'this' which is map itself not class
            //so apply() defines an extension function on all types. When you invoke it, it calls
            // the closure passed in parameter and then returns the receiver object that closure
            // ran on.
            map.put("hello", Test())
            // so it will replace value Test() at hello
            //but apply will always takes this as an argument and returns this only
            //so it will return Test() object only because it is calling on Test itself-map["hello"]
        }

        //apply most special example
        val applyfile = File("dir").apply {
            mkdirs()
        }
        //so it will convert string into file creates the file by calling mkdir() and returns the
        //file
        //above code is equivalent to below one
        //File makeDir(String path) {
        //  File result = new File(path);
        //  result.mkdirs();
        //  return result;
        //}

        //run{}
        //it takes this as a receiver (argument) and returns its result
        val run = map["hello"].run {
            //Use run() function if you need to compute some value or want to limit the scope of
            // multiple local variables
            //It is used with lambdas that do not return values, but rather just create some
            // side-effects
        }
        //like let run also returns last statement but run doesn't support it keyword
        //chaining run and let
        var p: String? = null
        p?.let { println("p is $p") } ?: run {
            println("p was null. Setting default value to: ")
            p = "Kotlin"
        }

        println(p)
        //Prints
        //p was null. Setting default value to:
        //Kotlin

        //let us take an awesome example of Kotlin run{}
        //let we want to change the age of first person then
        val runanother = firstPerson.run {
            //this is Person because firstperson is a person type
            age += 1
            "Age is now$age"//so now it will return "Age is now$age"
        }

        //with{}
        run {

        }
        let {

        }
        apply {

        }
        //but with{} will error because with is not an extension function
        with(map) {

        }
        // with() is convenient when you find yourself having to call multiple different methods on
        // the same object. Instead of repeating the variable containing this object on each line,
        // you can instead “factor it out” with a with call
//        val withres=with(Window()) {
//            setWidth(100)
//            setHeight(200)
//            setBackground(RED)
//        }


        //with{} vs apply{} block what is the difference
        val person = CheckPerson()
        person.age = 1
        person.name = "vikas"
        //instead of doing above things we can use with{} or apply{}
        with(person) {
            age = 10
            name = "vijay"
        }
        //but with{} can't use methods of CheckPerson() class like this so use apply
        person.apply {
            age = 10
            name = "vijay"//but in case of apply {} it returns receiver so it will return person
        }.callthis()//so as it is returns receiver so we can call all methods here and with don't
        // return receiver
        //and another interesting thing about with{} and apply{} is that if we want to initialize
        // only some fields of constructor then we can do with these apply{} and with{} block

        //also{} usages
        //sometimes we want to modify our receiver name then we can use also{} here it can be modify
        // into anything
        person.also { personDouble ->
            personDouble.age += 1
        }.callthis()
    }

    fun test() {
        val str: String = "..."
        val result = str.let {
            print(this) // Receiver
            print(it) // Argument
            42 // return value - last statement is always return statement from let
        }
//        this is the instance of KotlinActivity class (this@KotlinActivity) because test is a method
// of KotlinActivity. In case test would be a free function (not attached to any class), we would have a
// compiler error it is the String "...", on which we’ve called let
//        result is the number 42, that we’ve returned from the block
    }
}

data class Person(var name: String, var age: Int)

class CheckPerson {
    var age: Int = 0
    var name: String = ""
    fun callthis() {
        print("Hello from CheckPerson")
    }
}