//
//  ImageHelper.h
//  MyApp
//
//  Created by 融易乐 on 2017/6/19.
//
//

#import <Foundation/Foundation.h>

@interface ImageHelper : NSObject
- (NSData *)compressOriginalImage:(UIImage *)image toMaxDataSizeKBytes:(CGFloat)size;

-(NSString*)imageToBase64:(UIImage *)img;
- (UIImage *)fixOrientation:(UIImage *)aImage;
@end
