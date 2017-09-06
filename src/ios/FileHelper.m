//
//  FileHelper.m
//  AnotherDemo
//
//  Created by 融易乐 on 2017/6/8.
//  Copyright © 2017年 融易乐. All rights reserved.
//

#import "FileHelper.h"
#import "UIImage+STBlur.h"

@implementation FileHelper

NSString * const aliveFileName=@"alive.jpg";
NSString * const signFileName=@"sign.jpg";
NSString * const IDFileName=@"ID.jpg";


static NSString* parentFilePath=nil;//这里应该写单例 （IOS的粒度？应该差不太多）（这里其实影响不大）



-(NSString* )savePic:(UIImage*)image withName:(NSString*)name ByType:(cordovaParentType)parentType ifClear:(BOOL) ifClear{
    NSString* path=[self getFilePath:name withParentType:parentType clearParent:ifClear];
    if([self saveImage:image inPath:path]){
        return path;
    }else{
        return nil;
    }
}


-(NSString *)getDocumentPath{
    
    NSArray*paths=NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,NSUserDomainMask,YES);
    
    NSString*path=[paths objectAtIndex:0];
    
    //    NSLog(@"path:%@",path);
    
    return path;
    
    
}

//hui zidong chuangjian fu mulu
-(NSString *)getFilePath:(NSString *)fileName withParentType:(cordovaParentType)parentType clearParent:(BOOL) ifClear{
    
    NSString * documentPath=[self getDocumentPath];
    NSString * parentFloder=nil;
    
    switch (parentType) {
        case PATH_SCAN:
            parentFloder=@"scan";
            break;
        case PATH_ALIVE:
            parentFloder=@"alive";
            break;
        case PATH_SIGN:
            parentFloder=@"sign";
            break;
        case PATH_ID:
            parentFloder=@"id";
            break;
        default:
            break;
    }
    
    if (documentPath==nil||parentFloder==nil) {
        return nil;
    }
    
    NSString* parentPath=[NSString stringWithFormat:@"%@/%@",documentPath,parentFloder];
    
    [self createFloder:parentPath];
    
    if (ifClear) {
        [self clearPath:parentPath];
    }
    
    NSString* tagPath=[parentPath stringByAppendingPathComponent:fileName];//跟上面的是等价的
    
    return tagPath;
}

-(void)createFloder:(NSString*) path{
    
    NSFileManager *fileManager = [NSFileManager defaultManager];
    
    BOOL isDir = NO;
    
    // fileExistsAtPath 判断一个文件或目录是否有效，isDirectory判断是否一个目录
    BOOL existed = [fileManager fileExistsAtPath:path isDirectory:&isDir];
    
    if ( !(isDir == YES && existed == YES) ) {
        
        // 在 tmp 目录下创建一个 temp 目录
        BOOL res= [fileManager createDirectoryAtPath:path withIntermediateDirectories:YES attributes:nil error:nil];
        NSLog(@"create=%ld",res);
        
    }else{
        
    }
    
    
}

//实现还没写
-(void) clearPath:(NSString *)path{
    
    
}

//需要倒入UIImage+STBlur.h"(自己实现的UIImage的分类，没找到UIImage怎么导入)
-(BOOL)saveImage:(UIImage *)image inPath:(NSString *) path{
    
    return [UIImagePNGRepresentation(image) writeToFile:path atomically:YES];
}

//只能到秒级
+(NSString*)getCurrentTimeString{
    NSDate *currentSecondData = [NSDate date];
    
    NSString* current= [NSString stringWithFormat:@"%ld", (long)[currentSecondData timeIntervalSince1970]];
    
    return current;
}


@end
