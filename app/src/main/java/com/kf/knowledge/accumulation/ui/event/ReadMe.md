
# Android中点击事件传递详解

## GroupView 中方法的说明
     * public boolean onInterceptTouchEvent(MotionEvent ev)

        默认调用 super.onInterceptTouchEvent(ev)，该方法放回 false。不拦截TouchEvent
     * public boolean onTouchEvent(MotionEvent event)

        默认调用 super.onTouchEvent(ev)，该方法放回 false。


## 根据MyLayout , 运行实例说明,从 onInterceptTouchEvent 中进行说明
    * Tag1 点击MyButton 运行路径,正常执行路径
        1. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        2. D/com.kf.knowledge.accumulation.ui.event.MyButton: MyButton onTouchEvent
        3. D/com.kf.knowledge.accumulation.ui.event.MyButton: Action down
        4. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        5. D/com.kf.knowledge.accumulation.ui.event.MyButton: MyButton onTouchEvent
        6. D/com.kf.knowledge.accumulation.ui.event.MyButton: Action up
    * Tag2 点击MyLayout区域，正常执行路径
        1. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        2. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent() false
        3. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action down
    * Tag3 点击MyButton ，修改 MyLayout中 onInterceptTouchEvent() 方法，返回 **true**，运行路径
        1. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        2. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent() false
        3. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action down
> 由上面运行实例，可以得知。MyLayout 装它的容器，的onInterceptTouchEvent 返回是true。
    当 MyLayout 的方法 onInterceptTouchEvent 返回是默认情况false，则不拦截该事件的传递。
    当 MyLayout 的方法 onInterceptTouchEvent 返回是true，MyButton进行拦截，在OnTouch 的 Down中消费了。

## 根据MyLayout , 运行实例说明，从onTouchEvent() 中进行说明
    * MyButton 中 onTouchEvent() super.onTouchEvent(event) ，默认返回：true
    * MyLayout 中 onTouchEvent() super.onTouchEvent(event) ，默认返回：false
    * MyLayout 中 onInterceptTouchEvent() super.onInterceptTouchEvent() ，默认返回：false
    * Tag4 设定 MyLayout 中 onInterceptTouchEvent 返回 **true** ， onTouchEvent() 返回 **true**
        1. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        2. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent()
        3. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action down
        4. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent()
        5. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action up
        > 由这个实例可以得到，这个点击事件完全是给MyLayout消费了。
    * Tag5 MyLayout使用默认，MyButton onTouchEvent 返回**false**
        1. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        2. D/com.kf.knowledge.accumulation.ui.event.MyButton: MyButton onTouchEvent true
        3. D/com.kf.knowledge.accumulation.ui.event.MyButton: Action down
        4. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent()
        5. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action down
        > 可以看到：容器没有拦截 -> 传递给控件 -> 控件使用了 onTouchEvent事件 -> 控件返回false，也就是不在消费该事件流了 ->
            传递给容器 -> 容器同样返回false，也不消费其他事件流 -> 传递给上一层的容器 -> 都不消费 -> End
    * Tag6 MyLayout 消费事件。MyLayout onTouchEvent() 返回 **true** ， MyButton onTouchEvent 返回**false**
        1. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onInterceptTouchEvent()
        2. D/com.kf.knowledge.accumulation.ui.event.MyButton: MyButton onTouchEvent true
        3. D/com.kf.knowledge.accumulation.ui.event.MyButton: Action down
        4. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent()
        5. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action down
        6. D/com.kf.knowledge.accumulation.ui.event.MyLayout: MyLayout onTouchEvent()
        7. D/com.kf.knowledge.accumulation.ui.event.MyLayout: Action up

## Android中点击传递事件的设计实现
    * 点击MyLayout区域，这个流程可以看出。在API中实现的，所有都没有拦截，默认都不消费方法。
    * 一个正常的点击事件包括：ACTION_DOWN -> ACTION_MOVE -> ACTION_UP
    * **首先监测父类是否使用onInterceptTouchEvent拦截** ，如果拦截后，所有事件都在当前的容器中消费事件。
    * 全面理解事件传递的执行与消费
        1. 容器拦截后，使用该事件，需要在onTouchEvent()中返回true，使用其所有事件。Tag4 运行事件中表现出来。
        2. 容器拦截后，不使用该事件，需要在onTouchEvent()中返回false，那么只会消费 ACTION_DOWN事件。Tag3 运行事件中表现出来。
        3. 容器不拦截，控件使用所有事件。Tag1 运行事件中表现出来。
        4. 容器不拦截，控件不使用事件。Tag5 运行事件中表现出来。
        5. 容器不拦截，容器控件消费事件。控件不消费事件。Tag6 运行事件中表现出来。



