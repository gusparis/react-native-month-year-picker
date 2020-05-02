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

NSCalendar *gregorian;

NSMutableArray *months;
NSMutableArray *years;
NSInteger selectedMonthRow;
NSInteger selectedYearRow;

- (instancetype)initWithFrame:(CGRect)frame
{
    if ((self = [super initWithFrame:frame])) {
        self.delegate = self;
        NSDate *date = [NSDate date];
        gregorian = [NSCalendar currentCalendar];
        NSDateComponents *dateComponents = [gregorian components:(NSCalendarUnitMonth|NSCalendarUnitYear) fromDate:date];
        NSInteger currentMonth = [dateComponents month];
        NSInteger currentYear = [dateComponents year];
        
        years = [NSMutableArray array];
        for(NSInteger i = currentYear - DEFAULT_SIZE; i <= currentYear + DEFAULT_SIZE; i ++) {
            [years addObject: [NSNumber numberWithLong:i]];
        }
        
        months = [NSMutableArray array];
        NSDateFormatter *df = [[NSDateFormatter alloc] init];
        for(NSInteger i = 0; i < 12; i ++){
            [months addObject:[[df monthSymbols] objectAtIndex:(i)]];
        }
        
        selectedMonthRow = (DEFAULT_SIZE / 2) - 7 + currentMonth;
        selectedYearRow = DEFAULT_SIZE;
        [self selectRow:selectedMonthRow inComponent:0 animated:YES];
        [self selectRow:selectedYearRow inComponent:1 animated:YES];
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
        case 1:
            return [NSString stringWithFormat:@"%@", years[row]];
        default:
            return nil;
    }
}

- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component
{
    NSDateComponents *minComponents = _minimumDate ? [gregorian components:NSCalendarUnitMonth | NSCalendarUnitYear fromDate:_minimumDate] : nil;
    NSDateComponents *maxComponents = _maximumDate ? [gregorian components:NSCalendarUnitMonth | NSCalendarUnitYear fromDate:_maximumDate] : nil;
    
    switch (component) {
        case 0: {
            NSInteger tmpMonth = (row % 12) + 1;
            NSInteger tmpYear = [years[selectedYearRow] longValue];
            if ((minComponents && tmpYear == [minComponents year] && tmpMonth < [minComponents month]) || (maxComponents && tmpYear == [maxComponents year] && tmpMonth > [maxComponents month])) {
                [self selectRow:selectedMonthRow inComponent:0 animated:true];
                return;
            }
            selectedMonthRow = row;
            break;td
        }
        case 1: {
            NSInteger tmpYear = [years[row] longValue];
            if ((minComponents && tmpYear < [minComponents year]) || (maxComponents && tmpYear > [maxComponents year])) {
                [self selectRow:selectedYearRow inComponent:1 animated:true];
                return;
            }
            selectedYearRow = row;
            break;
        }
        default:
            return;
    }
    
    if (_onChange) {
        _onChange(@{
            @"newDate": [NSString stringWithFormat: @"%@-%@", [NSString stringWithFormat: @"%ld", (selectedMonthRow % 12) + 1], [NSString stringWithFormat: @"%@", years[selectedYearRow]]]
        });
    }
}

@end
