# MultiStyleLoadingView #
<p align="center">
    <img src="https://img.shields.io/badge/version-1.0.4-brightgreen.svg">
    <img src="https://img.shields.io/badge/build%20-passing-brightgreen.svg">
    <img src="https://img.shields.io/badge/license-Apache--2.0-blue.svg">
    <img src="https://img.shields.io/badge/Author-HappyFatDoge-orange.svg">
</p>

一个应用于Android的多样式进度加载view库；

## Download ##

```
implementation 'com.fatdoge.loadingview:MultiStyleLoadingView:1.0.4'
```

##  可供使用的进度加载view ##

1. 闹钟进度加载view——AlarmClockLoadingView

   可设置属性（同时提供属性设置接口）：

   * 秒针转动一圈时间——timeForSpeed
   * 进度 / 进度字体大小 / 颜色——progress / progressTextSize / progressTextColor
   * 进度加载描述 / 字体大小 / 颜色——progressDescription / desTextSize / desTextColor
   
   <p align="center">
    <img src="https://github.com/FatWood/MultiStyleLoadingView/blob/master/art/alarm_clock.gif">
   </p>
   
   
   
2. 肥柴进度加载view——HappyFatDogeLoadingView

   可设置属性（同时提供属性设置接口）：

   * 是否具有进度及加载描述——withProgressDisplay
   * 进度 / 进度字体大小 / 颜色——progress / progressTextSize / progressTextColor
   * 进度加载描述 / 字体大小 / 颜色——progressDescription / desTextSize / desTextColor

   具有进度的肥柴加载view与无进度的肥柴加载view

   <p align="center">
       <img src="https://github.com/FatWood/MultiStyleLoadingView/blob/master/art/fat_doge_with_loading.gif">
       <img src="https://github.com/FatWood/MultiStyleLoadingView/blob/master/art/fat_doge_without_loading.gif">
</p>
   

   
3. 波浪球进度加载view——WaveBallLoadingView

   可设置属性（同时提供属性设置接口）：

   * 圆环外半径、圆环宽度——ringOuterRadius / ringWidth
   
   * 圆环起始颜色、终止颜色——startColor / endColor
   
   * 椭圆环颜色——ovalRingColor

   * 进度 / 进度字体大小 / 颜色——progress / progressTextSize / progressTextColor
   
   * 进度加载描述 / 字体大小 / 颜色——progressDescription / desTextSize / desTextColor
   
     
   
   提供接口：
   
   * 设置椭圆环旋转动画结束接口——endOvalRingAnim
   
   <p align="center">
       <img src="https://github.com/HappyFatDoge/MultiStyleLoadingView/blob/dev_fatdoge/art/wave_ball.gif">
   </p>

## 版本信息 ##

[版本信息](https://github.com/FatWood/MultiStyleLoadingView/blob/master/VERSION.md)

