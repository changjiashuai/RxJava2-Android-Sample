<img src=https://raw.githubusercontent.com/changjiashuai/RxJava2-Android-Sample/master/assets/rxjava2.png >
<img src=https://raw.githubusercontent.com/changjiashuai/RxJava2-Android-Sample/master/assets/RxJava2.0.png >

# Learning RxJava 2 for Android by example

[![Mindorks](https://img.shields.io/badge/mindorks-opensource-blue.svg)](https://mindorks.com/open-source-projects)
[![Mindorks Community](https://img.shields.io/badge/join-community-blue.svg)](https://mindorks.com/join-community)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RxJava2%20Android%20Samples-blue.svg?style=flat)](https://android-arsenal.com/details/3/4314)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/LICENSE)

## How to use RxJava 2 in Android Application

### This project is for : 
* who is migrating to RxJava 2 
* or just started with RxJava.

### Just Build the project and start learning RxJava by examples.

RxJava 2.0 has been completely rewritten from scratch on top of the Reactive-Streams specification. The specification itself has evolved out of RxJava 1.x and provides a common baseline for reactive systems and libraries.

Because Reactive-Streams has a different architecture, it mandates changes to some well known RxJava types.



### Using RxJava 2.0 Library in your application

Add this in your build.gradle
```groovy
compile 'io.reactivex.rxjava2:rxjava:2.0.6'
```
If you are using RxAndroid also, then add the following
```groovy
compile 'io.reactivex.rxjava2:rxandroid:2.0.1'
```

# RxJava 2 Examples present in this sample project

* RxJava 2.0 Example using `CompositeDisposable` as `CompositeSubscription` and `Subscription` have
been removed.

* RxJava 2 Example using `Flowable`.

* RxJava 2 Example using `SingleObserver`, `CompletableObserver`.

* RxJava 2 Example using RxJava2 operators such as `map, zip, take, reduce, flatMap, filter, buffer, skip, merge, concat, replay`, and much more:

* RxJava 2 Android Samples using `Function` as `Func1` has been removed.

* RxJava 2 Android Samples  using `BiFunction` as `Func2` has been removed.

* And many others android examples

# Quick Look on few changes done in RxJava2 over RxJava1

RxJava1 -> RxJava2

* `onCompleted` -> `onComplete` - without the trailing d
* `Func1` -> `Function`
* `Func2` -> `BiFunction`
* `CompositeSubscription` -> `CompositeDisposable`
* `limit` operator has been removed - Use `take` in RxJava2
* and much more.

# Operators :
* `Map` -> transform the items emitted by an Observable by applying a function to each item
* `Zip` -> combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function
* `Filter` -> emit only those items from an Observable that pass a predicate test
* `FlatMap` -> transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
* `Take` -> emit only the first n items emitted by an Observable
* `Reduce` -> apply a function to each item emitted by an Observable, sequentially, and emit the final value
* `Skip` -> suppress the first n items emitted by an Observable
* `Buffer` -> periodically gather items emitted by an Observable into bundles and emit these bundles rather than emitting the items one at a time
* `Concat` -> emit the emissions from two or more Observables without interleaving them
* `Replay` -> ensure that all observers see the same sequence of emitted items, even if they subscribe after the Observable has begun emitting items
* `Merge` -> combine multiple Observables into one by merging their emissions


# Highlights of the examples :

* [DisposableExampleActivity]() - Using `CompositeDisposable`
* [FlowableExampleActivity]() - Using `Flowable` and `reduce` operator
* [SingleObserverExampleActivity]() - Using `SingleObserver`
* [CompletableObserverActivity]() - Using `CompletableObserver`
* [MapExampleActivity]() - Using `map` Operator
* [ZipExampleActivity]() - Using `zip` Operator
* [BufferExampleActivity]() - Using `buffer` Operator
* [TakeExampleActivity]() - Using `take` Operator
* [ReduceExampleActivity]() - Using `reduce` Operator
* [FilterExampleActivity]() - Using `filter` Operator
* [SkipExampleActivity]() - Using `skip` Operator
* [ReplayExampleActivity]() - Using `replay` Operator
* [ConcatExampleActivity]() - Using `concat` Operator
* [MergeExampleActivity]() - Using `merge` Operator
* [DeferExampleActivity]() - Using `defer` Observable
* [IntervalExampleActivity]() - Using `Interval`
* [RxBusActivity]() - RxBus, RxJava2Bus, EventBus, RxEventBus
* [PaginationActivity]() - Pagination for loadMore in RecyclerView
* [ComposeOperatorExampleActivity]() - Compose operator for reusable

### TODO 

* Many examples are to be added 

### Contact - Let's become friend
- [Twitter](https://twitter.com/FlyRj1031cjs)
- [Github](https://github.com/changjiashuai)


### Contributing to RxJava 2 Android Samples
Just make pull request. You are in!

 
=======
