# RxJava2.0


## Observable

Observable  
.create()//创建  
.subscribeOn().observeOn()//线程调度  
.subscribe()//订阅  
  
1、Hot:创建后立即发送事件  
2、Cold:有订阅者才开始发送事件

- [x] Observable

- [x] Flowable

- Single

	- [ ] 一种只发射一个值立马结束订阅的Observable

- [ ] …

## Observer

- Observer

- Subscriber

- SingleObserver

- …

## 1.0差异

- 背压处理

	- Flowable

- 架构调整

	- reactive-streams

		- https://github.com/reactive-streams/reactive-streams-jvm

- [http://blog.csdn.net/qq_35064774/article/details/53045298](http://blog.csdn.net/qq_35064774/article/details/53045298)

- [https://news.realm.io/cn/news/gotocph-jake-wharton-exploring-rxjava2-android/](https://news.realm.io/cn/news/gotocph-jake-wharton-exploring-rxjava2-android/)

## Operators

- Creating
    that emits a particular item:Just  
    that was returned from a function called at subscribe-time:Start  
    that was returned from an Action, Callable, Runnable, or something of that sort, called at   
    subscribe-time:From  
    after a specified delay:Timer  
    that pulls its emissions from a particular Array, Iterable, or something like that:From  
    by retrieving it from a Future:Start  
    that obtains its sequence from a Future:From  
    that emits a sequence of items repeatedly:Repeat  
    from scratch, with custom logic:Create  
    for each observer that subscribes:Defer  
    that emits a sequence of integers:Range  
    at particular intervals of time:Interval  
    after a specified delay:Timer  
    that completes without emitting items:Empty  
    that does nothing at all:Never

	- [x] Create

	- Defer

		- [x] 不创建Observable，直到有订阅者订阅，并为每个订阅者创建一个新的观察事件

	- Empty/Never/Throw

		- [x] 创建具有非常精确和有限行为的观察事件 —>主要用于测试用例

	- Interval

		- [x] 创建一个以特定时间间隔发送整数序列的Observable

	- Just

		- [x] 将对象或一组对象转换为该对象或那些对象的Observable

	- From

		- [x] 将一些其他对象或数据结构转换为Observable

	- Start

		- [ ] 创建一个发出一个函数事件并以该函数的返回值作为消息发出的Observable

	- Range

		- [x] 创建一个可发出一系列顺序整数的Observable

	- Timer

		- [x] 创建一个在指定延迟后发出一个事件的Observable

	- Repeat

		- [ ] 创建一个可以重复发出特定项目或序列的Observable

- Transforming
  I want to emit the items from an Observable after transforming them  
    one at a time with a function:Map  
    by emitting all of the items emitted by corresponding Observables:FlatMap  
    one Observable at a time, in the order they are emitted:ConcatMap  
    based on all of the items that preceded them:Scan  
    by attaching a timestamp to them:Timestamp  
    into an indicator of the amount of time that lapsed before the emission of the item:TimeInterval

	- 分组

		- 缓存

			- Window

				- [x] 在一定时间间隔内缓存结果，并将结果集合封装成Observable

			- Buffer

				- [x] 以一定间隔收集可观察事件到捆绑包中，并发布这些捆绑包，而不是一次发送一个事件

		- GroupBy

			- [x] 将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列，哪个数据项由哪一个Observable发射是由一个函数判定的，这个函数给每一项指定一个key，key相同的数据会被同一个Observable发射。

	- 对象转换

		- Map

			- [x] 对Observable发射的数据都应用一个函数，然后再发射最后的结果集。最后map方法返回一个新的Observable

		- FlatMap

			- [x] 对Observable发射的数据都应用(apply)一个函数，这个函数返回一个Observable，然后合并这些Observables，并且发送合并的结果(Observable)

		- concatMap

			- [ ] 与flatMap相似，但是数据是有序的

		- switchMap

			- [ ] 场景：输入框搜索，丢弃上一次未完成的请求，使用新的请求返回结果

	- 累加

		- Scan

			- [x] 通过遍历原Observable产生的结果，一次对每一个结果项按照指定规则进行运算，计算后的结果作为下一个迭代项参数，每一次迭代项都会把计算结果输出给订阅者

- Error Handing
  I want an Observable to recover gracefully  
    from a timeout by switching to a backup Observable:Timeout  
    from an upstream error notification:Catch  
    by attempting to resubscribe to the upstream Observable:Retry

	- Catch
	  从onError中恢复

		- error

			- [x] onErrorResumeNext

			- [x] onErrorReturn

			- [x] onErrorReturnItem

		- exception

			- [x] onExceptionResumeNext

	- [x] Retry

- Filtering
  I want to reemit only certain items from an Observable  
    by filtering out those that do not match some predicate:Filter  
    that is, only the first item:First  
    that is, only the first items:Take  
    that is, only the last item:Last  
    that is, only item n:ElementAt  
    that is, only those items after the first items  
    that is, after the first n items:Skip  
    that is, until one of those items matches a predicate:SkipWhile  
    that is, after an initial period of time:Skip  
    that is, after a second Observable emits an item:SkipUntil  
    that is, those items except the last items  
    that is, except the last n items:SkipLast  
    that is, until one of those items matches a predicate:TakeWhile  
    that is, except items emitted during a period of time before the source completes:SkipLast  
    that is, except items emitted after a second Observable emits an item:TakeUntil  
    by sampling the Observable periodically:Sample  
    by only emitting items that are not followed by other items within some duration:Debounce  
    by suppressing items that are duplicates of already-emitted items:Distinct  
    if they immediately follow the item they are duplicates of:DistinctUntilChanged  
    by delaying my subscription to it for some time after it begins emitting items:DelaySubscription

	- Debounce

		- [x] 该操作会把在一个时间段(传入间隔时间)挨在一起的几个事件看成一个单独事件，这几个事件里只有最后一个会被触发

	- Distinct

		- [x] 去除重复数据

	- 发射指定数据

		- First

			- [x] 只发射第一个数据

		- ElementAt

			- [x] 只发射指定索引(index)的数据

		- Last

			- [x] 只发射最后一个数据，可以指定默认值（发射数据为空）

	- Filter

		- [x] 指定过滤函数，通过的数据被发射

	- IgnoreElements

		- [x] 不发射任何数据，但是监听数据发射终止通知

	- Sample

		- [x] 以一定时间间隔采样，发射该采样区域最新的一个数据

	- Skip

		- [x] 跳过指定数量的数据（从前往后数）

	- SkipLast

		- [x] 跳过指定数量的数据(从后往前数)

	- Take

		- [x] 发射指定数量的数据（从前往后数）

	- TakeLast

		- [x] 发射指定数量的数据（从后往前数）

- [ ] Backpressure

- Combining
  I want to create an Observable by combining other Observables    
    and emitting all of the items from all of the Observables in whatever order they are received:Merge  
    and emitting all of the items from all of the Observables, one Observable at a time:Concat  
    by combining the items from two or more Observables sequentially to come up with new items to emit  
    whenever each of the Observables has emitted a new item:Zip  
    whenever any of the Observables has emitted a new item:CombineLatest  
    whenever an item is emitted by one Observable in a window defined by an item emitted by another:Join  
    by means of Pattern and Plan intermediaries:And/Then/When  
    and emitting the items from only the most-recently emitted of those Observables:Switch

	- And/Then/When

		- [ ] rxjava-joins扩展包

	- CombineLatest

		- [x] 每个数据源发射的最新数据组合在一起

		- [ ] combineLatestDelayError

	- Join

		- [ ] 根据指定函数逻辑（数据窗口期[生命周期]）合并多个数据源

	- StartWith

		- [ ] 数据源发射前追加一个数据源或数据

	- Merge

		- [x] 合并多个数据源，不同数据源的数据可能会交错(concat:不会交错)

		- [ ] mergeArray

		- [ ] mergeDelayError

		- [ ] mergeArrayDelayError

	- Switch

		- switchOnNext

			- [x] 把一组Observable转换成一个Observable，对于这组Observable所产生的结果，如果在同一个时间内存在两个或多个Observable提交的结果，只取最后一个Observable的结果

		- [ ] switchOnNextDelayError

	- Zip
	  使用zip操作符等待多个网络请求完成  
	  :[http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0325/4080.html](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0325/4080.html)

		- [x] 根据指定函数合并(按索引对应数据)多个数据源

		- [ ] zipArray

		- [ ] zipIterable

- Utility

	- Delay

		- delay

			- [x] 订阅延迟发射

		- delaySubscription

			- [x] 延迟订阅

	- Do

		- [ ] 注册一个动作来处理各种可观察的生命周期事件

		- [x] 1、doOnLifecycle

		- [x] 2、doOnSubscribe

		- 3、loop

			- [x] 1、doOnEach

			- [x] 2、doOnNext

			- [x] 3、doAfterNext

		- [x] 4、doOnTerminate

		- [x] 5、doOnComplete

		- [x] 6、doFinally

		- [x] 7、doAfterTerminate

		- [ ] …

	- Materialize/Dematerialize

		- [ ] Notification objects wrap and unwrap

	- [ ] Serialize

	- 连用

		- [ ] Subscribe

		- [ ] ObserveOn

		- [ ] SubscribeOn

	- TimeInterval

		- [x] 发射的不再是原始数据，而是原始数据发射的时间间隔

	- Timeout

		- [x] 超时机制，可以指定超时后的逻辑

	- Timestamp

		- [x] 发射的数据不再是原始数据，而是把数据发射的时间打包一起发射出去

	- Using
	  I want to create a resource that has the same lifespan as the Observable:Using

		- [x] 创建一个只在它的生命周期内存在的资源，当Observable终止时这个资源会被自动释放

- Conditional and Boolean

	- All

		- [x] 根据一个函数对原Observable发射的所有数据进行判断，最终返回的结果就是这个判断结果。这个函数使用发射的数据作为参数，内部判断所有的数据是否满足我们定义好的判断条件，如果全部满足则返回true，否则就返回false

	- Amb

		- [x] 传递一个数据源给amb，它只发射其中首先发射数据或通知（onError、onComplete）的那个Observable的所有数据，而其他所有的Observable的发射物将被丢弃

	- Contains

		- [x] 判断数据源发射的数据是否包含某一个数据，如果包含返回true，如果数据源已经结束却还没有发射这个数据则返回false

	- DefaultIfEmpty

		- [x] 简单的精确地发射原始Observable的值，如果原始Observable没有发射任何数据正常终止（onComplete），DefaultIfEmpty返回的Observable就发射一个你提供的默认值

	- SequenceEqual

		- [x] 判定两个Observable是否发射相同的数据序列，传递两个Observable，它会比较两个Observable的发射物，如果两个序列是相同的（相同的数据，相同的顺序，相同的终止状态），它就会发射true，否则发射false

	- SkipUntil

		- [x] 订阅原始的Observable，但是直到第二个Observable发射了一个数据那一刻，才开始从这一刻原始数据发射的数据开始发射，原始数据中之前发射的全部丢弃。

	- SkipWhile

		- [x] 订阅原始Observable，但是忽略它的发射物，直到你指定的某个条件变为false的那一刻，它开始发射原始Observable。

	- TakeUntil

		- [x] 与skipUntil操作符相反，订阅并开始发射原始Observable，它还监视提供的第二个Observable。如果第二个Observable发射了一项数据或者发射了一个终止通知（onError、onComplete）TakeUntil返回的Observable会停止发射原始Observable并终止。

	- TakeWhile

		- [x] 订阅原始的Observable，并开始发射，直到你指定的某个条件变为false的那一刻，它停止发射原始的Observable，并且终止自己的Observable。

- Mathematical and Aggregate
  I want to evaluate the entire sequence of items emitted by an Observable  
    and emit a single boolean indicating if all of the items pass some test:All  
    and emit a single boolean indicating if the Observable emitted any item (that passes some test):Contains  
    and emit a single boolean indicating if the Observable emitted no items:IsEmpty  
    and emit a single boolean indicating if the sequence is identical to one emitted by a second Observable:SequenceEqualand   
    emit the average of all of their values:Averageand   
    emit the sum of all of their values:Sum  
    and emit a number indicating how many items were in the sequence:Count  
    and emit the item with the maximum value:Max  
    and emit the item with the minimum value:Min  
    by applying an aggregation function to each item in turn and emitting the result:Scan

	- [ ] Average

	- Concat

		- [ ] 将多个Observable结合成一个Observable并发射数据，并且严格按照先后顺序发射数据，前一个Observable的数据没有发射完成，是不能发射后面Observable的数据的。

	- Count

		- [x] 将一个Observable转换为发射单个值的Observable，这个值表示原始Observable发射的数据的数量。

	- [ ] Max

	- [ ] Min

	- Reduce

		- [x] 应用一个函数接收Observable发射的数据，函数的计算结果作为下次计算的参数，输出最后的结果。

	- [ ] Sum

- Converting

	- [ ] To

- Connectable
  connect方法被调用，被订阅事件才开始发送  
    
  I want an Observable that does not start emitting items to subscribers until asked:Publish  
    and then only emits the last item in its sequence:PublishLast  
    and then emits the complete sequence, even to those who subscribe after the sequence has begun:Replay  
    but I want it to go away once all of its subscribers unsubscribe:RefCount  
    and then I want to ask it to start:Connect

	- Connect

		- [ ] 使ConnectableObservale开始发射数据

	- Observable—>ConnectableObservable

		- [ ] Publish

		- Replay

			- [ ] 缓存订阅之前发射的数据，可指定缓存参数

	- ConnectableObservable—>Observable

		- [ ] RefCount

## Subject

- [ ] 一种在ReactiveX中实现的桥接或代理，即作为Observer又作为Observable

- [ ] AsyncSubject

- [ ] BehaviorSubject

- [ ] PublishSubject

- [ ] ReplaySubject

- [ ] …

## Processor

新增：与Subject相同，支持背压

- [ ] AsyncProcessor

- [ ] BehaviorProcessor

- [ ] …

## Scheduler

- SubscribeOn

	- 工作线程

	- 只需调用一次

	- computation()

		- 计算工作：事件循环和回调处理，默认线程数等于处理器数

	- io()

		- I/O绑定的工作：默认是个根据需求增长的缓存线程池

	- form(executor)

		- 使用指定的Executor作为调度线程

	- immediate()—>2.x移除

		- 立即在当前线程执行

	- newThread()

		- 为每个工作创建一个新线程

	- trampoline()

		- 队列线程

- ObserveOn

	- 接收通知线程

	- 可多次调用改变线程

- [http://blog.csdn.net/jungle_pig/article/details/54705818](http://blog.csdn.net/jungle_pig/article/details/54705818)

## Test

- RxJavaPlugins

	- [x] [http://www.infoq.com/cn/articles/Testing-RxJava2](http://www.infoq.com/cn/articles/Testing-RxJava2)

