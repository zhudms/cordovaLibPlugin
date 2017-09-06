//
//  JsonHelper.m
//  AnotherDemo
//
//  Created by 融易乐 on 2017/6/9.
//  Copyright © 2017年 融易乐. All rights reserved.
//

#import "JsonHelper.h"

@implementation JsonHelper

+(NSString *)dicToJsonString:(NSDictionary *) dic{
    return [dic JSONString];
}
@end
