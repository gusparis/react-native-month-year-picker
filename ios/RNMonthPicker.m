//
//  RNMonthPicker.m
//  RNMonthPicker
//
//  Created by Gustavo Paris on 22/04/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import "RNMonthPicker.h"

#import <React/RCTConvert.h>
#import <React/RCTUtils.h>

#define DEFAULT_SIZE 204

@interface RNMonthPicker() <UIPickerViewDataSource, UIPickerViewDelegate>
@end

@implementation RNMonthPicker

NSMutableArray *months;
NSMutableArray *years;
NSString *selectedMonth;
NSString *selectedYear;

- (instancetype)initWithFrame:(CGRect)frame
{
    if ((self = [super initWithFrame:frame])) {
        self.delegate = self;
        NSDate *date = [NSDate date];
        NSCalendar *gregorian = [NSCalendar currentCalendar];
        NSDateComponents *dateComponents = [gregorian components:(NSCalendarUnitMonth|NSCalendarUnitYear) fromDate:date];
        NSInteger currentMonth = [dateComponents month];
        NSInteger currentYear = [dateComponents year];
        
        years = [NSMutableArray array];
        for(NSInteger i = currentYear - DEFAULT_SIZE; i <= currentYear + DEFAULT_SIZE; i ++) {
            [years addObject:@(i)];
        }
        
        months = [NSMutableArray array];
        NSDateFormatter *df = [[NSDateFormatter alloc] init];
        for(NSInteger i = 0; i < 12; i ++){
            [months addObject:[[df monthSymbols] objectAtIndex:(i)]];
        }
        
        [self selectRow:(DEFAULT_SIZE / 2) - 7 + currentMonth inComponent:0 animated:YES];
        [self selectRow:DEFAULT_SIZE inComponent:1 animated:YES];

        selectedMonth = [NSString stringWithFormat:@"%ld", ([self selectedRowInComponent:0] % 12) + 1];
        selectedYear = years[[self selectedRowInComponent:1]];
    }
    return self;
}

RCT_NOT_IMPLEMENTED(- (instancetype)initWithCoder:(NSCoder *)aDecoder)

#pragma mark - UIPickerViewDataSource protocol
// number of columns
- (NSInteger)numberOfComponentsInPickerView:(nonnull UIPickerView *)pickerView {
    return 2;
}

// number of rows
- (NSInteger)pickerView:(nonnull UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    switch (component) {
        case 0:
            return DEFAULT_SIZE;
        case 1:
            return [years count];
            break;
        default:
            return 0;
    }
}

#pragma mark - UIPickerViewDelegate methods
// row titles
- (NSString *)pickerView:(nonnull UIPickerView *) pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    switch (component) {
        case 0:
            return [NSString stringWithFormat:@"%@", months[row % 12]];
            break;
        case 1:
            return [NSString stringWithFormat:@"%@", years[row]];
            break;
        default:
            return nil;
    }
}

// TODO set row style
//- (UIView *)pickerView:(UIPickerView *)pickerView
//            viewForRow:(NSInteger)row
//          forComponent:(NSInteger)component
//           reusingView:(UILabel *)label {
//
//    label.textColor = [RCTConvert UIColor:_items[row][@"textColor"]] ?: _color;
//    label.text = [self pickerView:pickerView titleForRow:row forComponent:component];
//    return label;
//}

- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component
{
    switch (component) {
        case 0:
            selectedMonth = [NSString stringWithFormat: @"%ld", (row % 12) + 1];
            break;
        case 1:
            selectedYear = [NSString stringWithFormat: @"%@", years[row]];
            break;
        default:
            return;
    }

    if (_onChange) {
        _onChange(@{
            @"newDate": [NSString stringWithFormat: @"%@-%@", selectedMonth, selectedYear]
        });
    }
}

@end
