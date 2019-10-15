> 背景:   
    刚开始学习策略模式的时候, 不知道context有什么作用, 觉得不用context, 直接在客户端用IStrategy也可以了.
然后搜了一些资料

> 参考博客:  
  https://softwareengineering.stackexchange.com/questions/187378/context-class-in-strategy-pattern  
  https://stackoverflow.com/questions/12545305/why-we-need-context-class-in-strategy-design-pattern
  https://www.javagists.com/strategy-design-pattern

##我的理解

策略模式中有四个对象: 客户端(client), 策略上下文(context), 策略接口(IStrategy),以及多个具体策略实现(StrategyImpl)

####作用1:  让客户端和具体策略完全解耦  
  > 在没有context的情况下, client直接持有策略接口, 如果具体策略改变, 需要更改client的代码, 但是如果让context持有IStrategy
  那就不需要更改client

####作用2: 策略"上下文"
  > 我觉得client在整个处理流程中只要知道他调了一个策略就行了, 不必要知道具体策略是什么, 甚至策略的选择都让策略上下文去维护  
  
  > 比如看到很多策略模式的实现, 都是在context中维护了strategy list, 然后在里面通过参数去选取对应的策略, 这块"选取对应策略"放在context中比放在client中更合适
  所谓: "封装变化", "对修改关闭,对扩展开放"
  
  
   
   