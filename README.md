#cordova.ryl.cordovalib使用注意事项

自己写的cordova 插件的基类,定义了大部分通信使用的工具类, pulgin的基类,文件处理类

## ios
+ JSONKit.m 文件要在TARGETS-Build Phases 中设置-fno-objc-arc属性（因为这个文件太老了，不支持arc
）

