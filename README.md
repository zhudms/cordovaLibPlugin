#cordova.ryl.cordovalib使用注意事项

## ios
+ JSONKit.m 文件要在TARGETS-Build Phases 中设置-fno-objc-arc属性（因为这个文件太老了，不支持arc
）

