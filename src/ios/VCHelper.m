//
//  VCHelper.m
//  MyPaintDemo
//
//  Created by 融易乐 on 2017/6/16.
//  Copyright © 2017年 融易乐. All rights reserved.
//

#import "VCHelper.h"

@implementation VCHelper



-(void)showPage:(UIViewController *)nextVC byWindow:(UIWindow *)window{
    if (nextVC!=nil) {
        [window.rootViewController presentViewController:nextVC animated:YES completion:nil];
    }else{
        NSLog(@"showpage error");
    }
}


-(void)hidePage:(UIViewController *)nextVC byWindow:(UIWindow *)window{
    if (nextVC!=nil) {
        [window.rootViewController dismissViewControllerAnimated:true completion:nil];
//        [window.rootViewController presentViewController:scanVC animated:YES completion:nil];
    }else{
        NSLog(@"hidepage error");
    }
}




@end
