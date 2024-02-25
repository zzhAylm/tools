package com.zzh.boot3.guava;

import cn.hutool.core.io.resource.ClassPathResource;
import com.google.common.base.*;
import com.google.common.cache.*;
import com.google.common.collect.*;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.util.concurrent.RateLimiter;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/2/6 11:23
 */
@Slf4j
//@SpringBootTest
public class GuavaTest {


    @Test
    public void guavaTest() {
        String s1 = Strings.emptyToNull("");
        System.out.println(s1); // null
        String s2 = Strings.nullToEmpty(null);
        System.out.println(s2); //
    }

    @Test
    public void guavaTest2() {
        String s3 = Strings.commonPrefix("mrbird", "mr.right");
        System.out.println(s3); // mr
        String s4 = Strings.commonSuffix("mrbird", "third");
        System.out.println(s4); // ird

        String s5 = Strings.repeat("mrbird", 3);
        System.out.println(s5); // mrbirdmrbirdmrbird

    }

    @Test
    public void guavaTest3() throws InterruptedException {
        System.out.println("程序开始处理");
        Stopwatch stopwatch = Stopwatch.createStarted();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("程序处理结束，耗时" + stopwatch.stop()); // 程序处理结束，耗时2.002 s


    }

    @Test
    public void guavaTest4() {
//        List<String> list=null;
//        Preconditions.checkNotNull(list,"list is null");
//        Preconditions.checkNotNull(list, "list长度必须为%s", 2);

        String value = "hello";
        Preconditions.checkArgument("world".equals(value), "参数内容必须为world");


        List<String> list = ImmutableList.of();
        Preconditions.checkElementIndex(10, list.size(), "下标越界，不存在第10个元素");


    }

    @Test
    public void guavaIOTest() throws IOException {

        ClassPathResource classPathResource = new ClassPathResource("static/test.txt");
        String value = "添加一些内容";
        CharSink charSink = Files.asCharSink(classPathResource.getFile(), Charsets.UTF_8, FileWriteMode.APPEND); // 追加
        charSink.write(value);
        String read = Files.asCharSource(classPathResource.getFile(), Charsets.UTF_8).read();
        System.out.println(read);
    }

    // 1秒钟产生0.5张令牌
    private static RateLimiter limiter = RateLimiter.create(0.5);

