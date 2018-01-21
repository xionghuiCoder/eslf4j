eslf4j
=========

### eslf4j（expand slf4j）是基于slf4j的一个扩展工具包，主要用于解决线上日志的bug定位问题。
对于大并发的网站，定位bug一直是个头疼的问题，为了定位问题而记录大量的debug或info日志，很可能会影响性能，而且大量的日志对于日志分析也很不友好，还会占用大量磁盘空间；所以很多人的选择是启用error级别的日志，但是在这种情况下，如果线上出现了bug，往往只有一条错误日志，这对于定位问题几乎没有任何帮助，因为导致这个error往往是由于上下文的一个或几个错误数据或操作导致的，而上下文的info或debug日志并没有输出。

eslf4j能够控制打印上下文日志，比如可以定义线上日志的级别为error以输出最少的日志，并且配置eslf4j使得一旦出现error，能同时打印出上下文的info或者debug信息。这样既能保证性能，也方便定义问题。

### eslf4j配置
<ol>
<li>count=100：count为上下文的日志数量，必须配置（否则可能会造成内存泄露，这跟线程连接池和ThreadLocal的实现有关，就不详细介绍了）；比如这里配置为100，表示一旦打印一条日志，会同时打印出该条日志前的100条日志。</li>
<li>buffersize=10m：buffersize为eslf4j缓存的日志所占用的空间，必须配置，当缓存达到配置的大小时就会使用memorymanager来释放内存；这里配置的buffersize为10m，表示缓存最多会占用10m内存，另外支持单位b（bit），c（char），k（kb），g（gb）。</li>
<li>minthreshold=debug：minthreshold为日志级别的最低闸值，必须配置，支持all,trace,debug,info,warn,error,fatal和off；比如这里配置为debug，只有级别大于或等于debug的日志才会被缓存并打印出来。</li>
<li>filter=xxx：filter是日志过滤器，是一个扩展点，可以配置也可以不配置，当然也可以配置多个；filter需要实现com.jd.o2o.core.filter.Filter接口，而且需要一个无参构造器；filter会在日志被缓存前调用，可以自处理日志。</li>
<li>memorymanager=xxx：memorymanager用于管理内存，也是一个扩展点，可以配置也可以不配置，memorymanager需要实现com.jd.o2o.core.memory.IMemoryManager接口，而且需要一个无参构造器，如果不配置会默认使用com.jd.o2o.core.memory.impl.DefaultMemoryManagerImpl来管理内存，memorymanager会在日志缓存达到buffersize时调用来释放内存。</li>
</ol>

### eslf4j demo
线上日志级别为error，eslf4j配置的count为100，minthreshold为debug；假设活动线程数为1000，error错误率为1/1000；当一个线程出现error时，会打印出该线程下出现该error的前100条debug级别或debug级别以上的日志；而其它999个线程日志都不会输出。

### note
<ol>
<li>JDK支持1.6或1.6+</li>
<li>在修改源码前，请导入googlestyle-java.xml以保证一致的代码格式</li>
</ol>
