目录
- [ ] 第一部分 基础
    - [ ] 第一章 简介
    - [ ] 第二章 线程安全性
    - [ ] 第三章 对象的共享
    - [ ] 第四章 对象的组合
    - [ ] 第五章 基础构建模块
- [ ] 第二部分 结构化并发应用程序
    - [ ] 第6章 任务执行
    - [ ] 第7章 取消与关闭
    - [ ] 第8章 线程池的使用
    - [ ] 第9章 图形用户界面应用程序
- [ ] 第三部分 活跃性,性能与测试
    - [ ] 第10章 避免活跃性危险
    - [ ] 第11章 性能与可伸缩性
    - [ ] 第12章 并发程序的测试
- [ ] 第四部分 高级主题
    - [ ] 第13章 显式锁
    - [ ] 第14章 构建自定义的同步工具
    - [ ] 第15章 原子变量与非阻塞同步机制
    - [ ] 第16章 java内存模型
    
---

## 第3章 对象的共享
> 要编写正确的并发程序,关键在于: 在访问共享可变状态时进行正确的管理 

同步的两个重要特性:
1. 原子性
2. 内存可见性

###3.1 可见性
```java
public class NoVisibility {
    private static boolean ready;
    private static int number;
    private static class ReaderThread implements Runnable{
        @Override
        public void run() {
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) {
        Thread t = new Thread(new ReaderThread());
        t.start();
        ready = true;
        number = 1;
    }
}
```
上面可能的结果:
1. 可能输出1  &emsp;&emsp;大概率
2. 可能输出0 &emsp;&emsp;(重排序)
3. 可能一直循环下去???

> 对于缺乏足够同步的程序,有个简单的处理方法: 只要有数据在多个线程
之间共享,就是用正确的同步

#### 3.1.1 失效数据
> 一个线程可能得到一个已经失效的值,更糟糕的是:可能得到一个变量的最新值,而获得另一个变量的失效值

#### 3.1.2 非原子的64位操作
> "失效值" 至少是之前的某个线程设置的. 这种安全性保证也被成为最低安全性

特例:  
  > 非volatile类型的64位数值变量(double 和 long)  
  > jvm内存模型要求,变量的读操作和写操作都必须是原子性的, 但是对于非volatile的double和long,
  > 读操作和写操作可能被分解为2个32位操作, 除非用volatile申明他们,或者用锁保护起来
  
####3.1.3 加锁与可见性  

