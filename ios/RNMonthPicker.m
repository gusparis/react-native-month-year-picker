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

#define DEFAULT_YEAR_SIZE 408
#define DEFAULT_MONTH_SIZE 3000

@interface RNMonthPicker() <UIPickerViewDataSource, UIPickerViewDelegate>
@end

@implementation RNMonthPicker

NSCalendar *gregorian;
NSDateFormatter *df;
NSDateComponents *maxComponents;
NSDateComponents *minComponents;

NSMutableArray *years;
NSInteger selectedMonthRow;
NSInteger selectedYearRow;

- (instancetype)initWithFrame:(CGRect)frame
{
    if ((self = [super initWithFrame:frame])) {
        self.delegate = self;
        gregorian = [NSCalendar currentCalendar];
        df = [[NSDateFormatter alloc] init];
        [df setDateFormat:@"MMMM"];
        _value = nil;
        _minimumDate = nil;
        _maximumDate = nil;
    }
    return self;
}

RCT_NOT_IMPLEMENTED(- (instancetype)initWithCoder:(NSCoder *)aDecoder)

- (void)setLocale:(NSLocale *)useLocale {
    [df setLocale:useLocale];
}

- (void)setMode:(NSString *)mode {
    if ([mode isEqualToString:@"number"]) {
        [df setDateFormat:@"MM"];
    } else if ([mode isEqualToString:@"shortNumber"]) {
        [df setDateFormat:@"M"];
    } else if ([mode isEqualToString:@"short"]) {
        [df setDateFormat:@"MMM"];
    }
}

- (void)initYears:(NSInteger)selectedYear {
    years = [NSMutableArray array];
    for(NSInteger i = selectedYear - DEFAULT_YEAR_SIZE; i <= selectedYear + DEFAULT_YEAR_SIZE; i ++) {
        [years addObject: [NSNumber numberWithLong:i]];
    }
}

- (void)setValue:(nonnull NSDate *)value {
    if (value != _value) {
        NSDateComponents *selectedDateComponents = [gregorian components:(NSCalendarUnitMonth|NSCalendarUnitYear) fromDate:value];
        if (!_value) {
            [self initYears: [selectedDateComponents year]];
            selectedMonthRow = (DEFAULT_MONTH_SIZE / 2) + [selectedDateComponents month];
            selectedYearRow = DEFAULT_YEAR_SIZE;
            [self setSelectedRows: NO];
        }
        _value = value;
    }
}

- (void)setMaximumDate:(NSDate *)maximumDate {
    _maximumDate = maximumDate;
    maxComponents = _maximumDate ? [gregorian components:NSCalendarUnitMonth | NSCalendarUnitYear fromDate:_maximumDate] : nil;
}

- (void)setMinimumDate:(NSDate *)minimumDate {
    _minimumDate = minimumDate;
    minComponents = _minimumDate ? [gregorian components:NSCalendarUnitMonth | NSCalendarUnitYear fromDate:_minimumDate] : nil;
}

- (void)setSelectedRows:(BOOL)animated {
    dispatch_async(dispatch_get_main_queue(), ^{
        [self selectRow:selectedMonthRow inComponent:0 animated:animated];
        [self selectRow:selectedYearRow inComponent:1 animated:animated];
    });
}

#pragma mark - UIPickerViewDataSource protocol
// number of columns
- (NSInteger)numberOfComponentsInPickerView:(nonnull UIPickerView *)pickerView {
    return 2;
}

// number of rows
- (NSInteger)pickerView:(nonnull UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    switch (component) {
        case 0:
            return DEFAULT_MONTH_SIZE;
        case 1:
            return (DEFAULT_YEAR_SIZE * 2) + 1;
            break;
        default:
            return 0;
    }
}

#pragma mark - UIPickerViewDelegate methods
// row titles
- (NSString *)pickerView:(nonnull UIPickerView *) pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    switch (component) {
        case 0: {
            NSDateComponents *comps = [[NSDateComponents alloc] init];
            [comps setMonth: row % 12];
            return [NSString stringWithFormat:@"%@", [df stringFromDate:[gregorian dateFromComponents:comps]]];
        }
        case 1:
            return [NSString stringWithFormat:@"%@", years[row]];
        default:
            return nil;
    }
}

- (void)getSelectedMonthRow:(NSInteger)row {
    NSInteger month = row % 12 != 0 ? row % 12 : 12;
    NSInteger year = [years[selectedYearRow] longValue];
    if (minComponents && year == [minComponents year] && month < [minComponents month]) {
        selectedMonthRow = row + [minComponents month] - month;
    } else if (maxComponents && year == [maxComponents year] && month > [maxComponents month]) {
        selectedMonthRow = row + [maxComponents month] - month;
    } else {
        selectedMonthRow = row;
    }
}

- (void)getSelectedYearRow:(NSInteger)row {
    NSInteger year = [years[row] longValue];
    if (minComponents && (year < [minComponents year])) {
        selectedYearRow = [years indexOfObject:[NSNumber numberWithInteger:[minComponents year]]];
    } else if (maxComponents && year > [maxComponents year]) {
        selectedYearRow = [years indexOfObject:[NSNumber numberWithInteger:[maxComponents year]]];
    } else {
        selectedYearRow = row;
    }
}

- (void)pickerView:(__unused UIPickerView *)pickerView
      didSelectRow:(NSInteger)row inComponent:(__unused NSInteger)component {
    switch (component) {
        case 0:
            [self getSelectedMonthRow:row];
            break;
        case 1:
            [self getSelectedYearRow:row];
            [self getSelectedMonthRow:selectedMonthRow];
            break;
        default:
            return;
    }
    [self setSelectedRows: YES];
    if (_onChange) {
        _onChange(@{
                @"newDate": [NSString stringWithFormat: @"%@-%@", [NSString stringWithFormat: @"%ld", selectedMonthRow % 12 != 0 ? selectedMonthRow % 12 : 12], [NSString stringWithFormat: @"%@", years[selectedYearRow]]]
        });
    }
}

@end
