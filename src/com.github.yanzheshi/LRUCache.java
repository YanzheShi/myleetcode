package com.github.yanzheshi;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode 39 LRU cache
 * 试用map来保证put，get的效率
 * 通过链表来维护LRU， LRU
 * 对于链表只需要吧当前使用的元素放到第一位即可，没必要用优先队列，简化了调整堆的过程
 */
public class LRUCache {

    private Map<Integer, CacheNode> map;

    /**
     * 双端队列
     */
    private LinkedCacheNode list;

    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        list = new LinkedCacheNode();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            CacheNode cacheNode = map.get(key);
            list.moveNodeToFirst(cacheNode);
            return cacheNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            CacheNode cacheNode = map.get(key);
            cacheNode.value = value;
            list.moveNodeToFirst(cacheNode);
            return;
        }
        map.put(key, list.addFirst(key, value));
        if (list.size > capacity) {
            CacheNode cacheNode = list.removeLast();
            if (cacheNode != null) {
                map.remove(cacheNode.key);
            }
        }
    }

    /**
     * 双向链表节点
     * 保存值 以及前后节点方便做删除
     */
    class CacheNode {
        int key;
        int value;
        CacheNode prev;
        CacheNode next;

        public CacheNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class LinkedCacheNode {
        int size;

        CacheNode head;

        CacheNode tail;

        public LinkedCacheNode() {
            size = 0;
            head = null;
            tail = null;
        }

        public CacheNode addFirst(int key, int value) {
            CacheNode node = new CacheNode(key, value);
            node.next = head;
            if (head != null) {
                head.prev = node;
            } else {
                // 初始节点
                tail = node;
            }
            head = node;
            size++;
            return node;
        }

        public void moveNodeToFirst(CacheNode node) {
            if (head == node) {
                return;
            }

            //摘除节点
            node.prev.next = node.next;
            if (node != tail) {
                node.next.prev = node.prev;
            } else {
                // node 为最后一个节点
                tail = node.prev;
            }

            // 插入到头
            node.next = head;
            head.prev = node;
            head = node;
        }

        public CacheNode removeLast() {
            CacheNode tem = tail;
            if (tail != null) {
                tail = tail.prev;
                if (tail != null) {
                    tail.next = null;
                }
                size--;
            }
            return tem;
        }

        public int size() {
            return size;
        }
    }

}
