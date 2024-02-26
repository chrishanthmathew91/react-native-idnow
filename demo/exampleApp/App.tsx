/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {
  Button,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
// import {IDnowManager} from 'react-native-idnow';

const options = {
  showVideoOverviewCheck: false,
  transactionToken: 'ZBW-TALJU',
  environment: 'LIVE',
  // showErrorSuccessScreen: true,
  // appearance: {
  //   primaryBrandColor: -12939777,
  //   proceedButtonBackgroundColor: -12939777,
  //   proceedButtonTextColor: -1,
  //   defaultTextColor: -1,
  //   fontNameRegular: 'OpenSans-Regular',
  //   fontNameLight: 'OpenSans-Ligth',
  //   fontNameMedium: 'OpenSans-Bold',
  //   successColor: -14963413,
  //   failureColor: -14211289,
  //   photoIdentRetakeButtonBackgroundColor: -12939777,
  //   photoIdentRetakeButtonTextColor: -1,
  //   enableStatusBarStyleLightContent: false,
  // },
};

function App(): JSX.Element {
  const onPress = async () => {
    console.log('---onPress--');
    // try {
    //   const resp = await IDnowManager.startVideoIdent(options);
    //   console.warn('==== resp', resp);
    // } catch (e) {
    //   console.warn('==== e', e);
    // }
  };
  return (
    <SafeAreaView style={styles.container}>
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <View style={styles.container}>
          <Text style={styles.welcome}>react-native-idnow demo</Text>
          <Button title="Start video identification" onPress={onPress} />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});

export default App;
