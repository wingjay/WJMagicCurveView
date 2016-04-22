# 中文版介绍来自[android-cjj](https://github.com/android-cjj)同学

最近看了群里一个小伙伴[wingjay](https://github.com/wingjay)的博文[《有趣的曲线在Android上的实现》](https://wingjay.com/2016/01/25/%E6%9C%89%E8%B6%A3%E7%9A%84%E6%9B%B2%E7%BA%BF%E5%9C%A8Android%E4%B8%8A%E7%9A%84%E5%AE%9E%E7%8E%B0/)，文中又看到了一篇[《简单法则的魅力》](http://mp.weixin.qq.com/s?__biz=MzA4NTc5MDU5OQ==&mid=411441608&idx=1&sn=5e846a882f58a7ba1b5312bdbeaafccf&scene=23&srcid=0120GiYhMXjmNDoN9MFQj7f5#rd),然后得到了这样一段代码：
```
float aX,aY,bX,bY,angleA,angleB,speedA,speedB,aXR,aYR,bXR,bYR;
color c;
void setup(){
  size(700,700);
  background(0);
  speedA = 0.025;
  speedB = 0.006;
  aXR = 320;
  aYR = 320;
  bXR = 320;
  bYR = 80;
}

void draw(){
  translate(width/2,height/2);
  angleA += speedA;
  angleB += speedB;
  aX = cos(angleA) * aXR;
  aY = sin(angleA) * aYR;
  bX = cos(angleB) * bXR;
  bY = sin(angleB) * bYR;
  c = color(255,50);
  stroke(c);
  line(aX, aY, bX, bY);
}
void mousePressed(){
  saveFrame(frameCount + " speedA-" + speedA + "  speedB-" + speedB + " aXR-"+aXR + " aYR-"+aYR +  " bXR-"+bXR + " bYR-"+bYR +".png");
}
```
一开始我也不信，因为我看了下代码，绘制只有这句` line(aX, aY, bX, bY);`，然后自己敲了一下，按自己的想法去自定义ArtLine了。
结果还真的挺神奇的，因此，我又改了参数，做多了几种效果，请看下图：

![](http://ww1.sinaimg.cn/mw690/7ef01fcagw1f35hl220wyg20at0a5h5e.gif)

紫之花


![](http://ww1.sinaimg.cn/mw690/7ef01fcagw1f35hl30ev0g20at0a5n96.gif)

青之草


![](http://ww3.sinaimg.cn/mw690/7ef01fcagw1f35hl3ol83g20at0a5111.gif)

黄之星


![](http://ww4.sinaimg.cn/mw690/7ef01fcagw1f35hl53hrrg20at0a54h3.gif)

蓝之海


![](http://ww3.sinaimg.cn/mw690/7ef01fcagw1f35hl5o6hfg20at0a5ahb.gif)

粉之晶


![](http://ww3.sinaimg.cn/mw690/7ef01fcagw1f35hkpli4kg20at0a5qkc.gif)

橙之橘

以上图文不符，请以代码为准！

上面的所有图形都是由同一段代码实现的，只有寥寥几行代码。其中的核心变量有 6 个。换句话说，只需修改 6 个参数就能衍生以上图形。
参数分别是：
* 点 A 的运动速率
* 点 A 在 x 轴的运动范围
* 点 A 在 y 轴的运动范围
* 点 B 的运动速率
* 点 B 在 x 轴的运动范围
* 点 B 在 y 轴的运动范围

我就开启了个动画，不断的绘画view自身，核心代码如下：

```java
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        angleA += speedA;
        angleB += speedB;
        aX = (float) (Math.cos(angleA) * aXR);
        aY = (float) (Math.sin(angleA) * aYR);
        bX = (float) (Math.cos(angleB) * bXR);
        bY = (float) (Math.sin(angleB) * bYR);

        listPos.add(aX);
        listPos.add(aY);
        listPos.add(bX);
        listPos.add(bY);

        canvas.translate(centerX, centerY);
        canvas.drawColor(Color.BLACK);
        canvas.save();
        for (int i = 0; i < listPos.size(); i++) {
            if (i % 4 == 0) {
                canvas.drawLine(listPos.get(i), listPos.get(i + 1), listPos.get(i + 2), listPos.get(i + 3), paint);
            }
        }
        canvas.restore();
    }
```

好像挺简单的，就测试的时候麻烦点！源码已上传github ,你可以在这里找到[wingjay](https://github.com/wingjay)!

这里打打个广告！最近发起了个 android sdk 源码解析——旨在帮助初学者更好的学习Android ！高手请不要略过，仓库上还有很多Class没被认领分析，有兴趣的可以一起愉快搞基哦!虽然很多人说我瞎折腾，但是，还是有些人喜欢的！
另外，关于群里一言不合就开车事件

![](http://ww3.sinaimg.cn/mw690/7ef01fcagw1f35jzd8fjwj20jf0ccgmm.jpg)

我们已经深深反省，决定深夜开车！


####关于android-cjj
如果你喜欢这个东东的话，可以关注[cjj github](https://github.com/android-cjj) ,也可以关注微博[Android_cJJ](http://weibo.com/chenjijun2011/).

