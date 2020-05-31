# react-native-month-year-picker
React Native month picker component for iOS and Android

| android | iOS |
| --- | --- |
| <img src="./screenshots/android.png" width="150">|<img src="./screenshots/ios.png" width="150">

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
import { View, SafeAreaView, Text } from 'react-native';
import MonthPicker from 'react-native-month-year-picker';

const App = () => {
  const [date, setDate] = useState(new Date());
  const [show, setShow] = useState(false);

  const showPicker = (value) => setShow(value);

  const onValueChange = (event, newDate) => {
    const selectedDate = newDate || date;

    showPicker(false);
    setDate(selectedDate);
  };

  return (
    <SafeAreaView>
      <Text>Month Picker Example</Text>
      <Text>{moment(date, "MM-YYYY")}</Text>
      <TouchableOpacity onPress={() => showPicker(true)}>
        <Text>OPEN</Text>
      </TouchableOpacity>
      {show && (
        <MonthPicker
          onChange={onValueChange}
          value={date}
          minimumDate={new Date()}
          maximumDate={new Date(2025, 5)}
        />
      )}
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
Events returned by onChange function:
```js
import { ACTION_DATE_SET, ACTION_DISMISSED } from 'react-native-month-year-picker';
...
onValueChange = (event, newDate) => {
  switch(event) {
    case ACTION_DATE_SET:
      onSuccess(newDate);
      break;
    case ACTION_DISMISSED:
    default:
      onCancel(); //when ACTION_DISMISSED new date will be undefined
  }
}
...
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

#### `okButton` (`optional`)

Picker modal confirmation button text. Default `OK`.

```js
<RNMonthPicker okButton="Confirm" />
```

#### `cancelButton` (`optional`)

Picker modal cancelation button text. Default `Cancel`.

```js
<RNMonthPicker cancelButton="Abort" />
```

#### `okButtonStyle` (`iOS only`)

Picker modal confirmation button text style.

```js
<RNMonthPicker okButtonStyle={{ color: 'blue', letterSpacing: 1.0 }} />
```

#### `cancelButtonStyle` (`iOS only`)

Picker modal cancelation button text style.

```js
<RNMonthPicker cancelButtonStyle={{ color: 'red', letterSpacing: 0.25 }} />
```

## Running example
1. Install required pods in example/ios by running `npx pod-install`.
2. Run `yarn start` to start Metro Bundler.
3. Run `yarn ios` or `yarn android`.