//
//  FileHelper.h
//  AnotherDemo
//
//  Created by 融易乐 on 2017/6/8.
//  Copyright © 2017年 融易乐. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <AVFoundation/AVFoundation.h>
#import <UIKit/UIKit.h>

@interface FileHelper : NSObject

UIKIT_EXTERN  NSString* const aliveFileName;
UIKIT_EXTERN  NSString* const signFileName;
UIKIT_EXTERN  NSString* const IDFileName;

typedef NS_ENUM(NSUInteger,cordovaParentType){
    
    PATH_SCAN=0,
    PATH_ALIVE,
    PATH_SIGN,
    PATH_ID
    
};

-(NSString*)savePic:(UIImage*)image withName:(NSString*)name ByType:(cordovaParentType)parentType  ifClear:(BOOL) ifClear;

-(NSString *)getDocumentPath;

-(NSString *)getFilePath:(NSString *)fileName withParentType:(cordovaParentType)parentType clearParent:(BOOL) ifClear;

-(BOOL) clearPath:(NSString*)path;

-(BOOL)saveImage:(UIImage *)image inPath:(NSString *) path;

+(NSString*)getCurrentTimeString;

@end
