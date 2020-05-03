# react-native-month-year-picker
React Native month picker component for iOS and Android

| android | iOS |
| --- | --- |
| *under construction...*|<img src="./screenshots/ios.png" width="150">

## Getting started

`$ npm install react-native-month-year-picker --save`

or

`$ yarn add @react-native-month-year-picker`

### For react-native@0.60.0 or above

As [react-native@0.60.0](https://reactnative.dev/blog/2019/07/03/version-60) or above supports autolinking, so there is no need to run linking process. 
Read more about autolinking [here](https://github.com/react-native-community/cli/blob/master/docs/autolinking.md).

#### iOS
CocoaPods on iOS needs this extra step

```
npx pod-install
```
## Usage
```javascript
import React, { useState } from 'react';
import { View, Button, Text } from 'react-native';
import MonthPicker from 'react-native-month-year-picker';

const App = () => {
  const [date, setDate] = useState(new Date());

  const onValueChange = (event, newDate) => setDate(newDate);

  return (
    <SafeAreaView style={styles.container}>
      <Text>Month Picker Example</Text>
      <MonthPicker
        onChange={onValueChange}
        value={date}
        minimumDate={new Date()}
        maximumDate={new Date(2025, 5)}
      />
    </SafeAreaView>
  );
};

export default App;

```

## Props

#### `onChange` (`optional`)

Date change handler.

This is called when the user changes the date in the UI. It receives the event and the date as parameters.

```js
setDate = (event, date) => {};

<RNMonthPicker onChange={this.setDate} />;
```

#### `value` (`required`)

Defines the date value used in the component.

```js
<RNMonthPicker value={new Date()} />
```

#### `maximumDate` (`optional`)

Defines the maximum date that can be selected. Use year and month constructor.

```js
<RNMonthPicker maximumDate={new Date(2030, 10)} />
```

#### `minimumDate` (`optional`)

Defines the minimum date that can be selected. Use year and month constructor.

```js
<RNMonthPicker minimumDate={new Date(2020, 5)} />
```

#### `outputFormat` (`optional`)

Defines the output format in which the date will return. Defaults to `MM-YYYY`. Refer to [moment.js docs](https://momentjs.com/docs/#/parsing/string-format/) for more information about formats.

```js
<RNMonthPicker outputFormat="M-YY" />
```

### more under construction...