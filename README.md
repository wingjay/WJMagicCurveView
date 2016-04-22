# WJMagicCurveView
By setting several parameters simply, You'll get a fancy Magic Curve immediately.

[中文介绍，来自Android-CJJ同学](https://github.com/wingjay/WJMagicCurveView/blob/master/README_CN.md)

# Let's see what it is

<table>
  <thead>
    <tr>
      <th>Order</th>
      <th>Demo</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1</td>
      <td><img src="https://camo.githubusercontent.com/d9eae67c303601789f8ce14e694047618d0ab9e7/687474703a2f2f7777312e73696e61696d672e636e2f6d773639302f3765663031666361677731663335686c333065763067323061743061356e39362e676966" width="500"></td>
    </tr>
    <tr>
      <td>2</td>
      <td><img src="https://camo.githubusercontent.com/f66e0343a7adc7309b86ed3ccfd5e2c72f144fa7/687474703a2f2f7777312e73696e61696d672e636e2f6d773639302f3765663031666361677731663335686c323230777967323061743061356835652e676966" width="500"></td>
    </tr>
    <tr>
      <td>3</td>
      <td><img src="https://camo.githubusercontent.com/8029c08f06a2402d78862c9d1d3245ed478a2df5/687474703a2f2f7777332e73696e61696d672e636e2f6d773639302f3765663031666361677731663335686c336f6c383367323061743061353131312e676966" width="500"></td>
    </tr>
    <tr>
      <td>4</td>
      <td><img src="https://camo.githubusercontent.com/46338cd26305a61f13aa7ddd1f55d3f51705abb0/687474703a2f2f7777332e73696e61696d672e636e2f6d773639302f3765663031666361677731663335686c356f36686667323061743061356168622e676966" width="500"></td>
    </tr>
    <tr>
      <td>5</td>
      <td><img src="https://camo.githubusercontent.com/80b98f800388ded00106762b3d58390087765f87/687474703a2f2f7777332e73696e61696d672e636e2f6d773639302f3765663031666361677731663335686b706c69346b6732306174306135716b632e676966" width="500"></td>
    </tr>
  </tbody>
</table>

# How it works
By setting eight parameters, WJMagicCurveView will draw a beautiful curve based on a math function.

First, we'll create two points and make them rotate in specified speed and specified radius revolving a same center;
Second, whenever they reach a point during rotating, we'll draw a line connecting these two points;
Thirds, continue rotate and draw lines, Bingo! You're creating a new beautiful curve!

# How to create your own curve
We provide eight base parameters for normal users and more for developers from code level. Here are explaintion for these parameters:

1. radiusAX、radiusAY:  A point is the outer rotating point, these two are the radius of A;
2. radiusBX、radiusBY:  B point is the inner rotating point;
3. speedOuterPoint、speedInnerPoint: the rotating speed for A & B;
4. loopTotalCount: the loop count for these two rotating points;
5. durationSeconds: the durationg speed for rotating.

# Playable apk
[Download it from here](https://github.com/wingjay/WJMagicCurveView/raw/master/demo.apk) and make a try!

# How to use it
```java
WJMagicCurveView wjMagicCurveView = (WJMagicCurveView) findViewById(R.id.wj_magic_curve_view);
// set parameters. of course it's not necessary to set every parameters because they all have default value
wjMagicCurveView.setRadius(radiusAX, radiusAY, radiusBX, radiusBY)
                .setDurationSec(durationSeconds)
                .setLoopTotalCount(loopTotalCount)
                .setSpeed(speedOuterPoint, speedInnerPoint)
                .startDraw();
```
```java
// stop Draw
wjMagicCurveView.stopDraw();
```
```java
// destory and recycle bitmap
wjMagicCurveView.destory();
```
```java
// Customize your own curve here
I create a enum called WJMagicCurveViewParameters, 
You can create your own WJMagicCurveViewParameters with eight parameters,
for empty fields, use -1 as default value.
```

# Related resource
[有趣的曲线在Android上的实现](https://wingjay.com/2016/01/25/%E6%9C%89%E8%B6%A3%E7%9A%84%E6%9B%B2%E7%BA%BF%E5%9C%A8Android%E4%B8%8A%E7%9A%84%E5%AE%9E%E7%8E%B0/)

[Processing: 简单法则的魅力](http://mp.weixin.qq.com/s?__biz=MzA4NTc5MDU5OQ==&mid=411441608&idx=1&sn=5e846a882f58a7ba1b5312bdbeaafccf&scene=23&srcid=0120GiYhMXjmNDoN9MFQj7f5#rd)

###Reach me - wingjay
![](http://tp3.sinaimg.cn/1625892654/180/5739331233/1)

You can get information about me and reach me in my github page: https://github.com/wingjay

Blog: https://wingjay.com

Weibo: http://weibo.com/u/1625892654

Feel free to give me advices by <mailto:yinjiesh@126.com>

Thanks!

![First](https://wingjay.com/img/%E6%9C%89%E8%B6%A3%E7%9A%84%E6%9B%B2%E7%BA%BF%E5%9C%A8Android%E4%B8%8A%E7%9A%84%E5%AE%9E%E7%8E%B0/ring.gif)
