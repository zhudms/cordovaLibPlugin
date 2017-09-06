//
//  VCHelper.h
//  MyPaintDemo
//
//  Created by 融易乐 on 2017/6/16.
//  Copyright © 2017年 融易乐. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface VCHelper : UIViewController

-(void)showPage:(UIViewController *)nextVC byWindow:(UIWindow *)window;
-(void)hidePage:(UIViewController *)nextVC byWindow:(UIWindow *)window;


@end
