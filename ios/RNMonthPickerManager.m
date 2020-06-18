//
//  RNMonthPickerManager.m
//  RNMonthPicker
//
//  Created by Gustavo Paris on 22/04/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNMonthPickerManager.h"
#import "RNMonthPicker.h"

@implementation RNMonthPickerManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
    return [RNMonthPicker new];
}

RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(value, NSDate)
RCT_EXPORT_VIEW_PROPERTY(minimumDate, NSDate)
RCT_EXPORT_VIEW_PROPERTY(maximumDate, NSDate)
RCT_EXPORT_VIEW_PROPERTY(enableAutoDarkMode, BOOL)

@end
