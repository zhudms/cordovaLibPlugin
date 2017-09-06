//
//  LocalDef.h
//  MyApp
//
//  Created by 融易乐 on 2017/5/24.
//
//

#import <UIKit/UIKit.h>

@interface LocalDef : NSObject

typedef NS_OPTIONS(NSInteger, OCRIDErrorCode) {
    kGetScanTypeFailed = 9,
    kGetCardMessageFailed = 10,
    kScanIsCancel = 11,
};

typedef NS_OPTIONS(NSInteger, AliveAction) {
    kScanBilnk = 0,
    kScanNod = 1,
    kScanMouth = 2,
    kScanShak = 3,
};

typedef NS_OPTIONS(NSInteger, AliveError) {
    kAliveNoPic = 5,
    kAliveUserCancel = 6,//和LocalData中定义的kAliveCancel对应消息回掉的不同阶段--从sdk传递到自定义层／从自定义层传递到前端
    kAliveFail = 7,
    kAliveError =8,
};


UIKIT_EXTERN NSInteger const scanFront;
UIKIT_EXTERN NSInteger const scanBack;
UIKIT_EXTERN NSInteger const scanBoth;
UIKIT_EXTERN NSInteger const aliveNumb;

UIKIT_EXTERN NSInteger const kAliveKind;


UIKIT_EXTERN NSString* const kAliveInitError;
UIKIT_EXTERN NSString* const kAliveCancel;

UIKIT_EXTERN NSInteger const resultSuccess;
UIKIT_EXTERN NSInteger const resultFail;

@end
