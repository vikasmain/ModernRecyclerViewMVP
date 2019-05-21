package com.rao.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import org.reactivestreams.Subscriber


class TestApi {

    /*
     * Observable.create() -> We will need to call the
     * respective methods of the emitter such as onNext()
     * & onComplete() or onError()
     *
     * */
    internal var observable: Observable<Any> = Observable.just(1, 2, 3)
            .map { x -> x * 2 }
    internal var filter: Observable<String> = Observable.just("Apple", "Anna",
            "Banana", "Mango", "Grapes")
            .filter { x -> x.toUpperCase().startsWith("A") }
    //now x working like a string here so all methods apply on string also applying on x also here.

    //means everything u write up will work for below

    //range operators
    internal var range: Observable<Int> = Observable.range(1, 10)
            .take(5)//it will take first 5 items only op-1 2 3 4 5

    internal var range2: Observable<Int> = Observable.range(1, 10)
            .take(5)//it will take first 5 items only op-1 2 3 4 5
            .takeLast(2)//op - 4 5

    internal var range3: Observable<Int> = Observable.range(0, 10)
            .takeWhile { item -> item <= 9 }//it will print op-1 2 3 4 5 7 8 9

    //Merge operations
    internal var m1: Observable<String> = Observable.just("hello ", "world")

    internal var m2: Observable<String> = Observable.just("hi", "everyone")
    internal var merger: Observable<String> = Observable.merge(m1, m2).filter { it ->
        it.toString()
                .startsWith("h")
    }//hello  hi
    internal var compositeObserver: Observable<String> = Observable.fromArray(
            "First", "Second", "Third", "Fourth",
            "Five", "Six", "Seven", "Eight", "Nine", "Ten")

    //create() needs Emitter for its implementation
    internal var ghost: Observable<String> = Observable.create { emitter ->
        emitter.onNext("Hello From Create() ")

    }
}
