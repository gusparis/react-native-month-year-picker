//
//  RNMonthPicker.h
//  RNMonthPicker
//
//  Created by Gustavo Paris on 22/04/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>

#import <React/UIView+React.h>

@interface RNMonthPicker : UIPickerView

@property (nonatomic, copy) RCTBubblingEventBlock onChange;
@property (nonatomic, assign) NSDate* value;
@property (nonatomic, assign) NSDate* minimumDate;
@property (nonatomic, assign) NSDate* maximumDate;
@property (nonatomic, assign) BOOL enableAutoDarkMode;

@end
