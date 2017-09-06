//
//  UIImage+STBlur.h
//
//  Created by sluin on 15/12/24.
//  Copyright © 2015年 SunLin. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIImage (STBlur)

- (UIImage *)blurredImageWithRadius:(CGFloat)radius iterations:(NSUInteger)iterations tintColor:(UIColor *)tintColor;

@end
