//
//  UIImage+STBlur.m
//
//  Created by sluin on 15/12/24.
//  Copyright © 2015年 SunLin. All rights reserved.
//

#import "UIImage+STBlur.h"
#import <Accelerate/Accelerate.h>

@implementation UIImage (STBlur)

- (UIImage *)blurredImageWithRadius:(CGFloat)radius iterations:(NSUInteger)iterations tintColor:(UIColor *)tintColor
{
    if (floorf(self.size.width) * floorf(self.size.height) <= 0.0f) return self;
    
    uint32_t boxSize = (uint32_t)(radius * self.scale);
    
    if (boxSize % 2 == 0) boxSize ++;
    
    CGImageRef imageRef = self.CGImage;
    
    vImage_Buffer buffer1, buffer2;
    
    buffer1.width = buffer2.width = CGImageGetWidth(imageRef);
    
    buffer1.height = buffer2.height = CGImageGetHeight(imageRef);
    
    buffer1.rowBytes = buffer2.rowBytes = CGImageGetBytesPerRow(imageRef);
    
    size_t bytes = buffer1.rowBytes * buffer1.height;
    
    buffer1.data = malloc(bytes);
    
    buffer2.data = malloc(bytes);

    void *tempBuffer = malloc((size_t)vImageBoxConvolve_ARGB8888(&buffer1, &buffer2, NULL, 0, 0, boxSize, boxSize,
                                                                 
                                                                 NULL, kvImageEdgeExtend + kvImageGetTempBufferSize));
    
    CFDataRef dataSource = CGDataProviderCopyData(CGImageGetDataProvider(imageRef));
    
    memcpy(buffer1.data, CFDataGetBytePtr(dataSource), bytes);
    
    CFRelease(dataSource);
    
    for (NSUInteger i = 0; i < iterations; i++) {
        vImageBoxConvolve_ARGB8888(&buffer1, &buffer2, tempBuffer, 0, 0, boxSize, boxSize, NULL, kvImageEdgeExtend);
        
        void *temp = buffer1.data;
        
        buffer1.data = buffer2.data;
        
        buffer2.data = temp;
        
    }
    
    free(buffer2.data);
    
    free(tempBuffer);
    
    CGContextRef ctx = CGBitmapContextCreate(buffer1.data, buffer1.width, buffer1.height,
                                             
                                             8, buffer1.rowBytes, CGImageGetColorSpace(imageRef),
                                             
                                             CGImageGetBitmapInfo(imageRef));
    
    if (tintColor && CGColorGetAlpha(tintColor.CGColor) > 0.0f) {
        
        CGContextSetFillColorWithColor(ctx, [tintColor colorWithAlphaComponent:0.25].CGColor);
        
        CGContextSetBlendMode(ctx, kCGBlendModePlusLighter);
        
        CGContextFillRect(ctx, CGRectMake(0, 0, buffer1.width, buffer1.height));
    }
    
    imageRef = CGBitmapContextCreateImage(ctx);
    
    UIImage *image = [UIImage imageWithCGImage:imageRef scale:self.scale orientation:self.imageOrientation];
    
    CGImageRelease(imageRef);
    
    CGContextRelease(ctx);
    
    free(buffer1.data);
    
    return image;
    
}

@end
