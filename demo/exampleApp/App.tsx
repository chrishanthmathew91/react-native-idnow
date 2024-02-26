import React from 'react';
import {
  Button,
  SafeAreaView,
  ScrollView,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import {IDnowManager} from 'react-native-idnow';

const options = {
  showVideoOverviewCheck: false,
  transactionToken: 'ZBW-TALJU',
  environment: 'LIVE',
  showErrorSuccessScreen: true,
};

function App(): JSX.Element {
  const onPress = async () => {
    try {
      const resp = await IDnowManager.startVideoIdent(options);
      console.log('==== resp', resp);
    } catch (e) {
      console.warn('==== e', e);
    }
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