    @Test
    public void guavaRateLimiterTest() throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5);
        IntStream.range(0, 5).forEach(i -> service.submit(() -> testLimiter()));

        service.shutdown();
        Thread.sleep(1000 * 10);


        RateLimiter limiter = RateLimiter.create(1);
        System.out.println(limiter.acquire(4));
        System.out.println(limiter.acquire(3));
        System.out.println(limiter.acquire(2));
        System.out.println(limiter.acquire(1));
    }

    private static void testLimiter() {
        System.out.println(Thread.currentThread() + " waiting " + limiter.acquire());
    }


    @Test
    public void guavaCacheTest() throws InterruptedException {
        Cache<String, Object> cache = CacheBuilder.newBuilder().build();
        cache.put("key", "value");
        log.info("key={}", cache.getIfPresent("key"));

        LoadingCache<String, Object> loadingCache = CacheBuilder.newBuilder().build(
                new CacheLoader<>() {
                    @Override
                    public Object load(String key) throws Exception {
                        return String.format("hello %s", key);
                    }
                });

        //方法getUnchecked作用为：当值不存在时，会通过CacheLoader计算出值，然后存到缓存中。
        System.out.println(loadingCache.getUnchecked("key"));
        System.out.println(loadingCache.getIfPresent("key"));


    }

    @Test
    public void timeCacheTest() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(2, TimeUnit.SECONDS).build();

        cache.put("k1", "v1");
        cache.put("k2", "v2");

        cache.getIfPresent("k1");
        TimeUnit.SECONDS.sleep(1);
        cache.getIfPresent("k1");
        TimeUnit.SECONDS.sleep(1);

        System.out.println(cache.getIfPresent("k1")); // v1
        System.out.println(cache.getIfPresent("k2")); // null
        System.out.println(cache.size()); // 1
        System.out.println(cache.asMap()); // {k1=v1}
    }


    @Test
    public void softWeakKeyCacheTest() {
        Cache<String, String> cache = CacheBuilder.newBuilder().weakKeys().softValues().build();
        Cache<String, String> refreshCache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.SECONDS).build();
    }


    @Test
    public void cacheCollectionTest(){
        // 不可变集合
        ImmutableSet<String> FRUITS = ImmutableSet.of("apple", "watermelon", "cherry", "mango");
//        FRUITS.remove("apple");
        FRUITS.stream().toList().forEach(System.out::println);
    }

    @Test
    public void rangeSetTest(){

        RangeSet<Integer> rangeSet= TreeRangeSet.create();
        //[0,10]  存储区间的集合
        rangeSet.add(Range.closed(0, 10));
        rangeSet.add(Range.closed(11, 20));
        rangeSet.asRanges().forEach(System.out::println);

        RangeSet<Integer> integerRangeSet = rangeSet.subRangeSet(Range.closed(1, 15));
        System.out.println(rangeSet.contains(20));
        System.out.println(rangeSet.rangeContaining(20));
        log.info("range ={}", integerRangeSet);
    }


    @Test
    public void tableTest(){

        Table<String, String, Integer> hashBasedTable = HashBasedTable.create();

        hashBasedTable.put("row1", "column1", 10);
        hashBasedTable.put("row2", "column1", 10);
        hashBasedTable.put("row2", "column1", 10);
        hashBasedTable.put("row4", "column1", 10);
        hashBasedTable.put("row5", "column1", 10);
        hashBasedTable.put("row6", "column1", 10);
        hashBasedTable.put("row7", "column1", 10);
        hashBasedTable.put("row1", "column2", 10);
        hashBasedTable.put("row1", "column3", 10);
        hashBasedTable.put("row1", "column4", 10);
        hashBasedTable.put("row1", "column5", 10);
        hashBasedTable.put("row1", "column6", 10);
        hashBasedTable.values().forEach(System.out::println);
    }

    @Test
    public void multiMapTest(){

        String key = "hello";
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put(key, "world");
        multimap.put(key, "java");

        System.out.println(multimap); // {hello=[world, java]}
        System.out.println(multimap.get(key)); // [world, java]
    }
    @Test
    public void listsTest(){
        // 反转
        ArrayList<String> list = Lists.newArrayList("a", "b", "c");
        List<String> reverse = Lists.reverse(list);
        System.out.println(reverse); // [c, b, a]
        List<Character> characters = Lists.charactersOf("mrbird");
        System.out.println(characters); // [c, b, a]
    }

    @Test
    public void setsTest(){
        // 获取两个set的差集
        Set<Character> first = ImmutableSet.of('a', 'b', 'c');
        Set<Character> second = ImmutableSet.of('c', 'd', 'e');
        Set<Character> difference = Sets.symmetricDifference(first, second);

        System.out.println(difference); // [a, b, d, e]

    }

    @Test
    public void joinerTest(){
        HashMap<String, Integer> map = Maps.newHashMap();
        map.put("mrbird", 18);
        map.put("scott", 28);

        String join = Joiner.on(",").withKeyValueSeparator("~").join(map);
        System.out.println(join); // mrbird~18,scott~28

        List<String> list = Lists.newArrayList("a", null, "b", "c", "d");
        String result = Joiner.on(",").skipNulls().join(list);

        System.out.println(result); // a,b,c,d


        String value = "a,b,c,d      ";
        List<String> list1 = Splitter.on(",").trimResults().splitToList(value);

        System.out.println(list1); // [a, b, c, d]

        String mapValue = "mrbird=18,scott=28";
        Map<String, String> maps = Splitter.on(",").withKeyValueSeparator("=").split(mapValue);

        System.out.println(maps); // {mrbird=18, scott=28}

    }



    //Iterables

    @Test
    public void filterTest(){

        List<String> list = Lists.newArrayList("java", "javascript", "c#", "golang");
        Iterable<String> result = Iterables.filter(list, Predicates.containsPattern("a"));
        Collection<String> result2 = Collections2.filter(list,
                Predicates.or(
                        Predicates.containsPattern("a"),
                        Predicates.containsPattern("#")
                ));
        System.out.println(result); // [java, javascript, golang]
        System.out.println(result2); // [java, javascript, golang]

//        Iterables.
//
    }

    public class GuavaCacheUtil {

        private static Logger logger = LoggerFactory.getLogger(GuavaCacheUtil.class);

        private static Cache<String, String> cache;

        static {
            RemovalListener<String, String> listener
                    = n -> logger.info("监听到删除事件，key={}，value={}", n.getKey(), n.getValue());
            cache = CacheBuilder.newBuilder()
                    .removalListener(listener).build();
        }

        /**
         * 添加缓存
         *
         * @param key   键
         * @param value 值
         */
        public void put(String key, String value) {
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                cache.put(key, value);
            }
        }

        /**
         * 批量添加缓存
         *
         * @param map key,value集合
         */
        public void putAll(Map<String, String> map) {
            cache.putAll(map);
        }

        /**
         * 删除缓存
         *
         * @param key 键
         */
        public void remove(String key) {
            if (StringUtils.isNotBlank(key)) {
                cache.invalidate(key);
            }
        }

        /**
         * 批量删除缓存
         *
         * @param keys key集合
         */
        public void remove(List<String> keys) {
            if (!CollectionUtils.isEmpty(keys)) {
                cache.invalidateAll(keys);
            }
        }

        /**
         * 清空缓存
         */
        public void removeAll() {
            cache.invalidateAll();
        }

        /**
         * 获取缓存
         *
         * @param key 键
         * @return 值
         */
        public String get(String key) {
            return StringUtils.isNotBlank(key) ? cache.getIfPresent(key) : null;
        }

        /**
         * 批量获取缓存
         *
         * @param keys 键集合
         * @return 值集合
         */
        public ImmutableMap<String, String> get(List<String> keys) {
            return !CollectionUtils.isEmpty(keys) ? cache.getAllPresent(keys) : null;
        }
    }

}
